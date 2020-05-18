package com.morse.commons.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLSession;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request.Builder;

public class ConnectionWS {
	private static Logger LOGGER = Logger.getLogger(ConnectionWS.class);
	
    public static ResponseWS getResponseHttpClientApache(String url, String mediaType, String method, HashMap<String, String> parametersUrl, String body, HashMap<String, String> headers, Boolean withSSL, HashMap<String, String> proxyConfig) {
        String urlWS = url;
        CloseableHttpClient httpClient = null;
        
        if (withSSL != null && withSSL) {
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(SSLContexts.createDefault(), 
                    new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", sslConnectionSocketFactory).build());
            httpClient = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager).build();
        }
        else
            httpClient = HttpClientBuilder.create().build();
        
		if (parametersUrl != null && parametersUrl.size() > 0)
			for(String parameterName : parametersUrl.keySet()) {
				if (parameterName.substring(0, 2).equals("&&"))
					urlWS = urlWS + "/";
				else {
					if (urlWS == url)
						urlWS = urlWS + "?";
					else
						urlWS = urlWS + "&";
					urlWS = urlWS + parameterName + "=";
				}
				urlWS = urlWS + parametersUrl.get(parameterName);
			}
        
        try {
        	HttpUriRequest request = null;
            RequestConfig config = null;
            
            if (proxyConfig != null) {
                String host = proxyConfig.get("HOST");
                String port = proxyConfig.get("PORT");
                String scheme = proxyConfig.get("SCHEME");
                
                HttpHost proxy = new HttpHost(host, Integer.valueOf(port), scheme);
                config = RequestConfig.custom().setProxy(proxy).build();
            }
            
            if (method == "GET") {
                request = new HttpGet(urlWS);
                
                if (config != null)
                    ((HttpGet)request).setConfig(config);
            }
            if (method == "DELETE") {
                request = new HttpDelete(urlWS);
                
                if (config != null)
                    ((HttpDelete)request).setConfig(config);
            }
            if (method == "POST") {
                request = new HttpPost(urlWS);
                
                if (config != null)
                    ((HttpPost)request).setConfig(config);
                
                if (body != null) {
                     StringEntity requestEntity = null;
                     
                     if (mediaType == "application/json")
                         requestEntity = new StringEntity(body, ContentType.APPLICATION_JSON);

                     if (requestEntity != null) 
                         ((HttpPost)request).setEntity(requestEntity);
                }
            }

            if (headers != null)
                for(String key : headers.keySet()) 
                    request.addHeader(key, headers.get(key));
            
            LOGGER.info("CONNECT URL: " + urlWS.trim());
        
            HttpResponse responseHttp = httpClient.execute(request);

            ResponseWS response = new ResponseWS(responseHttp.getStatusLine().getStatusCode(), responseHttp.getStatusLine().toString());
            
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                br.close();
                
                response.setBody(sb.toString());
            }
            catch (Exception e) { }
            
            if (responseHttp.getAllHeaders().length > 0)
                for (Header header : responseHttp.getAllHeaders())
                    response.getHeaders().put(header.getName(), header.getValue());
            
            LOGGER.info("RESPONSE URL: " + url.trim() + " - BODY =" + response.getBody());
            
            return response;
        }
        catch (Exception ex) {
            LOGGER.error(ex);
        }
        finally {
            try { httpClient.close(); }
            catch (Exception ex) { LOGGER.error(ex); }
        }

        return null;
    }
	
	public static ResponseWS getResponseOkHttpClient(String url, String mediaType, String method, HashMap<String, String> parametersUrl, String body, HashMap<String, String> headers, String protocolSSL)
	{
		RequestBody requestBody = null;
		String urlWS = url;
		OkHttpClient client = new OkHttpClient();
		
		if (parametersUrl != null && parametersUrl.size() > 0)
			for(String parameterName : parametersUrl.keySet()) {
				if (parameterName.substring(0, 2).equals("&&"))
					urlWS = urlWS + "/";
				else {
					if (urlWS == url)
						urlWS = urlWS + "?";
					else
						urlWS = urlWS + "&";
					urlWS = urlWS + parameterName + "=";
				}
				urlWS = urlWS + parametersUrl.get(parameterName);
			}
		
		Builder builder = new Request.Builder().url(urlWS.trim());
		OkHttpClient.Builder buildHttpClient = client.newBuilder();
		
		buildHttpClient.connectTimeout(10, TimeUnit.MINUTES);
		buildHttpClient.readTimeout(10, TimeUnit.MINUTES);
		buildHttpClient.writeTimeout(10, TimeUnit.MINUTES);
		
		if (protocolSSL != null) {
			try {
				TrustManager[] trustAllCerts = getTrustAllCerts(); 
				//SSLContext trustAllSslContext = SSLContext.getInstance("TLSv1.2");
				SSLContext trustAllSslContext = SSLContext.getInstance(protocolSSL);
				trustAllSslContext.init(null, trustAllCerts, new SecureRandom());
				
				buildHttpClient.sslSocketFactory(trustAllSslContext.getSocketFactory(), (X509TrustManager)trustAllCerts[0]);
				buildHttpClient.hostnameVerifier(new HostnameVerifier() {
			      @Override
			      public boolean verify(String hostname, SSLSession session) {
			        return true;
			      }
			    });
			}
			catch (Exception ex) { LOGGER.error(ex); }
		}
		
		client = buildHttpClient.build();
		
		if (body != null && mediaType != null)
			requestBody = RequestBody.create(MediaType.parse(mediaType), body);
		
		switch (method) {
			case "GET":  
				builder = builder.get();
				break;
			case "POST":  
				builder = builder.post(requestBody);
                break;
			case "PUT":  
				builder = builder.put(requestBody);
                break;
			case "DELETE":
				builder = builder.delete(requestBody);
                break;
			default: 
				break;
		}

		if (headers != null)
			for(String key : headers.keySet()) 
				builder.addHeader(key, headers.get(key));

		Request request = builder.build(); 

		try 
		{
			LOGGER.info("CONNECT URL: " + urlWS.trim());
			
			Response responseHttp = client.newCall(request).execute();
			
			LOGGER.info("RESPONSE URL: " + urlWS.trim() + " - CODE=" + responseHttp.code() + " - MESSAGE=" + responseHttp.message());

			ResponseWS response = new ResponseWS(responseHttp.code(), responseHttp.message());
			
			try { response.setBody(responseHttp.body().string()); }
			catch (Exception e) { }
			
			Headers responseHeaders = responseHttp.headers();
			
			for (String headerKey : responseHeaders.names())
				response.getHeaders().put(headerKey, responseHeaders.get(headerKey));
		
			return response;
		}
		catch (Exception ex) {
			LOGGER.error(ex);
		}
		
		return null;

	}
	
	private static TrustManager[] getTrustAllCerts() {
		return new TrustManager[] {
			new X509TrustManager() {
		        @Override
		        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		        }

		        @Override
		        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		        }

		        @Override
		        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		          return new java.security.cert.X509Certificate[]{};
		        }
		    }
		};
	}
}

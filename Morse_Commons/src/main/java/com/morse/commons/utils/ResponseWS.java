package com.morse.commons.utils;

import java.util.HashMap;

public class ResponseWS {	
	
    private Integer code;
    private String message;
    private HashMap<String, String> headers;
    private String body;
    
	protected ResponseWS() { 
		this.headers = new HashMap<String, String>();
	}
	
	public ResponseWS(Integer code, String message) {
		this.code = code;
		this.message = message;
		this.headers = new HashMap<String, String>();
	}
	
	public Integer getCode() {
		return this.code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public HashMap<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
}

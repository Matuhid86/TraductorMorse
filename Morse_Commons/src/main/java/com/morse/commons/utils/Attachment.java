package com.morse.commons.utils;

import java.io.InputStream;
import java.util.StringTokenizer;

public class Attachment {	
	
    private InputStream inputStream;
    private String fileName;

    public InputStream getInputStream() {
    	return inputStream;
    }
    public void setInputStream(InputStream inputStream) {
    	this.inputStream = inputStream;
    }

    public String getFileName() {
    	return fileName;
    }
    public void setFileName(String fileName) {
    	this.fileName = fileName;
    }
    
	
	public String getExtension() {
		String extension = "";
		
		if (this.fileName != null) {
			StringTokenizer st = new StringTokenizer(this.fileName, ".");
		
			while (st.hasMoreTokens())
				extension = st.nextToken();
		}

		return extension;
	}
}

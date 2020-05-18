package com.morse.commons.utils;

import java.util.ArrayList;
import java.util.List;

public class EMail {	
	
    private String from;
    private String to;
    private String cc;
    private String cco;
    private String subject;
    private String body;
    private List<Attachment> attachments;

	protected EMail() { 
		this.attachments = new ArrayList<Attachment>();
	}
	
	public EMail(String from, String to, String subject, String body) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.attachments = new ArrayList<Attachment>();
	}
	
	public String getFrom() {
		return this.from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getCC() {
		return cc;
	}
	public void setCC(String cc) {
		this.cc = cc;
	}
	
	public String getCCO() {
		return cco;
	}
	public void setCCO(String cco) {
		this.cco = cco;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

    public List<Attachment> getAttachments() {
		return attachments;
    }
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
}

package com.moxi.utils;

import java.util.Date;



public class SimpleNetObject {
	public static Integer SUCCESS=1;
	private static SimpleNetObject SNO_SUCCESS=null;
	
	
	public static SimpleNetObject getSuccessObject(){
		if(SNO_SUCCESS==null){
			SNO_SUCCESS=new SimpleNetObject();
			SNO_SUCCESS.setResult(1);
			SNO_SUCCESS.setMessage("保存成功");
		}
		return SNO_SUCCESS;
	}
	private Integer result=1;
	private String message;
	
	private Object data;
	
	
	private Object extraData;

	private String reason;
	private Date servertime;//服务器时间
	
	public Date getServertime() {
		return new Date();
	}

	public void setServertime(Date servertime) {
		this.servertime = servertime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}
	public SimpleNetObject(){}
	public SimpleNetObject(String successMessage){
		this.message=successMessage;		
	}
	public SimpleNetObject(int result,String message){
		this.result=result;
		this.message=message;
	}
	
//	public static void main(String[] args){
//		long start=System.currentTimeMillis();
//		for(int i=0;i<1000000;i++){
//			SimpleNetObject sno=new SimpleNetObject();
//			
//		}
//		long end=System.currentTimeMillis();
//		System.out.println("耗时"+(end-start));
//		
//	}
	
}

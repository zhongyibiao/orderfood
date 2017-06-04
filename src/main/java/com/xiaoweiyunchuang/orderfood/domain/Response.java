package com.xiaoweiyunchuang.orderfood.domain;

import java.io.Serializable;

public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7997940257180925720L;
	

	// 错误代码
	private int errorCode;
	
	// 错误提示
	private String errorText;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}

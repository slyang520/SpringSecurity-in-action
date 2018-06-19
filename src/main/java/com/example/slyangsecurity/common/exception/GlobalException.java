package com.example.slyangsecurity.common.exception;

public class GlobalException extends Exception {

	private GlobalCode globalCode;

	public GlobalException(GlobalCode globalCode) {
		this.globalCode = globalCode;
	}

	public GlobalCode getGlobalCode() {
		return globalCode;
	}

	public void setGlobalCode(GlobalCode globalCode) {
		this.globalCode = globalCode;
	}
}

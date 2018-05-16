package com.example.slyangsecurity.common.utils;

public enum GlobalCode {

	SUCCESS(200, "请求成功"),
	TOKEN_INVALID(101, "token 失效"),
	SERVER_ERROR(500, "服务 出现了意外的错误"),
	NOT_AUTHORITY(403, "当前没有权限"),

	;

	private int code;
	private String message;

	GlobalCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

}

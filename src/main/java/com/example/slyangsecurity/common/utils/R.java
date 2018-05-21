package com.example.slyangsecurity.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

public class R {

	private int code;    // 全局code
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Data data;

	public static R ok() {
		return new R(GlobalCode.SUCCESS);
	}

	public static R ok(Object data) {
		R r = new R(GlobalCode.SUCCESS);
		r.setData(new Data());
		r.getData().setData(data);
		return r;
	}

	public static R errorLocal(int code, String message) {
		R r = new R(GlobalCode.SUCCESS);
		r.setData(new Data());
		r.getData().setCode(code);
		r.getData().setMessage(message);
		return r;
	}

	public static R errorGlobal(GlobalCode globalCode) {
		return new R(globalCode);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	private R(GlobalCode globalCode) {
		this.code = globalCode.getCode();
		this.message = globalCode.getMessage();
	}

	@Override
	public String toString() {
		return "R{" +
				"code=" + code +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}

	private static class Data {
		private int code;   // 业务code
		private Object data;
		@JsonInclude(JsonInclude.Include.NON_NULL)
		private String message;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return "Data{" +
					"code=" + code +
					", data=" + data +
					", message='" + message + '\'' +
					'}';
		}
	}

}

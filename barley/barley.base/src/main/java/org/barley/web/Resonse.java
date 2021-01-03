package org.barley.web;

public class Resonse {
	public static final String SUCCESS = "1";
	public static final String FAILED = "0";

	private String c;
	private String msg;
	private Object data;

	public Resonse() {
	}

	public Resonse(String code, String message) {
		this.c = code;
		this.msg = message;
	}

	public static Resonse newSucessResult(String msg) {
		return new Resonse(SUCCESS, msg);
	}

	public static Resonse newSucessResult(String msg, Object data) {
		Resonse ar = new Resonse();
		ar.msg = msg;
		ar.data = data;
		ar.c = SUCCESS;
		return ar;
	}

	public static Resonse newFailResult(String msg) {
		return new Resonse(FAILED, msg);
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean getSuccess() {
		if (SUCCESS.equals(getC())) {
			return true;
		} else {
			return false;
		}
	}

}

package com.ctscafe.admin.utilities;

public class JsonResponse {
	private String status;
	private Object message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JsonResponse [status=" + status + ", message=" + message + "]";
	}

}

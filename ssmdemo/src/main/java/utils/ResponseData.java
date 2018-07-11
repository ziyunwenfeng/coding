package utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
	public static String ERROR = "errors";
	private  String message;
	private  int code;
	private  Map<String,Object> data = new HashMap<String,Object>();
	public String getMessage() {
		return message;
	}
	
	public int getCode() {
		return code;
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	
	public ResponseData putData(Object value,String key) {
		data.put(key, value);
		return this;
	}
	
	public ResponseData(String message,int code) {
		this.message = message;
		this.code = code;
	}
	
	public static ResponseData ok() {
		return new ResponseData("ok",200);
	}
	
	public static ResponseData notFound() {
		return new ResponseData("not found",400);
	}
	public static ResponseData badRequest() {
		return new ResponseData("badRequest",400);
	}
	public static ResponseData forbidden() {
		return new ResponseData("forbidden",403);
	}
	public  static ResponseData unauthorized() {
		return new ResponseData("unauthorized",401);
	}
	public static ResponseData serverInternalError() {
		return new ResponseData("serverInternalError",500);
	}
	public static ResponseData customerError() {
		return new ResponseData("customerError",1001);
	}
}

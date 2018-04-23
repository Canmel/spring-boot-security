package com.goshine.core.base;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面响应entity
 */
public class R extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 200);
	}
	
	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(500, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public R msg(String msg) {
		super.put("msg", msg);
		return this;
	}

	public static R page(long total, Object object) {
		R r = new R();
		r.put("totalProperty", total);
		r.put("root", object);
		return r;
	}

	public static R page(PageInfo pageInfo) {
		R r = new R();
		r.put("totalProperty", pageInfo.getTotal());
		r.put("pageNum", pageInfo.getPageNum());
		r.put("root", pageInfo.getList());
		return r;
	}

	public static R model(Object obje) {
		R r = new R();
		r.put("obj", obje);
		return r;
	}
}
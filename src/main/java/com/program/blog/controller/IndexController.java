package com.program.blog.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 主页
 * @author yangyang.zhang
 * @Package com.program.blog.controller 
 * @Date 2017年10月25日 下午8:48:30 
 * @Description TODO(用一句话描述该文件做什么)
 * @version V1.0
 */
public class IndexController extends BaseController {
	
	public void blog(){
		String method = getPara("method");
		String data = getPara("data");
		JSONObject jsonObject = new JSONObject();
		switch (method) {
		case "getArticle":
			List<Record> blogs = Db.find("SELECT * FROM tb_article");
			JSONArray array = paresList(blogs);
			jsonObject.put("data", array);
			break;

		default:
			break;
		}
		System.out.println(jsonObject.toJSONString());
		renderJson(jsonObject);
	}
	
	public JSONArray paresList(List<Record> list) {
		JSONArray array = new JSONArray();
		for (Record record : list) {
			Map<String, Object> map = record.getColumns();
			JSONObject object = new JSONObject();
			for (String key : map.keySet()) {
				object.put(key, map.get(key));
			}
			array.add(object);
		}
		return array;
	}
	
}

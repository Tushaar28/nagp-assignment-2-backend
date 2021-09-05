package com.nagp.tushaar.util;

import com.google.gson.JsonObject;
import com.nagp.tushaar.model.Blog;

public class BlogGenerate {
	
	public static JsonObject generateJson(Blog blog) {
		JsonObject res = new JsonObject();
		res.addProperty("id", blog.getId());
		res.addProperty("title", blog.getTitle());
		res.addProperty("description", blog.getDescription());
		return res;
	}

}

package com.example.slyangsecurity.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebUtil {

	public static void writeJSON(Object object, HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(objectMapper.writeValueAsString(object));
		out.flush();
		out.close();
	}

}



package com.example.slyangsecurity.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorPageController implements ErrorController {

	private Logger logger = LoggerFactory.getLogger(ErrorPageController.class);

	@RequestMapping
	public String errorHtml(HttpServletRequest request,
							HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		response.setStatus(status.value());
		int http_code = status.value();

		logger.info("errorHtml http_status ={}", http_code);

		if (http_code == HttpStatus.NOT_FOUND.value()) {
			return "404.html";
		} else if (http_code >= 400 && http_code < 500) {
			return "40x.html";
		}
		return "50X.html";
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try {
			return HttpStatus.valueOf(statusCode);
		} catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}


	@Override
	public String getErrorPath() {
		return null;
	}
}

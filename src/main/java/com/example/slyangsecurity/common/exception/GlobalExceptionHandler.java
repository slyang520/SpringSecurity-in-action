package com.example.slyangsecurity.common.exception;


import com.example.slyangsecurity.common.utils.GlobalCode;
import com.example.slyangsecurity.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public R apiCommonErrorHandler(Exception e) throws Exception {
		logger.info("apiErrorHandler  {} ", e.getMessage());
		if (e instanceof AccessDeniedException) {
			return R.errorGlobal(GlobalCode.NOT_AUTHORITY);
		}
		return R.errorGlobal(GlobalCode.SERVER_ERROR);
	}

	@ExceptionHandler(value = LocalException.class)
	@ResponseBody
	public R apiLocalErrorHandler(LocalException e) throws Exception {
		logger.info("apiErrorHandler   LocalException {} ", e.getMessage());
		return R.errorLocal(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(value = GlobalException.class)
	@ResponseBody
	public R apiGlobalErrorHandler(GlobalException e) throws Exception {
		logger.info("apiErrorHandler  GlobalException {} ", e.getMessage());
		return R.errorGlobal(e.getGlobalCode());
	}

}
package com.example.slyangsecurity.common.exception;


import com.example.slyangsecurity.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
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

	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public R apiLocalErrorHandler(BusinessException e) throws Exception {
		logger.info("apiErrorHandler   BusinessException {} ", e.getMessage());
		return R.errorBusiness(e.getCode(), e.getMessage(), e.getData());
	}

	@ExceptionHandler(value = GlobalException.class)
	@ResponseBody
	public R apiGlobalErrorHandler(GlobalException e) throws Exception {
		logger.info("apiErrorHandler  GlobalException {} ", e.getMessage());
		return R.errorGlobal(e.getGlobalCode());
	}

}
//package com.example.slyangsecurity.config.security;
//
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * POST
// * <p>
// * application/x-www-form-urlencoded
// * 接口解密
// */
//public class DecodeApiFilter extends GenericFilterBean {
//
//	private static final String METHOD = "POST";
//	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
//
//	@Override
//	public void doFilter(ServletRequest req,
//						 ServletResponse res,
//						 FilterChain chain) throws IOException, ServletException {
//
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) res;
//
//		if (METHOD.equalsIgnoreCase(request.getMethod()) &&
//				CONTENT_TYPE.equalsIgnoreCase(request.getContentType())) {
//			//
//
//		} else {
//			chain.doFilter(request, response);
//		}
//	}
//
//
//}

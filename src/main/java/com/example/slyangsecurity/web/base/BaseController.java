package com.example.slyangsecurity.web.base;

import com.example.slyangsecurity.common.exception.GlobalException;
import com.example.slyangsecurity.common.exception.GlobalCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public abstract class BaseController {

    protected Object getUser() throws GlobalException {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            return authentication.getDetails();
        } else {
            throw new GlobalException(GlobalCode.USER_NOT_LOGIN);
        }
    }

    protected String getFtlRoot(String url) {
        //return  url;
        return "theme_ftl/" + url;
    }

}

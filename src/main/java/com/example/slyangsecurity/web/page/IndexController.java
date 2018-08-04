package com.example.slyangsecurity.web.page;

import com.example.slyangsecurity.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping("")
    public String index(){
        return getFtlRoot("index");
    }


}

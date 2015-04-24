package com.opdar.framework.configureweb.controller;

import com.opdar.framework.configureweb.beans.TestBean;
import com.opdar.framework.web.anotations.After;
import com.opdar.framework.web.anotations.Before;
import com.opdar.framework.web.anotations.Controller;
import com.opdar.framework.web.anotations.Router;

/**
 * Created by Jeffrey on 2015/4/10.
 * E-Mail:shijunfan@163.com
 * Site:opdar.com
 * QQ:362116120
 */
@Controller("/test/")
@After(ControllerInterceptor.class)
@Before(ControllerInterceptor.class)
public class TestController {

    private static int i=0;
    private static Object object = new Object();
    @Router
    @After(SeedInterceptor.class)
    @Before(SeedInterceptor.class)
    public String router2(String testParam,TestBean bean){
        synchronized (object){
            i++;
        }
        return "this is /test/router2  testParam : "+testParam;
    }

    @Router("index")
    public String routerRender(String testParam, TestBean bean){
        return String.valueOf(i);
    }
}

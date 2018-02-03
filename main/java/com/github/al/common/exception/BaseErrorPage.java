package com.github.al.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author: chenyi
 * @Email: 228112142@qq.com
 * @Description:
 * @Date: 2018/1/6 14:46
 */
@Controller
@RequestMapping("/error")
public class BaseErrorPage  implements ErrorController {

    Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @Override
    public String getErrorPath() {
        logger.info("进入自定义错误页面");
        return "/404.html";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
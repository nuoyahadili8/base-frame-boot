package com.github.al.common.log;

import com.alibaba.fastjson.JSON;
import com.github.al.common.shiro.ShiroUtils;
import com.github.al.common.utils.StringUtil;
import com.github.al.service.SysLogService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @author chenyi
 * @date 2017/12/28
 */
@Aspect
@Component
@Order(5)
public class WebLogAspect {

    @Autowired
    private SysLogService logService;

    private Logger logger = Logger.getLogger(getClass());

    @Pointcut("execution(public * xin.cymall.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取方法日志注解
        String value=getControllerMethodDescription(joinPoint);
        logger.info("SysLog : " + value);

        if(!StringUtil.isEmpty(value)){
            // TODO: 2017/12/28  插入数据库......
            com.github.al.entity.SysLog sysLog=new com.github.al.entity.SysLog();
            sysLog.setCreateDate(new Date());
            sysLog.setIp(request.getRemoteAddr());
            sysLog.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            sysLog.setOperation(value);
            //请求的参数
            Object[] args = joinPoint.getArgs();
            String params = JSON.toJSONString(args[0]);
            sysLog.setParams(params);
            sysLog.setUsername(ShiroUtils.getUserName());
            logService.save(sysLog);
        }

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);

    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @author chenyi
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    Annotation annotation= method.getAnnotation(SysLog. class);
                    if(annotation!=null){
                        description = method.getAnnotation(SysLog. class).value();
                    }
                    break;
                }
            }
        }
        return description;
    }



}
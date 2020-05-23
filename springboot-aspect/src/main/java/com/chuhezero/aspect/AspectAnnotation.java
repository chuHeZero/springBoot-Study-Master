package com.chuhezero.aspect;

import com.chuhezero.entity.UserEntity;
import com.chuhezero.result.ResultBody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class AspectAnnotation {

    /*
    * 切入点
    * */
    @Pointcut("execution(public * com.chuhezero.controller.*.*(..))")
    public void doOperation(){

    }


    /**
     * @param joinPoint
     * @Title: before
     * @Description: 前置通知处理方法
     * 在处理之前调用，比如参数、权限校验
     */
    @Before("doOperation()")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] objs = joinPoint.getArgs();
        for (Object obj : objs) {
            UserEntity user = (UserEntity) obj;
            System.out.println("使用注解操作：前置通知，请求的user:" + user);
            user.setName(base64DeStr(user.getName()));
        }
    }

    @After("doOperation()")
    public void after() {
        System.out.println("使用注解操作：后置通知");
    }


    @Around("doOperation()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objs = joinPoint.getArgs();
        for (Object obj : objs) {
            UserEntity user = (UserEntity) obj;
            System.out.println("使用注解操作：环绕通知，请求的user:"+user);
            user.setAge(17);
            return joinPoint.proceed(objs);
        }
        return joinPoint.proceed(objs);
    }


    @AfterReturning(returning = "object", pointcut = "doOperation()")
    public void doAfterReturning(Object object) {
        ResultBody resultBody = (ResultBody) object;
        String str = null;
        try {
            str = base64EnStr(resultBody.getResult());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resultBody.setResult(str);
    }


    public String base64EnStr(String str) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
    }


    public static String base64DeStr(String encodeStr) throws UnsupportedEncodingException {
        byte[] decodeStr = Base64.getDecoder().decode(encodeStr);
        return new String(decodeStr, "UTF-8");
    }
}

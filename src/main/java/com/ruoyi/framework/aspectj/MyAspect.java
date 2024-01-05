package com.ruoyi.framework.aspectj;


import com.ruoyi.framework.aspectj.lang.annotation.MyAOP;
import net.sf.jsqlparser.statement.select.Join;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@EnableAsync
public class MyAspect
{
    @Pointcut("@annotation(com.ruoyi.framework.aspectj.lang.annotation.MyAOP)")
    //这个方法只是用来关联AOP注解？？？
    public void loginPointCut(){
        // 这里的方法永远不会被执行？？？
    }

    //@AfterReturning(pointcut = "loginPointCut()")
    @Before("loginPointCut()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("前置通知... ...");
        // 增强方法
        analogHandle(joinPoint);
        System.out.println("前置通知执行完成");

    }

    @Async
    public MyAOP analogHandle(JoinPoint joinPoint){
        System.out.println("调用Handle方法... ...");
        //System.out.println("signature is : "+joinPoint.getSignature());
        System.out.println("target is :" +joinPoint.getTarget());
        System.out.println("this is : "+joinPoint.getThis());
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature=(MethodSignature) signature;
        Method method = methodSignature.getMethod();
        System.out.println("调用方法为："+method.getName());
        if(method != null){
            return method.getAnnotation(MyAOP.class);
        }
        return null;
    }

    @After(value = "loginPointCut()")
    public void doAfter(JoinPoint joinPoint) throws InterruptedException {

        System.out.println("后置通知... ...");


    }

    @AfterThrowing(value = "loginPointCut()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Exception e){
        System.out.println(joinPoint.getSignature()+" 类报错  "+e.getMessage());
    }









}

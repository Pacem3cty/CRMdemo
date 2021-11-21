package com.example.crmdemo.common.aspect;

import com.example.crmdemo.common.annoation.RequiredPermission;
import com.example.crmdemo.common.api.ResultCode;
import com.example.crmdemo.common.exception.ApiException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * AOP代理类 通过比对权限注解上的权限码验证用户权限
 *
 * @author wr
 * @since 2021-11-17
 */
@Component
@Aspect
public class PermissionProxy {

    @Resource
    private HttpSession session;

    @Around(value = "@annotation(com.example.crmdemo.common.annoation.RequiredPermission)")//增强动作织入切点位置
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object result;
        List<String> permissions = (List<String>) session.getAttribute("permissions");//得到当前登录用户的权限

//        System.err.println("获取权限列表为："+permissions);

        if(permissions == null || permissions.size() < 1){//若用户没有任何权限 则抛出无权限异常
            throw new ApiException(ResultCode.FORBIDDEN);
        }

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();//得到对应目标
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);//通过反射获取注解对象
        System.err.println("获取注解权限码为："+requiredPermission.code());
        if(!(permissions.contains(requiredPermission.code()))){//若权限中不包含当前方法上注解指定的权限码 则抛出无权限异常
            System.err.println("抛出无权限异常");
            throw new ApiException(ResultCode.FORBIDDEN);
        }

        result = pjp.proceed();
        return  result;
    }
}

package com.goshine.web.aspect;

import java.util.Date;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

	@Around("execution(* com.goshine.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("time aspect start");
		Object[] args = pjp.getArgs();
		for (Object object : args) {
			System.out.println("args is :" + object);
		}
		System.out.println();
		Long startTime = new Date().getTime();
		Object object = pjp.proceed();
		System.out.println("Time Aspect 耗时 ：" + (new Date().getTime() - startTime));
		System.out.println("time aspect end");
		return object;
	}

}

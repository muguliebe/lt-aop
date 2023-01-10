package com.example.fwk.filter.aop

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
class PointcutList {

    @Pointcut("execution(* com.example.ltaop..controller..*.*(..))")
    fun allController(){}

}
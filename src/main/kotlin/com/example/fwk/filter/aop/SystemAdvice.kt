package com.example.fwk.filter.aop

import ch.qos.logback.classic.Logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.UUID
import javax.servlet.http.HttpServletResponse

@Aspect
@Component
class SystemAdvice {

    companion object {
        val log: Logger = LoggerFactory.getLogger(SystemAdvice::class.java) as Logger
    }

    @Around("PointcutList.allController()")
    fun adviceController(pjp:ProceedingJoinPoint): Any? {

        val result :Any?
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val response = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).response as HttpServletResponse
        val signatureName = "${pjp.signature.declaringType.simpleName}.${pjp.signature.name}"

        log.info(">>>>> controller start [$signatureName() from ${req.remoteAddr}] by ${req.method} ${req.requestURI}")
        log.info("guID: ${UUID.randomUUID()}")

        try {
            result = pjp.proceed()
        }catch (e: Exception){
            throw e
        }
        log.info(">>>>> controller end [$signatureName() from ${req.remoteAddr}]")

        return result
    }
}
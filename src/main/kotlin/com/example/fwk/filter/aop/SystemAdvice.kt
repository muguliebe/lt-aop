package com.example.fwk.filter.aop

import ch.qos.logback.classic.Logger
import com.example.fwk.core.service.TrsService
import com.example.fwk.core.util.FwkUtil
import com.example.fwk.custom.pojo.Commons
import com.example.ltaop.part.com.service.AuthService
import com.example.ltaop.util.DateUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.net.URI
import java.time.Duration
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.UUID
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletResponse
import kotlin.system.measureTimeMillis

@Aspect
@Component
class SystemAdvice {

    companion object {
        val log: Logger = LoggerFactory.getLogger(SystemAdvice::class.java) as Logger
    }

    @Autowired lateinit var common: Commons
    @Autowired lateinit var serviceTrs: TrsService
    @Autowired lateinit var serviceAuth: AuthService
    @Autowired lateinit var rm: RequestMappingHandlerMapping

    @Around("PointcutList.allController()")
    fun adviceController(pjp: ProceedingJoinPoint): Any? {

        val result: Any?
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val res =
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).response as HttpServletResponse
        val signatureName = "${pjp.signature.declaringType.simpleName}.${pjp.signature.name}"

        log.info(">>>>> controller start [$signatureName() from ${req.remoteAddr}] by ${req.method} ${req.requestURI}")

        setAuth()
        setCommonArea()

        val elapsedApi = measureTimeMillis {
            try {
                result = pjp.proceed()
            } catch (e: Exception) {
                log.error(">>>>> controller end [$signatureName() from ${req.remoteAddr}] with Error[${e.message}")
                saveTr(e)
                throw e
            }
        }

        if(elapsedApi > 1000){
            TODO("문자 알림")
        }
        log.info(">>>>> controller end [$signatureName() from ${req.remoteAddr}]")

        saveTr()

        return result
    }

    private fun setAuth() {
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

        val jwt = req.getHeader("authorization") ?: return
        val user = serviceAuth.decodeToken(jwt) ?: return

        common.user = user
    }

    private fun saveTr(e: Exception? = null) {
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val res =
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).response as HttpServletResponse

        common.area.err = e
        common.area.endDt = OffsetDateTime.now(ZoneId.of("+9"))
        common.area.elapsed = Duration.between(common.area.startDt, common.area.endDt).toMillis()
        common.area.statCd = res.status.toString()

        val originalUri = req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI)
        originalUri?.let { common.area.path = originalUri.toString() }

        e?.let {
            // error message
            val errorStack: ArrayList<String> = arrayListOf()
            var cause: Throwable = e
            while (cause.cause != null) {
                errorStack.add(cause.cause!!.message.toString())
                cause = cause.cause!!
            }
            common.area.errMsg = cause.message

            // status code
            common.area.statCd = "500"
        }

        serviceTrs.insertTransaction(common.area)

    }

    private fun setCommonArea() {
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val res =
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).response as HttpServletResponse

        val ca = common.area
        ca.startDt = OffsetDateTime.now(ZoneId.of("+9"))
        ca.gid = UUID.randomUUID().toString()
        ca.date = DateUtils.currentDate()

        val p = FwkUtil.getRequestMappingInfo(req, rm).pathPatternsCondition.toString()
        ca.path = p.substring(1, p.length - 1)
        ca.remoteIp = req.remoteAddr
        ca.queryString = req.queryString
        ca.method = req.method

        if (req.getHeader("referer") != null) {
            val referrer = req.getHeader("referer")
            ca.referrer = URI(referrer).path
        }

    }
}
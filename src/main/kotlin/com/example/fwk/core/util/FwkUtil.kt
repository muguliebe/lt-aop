package com.example.fwk.core.util

import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import javax.servlet.http.HttpServletRequest
import kotlin.system.measureTimeMillis

object FwkUtil {

    fun getRequestMappingInfo(req: HttpServletRequest, handlerMapping: RequestMappingHandlerMapping): RequestMappingInfo {
        val hm = handlerMapping.getHandler(req)!!.handler as HandlerMethod
        val handlers = handlerMapping.handlerMethods.toList()
        lateinit var requestInfo: RequestMappingInfo
        lateinit var handlerMethod: HandlerMethod

        val elapsedApi = measureTimeMillis {
            for (i in 0 until handlers.count()) {
                val pair = handlers[i]

                if (pair.second.method == hm.method) {
                    requestInfo = pair.first
                    handlerMethod = pair.second
                }
            }
        }
        return requestInfo
    }

}
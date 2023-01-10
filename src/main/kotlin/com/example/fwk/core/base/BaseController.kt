package com.example.fwk.core.base

import ch.qos.logback.classic.Logger
import com.example.fwk.custom.pojo.Commons
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

open class BaseController {
    protected final val log = LoggerFactory.getLogger(this::class.java) as Logger
    @Autowired lateinit var commons: Commons
}
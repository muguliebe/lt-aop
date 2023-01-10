package com.example.fwk.custom.pojo

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
data class Commons(
    var area: CommonArea = CommonArea(),
    var user: CommonsUser? = null
) : Cloneable {
    override fun clone(): CommonArea {
        return super.clone() as CommonArea
    }
}

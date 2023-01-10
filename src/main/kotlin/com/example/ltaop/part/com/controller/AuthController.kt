package com.example.ltaop.part.com.controller

import com.example.fwk.core.base.BaseController
import com.example.ltaop.part.com.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController : BaseController() {

    @Autowired lateinit var service: AuthService

    @PostMapping("/sign-in")
    fun signIn(@RequestBody input:SignInReq): String {
        return service.signIn(input.email)
    }
}

data class SignInReq (
    val email: String
)

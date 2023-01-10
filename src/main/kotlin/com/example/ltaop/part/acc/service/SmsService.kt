package com.example.ltaop.part.acc.service

import com.example.fwk.core.base.BaseService
import com.example.ltaop.part.com.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SmsService: BaseService() {

    @Autowired lateinit var serviceUser: UserService

    fun sendSms(userId: Int) {
        // 문자 보내기

//        val user = serviceUser.getUser(userId)
        val user = commons.user

        // 수신거부 체크
        if(user!!.isDeny == "Y") {
            throw RuntimeException("사용자 수신거부")
        }

        // 휴대전화번호 가져와서 보낸다.

    }
}
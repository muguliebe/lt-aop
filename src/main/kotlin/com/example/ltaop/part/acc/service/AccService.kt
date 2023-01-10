package com.example.ltaop.part.acc.service

import com.example.fwk.core.base.BaseService
import com.example.ltaop.part.com.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccService: BaseService() {

    @Autowired lateinit var serviceSms: SmsService
    @Autowired lateinit var serviceUser: UserService

    fun deposit(accNo: String) {
        // 회원레벨 = 3
//        val user = serviceUser.getUser(commons.user!!.userId)
        val user = commons.user

//        if( commons.area.isProd == false )
//            throw RuntimeException("실행 가능한 환경이 아닙니다.")

//        if( user.level >= 3)


        // 문자 보내주고
        serviceSms.sendSms(commons.user!!.userId)

        // 이메일 보내주고

    }
}
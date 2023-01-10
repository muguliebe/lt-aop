package com.example.ltaop.part.com.controller

import com.example.ltaop.entity.ComUserMst
import com.example.ltaop.part.com.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

/**
 * 사용자 정보 조회
 */
@RestController
@RequestMapping("/users")
class UserController {

    @Autowired lateinit var service: UserService

    /**
     * 사용자 다건 조회
     */
    @GetMapping
    fun getListUser() = service.getListUser()


    /**
     * 사용자 단건 조회
     */
    @GetMapping("/{id}")
    fun getListUser(@PathVariable id: Int): ComUserMst {
        val optUser = service.getUser(id)
//        if(optUser.isEmpty())
//            throw ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user : $id")

        return optUser.get()
    }


    /**
     * 사용자명 수정
     */
    @PutMapping("/{id}")
    fun updateUserNm(@PathVariable id: Int, name: String) = service.updateUserNm(id, name)

}
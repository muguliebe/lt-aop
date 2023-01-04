package com.example.ltaop.part.com.service

import com.example.ltaop.entity.ComUserMst
import com.example.ltaop.jpa.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService {

    @Autowired lateinit var repoUser: UserRepo

    /**
     * 사용자 다건 조회
     */
    fun getListUser(): MutableIterable<ComUserMst> = repoUser.findAllByUseYn("Y")

    /**
     * 사용자 단건 조회
     */
    fun getUser(id:Int): Optional<ComUserMst> = repoUser.findById(id)

    /**
     * 사용자명 변경
     */
    fun updateUserNm(id: Int, name: String): UserRepo {
        val optUser = repoUser.findById(id)
        if(optUser.isEmpty)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no user : $id")

        val user = optUser.get()
        user.userNm = name

        repoUser.save(user)

        return repoUser
    }

    /**
     * 사용자 삭제
     */
    fun deleteUser(id: Int): ComUserMst {
        val optUser = repoUser.findById(id)
        if(optUser.isEmpty)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no user : $id")

        val user = optUser.get()
        user.useYn = "N"

        repoUser.save(user)

        return user
    }
}
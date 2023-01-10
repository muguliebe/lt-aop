package com.example.ltaop.jpa

import com.example.ltaop.entity.ComUserMst
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo : JpaRepository<ComUserMst, Int> {
    fun findAllByUseYn(useYn: String) : MutableIterable<ComUserMst>
    fun findByEmail(email: String) : Optional<ComUserMst>
}
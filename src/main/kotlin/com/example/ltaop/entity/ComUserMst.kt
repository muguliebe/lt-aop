package com.example.ltaop.entity

import org.springframework.context.annotation.Description
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "com_user_mst")
@Description("사용자 마스터")
data class ComUserMst(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Int = 0,  // 사용자 ID
    var email: String = "", // 이메일
    var userNm: String = "", // 성명
    var useYn: String = "",
    var createUserId: Int? = null,
    var updateUserId: Int? = null,
    var createDt: OffsetDateTime? = null,
    var updateDt: OffsetDateTime? = null,
) {
}

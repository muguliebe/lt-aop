package com.example.fwk.custom.entity

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.context.annotation.Description
import java.io.Serializable
import java.time.LocalDate
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "fwk_trs_hst")
@Description("거래내역")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class FwkTrsHst(
    @EmbeddedId
    var id:FwkTrsHstId = FwkTrsHstId(),
    var method: String = "",                          // HTTP Method ( GET, PUT.. )
    var path: String = "",                            // API URL
    var startTime: String? = null,                    // 거래 시작 시간 (yyMMdd)
    var endTime: String? = null,                      // 거래 종료 시간
    var elapsed: Long? = null,                        // 수행 시간 (ms)
    var referrer: String? = null,                     // 호출 URL
    var remoteIp: String = "",                        // 호출지 IP
    var statCode: String? = null,                     // HTTP 상태 코드 (200, 500..)
    var queryString: String? = null,                  // HTTP Query String
    var body: String? = null,                         // HTTP Input Body
    var errMsg: String? = null,                       // 에러 메시지
    var createUserId: Int? = null,                    // 생성자 ID
    var createDt: OffsetDateTime? = null              // 생성 일시
)

@Embeddable
data class FwkTrsHstId(
    var trDy: LocalDate = LocalDate.now(),            // 거래 일자
    var gid: String = "",                             // 글로벌 ID
) : Serializable
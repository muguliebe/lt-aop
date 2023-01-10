package com.example.fwk.core.service

import com.example.fwk.custom.entity.FwkTrsHst
import com.example.fwk.custom.jpa.FwkTrsHstRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import com.example.fwk.custom.pojo.CommonArea
import org.springframework.scheduling.annotation.Async
import java.time.Duration
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class TrsService {

    @Autowired lateinit var repo: FwkTrsHstRepo

    /**
     * 거래내역 생성
     */
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun insertTransaction(commons: CommonArea) {

        // set insert input
        val tr = FwkTrsHst()
        tr.id.trDy = LocalDate.parse(commons.date)
        tr.id.gid = commons.gid
        tr.method = commons.method
        tr.path = commons.path
        tr.statCode = commons.statCd
        tr.startTime = commons.startDt?.format(DateTimeFormatter.ofPattern("HHmmss"))
        tr.endTime = commons.endDt?.format(DateTimeFormatter.ofPattern("HHmmss"))
        tr.elapsed = when (commons.elapsed) {
            null -> Duration.between(commons.startDt, commons.endDt).toMillis()
            else -> commons.elapsed
        }
        tr.remoteIp = commons.remoteIp
        tr.queryString = commons.queryString
        tr.body = commons.body
        tr.errMsg = commons.errMsg
        tr.referrer = commons.referrer
        tr.createDt = OffsetDateTime.now(ZoneId.of("Asia/Seoul"))

        // insert
        repo.save(tr)
    }

}

package com.example.ltaop.util

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

    private val zoneId: ZoneId = ZoneId.of("Asia/Seoul")

    fun now(): LocalDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId)

    fun nowDate(): Date = Date.from(now().atZone(zoneId).toInstant())

    // localDateTime -> Date
    fun fromLocalDateTimeToDate(from: LocalDateTime): Date = Date.from(from.atZone(zoneId).toInstant())

    // localDate -> Date 포맷으로 리턴
    fun fromLocalDateToDate(from: LocalDate): Date = Date.from(from.atStartOfDay(zoneId).toInstant())

    // 현재 일시를 지정한 포맷으로 리턴
    fun currentDateTimeFormat(format: String): String = now().format(DateTimeFormatter.ofPattern(format))

    // 현재 일자 (yyyy-MM-dd) 리턴
    fun currentDate(): String = currentDateTimeFormat("yyyy-MM-dd")

    // TimeStamp(yyyy.MM.dd HH:mm:ss) 리턴
    fun currentTimeStamp(): OffsetDateTime = OffsetDateTime.now(zoneId)
    fun currentTimestampString(): String = currentDateTimeFormat("yyyy-MM-dd HH:mm:ss")

}

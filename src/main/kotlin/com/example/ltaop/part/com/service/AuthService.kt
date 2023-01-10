package com.example.ltaop.part.com.service

import ch.qos.logback.classic.Logger
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import com.example.fwk.custom.pojo.CommonsUser
import com.example.ltaop.jpa.UserRepo
import com.example.ltaop.util.DateUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException

@Service
class AuthService {

    companion object {
        val log: Logger = LoggerFactory.getLogger(AuthService::class.java) as Logger
    }

    private final var algorithm: Algorithm
    private final var verifier: JWTVerifier
    @Autowired lateinit var repo: UserRepo

    init {
        try {
            this.algorithm = Algorithm.HMAC256("exam")
            this.verifier = JWT.require(algorithm)
                .build()
        } catch (e: UnsupportedEncodingException) {
            log.error("sign err: encode not supported")
            throw Error(e)
        }
    }

    /**
     * 로그인
     */
    fun signIn(email: String): String {

        // Init --------------------------------------------------------------------------------------------------------
        val selectedUser = repo.findByEmail(email)

        // Valid -------------------------------------------------------------------------------------------------------
        if (selectedUser.isEmpty) {
            throw NoSuchElementException("사용자가 존재하지 않습니다.")
        }

        // Main --------------------------------------------------------------------------------------------------------
        val jwt = JWT.create()
            .withIssuer("com.examample.ltaop")
            .withClaim("email", email)
            .withClaim("userId", selectedUser.get().userId)
            .withIssuedAt(DateUtils.nowDate())
            .withExpiresAt(
                DateUtils.fromLocalDateTimeToDate(
                    DateUtils.now()
                        .plusDays(1)
                )
            )
            .sign(algorithm)

        // End --------------------------------------------------------------------------------------------------------
        return jwt

    }

    /**
     * 토큰 디코딩
     */
    fun decodeToken(jwt: String): CommonsUser? {
        if (!isValidToken(jwt))  // IF 토큰이 유효하지 않다면
            return null          //    THEN null

        val decoded = verifier.verify(jwt)
        val userId = decoded.getClaim("userId").asInt()
        val email = decoded.getClaim("email").asString()

        return CommonsUser(
            userId = userId, email = email, jwt = jwt
        )
    }

    /**
     * 유효한 토큰인가?
     */
    fun isValidToken(jwt: String?): Boolean {
        return try {
            if (jwt == null) return false
            verifier.verify(jwt)
            true
        } catch (e: JWTDecodeException) {
            false
        } catch (e: Exception) {
            false
        }
    }
}
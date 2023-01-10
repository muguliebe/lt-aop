package com.example.fwk.custom.jpa

import com.example.fwk.custom.entity.FwkTrsHst
import com.example.fwk.custom.entity.FwkTrsHstId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FwkTrsHstRepo : JpaRepository<FwkTrsHst, FwkTrsHstId>

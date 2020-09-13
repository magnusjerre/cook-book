package com.baje.cookbook.repository

import com.baje.cookbook.models.CookBookUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CookBookUserRepository : JpaRepository<CookBookUser, Long> {
}
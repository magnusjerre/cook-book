package com.baje.cookbook.repository

import com.baje.cookbook.models.Recipe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository : JpaRepository<Recipe, Long> {
    fun findAllByOwnerId(ownerId: Long): List<Recipe>
}
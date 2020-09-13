package com.baje.cookbook.models


import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class CookBookUser(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var externalUserProviderId: String,
        @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], orphanRemoval = true)
        var recipes: List<Recipe>
)

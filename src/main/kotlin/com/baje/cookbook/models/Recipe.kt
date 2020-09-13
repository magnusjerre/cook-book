package com.baje.cookbook.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Recipe (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var name: String,
        var intro: String?,
        var baseUnit: Int?,
        var activeTimeMin: Int?,
        var activeTimeMax: Int?,
        var totalTimeMin: Int?,
        var totalTimeMax: Int?,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(foreignKey = ForeignKey(name = "owner_id"), name = "owner_id")
        @JsonIgnore
        var owner: CookBookUser
)
package com.baje.cookbook.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Recipe(
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
        var owner: CookBookUser,
        @OneToMany(mappedBy = "recipe", cascade = [CascadeType.ALL], orphanRemoval = true)
        var sections: List<Section>
)
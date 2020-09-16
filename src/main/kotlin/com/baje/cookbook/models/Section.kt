package com.baje.cookbook.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Section (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var sequenceNumber: Short,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(foreignKey = ForeignKey(name = "recipe_id"), name = "recipe_id")
        @JsonIgnore
        var recipe: Recipe,
        @OneToMany(mappedBy = "section", cascade = [CascadeType.ALL], orphanRemoval = true)
        var ingredients: List<Ingredient>,
        @OneToMany(mappedBy = "section", cascade = [CascadeType.ALL], orphanRemoval = true)
        var steps: List<SectionStep>
)
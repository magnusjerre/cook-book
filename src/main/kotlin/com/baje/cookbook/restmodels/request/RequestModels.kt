package com.baje.cookbook.restmodels.request

data class Recipe(
        val name: String,
        val intro: String?,
        val baseUnit: Int?,
        val activeTimeMin: Int?,
        val activeTimeMax: Int?,
        val totalTimeMin: Int?,
        val totalTimeMax: Int?,
        val sections: List<Section>?
)

data class Section(
        val sequenceNumber: Short,
        val ingredients: List<Ingredient>,
        val steps: List<SectionStep>
)

data class Ingredient(
        val name: String,
        val measurement: Float,
        val measureUnit: String
)

data class SectionStep(
        val sequenceNumber: Short,
        val description: String
)

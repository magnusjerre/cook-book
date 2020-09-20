package com.baje.cookbook.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class SectionStep(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var sequenceNumber: Short?,
        var description: String?,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(foreignKey = ForeignKey(name = "section_id"), name = "section_id")
        @JsonIgnore
        var section: Section
)
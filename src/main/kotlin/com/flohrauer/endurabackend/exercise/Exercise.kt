package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.core.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "exercise")
class Exercise(
    @Column(name = "name")
    val name: String,
    @Column(name = "description", nullable = true)
    val description: String?,
    @Column(name = "instructions", nullable = true)
    val instructions: String?,
): BaseEntity()

package com.flohrauer.endurabackend.core

import jakarta.persistence.*
import java.util.UUID

@MappedSuperclass
open class BaseEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null
)

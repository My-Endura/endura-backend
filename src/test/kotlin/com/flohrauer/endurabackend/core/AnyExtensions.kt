package com.flohrauer.endurabackend.core

import com.google.gson.GsonBuilder

fun Any.toJson(): String {
    return GsonBuilder()
        .setPrettyPrinting()
        .create()
        .toJson(this)
}

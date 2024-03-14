package com.example.consciente_te.state

sealed class User(val role: String) {
    object Collector : User(role = "collector")
    object Guest : User(role = "guest")
}
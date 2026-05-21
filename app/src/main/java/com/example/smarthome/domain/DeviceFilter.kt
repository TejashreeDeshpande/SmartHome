package com.example.smarthome.domain

enum class DeviceFilter(
    val title: String,
    val icon: String
) {
    All("All", "🏠"),
    Online("Online", "🟢"),
    Offline("Offline", "⚫"),
    Lights("Lights", "💡"),
    Security("Security", "🔐")
}
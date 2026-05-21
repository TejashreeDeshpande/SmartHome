package com.example.smarthome.domain

enum class DeviceType(
    val title: String,
    val icon: String
) {
    LIGHT(title = "Light", icon = "💡"),

    THERMOSTAT(title = "Thermostat", icon = "🌡"),

    CAMERA(title = "Camera", icon = "📷"),

    SPEAKER(title = "Speaker", icon = "🔊"),

    LOCK(title = "Lock", icon = "🔐"),

    ENERGY(title = "Energy", icon = "⚡")
}
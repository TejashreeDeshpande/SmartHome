package com.example.smarthome.domain

interface SmartDevice {
    val id: String
    val name: String
    val room: String
    val type: DeviceType
    val status: DeviceStatus
}
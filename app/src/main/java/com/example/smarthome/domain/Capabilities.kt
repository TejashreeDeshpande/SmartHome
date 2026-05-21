package com.example.smarthome.domain

interface PowerControl {
    val isOn: Boolean
    fun togglePower()
}

interface BrightnessControl {
    var brightness: Int
}

interface TemperatureControl {
    var temperature: Int
}

interface VolumeControl {
    var volume: Int
}

interface SecurityControl {
    var isArmed: Boolean
    fun toggleSecurity()
}

interface LockControl {
    var lockState: LockState
    fun toggleLock()
}

sealed class LockState {
    data object Locked: LockState()
    data object Unlocked: LockState()
}
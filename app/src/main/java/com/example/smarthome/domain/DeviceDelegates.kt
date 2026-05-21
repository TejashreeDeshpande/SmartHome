package com.example.smarthome.domain

class PowerControlDelegate(
    initialValue: Boolean = true
) : PowerControl {
    override var isOn: Boolean = initialValue

    override fun togglePower() {
        isOn = !isOn
    }
}

class BrightnessControlDelegate(
    initialValue: Int = 70,
    private val onChanged: (String) -> Unit = {}
) : BrightnessControl {
    override var brightness: Int by RangeDelegate(
        min = 0,
        max = 100,
        initialValue = initialValue
    )
}

class TemperatureControlDelegate(
    initialValue: Int = 72
) : TemperatureControl {
    override var temperature: Int by RangeDelegate(
        min = 60,
        max = 85,
        initialValue = initialValue
    )
}

class VolumeControlDelegate(
    initialValue: Int = 45
) : VolumeControl {
    override var volume: Int by RangeDelegate(
        min = 0,
        max = 100,
        initialValue = initialValue
    )
}

class SecurityControlDelegate(
    initialValue: Boolean = true
) : SecurityControl {
    override var isArmed = initialValue
    override fun toggleSecurity() {
        isArmed = !isArmed
    }
}

class LockControlDelegate(
    initialValue: LockState = LockState.Locked
) : LockControl {
    override var lockState: LockState = initialValue

    override fun toggleLock() {
        lockState = when (lockState) {
            LockState.Locked -> LockState.Unlocked
            LockState.Unlocked -> LockState.Locked
        }
    }
}
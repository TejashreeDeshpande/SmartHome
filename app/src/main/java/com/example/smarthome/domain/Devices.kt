package com.example.smarthome.domain

data class SmartLight(
    override val id: String,
    override val name: String,
    override val room: String,
    override val status: DeviceStatus,
    private val powerDelegate: PowerControl = PowerControlDelegate(),
    private val brightnessDelegate: BrightnessControl = BrightnessControlDelegate()
) : SmartDevice,
    PowerControl by powerDelegate,
    BrightnessControl by brightnessDelegate {

    override val type: DeviceType = DeviceType.LIGHT

    val config: String by lazy {
        "Warm White - Schedule Enabled"
    }
}

data class Thermostat(
    override val id: String,
    override val name: String,
    override val room: String,
    override val status: DeviceStatus,
    private val temperatureDelegate: TemperatureControl = TemperatureControlDelegate()
) : SmartDevice,
    TemperatureControl by temperatureDelegate {

    override val type: DeviceType = DeviceType.THERMOSTAT

    val config: String by lazy {
        "Auto Mode - Eco Enabled"
    }
}

data class SecurityCamera(
    override val id: String,
    override val name: String,
    override val room: String,
    override val status: DeviceStatus,
    private val securityDelegate: SecurityControl = SecurityControlDelegate()
) : SmartDevice,
    SecurityControl by securityDelegate {

    override val type: DeviceType = DeviceType.CAMERA
}

data class SmartSpeaker(
    override val id: String,
    override val name: String,
    override val room: String,
    override val status: DeviceStatus,
    private val powerDelegate: PowerControl = PowerControlDelegate(),
    private val volumeDelegate: VolumeControl = VolumeControlDelegate()
) : SmartDevice,
    PowerControl by powerDelegate,
    VolumeControl by volumeDelegate {

    override val type: DeviceType = DeviceType.SPEAKER
}

data class DoorLock(
    override val id: String,
    override val name: String,
    override val room: String,
    override val status: DeviceStatus,
    private val lockDelegate: LockControl = LockControlDelegate()
) : SmartDevice,
    LockControl by lockDelegate {

    override val type: DeviceType = DeviceType.LOCK
}


data class EnergyMeter(
    override val id: String,
    override val name: String,
    override val room: String,
    override val status: DeviceStatus,
    val usageKwh: Double
) : SmartDevice {

    override val type: DeviceType = DeviceType.ENERGY
}


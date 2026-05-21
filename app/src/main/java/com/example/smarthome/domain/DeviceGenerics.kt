package com.example.smarthome.domain

interface DeviceController<T : SmartDevice> {
    fun update(device: T): T
}

class GenericPowerController<T>(
    private val updateBlock: (T) -> T
) : DeviceController<T> where T : SmartDevice {
    override fun update(device: T): T = updateBlock(device)
}

interface DeviceProvider<out T : SmartDevice> {
    fun getDevices(): List<T>
}

class SmartDeviceProvider(
    private val devices: List<SmartDevice>
) : DeviceProvider<SmartDevice> {
    override fun getDevices(): List<SmartDevice> = devices
}
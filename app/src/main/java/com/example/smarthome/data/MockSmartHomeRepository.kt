package com.example.smarthome.data

import com.example.smarthome.domain.*

class MockSmartHomeRepository {

    fun getDevices(): List<SmartDevice> {
        return listOf(

            SmartLight(
                id = "1",
                name = "Smart Light",
                room = "Living Room",
                status = DeviceStatus.ONLINE
            ),

            Thermostat(
                id = "2",
                name = "Thermostat",
                room = "Living Room",
                status = DeviceStatus.ONLINE
            ),

            SecurityCamera(
                id = "3",
                name = "Security Camera",
                room = "Front Door",
                status = DeviceStatus.OFFLINE
            ),

            SmartSpeaker(
                id = "4",
                name = "Smart Speaker",
                room = "Living Room",
                status = DeviceStatus.ONLINE
            ),

            DoorLock(
                id = "5",
                name = "Door Lock",
                room = "Main Door",
                status = DeviceStatus.OFFLINE
            ),

            EnergyMeter(
                id = "6",
                name = "Energy Meter",
                room = "Utility Room",
                status = DeviceStatus.ONLINE,
                usageKwh = 1.8
            ),

            SmartLight(
                id = "7",
                name = "Bedroom Lamp",
                room = "Bedroom",
                status = DeviceStatus.OFFLINE
            ),

            Thermostat(
                id = "8",
                name = "Guest Thermostat",
                room = "Guest Room",
                status = DeviceStatus.ONLINE
            ),

            SecurityCamera(
                id = "9",
                name = "Garage Camera",
                room = "Garage",
                status = DeviceStatus.ONLINE
            ),

            SmartSpeaker(
                id = "10",
                name = "Kitchen Speaker",
                room = "Kitchen",
                status = DeviceStatus.OFFLINE
            )
        )
    }
}
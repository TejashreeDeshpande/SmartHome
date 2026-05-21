package com.example.smarthome.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthome.domain.DeviceFilter
import com.example.smarthome.domain.DeviceStatus
import com.example.smarthome.domain.DeviceType
import com.example.smarthome.domain.DoorLock
import com.example.smarthome.domain.EnergyMeter
import com.example.smarthome.domain.LockState
import com.example.smarthome.domain.SecurityCamera
import com.example.smarthome.domain.SmartDevice
import com.example.smarthome.domain.SmartLight
import com.example.smarthome.domain.SmartSpeaker
import com.example.smarthome.domain.Thermostat
import com.example.smarthome.ui.theme.*

@Composable
fun HeaderSection() {
    Column {
        Text(
            text = "Smart Home",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )
        Text(
            text = "Manage and monitor all smart devices",
            style = MaterialTheme.typography.bodyMedium,
            color = MutedText
        )
    }
}

@Composable
fun DeviceFilterChips(
    selected: DeviceFilter,
    onSelected: (DeviceFilter) -> Unit
) {

    val filters = DeviceFilter.entries

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {

        items(filters) { filter ->

            val isSelected = selected == filter

            FilterChip(
                selected = isSelected,
                onClick = {
                    onSelected(filter)
                },
                label = {
                    Text(
                        text = filter.title,
                        style = MaterialTheme.typography.labelLarge
                    )
                },

                leadingIcon = {
                    Text(
                        text = filter.icon
                    )
                },

                shape = RoundedCornerShape(18.dp),

                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = isSelected,
                    borderColor = CardBorder,
                    selectedBorderColor = Copper
                ),

                colors = FilterChipDefaults.filterChipColors(
                    containerColor = CardColor,
                    labelColor = MutedText,
                    selectedContainerColor = Copper,
                    selectedLabelColor = WarmCream
                )
            )
        }
    }
}

@Composable
fun DeviceSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    val colors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Espresso.copy(alpha = 0.18f),
        unfocusedContainerColor = Espresso.copy(alpha = 0.12f),

        focusedBorderColor = Copper,
        unfocusedBorderColor = CardBorder,

        focusedTextColor = WarmCream,
        unfocusedTextColor = WarmCream
    )
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text("Search devices by name or room...")
        },
        leadingIcon = {
            Icon(Icons.Rounded.Search, contentDescription = null)
        },
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        colors = colors
    )
}

@Composable
fun ExpandableDeviceCard(
    device: SmartDevice,
    onClick: () -> Unit
) {
    val statusText = deviceStatusText(device)
    val accent = deviceAccent(device.type)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(28.dp))
            .clickable { onClick() }
            .animateContentSize(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.size(44.dp),
                    text = device.type.icon,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = device.name,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = device.room,
                        color = MutedText,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                GlowStatusDot(
                    online = device.status == DeviceStatus.ONLINE
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = statusText,
                    color = accent,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            DeviceMetricSection(device)
        }
    }
}

@Composable
fun DeviceMetricSection(device: SmartDevice) {
    when (device) {
        is SmartLight -> {
            MetricProgress(
                label = "Brightness",
                value = device.brightness,
                color = WarningAmber
            )
        }

        is Thermostat -> {
            Text(
                text = "Temperature Range 60°F - 85°F • ${device.config}",
                color = MutedText
            )
        }

        is SecurityCamera -> {
            Text(
                text = "Motion: None • Security Armed: ${device.isArmed}",
                color = MutedText
            )
        }

        is SmartSpeaker -> {
            MetricProgress(
                label = "Volume",
                value = device.volume,
                color = SpeakerAccent
            )
        }

        is DoorLock -> {
            Text(
                text = "Access: Family • Battery 85%",
                color = MutedText
            )
        }

        is EnergyMeter -> {
            MiniEnergyBars()
        }
    }
}

@Composable
fun MiniEnergyBars(
    modifier: Modifier = Modifier
) {

    val bars = listOf(
        0.35f,
        0.55f,
        0.75f,
        0.45f,
        0.9f,
        0.6f,
        0.8f
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.Bottom
    ) {

        bars.forEach { value ->

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(value)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                GoldAccent,
                                Copper
                            )
                        )
                    )
            )
        }
    }
}

@Composable
fun MetricProgress(
    label: String,
    value: Int,
    color: Color
) {
    Column {
        Row {
            Text(
                text = label,
                color = MutedText,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$value%",
                color = Color.White
            )
        }
        LinearProgressIndicator(
            progress = { value / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = color,
            trackColor = Color.White.copy(alpha = 0.12f)
        )
    }
}

@Composable
fun GlowStatusDot(online: Boolean) {
    Canvas(modifier = Modifier.size(12.dp)) {
        drawCircle(
            color = if (online) SuccessGreen else Color.Gray,
            radius = size.minDimension / 2
        )
    }
}

fun deviceStatusText(device: SmartDevice): String {
    return when (device) {

        is SmartLight -> {
            if (device.isOn) {
                "${device.brightness}%"
            } else {
                "OFF"
            }
        }

        is Thermostat -> {
            "${device.temperature}°F"
        }

        is SecurityCamera -> {
            if (device.isArmed) {
                "ARMED"
            } else {
                "OFF"
            }
        }

        is SmartSpeaker -> {
            "${device.volume}%"
        }

        is DoorLock -> {
            when (device.lockState) {
                LockState.Locked -> "LOCKED"
                LockState.Unlocked -> "OPEN"
            }
        }

        is EnergyMeter -> {
            "${device.usageKwh} kWh"
        }

        else -> ""
    }
}

fun deviceAccent(type: DeviceType): Color {
    return when (type) {
        DeviceType.LIGHT -> LightAccent
        DeviceType.THERMOSTAT -> ThermostatAccent
        DeviceType.CAMERA -> CameraAccent
        DeviceType.SPEAKER -> SpeakerAccent
        DeviceType.LOCK -> LockAccent
        DeviceType.ENERGY -> EnergyAccent
    }
}
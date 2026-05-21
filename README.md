# SmartHome

A modern Android POC for smart home management, built to explore the latest Jetpack Compose patterns and Kotlin features.

## Features

- **Device Dashboard**: Unified control for Lights, Thermostats, Cameras, Speakers, Locks, and Energy Meters.
- **Real-time Monitoring**: Live status tracking with custom visual indicators.
- **Deep Search & Filter**: Instant device discovery by name, room, or category.
- **Sleek Dark Theme**: A premium Material 3 UI designed for low-light environments.

## Compose & Kotlin Exploration

This project serves as a playground for modern Android development:

- **Advanced State**: Leveraging `collectAsStateWithLifecycle` for lifecycle-aware UI, and `derivedStateOf` for optimized computation.
- **Dynamic Layouts**: Efficient list rendering with `LazyColumn` and `LazyRow` using custom item keys.
- **Fluid Animations**: Smooth transitions with `animateContentSize` and customized Material 3 components.
- **Custom Graphics**: Using `Canvas` and `Brush` for high-performance status dots and energy visualization.
- **Clean UI Logic**: Complete separation of concerns using `koinViewModel` and immutable UI states.
- **Kotlin Delegation**: 
    - **Interface Delegation**: Using `by` to compose device capabilities (Power, Temp, Volume) modularly.
    - **Property Delegation**: Custom delegates for range-coercion and state change logging.

## Technical Foundation

- **Architecture**: Domain-Driven Design (DDD) with a clear separation between business logic and UI.
- **Dependency Injection**: Koin for lightweight and pragmatic DI.
- **Edge-to-Edge**: Full utilization of screen real estate with `enableEdgeToEdge` and Window Insets.

## Tech Stack

- **UI**: Jetpack Compose (Material 3)
- **Language**: Kotlin 2.x
- **DI**: Koin
- **Build**: Gradle (Kotlin DSL + Version Catalogs)

## Getting Started

1. **Clone**: `git clone ...`
2. **Open**: Android Studio Ladybug+
3. **Run**: Deploy the `app` module to an emulator or device.

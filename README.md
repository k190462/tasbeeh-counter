# Tasbeeh Counter

A Digital Tasbeeh Counter Android application built with Jetpack Compose and Material Design 3.

## Features

- **Digital Counter**: Large, prominent counter display with animated number transitions
- **Haptic Feedback**: Vibration on each count increment (toggleable)
- **Milestone Notifications**: Congratulatory messages at 25, 50, 100, 500, and 1000 counts
- **Save & History**: Save counts with timestamps and view history with sorting options
- **Dark Mode**: Light, Dark, and System-default theme options
- **Settings**: Sound toggle, haptic feedback toggle, font size adjustment, theme selection
- **Reset with Confirmation**: Prevents accidental counter resets

## Architecture

- **Pattern**: MVVM (Model-View-ViewModel)
- **UI**: Jetpack Compose with Material Design 3
- **Database**: Room Persistence Library for saved counts
- **Preferences**: DataStore for user settings
- **Language**: 100% Kotlin
- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 35

## Project Structure

```
app/src/main/java/com/tasbeeh/counter/
├── MainActivity.kt              # Entry point
├── TasbeehApplication.kt        # Application class
├── data/
│   ├── database/                 # Room database and DAO
│   ├── preferences/              # DataStore preferences
│   └── repository/               # Repository pattern
├── domain/
│   └── model/                    # Data models
└── ui/
    ├── TasbeehApp.kt             # Root composable with navigation
    ├── navigation/               # Navigation routes
    ├── screens/
    │   ├── counter/              # Main counter screen & ViewModel
    │   ├── history/              # Saved counts history & ViewModel
    │   └── settings/             # Settings screen & ViewModel
    └── theme/                    # Material 3 theme, colors, typography
```

## Building

```bash
./gradlew assembleDebug
```

## Testing

```bash
./gradlew test
```

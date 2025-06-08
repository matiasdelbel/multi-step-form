# 💳 Android Multi-Step Form with Jetpack Compose
A polished multi-step form built using **Jetpack Compose**, following best practices in modern 
Android development. This project demonstrates smooth screen transitions, robust state handling, 
and modular validation — ideal for form-heavy user flows such as onboarding or checkout processes.

![Demo](./assets/demo.gif)

## Key Features
- 🧭 Multi-step navigation using **Navigation Compose**
- 🧠 State management via **ViewModel** and **StateFlow**
- ✅ Input validation with modular use cases (e.g. Luhn algorithm for card number)
- 🧩 Separation of concerns and reusable UI components
- ✨ Animated transitions between form steps

## Tech Stack
- **Kotlin**
- **Jetpack Compose**
- **Navigation Compose**
- **StateFlow**
- **Hilt** (for Dependency Injection)
- **Validator Usecases**
- **CI/CD** with GitHub Actions

## Architecture Overview
- ViewModel manages step-specific UI state
- Each screen is a Composable, driven by state
- Validation logic encapsulated in use-case classes
- UI and business logic clearly separated

## Testing & Extensibility
This project emphasizes clean architecture, making it easy to:
- Add new steps to the form
- Inject new validation rules
- Reuse components in different contexts

## Getting Started
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle and Run the app on an emulator or device

## License
This project is licensed under the MIT License. See LICENSE for details.
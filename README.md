# 💳 Android Multi-Step Form with Jetpack Compose
A simple multi-step form built with Jetpack Compose and modern Android architecture practices.  
Designed to demonstrate clean state management, Compose navigation, and UI animations between steps.

> ⚙️ **Personal Portfolio Project — Clean Compose Architecture, State Management, and Form Flows**

// TODO add demo gif
![Demo](demo.gif)

## 🎯 Why this project?
When building modern Android apps, one common pattern is handling user input across multiple screens.
This project shows how I structure and reason about:
- Multi-step Compose navigation
- Clean ViewModel state management (StateFlow)
- Form validation logic (Luhn algorithm)
- Scalable state modeling
- Separation of concerns

## Tech Stack
- Kotlin
- Jetpack Compose
- Navigation Compose
- StateFlow
- Hilt
- Validator Usecases
- CI: GitHub Actions

## Architecture Diagram
```yml 
NavHost --> ViewModels --> StateFlow --> Validators --> Screens
```

# Architecture

- ViewModel only manages state and business logic.
- Validators return `ValidationError` sealed classes.
- UI layer maps errors to localized strings.
- InputField holds both `TextFieldValue` and validation error.
- Navigation extracted to dedicated functions for scalability.

## Testing
You can use the following credit card numbers in order to test the form:
- American Express	3714 4963 5398 431
- Visa	4871 0499 9999 9910
- Visa	4111 1111 1111 1111
- Mastercard	5454 5454 5454 5454

## Setup
1. Clone repo
2. Open in Android Studio
3. Run

## License
MIT — free to use and modify.

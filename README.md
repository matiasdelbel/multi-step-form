# 🧾 Android Multi-Step Form with Jetpack Compose
A simple multi-step form built with Jetpack Compose and modern Android architecture practices.  
Designed to demonstrate clean state management, Compose navigation, and UI animations between steps.

---

## 💡 Why this project?

When building modern Android apps, one common pattern is handling user input across multiple screens. 
This project shows how I structure and reason about:

- State shared across steps
- Safe navigation between screens
- Input validation per step
- Clean MVVM with Compose
- UI animations for smooth UX

---

## 🚀 Features

- Jetpack Compose UI
- Multi-step form with:
    - Step 1: Name + Email (with validation)
    - Step 2: Preferences (radio + checkbox)
    - Step 3: Summary and confirmation
- ViewModel holding centralized form state
- Navigation controlled by state validity
- `AnimatedContent` transitions between steps
- Modular folder structure
- Ready for testing, CI and future extension

---

## 🛠️ Tech Stack

- Kotlin
- Jetpack Compose
- Navigation Compose
- State management with `StateFlow`
- Hilt for DI
- `AnimatedContent` for transitions
- (Planned) GitHub Actions for CI

---

## 📁 Structure

```text
app/
 └── ui/
      ├── navigation/
      ├── screens/
      │    ├── step1/
      │    ├── step2/
      │    ├── step3/
      │    └── result/
      └── theme/
 └── FormViewModel.kt
```

## 🧪 Coming soon
- Unit tests for validation logic
- CI with GitHub Actions 
- Local data persistence (Room or DataStore)

##  🧠 Design Decisions
- State is centralized in one ViewModel
- Steps update their part of the state
- Navigation is not allowed unless the step is valid
- UI logic stays in Compose, business logic in ViewModel

##  🧰 Setup
- Clone this repo
- Open in Android Studio
- Run the app

## 📌 License
MIT — free to use and modify.

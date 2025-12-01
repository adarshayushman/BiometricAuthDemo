# 🔐 Android Biometric Authentication (Kotlin + Compose)

**Topic:** Secure Authentication using Fingerprint/Face ID  
**Author:** Adarsh AYUSHMAN

---

## 📱 Project Overview
This project demonstrates how to implement **Biometric Authentication** (Fingerprint & Face Unlock) in a modern Android application using **Jetpack Compose** and the **AndroidX Biometric Library**.

It includes two modules:
1.  **Demo App:** A fully working example of a locked "Secret" screen.
2.  **Student Exercise:** An unfinished version for you to complete.

### Key Concepts Covered
* `BiometricManager`: Checking if the device has hardware sensors.
* `BiometricPrompt`: Displaying the standard Android security dialog.
* `CryptoObject`: (Concept) How Android secures the cryptographic key (abstracted in this demo).
* **State Management:** Handling "Locked" vs "Unlocked" UI states in Jetpack Compose.

---

## ⚙️ Technical Implementation
The project uses the **MVVM** pattern principles (separating logic from UI) using a helper object to manage the complexity of the Android APIs.

### Main Components
1.  **`BiometricHelper` Object:** A singleton that wraps the `BiometricPrompt` and `BiometricManager` APIs. It checks if the hardware is ready and handles the success/error callbacks.
2.  **`MainActivity` (FragmentActivity):** The entry point. I used `FragmentActivity` instead of `ComponentActivity` because the Biometric library requires it to display the bottom sheet dialog.
3.  **State Hoisting:** The authentication status (`isAuthenticated`) is hoisted in the top-level Composable to control the UI flow.

---

## 🛠️ Setup & Requirements
To run this project on an emulator, specific configuration is required because emulators do not have physical sensors by default.

### Emulator Configuration
If testing on Android Studio Emulator:
1.  **Security Settings:** I set up a PIN code in the emulator settings (`Settings > Security > Device Unlock`).
2.  **Fingerprint Enrollment:** I added a "virtual" fingerprint via the emulator settings.
3.  **Simulating Touches:** The authentication is triggered by using the **Extended Controls** menu (three dots `...`) in the emulator toolbar -> **Fingerprint** -> **"Touch the Sensor"**.

---

## 🛠️ Prerequisites
To run this project, you need:
* **Android Studio** (Koala/Ladybug or newer recommended).
* **Android Device or Emulator** running **API 24 (Nougat)** or higher.
* **Important for Emulators:** You must set up a Lock Screen (PIN) and add a Fingerprint in the Emulator Settings for this to work.
  
---

## 📚 Libraries Used
* `androidx.biometric:biometric:1.1.0` - For the security dialogs.
* `androidx.compose.material3` - For the Material Design components.

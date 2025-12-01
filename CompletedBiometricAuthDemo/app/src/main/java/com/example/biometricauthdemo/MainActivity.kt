package com.example.biometricauthdemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiometricAuthApp()
        }
    }
}

@Composable
fun BiometricAuthApp() {
    // 1. Get the Context (needed to access system services)
    val context = LocalContext.current
    // We cast it to Activity because BiometricPrompt needs an Activity to show the popup
    val activity = context as? FragmentActivity

    // 2. Define our state variables
    // 'isAuthenticated' tracks if the user is logged in
    var isAuthenticated by remember { mutableStateOf(false) }
    // 'statusMessage' shows feedback to the user (e.g., "Error", "Success")
    var statusMessage by remember { mutableStateOf("App Locked") }

    // 3. The UI Layout
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isAuthenticated) {
            // --- SCENE 1: UNLOCKED ---
            // This only shows if the fingerprint matched
            Text(
                text = "🔓 SECRET UNLOCKED",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF4CAF50) // Green
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "The secret code is: 42", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {
                // Log out logic
                isAuthenticated = false
                statusMessage = "App Locked"
            }) {
                Text("Lock App")
            }
        } else {
            // --- SCENE 2: LOCKED ---
            // This is the default screen
            Text(
                text = "🔒 $statusMessage",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {
                // Check if the activity is valid and hardware exists
                if (activity != null && BiometricHelper.isBiometricAvailable(context)) {

                    // Call our helper function
                    BiometricHelper.authenticate(
                        activity = activity,
                        onSuccess = {
                            // This runs ONLY if fingerprint matches
                            isAuthenticated = true
                            statusMessage = "Unlocked!"
                        },
                        onError = { error ->
                            // This runs if user cancels or sensor fails
                            statusMessage = "Error: $error"
                        }
                    )
                } else {
                    statusMessage = "Biometrics not available on this device"
                }
            }) {
                Text("Unlock with Fingerprint")
            }
        }
    }
}
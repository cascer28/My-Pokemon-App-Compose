package com.cascer.mypokemonapp.presentation.screen.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cascer.mypokemonapp.R

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val registerSuccess = viewModel.registerSuccess
    val errorMessage = viewModel.error
    val context = LocalContext.current

    LaunchedEffect(registerSuccess) {
        if (registerSuccess) {
            Toast.makeText(context, "You're all set! Let's get started.", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.label_register),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp),
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(R.string.label_name)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            value = username,
            onValueChange = { username = it },
            label = { Text(text = stringResource(R.string.label_username)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.label_password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    val icon = if (passwordVisible) {
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    }
                    val desc = if (passwordVisible) {
                        "Hide Password"
                    } else {
                        "Show Password"
                    }
                    Icon(imageVector = icon, contentDescription = desc)
                }
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = stringResource(R.string.label_confirm_password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    val icon = if (confirmPasswordVisible) {
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    }
                    val desc = if (passwordVisible) {
                        "Hide Password"
                    } else {
                        "Show Password"
                    }
                    Icon(imageVector = icon, contentDescription = desc)
                }
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            onClick = {
                viewModel.register(name, username, password, confirmPassword)
            }
        ) {
            Text(text = stringResource(R.string.label_register))
        }
        TextButton(
            modifier = Modifier.padding(top = 20.dp),
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(stringResource(R.string.label_login_in_register))
        }
    }
}
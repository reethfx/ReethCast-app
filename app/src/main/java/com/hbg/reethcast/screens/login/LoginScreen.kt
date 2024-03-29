@file:Suppress("UNUSED_EXPRESSION")

package com.hbg.reethcast.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hbg.reethcast.R
import com.hbg.reethcast.data.viewmodels.LoginScreenViewModel
import com.hbg.reethcast.navegation.ReethcastScreens


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFF0D031F)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_logo_reethcast),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (showLoginForm.value) {
                Text(text = "Inicia sesión", color = Color(0xFF922DAD))
                UserForm(
                    isCreateAccount = false
                ) { email, password ->
                   Log.d("ReethCast", "Logged with $email and $password")
                    viewModel.signInWithEmailAndPassword(email, password) {
                        navController.navigate(ReethcastScreens.HomeScreen.name)
                    }
                }
            } else {
                Text(text = "Crea una cuenta", color = Color(0xFF922DAD) )
                UserForm(
                    isCreateAccount = true
                ) { email, password ->
                    Log.d("ReethCast", "Account created with $email and $password")
                    viewModel.createUserWithEmailAndPassword(email, password) {
                        navController.navigate(ReethcastScreens.HomeScreen.name)
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text1 =
                    if (showLoginForm.value) "¿No tienes cuenta?"
                    else "¿Ya tienes cuenta?"

                val text2 =
                    if (showLoginForm.value) "Regístrate"
                    else "Inicia sesión"
                Text(text = text1, color = Color(0xFFededed))
                Text(text = text2,
                    modifier = Modifier
                        .clickable { showLoginForm.value = !showLoginForm.value }
                        .padding(start = 5.dp),
                    color = Color(0xFF922DAD)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun UserForm(
 isCreateAccount: Boolean = false,
 onDone: (String, String) -> Unit = {email, pwd ->}
){
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val validated = remember(email.value, password.value){
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        EmailInput(
            emailState = email
        )
        PasswordInput (
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        SubmitButton(
            textId = if (isCreateAccount) "Crear cuenta" else "Accede a tu cuenta",
            validatedInput = validated,
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(
    textId: String,
    validatedInput: Boolean,
    onConfirm: () -> Unit
) {
    Button(onClick = onConfirm,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = validatedInput,
        colors = ButtonDefaults.buttonColors(Color(0xFFC15BD3))

    ) {
        Text(text = textId,
            modifier = Modifier
                .padding(5.dp),
        )

    }

}

@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = {
            Text(
                text = labelId, color = Color(0xFFededed)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Magenta,
        ),

        shape = CircleShape,
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()) {
                PasswordVisibleIcon(passwordVisible)
            } else null
        }
    )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(
            imageVector = image,
            contentDescription = "",
            tint = Color(0xFFededed))
    }
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId : String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email,

    )
}

@Composable
fun InputField(
     valueState: MutableState<String>,
     labelId: String,
     isSingleLine: Boolean = true,
     keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId, color = Color(0xFFededed))},
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Magenta,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
        )
}

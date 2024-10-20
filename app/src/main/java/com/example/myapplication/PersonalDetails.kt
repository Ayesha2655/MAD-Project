package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class PersonalDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                PersonalDetailsScreen()
            }
        }
    }
}

@Composable
fun PersonalDetailsScreen() {
    val context= LocalContext.current
    val name = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("Female") }  // Default selected gender

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Personal Details", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        // Name TextField
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name*") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Phone Number TextField
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            label = { Text("Number*") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Email TextField
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email* (For Invoices)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Gender Radio Buttons
        Text(text = "Gender", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RadioButton(
                selected = gender.value == "Male",
                onClick = { gender.value = "Male" }
            )
            Text(text = "Male", modifier = Modifier.padding(end = 16.dp))

            RadioButton(
                selected = gender.value == "Female",
                onClick = { gender.value = "Female" }
            )
            Text(text = "Female", modifier = Modifier.padding(end = 16.dp))

            RadioButton(
                selected = gender.value == "Other",
                onClick = { gender.value = "Other" }
            )
            Text(text = "Other")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Done Button
        Button(
            onClick = { val intent = Intent(context, HomeScreenActivity::class.java)
                context.startActivity(intent) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Done")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDetailsPreview() {
    MyApplicationTheme {
        PersonalDetailsScreen()
    }
}

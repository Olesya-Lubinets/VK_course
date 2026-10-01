package com.example.vk_course

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.vk_course.ui.theme.VK_courseTheme
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            VK_courseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstScreen()
                }
            }
        }
    }
}

@Composable
fun FirstScreen() {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Phone number") }
        )
        Button(
            onClick = {
                if (text.isEmpty()) {
                    Toast.makeText(context, "Text should be not empty", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val explicitIntent =
                    Intent(context, SecondActivity::class.java).apply { putExtra("text", text) }
                context.startActivity(explicitIntent)
            }
        ) {
            Text("Open Second Activity")
        }
        Button(onClick = {
            if (!isValidPhoneNumber(text)) {
                Toast.makeText(context, "Phone number is not valid", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val implicitIntentCall = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:$text")
            )
            context.startActivity(implicitIntentCall)
        }) {
            Text("Call")
        }
        Button(onClick = {
            if (text.isEmpty()) {
                Toast.makeText(context, "Text should be not empty", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val implicitIntentSend = Intent(
                Intent.ACTION_SEND
            ).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }

            context.startActivity(implicitIntentSend)
        }) {
            Text("Send message")
        }

    }

}

fun isValidPhoneNumber(phone: String): Boolean = Patterns.PHONE.matcher(phone).matches()




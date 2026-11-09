
package com.tina.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinaApp()
        }
    }
}

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

@Composable
fun TinaApp() {
    val background = Color(0xFF101018)
    val accent = Color(0xFFD6A6FF)

    var message by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                "Hello Boss! ❤️ Main Tina hoon. Aaj kya karna hai?",
                false
            )
        )
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(16.dp)
        ) {
            Text(
                text = "TINA",
                color = accent,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Your AI Companion",
                color = Color.LightGray,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .background(
                        Color(0xFF242033),
                        RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("♡", color = accent, fontSize = 48.sp)
                    Text(
                        "Tina is waiting for you...",
                        color = Color.White,
                        fontSize = 15.sp
                    )
                    Text(
                        "Hehe... Kya Boss? 😏",
                        color = accent,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Chat with Tina",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(messages) { chat ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            if (chat.isUser) Arrangement.End
                            else Arrangement.Start
                    ) {
                        Text(
                            text = chat.text,
                            color = Color.White,
                            modifier = Modifier
                                .widthIn(max = 280.dp)
                                .background(
                                    if (chat.isUser)
                                        Color(0xFF594477)
                                    else Color(0xFF292633),
                                    RoundedCornerShape(16.dp)
                                )
                                .padding(14.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFF242033),
                        RoundedCornerShape(28.dp)
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = message,
                    onValueChange = { message = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 15.sp
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (message.isEmpty()) {
                            Text(
                                "Boss, kuch likho...",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                )

                Button(
                    onClick = {
                        if (message.isNotBlank()) {
                            messages.add(
                                ChatMessage(message.trim(), true)
                            )
                            message = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accent
                    )
                ) {
                    Text("Send", color = Color.Black)
                }
            }
        }
    }
}

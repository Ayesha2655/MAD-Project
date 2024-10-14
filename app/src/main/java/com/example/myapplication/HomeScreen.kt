package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class HomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Welcome Text
        Text(
            text = "Welcome to Mahir Company",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Hello, Ayesha",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Coins and Wallet Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoCard(label = "0 Coins", modifier = Modifier.weight(1f))
            InfoCard(label = "0 Wallet", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Service Cards
        ServiceCard(title = "Home Services", subtitle1 = "Residential", subtitle2 = "Commercial")
        ServiceCard(title = "Salon Services", subtitle1 = "Females Only", isFemaleOnly = true)
        ServiceCard(title = "Cleaning Services", subtitle1 = "Residential", subtitle2 = "Commercial")
        ServiceCard(title = "Maintained by Mahir", subtitle1 = "Residential", subtitle2 = "Commercial")
    }
}

@Composable
fun InfoCard(label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                color = Color.White
            )
        }
    }
}




@Composable
fun ServiceCard(title: String, subtitle1: String, subtitle2: String? = null, isFemaleOnly: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                ServiceTag(text = subtitle1)
                if (subtitle2 != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    ServiceTag(text = subtitle2)
                } else if (isFemaleOnly) {
                    ServiceTag(text = "Females Only", backgroundColor = Color(0xFFF48FB1))
                }
            }
        }
    }
}

@Composable
fun ServiceTag(text: String, backgroundColor: Color = Color(0xFFB3E5FC)) {
    Box(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyApplicationTheme {
        HomeScreen()
    }
}
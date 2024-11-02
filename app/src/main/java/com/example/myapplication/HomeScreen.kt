package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Welcome to Mahir Company",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle menu icon click if needed */ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp) // Consistent padding
        ) {
            // Welcome Text
            Text(
                text = "Hello, Ayesha",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Coins and Wallet Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoCard(
                    label = "Coins",
                    iconTint = Color.White,
                    iconPainter = painterResource(id = R.drawable.baseline_account_balance_wallet_24),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                InfoCard(
                    label = "Wallet",
                    iconTint = Color.White,
                    iconPainter = painterResource(id = R.drawable.baseline_account_balance_wallet_24),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Row for Home Services and Salon Services
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StyledServiceCard(
                    title = "Home Services",
                    subtitle1 = "Residential",
                    subtitle2 = "Commercial",
                    imagePainter = painterResource(id = R.drawable.handyman1),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        val intent = Intent(context, HomeServicesActivity::class.java)
                        context.startActivity(intent)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                StyledServiceCard(
                    title = "Salon Services",
                    subtitle1 = "Females Only",
                    isFemaleOnly = true,
                    imagePainter = painterResource(id = R.drawable.beautician1),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        val intent = Intent(context, SalonServicesActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Other Service Cards
            ServiceCard(
                title = "Cleaning Services",
                subtitle1 = "Residential",
                subtitle2 = "Commercial",
                imagePainter = painterResource(id = R.drawable.cleaning1),
                onClick = {
                    val intent = Intent(context, CleaningServicesActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            ServiceCard(
                title = "Maintained by Mahir",
                subtitle1 = "Residential",
                subtitle2 = "Commercial",
                imagePainter = painterResource(id = R.drawable.mbm1),
                onClick = {
                    val intent = Intent(context, HomeServicesActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}





@Composable
fun InfoCard(label: String, iconPainter: Painter, modifier: Modifier = Modifier, iconTint: Color = Color.White) {
    Card(
        modifier = modifier
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically, // Center icon and text vertically
            horizontalArrangement = Arrangement.Center // Align icon and text horizontally
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = null,
                modifier = Modifier.size(24.dp), // Set icon size

                tint = iconTint // Change the icon color here
            )
            Spacer(modifier = Modifier.width(1.dp)) // Space between icon and text
            Text(
                text = label,
                fontFamily=FontFamily.Serif,
                fontWeight=FontWeight.Bold,

                color = Color.White
            )
        }
    }
}

@Composable
fun ServiceCard(
    title: String,
    subtitle1: String,
    subtitle2: String? = null,
    isFemaleOnly: Boolean = false,
    imagePainter: Painter,
    modifier: Modifier = Modifier, // Add modifier parameter
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),

    ) {
        Row(
            modifier = Modifier

                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically // Align items in the center
        ) {
            // Column for text content on the left
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontFamily=FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    modifier=Modifier
                        .padding(start=8.dp)
                )


                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    ServiceTag(text = subtitle1)
                    if (subtitle2 != null) {
                        Spacer(modifier = Modifier.width(2.dp))
                        ServiceTag(text = subtitle2)
                    }
                }
            }

            // Image on the right
            Image(
                painter = imagePainter,
                contentDescription = "Service Image",
                modifier = Modifier
                    .size(100.dp) // Adjust size as needed
                    .padding(start = 4.dp), // Space between text and image
                contentScale = ContentScale.Crop // Crop the image if necessary
            )
        }
    }
}

@Composable
fun ServiceTag(text: String, backgroundColor: Color = Color(0xFF2196F3)) {
    Box(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 7.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 8.sp,
            fontFamily= FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White // Change text color to white for better contrast
        )
    }
}

@Composable
fun StyledServiceCard(
    title: String,
    subtitle1: String,
    subtitle2: String? = null,
    isFemaleOnly: Boolean = false,
    imagePainter: Painter,
    modifier: Modifier = Modifier, // Add modifier parameter
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(10.dp),

    ) {
        Column(
            modifier = Modifier

                .padding(8.dp), // Add some padding to the content
            horizontalAlignment = Alignment.Start // Align content to the start
        ) {
            // Text content
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily=FontFamily.Serif,
                modifier = Modifier.padding(bottom = 4.dp) // Space below the title
            )
            Row {
                ServiceTag(text = subtitle1, ) // Dark Blue
                if (subtitle2 != null) {
                    Spacer(modifier = Modifier.width(2.dp))
                    // Set female-only tag to orange if applicable
                    if (isFemaleOnly) {
                        ServiceTag(text = subtitle2)
                    } else {
                        ServiceTag(text = subtitle2)
                    }
                }
            }
        }

        // Image aligned to the right and below the text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), // Space above the image
            contentAlignment = Alignment.BottomEnd // Align image to the bottom end (right side)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Service Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 8.dp), // Optional padding to the left
                contentScale = ContentScale.Crop // Crop the image if necessary
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyApplicationTheme {
        HomeScreen()
    }
}

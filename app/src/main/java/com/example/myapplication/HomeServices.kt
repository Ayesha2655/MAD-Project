package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class HomeServicesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                HomeServicesScreen()
            }
        }
    }
}


@Composable
fun HomeServicesScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() } // Fixed navigation bar at the bottom
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            // HomeServices Row with light gray background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1)) // Light gray background for the HomeServices row
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                HomeServicesRow()
            }

            // Search Bar Section with light gray background outside and white inside
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1)) // Light gray outside background
                    .padding(vertical = 8.dp)
            ) {
                SearchBar() // SearchBar remains with a white background
            }

            // Services Grid
            Text(
                text = "All Services",
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            ServicesGrid()
        }
    }
}

@Composable
fun HomeServicesRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "Back",
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = "Home Services",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Icon(
            painter = painterResource(id = R.drawable.phonesolid),
            contentDescription = "Call",
            modifier = Modifier.size(24.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                modifier = Modifier.size(20.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Slight padding inside the light gray background
            .background(Color.White, shape = MaterialTheme.shapes.large), // White background for the search bar
        shape = MaterialTheme.shapes.large, // Rounded shape
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White, // White background inside the TextField
            focusedIndicatorColor = Color.Transparent, // Hide underline when focused
            unfocusedIndicatorColor = Color.Transparent // Hide underline when not focused
        )
    )
}

@Composable
fun ServicesGrid() {
    val services = listOf(
        Pair("AC Services", painterResource(id = R.drawable.ac)),
        Pair("Carpenter", painterResource(id = R.drawable.carpenter)),
        Pair("Electrician", painterResource(id = R.drawable.electrician)),
        Pair("Geyser", painterResource(id = R.drawable.geyser)),
        Pair("Handyman", painterResource(id = R.drawable.handyman)),
        Pair("Home Appliances", painterResource(id = R.drawable.appliancerepair)),
        Pair("Home Inspection", painterResource(id = R.drawable.house_inspection_updated)),
        Pair("Painter", painterResource(id = R.drawable.painter)),
        Pair("Pest Control", painterResource(id = R.drawable.pestcontrol)),
        Pair("Plumber", painterResource(id = R.drawable.plumber))
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxHeight(),
    ) {
        items(services) { service ->
            ServiceItem(serviceName = service.first, imagePainter = service.second)
        }
    }
}

@Composable
fun ServiceItem(serviceName: String, imagePainter: Painter) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Card containing the image
        Card(
            modifier = Modifier
                .size(100.dp) // Adjust the size of the card as needed
                .padding(2.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Image(
                painter = imagePainter,
                contentDescription = serviceName,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        // Text below the card
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = serviceName, fontSize = 12.sp,
              fontWeight=FontWeight.Bold
            )
    }
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar(
        containerColor = Color.White,  // Use containerColor for background
        contentColor = Color.Black     // Use contentColor for the content color
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BottomNavItem(icon = painterResource(id = R.drawable.housesolid), "Home")
            BottomNavItem(icon = painterResource(id = R.drawable.listsolid), "Orders")
            BottomNavItem(icon = painterResource(id = R.drawable.usersolid), "Profile")
        }
    }
}


@Composable
fun BottomNavItem(icon: Painter, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(painter = icon, contentDescription = label, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun HomeServicesPreview() {
    MyApplicationTheme {
        HomeServicesScreen()
    }
}

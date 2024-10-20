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

class SalonServicesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                SalonServicesScreen()
            }
        }
    }
}


@Composable
fun SalonServicesScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() } // Fixed navigation bar at the bottom
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            // CleaningServices Row with light gray background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1)) // Light gray background for the HomeServices row
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                SalonServicesRow()
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
            Salon_Services_Grid()
        }
    }
}

@Composable
fun SalonServicesRow() {
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
            text = "Cleaning Services",
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



@Composable
fun Salon_Services_Grid() {
    val services = listOf(
        Pair("Facial", painterResource(id = R.drawable.facial)),
        Pair("Hair Cut", painterResource(id = R.drawable.haircutlogo)),
        Pair("Hair Treatment", painterResource(id = R.drawable.hair)),
        Pair("Makeup", painterResource(id = R.drawable.makeup3)),
        Pair("Mani Pedi", painterResource(id = R.drawable.nails)),
        Pair("Massage for Women", painterResource(id = R.drawable.massage)),
        Pair("Mehndi", painterResource(id = R.drawable.mehndi)),
        Pair("Monthly Beauty Deals", painterResource(id = R.drawable.servicepackages)),
        Pair("Salon Packages", painterResource(id = R.drawable.servicepackages)),
        Pair("Waxing", painterResource(id = R.drawable.waxing)),

    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Display 2 cards per row
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp), // Space between rows
        horizontalArrangement = Arrangement.spacedBy(5.dp), // Space between columns
        modifier = Modifier.fillMaxHeight(),
    ) {
        items(services) { service ->
            Salon_Service_Item(serviceName = service.first, imagePainter = service.second)
        }
    }
}

@Composable
fun Salon_Service_Item(serviceName: String, imagePainter: Painter) {
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
                    .align(Alignment.CenterHorizontally),
                alignment=Alignment.Center
            )
        }
        // Text below the card
        Spacer(modifier = Modifier.height(8.dp))
        Text( text = serviceName,
            fontSize = 14.sp,
            fontWeight=FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth() // Fill the width to align text equally
                .wrapContentWidth(Alignment.CenterHorizontally) // Center the text
        )
    }
}






@Composable
@Preview(showBackground = true)
fun SalonServicesPreview() {
    MyApplicationTheme {
        SalonServicesScreen()
    }
}

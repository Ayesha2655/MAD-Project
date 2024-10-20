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

class CleaningServicesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                CleaningServicesScreen()
            }
        }
    }
}


@Composable
fun CleaningServicesScreen() {
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
                CleaningServicesRow()
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
            Services_Grid()
        }
    }
}

@Composable
fun CleaningServicesRow() {
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
fun Services_Grid() {
    val services = listOf(
        Pair("Solar Panel Cleaning", painterResource(id = R.drawable.solar_cleaning)),
        Pair("Sofa Cleaning", painterResource(id = R.drawable.sofa)),
        Pair("Plastic Water Tank Cleaning", painterResource(id = R.drawable.water_tank_plastic)),
        Pair("Mattress Cleaning", painterResource(id = R.drawable.mattress)),
        Pair("Deep Cleaning", painterResource(id = R.drawable.commercial_deep_cleaning)),
        Pair("Curtain Cleaning", painterResource(id = R.drawable.curtain)),
        Pair("Chair Cleaning", painterResource(id = R.drawable.chair)),
        Pair("Cement Water Tank Cleaning", painterResource(id = R.drawable.water_tank_cement)),
        Pair("Carpet Cleaning", painterResource(id = R.drawable.carpet)),
        Pair("Car Wash Services", painterResource(id = R.drawable.carservice)),
        Pair("Car Detailing", painterResource(id = R.drawable.detailing))
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxHeight(),
    ) {
        items(services) { service ->
            Service_Item(serviceName = service.first, imagePainter = service.second)
        }
    }
}

@Composable
fun Service_Item(serviceName: String, imagePainter: Painter) {
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
@Preview(showBackground = true)
fun CleaningServicesPreview() {
    MyApplicationTheme {
        CleaningServicesScreen()
    }
}

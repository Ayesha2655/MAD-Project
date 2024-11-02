package com.example.myapplication


import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class GyserServicesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                GyserMainScreen()
            }
        }
    }
}
@Composable
fun GyserMainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { GyserServicesBottomNavigationBar(navController) } // Bottom navigation with NavController
    ) { innerPadding -> // innerPadding represents content padding
        NavHost(
            navController = navController,
            startDestination = "gyser_services",
            modifier = Modifier.padding(innerPadding) // Apply padding here
        ) {
            composable("gyser_services") { GyserServicesScreen(navController) }
            composable("home_services") { HomeServicesScreen(navController) }
            composable("orders") { OrdersScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
@Composable
fun GyserServicesScreen(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)


                .background(Color.White)
        )  {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                GyserServicesRow(onBackClick = {
                    (context as? Activity)?.finish()
                })
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
                    .padding(vertical = 8.dp)
            ) {
                GyserServiceSearchBar()
            }
            // Scrollable content
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                GyserRatingCard()
                Text(
                    text = "Choose from below",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                    color=Color.Black
                )
                GyserServiceList()
            }
        }
    }
}
@Composable
fun GyserServicesRow(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onBackClick) ,// Use clickable to handle click
            tint=Color.Black
        )

        Box(
            modifier = Modifier
                .weight(1f) // Takes up the remaining space
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AC Services",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color=Color.Black
            )
        }


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GyserServiceSearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.searches),
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
fun GyserRatingCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = "Rating Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("4.6 out of 5 stars rating",
                    color=Color.Black)
            }
            Text("       Average rating of the service"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Order Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("34114 Orders done",
                    color=Color.Black)
            }
        }
    }
}

@Composable
fun GyserServiceList() {
    val services = listOf(
        GyserServiceItem("Gas Geyser Dismounting", "Discounted Price", 1500, 1200, 5.0, R.drawable.ranche),
        GyserServiceItem("Gas Geyser Installation", "Discounted Price", 3000, 2500, 4.7, R.drawable.manualgyser),
        GyserServiceItem("Gas Geyser Repairing", "Visit and Inspection Charges", 1000, 800, 4.6, R.drawable.ranche),
        GyserServiceItem("Gas Geyser Service", "Discounted Price", 2500, 2000, 4.7, R.drawable.manualgyser),
        GyserServiceItem("Instant Electric Geyser Installation", "Discounted Price", 2500, 2000, 4.3, R.drawable.automaticgyser),
        GyserServiceItem("Instant Electric Geyser Installation", "Discounted Price", 2000, 1800, 0.0, R.drawable.automaticgyser)
    )

    Column {
        services.forEach { service ->
            GyserServiceCard(service)
        }
    }
}

data class GyserServiceItem(
    val name: String,
    val description: String,
    val originalPrice: Int,
    val discountedPrice: Int,
    val rating: Double,
    val imageRes: Int
)

@Composable
fun GyserServiceCard(service: GyserServiceItem) {
    var quantity by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = service.imageRes),
                    contentDescription = service.name,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(service.name, fontWeight = FontWeight.Bold,color=Color.Black)
                    Text(service.description,color=Color.Blue)
                    Row {
                        Text(
                            text = "Rs. ${service.originalPrice}",
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Rs. ${service.discountedPrice}", color = Color.Blue)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFF4CAF50))
                        Text("${service.rating}", color = Color(0xFF4CAF50))
                    }
                }
            }
            // Positioning the QuantityButton at the bottom right
            GyserQuantityButton(
                quantity = quantity,
                onAdd = { quantity++ },
                onRemove = { if (quantity > 0) quantity-- },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp) // Add padding to the button
            )
        }
    }
}

@Composable
fun GyserQuantityButton(quantity: Int, onAdd: () -> Unit, onRemove: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        // Define the button with border style
        val buttonModifier = Modifier
            .clip(RoundedCornerShape(5.dp)) // Rounded shape for the border
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(10.dp)) // Rounded border
            .padding(8.dp) // Padding inside the border
            .clickable(onClick = onAdd) // Make the box clickable

        if (quantity == 0) {
            // Show "Add" button with a border when no quantity is added
            Box(modifier = buttonModifier) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("ADD", fontWeight = FontWeight.Bold,color=Color.Black)
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .size(20.dp) // Set size of the box
                            .background(Color.Black, shape = RoundedCornerShape(16.dp)) // Black background with rounded corners
                            .padding(4.dp), // Padding for the icon
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Service", tint = Color.White) // White icon
                    }
                }
            }
        } else {
            // Show quantity buttons when at least one service is added
            Box(modifier = buttonModifier) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onRemove) {
                        Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                    Text(quantity.toString(), fontWeight = FontWeight.Bold,color=Color.Black)
                    IconButton(onClick = onAdd) {
                        Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }
            }
        }
    }
}



@Composable
fun GyserServicesBottomNavigationBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BottomNavItem(
                icon = painterResource(id = R.drawable.housesolid),
                label = "Home",
                onClick = { navController.navigate("home_services") }
            )
            BottomNavItem(
                icon = painterResource(id = R.drawable.listsolid),
                label = "Orders",
                onClick = { navController.navigate("orders") }
            )
            BottomNavItem(
                icon = painterResource(id = R.drawable.usersolid),
                label = "Profile",
                onClick = { navController.navigate("profile") }
            )
        }
    }
}

@Composable
fun GyserServicesBottomNavItem(icon: Painter,  label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() } // Navigate on click
    ) {
        Icon(painter = icon,
            contentDescription = label,

            modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun GyserServicesPreview() {
    MyApplicationTheme {
        GyserMainScreen()
    }
}

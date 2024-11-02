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

class CarpenterServicesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                CarpenterMainScreen()
            }
        }
    }
}
@Composable
fun CarpenterMainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { CarpenterServicesBottomNavigationBar(navController) } // Bottom navigation with NavController
    ) { innerPadding -> // innerPadding represents content padding
        NavHost(
            navController = navController,
            startDestination = "car_services",
            modifier = Modifier.padding(innerPadding) // Apply padding here
        ) {
            composable("car_services") { CarpenterServicesScreen(navController) }
            composable("home_services") { HomeServicesScreen(navController) }
            composable("orders") { OrdersScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
@Composable
fun CarpenterServicesScreen(navController: NavHostController) {
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
                CarpenterServicesRow(onBackClick = {
                    (context as? Activity)?.finish()
                })
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
                    .padding(vertical = 8.dp)
            ) {
                CarpenterServiceSearchBar()
            }
            // Scrollable content
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                CarpenterRatingCard()
                Text(
                    text = "Choose from below",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                    color=Color.Black
                )
                CarpenterServiceList()
            }
        }
    }
}
@Composable
fun CarpenterServicesRow(onBackClick: () -> Unit) {
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
                text = "Carpenter Services",
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
fun CarpenterServiceSearchBar() {
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
fun CarpenterRatingCard() {
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
                Text("4.3 out of 5 stars rating",
                    color=Color.Black)
            }
            Text("       Average rating of the service"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Order Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("19831 Orders done",
                    color=Color.Black)
            }
        }
    }
}

@Composable
fun CarpenterServiceList() {
    val services = listOf(
        CarpenterServiceItem("Carpenter Work", "Visit and Inspection Charges", 500,  4.8, R.drawable.wood),
        CarpenterServiceItem("Catcher Replacement", "Per Catcher", 500, 4.3,  R.drawable.range),
        CarpenterServiceItem("Door Installation", "Starting From", 1500, 4.3,  R.drawable.doorinstallation),
        CarpenterServiceItem("Door Repairing", "Vary After Inspection", 500, 4.2,  R.drawable.lock),
        CarpenterServiceItem("Drawer Lock Installation", "Vary After Inspection", 500, 4.0,  R.drawable.key),
        CarpenterServiceItem("Drawer Repairing", "Vary After Inspection", 500, 4.6,  R.drawable.drawer) ,
        CarpenterServiceItem("Furniture Repairing", "Visit and Inspection Charges", 500, 4.6,  R.drawable.draweinstallation),
        CarpenterServiceItem("Room door Lock Installation", "Vary After Inspection", 1200, 4.8,  R.drawable.c),
        CarpenterServiceItem("Wardrobe Repairing", "visit and Inspection Charges", 500, 4.3,  R.drawable.t)
    )

    Column {
        services.forEach { service ->
            CarpenterServiceCard(service)
        }
    }
}

data class CarpenterServiceItem(
    val name: String,
    val description: String,
    val originalPrice: Int,

    val rating: Double,
    val imageRes: Int
)

@Composable
fun CarpenterServiceCard(service: CarpenterServiceItem) {
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
                            color = Color.Blue,

                        )
                        Spacer(modifier = Modifier.width(8.dp))

                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFF4CAF50))
                        Text("${service.rating}", color = Color(0xFF4CAF50))
                    }
                }
            }
            // Positioning the QuantityButton at the bottom right
            CarpenterQuantityButton(
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
fun CarpenterQuantityButton(quantity: Int, onAdd: () -> Unit, onRemove: () -> Unit, modifier: Modifier = Modifier) {
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
fun CarpenterServicesBottomNavigationBar(navController: NavHostController) {
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
fun CarpenterServicesBottomNavItem(icon: Painter,  label: String, onClick: () -> Unit) {
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
fun CarpenterServicesPreview() {
    MyApplicationTheme {
        CarpenterMainScreen()
    }
}



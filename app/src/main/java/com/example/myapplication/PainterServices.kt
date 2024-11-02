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

class PainterServicesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                PainterMainScreen()
            }
        }
    }
}
@Composable
fun PainterMainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { PainterServicesBottomNavigationBar(navController) } // Bottom navigation with NavController
    ) { innerPadding -> // innerPadding represents content padding
        NavHost(
            navController = navController,
            startDestination = "paint_services",
            modifier = Modifier.padding(innerPadding) // Apply padding here
        ) {
            composable("paint_services") { PainterServicesScreen(navController) }
            composable("home_services") { HomeServicesScreen(navController) }
            composable("orders") { OrdersScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
@Composable
fun PainterServicesScreen(navController: NavHostController) {
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
                PainterServicesRow(onBackClick = {
                    (context as? Activity)?.finish()
                })
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
                    .padding(vertical = 8.dp)
            ) {
                PainterServiceSearchBar()
            }
            // Scrollable content
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                PainterRatingCard()
                Text(
                    text = "Choose from below",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                    color=Color.Black
                )
                PainterServiceList()
            }
        }
    }
}
@Composable
fun PainterServicesRow(onBackClick: () -> Unit) {
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
                text = "Painter Services",
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
fun PainterServiceSearchBar() {
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
fun PainterRatingCard() {
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
                Text("4.2 out of 5 stars rating",
                    color=Color.Black)
            }
            Text("       Average rating of the service"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Order Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("20095 Orders done",
                    color=Color.Black)
            }
        }
    }
}

@Composable
fun PainterServiceList() {
    val services = listOf(
        PainterServiceItem("Door Polish", "Visit & Inspection Charges", 500, 4.6,  R.drawable.doorsetting),
        PainterServiceItem("Furniture Polishing", "Visit & Inspection Charges", 500, 3.8,  R.drawable.m),
        PainterServiceItem("Gray Structure Paint", "Visit & Inspection Charges", 500, 4.2,  R.drawable.homewash),
        PainterServiceItem("House Paint", "Visit & Inspection Charges", 500, 4.4,  R.drawable.room),
        PainterServiceItem("Table Polish", "Visit & Inspection Charges", 500, 4.1,  R.drawable.paint),
        PainterServiceItem("Window Paint", "Visit & Inspection Charges", 500, 4.2,  R.drawable.polish)
    )

    Column {
        services.forEach { service ->
            PainterServiceCard(service)
        }
    }
}

data class PainterServiceItem(
    val name: String,
    val description: String,
    val originalPrice: Int,

    val rating: Double,
    val imageRes: Int
)

@Composable
fun PainterServiceCard(service: PainterServiceItem) {
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
            PainterQuantityButton(
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
fun PainterQuantityButton(quantity: Int, onAdd: () -> Unit, onRemove: () -> Unit, modifier: Modifier = Modifier) {
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
fun PainterServicesBottomNavigationBar(navController: NavHostController) {
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
fun PainterServicesBottomNavItem(icon: Painter,  label: String, onClick: () -> Unit) {
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
fun PainterServicesPreview() {
    MyApplicationTheme {
        PainterMainScreen()
    }
}

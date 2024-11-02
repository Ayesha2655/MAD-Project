package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import com.example.myapplication.ui.theme.MyApplicationTheme

class HomeServicesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) } // Bottom navigation with NavController
    ) { innerPadding -> // innerPadding represents content padding
        NavHost(
            navController = navController,
            startDestination = "home_services",
            modifier = Modifier.padding(innerPadding) // Apply padding here
        ) {
            composable("home_services") { HomeServicesScreen(navController) }
            composable("orders") { OrdersScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}

@Composable
fun HomeServicesScreen(navController: NavHostController) {
    val context = LocalContext.current

    Scaffold(

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                HomeServicesRow(onBackClick = {
                    (context as? Activity)?.finish()
                })
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
                    .padding(vertical = 8.dp)
            ) {
                SearchBar()
            }

            Text(
                text = "All Services",
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            ServicesGrid(navController)
        }
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
fun ServicesGrid(navController: NavHostController) {
    val context= LocalContext.current
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
            ServiceItem(serviceName = service.first, imagePainter = service.second) {
                // Navigate to the respective activity based on the service name
                // Use Intent to start the activity based on the service name
                when (service.first) {
                    "AC Services" -> context.startActivity(Intent(context, ACServicesActivity::class.java))
                    "Carpenter" -> context.startActivity(Intent(context, CarpenterServicesActivity::class.java))
                    "Electrician" -> context.startActivity(Intent(context, ElectricianServicesActivity::class.java))
                    "Geyser" -> context.startActivity(Intent(context,GyserServicesActivity::class.java))
                    "Handyman" -> context.startActivity(Intent(context, HandymanServicesActivity::class.java))
                    "Home Appliances" -> context.startActivity(Intent(context, HomeApplianceServicesActivity::class.java))
                    "Home Inspection" -> context.startActivity(Intent(context, ACServicesActivity::class.java))
                    "Painter" -> context.startActivity(Intent(context, PainterServicesActivity::class.java))
                    "Pest Control" -> context.startActivity(Intent(context, PestControlServicesActivity::class.java))
                    "Plumber" -> context.startActivity(Intent(context, PlumberServicesActivity::class.java))
                    // Add more cases as needed
                }
            }
        }
    }
}


@Composable
fun ServiceItem(serviceName: String, imagePainter: Painter,onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
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
fun HomeServicesRow(onBackClick: () -> Unit) {
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
                .clickable(onClick = onBackClick) // Use clickable to handle click
        )

        Text(
            text = "Home Services",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily= FontFamily.Serif
        )

        Icon(
            painter = painterResource(id = R.drawable.phonesolid),
            contentDescription = "Call",
            modifier = Modifier
                .size(24.dp)

        )
    }
}

@Composable
fun OrdersScreen() {
    // State for selected tab index
    var selectedTabIndex by remember { mutableStateOf(0) }

    // List of tab titles
    val tabTitles = listOf("Active", "Previous", "Scheduled")

    Column(modifier = Modifier.fillMaxSize()) {
        // Tabs for navigation
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
            contentColor = Color.Blue,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])

                        .height(2.dp)
                        .background(Color.Blue)
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.Blue else Color.Gray,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        // Content based on selected tab
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTabIndex) {
                0 -> ActiveTabContent()
                1 -> PreviousTabContent()
                2 -> ScheduledTabContent()
            }
        }
    }
}

@Composable
fun ActiveTabContent() {
    val context=LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "No Order Yet", fontSize = 20.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "No orders icon",
            modifier = Modifier.size(80.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { val intent = Intent(context, HomeServicesActivity::class.java)
            context.startActivity(intent) }) {
            Text(text = "Book Now")
        }
    }
}

@Composable
fun PreviousTabContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No Previous Orders", fontSize = 20.sp, color = Color.Gray)
    }
}

@Composable
fun ScheduledTabContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No Scheduled Orders", fontSize = 20.sp, color = Color.Gray)
    }
}


@Composable
fun ProfileScreen() {
    // Implement your Profile screen here
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Profile Screen", fontSize = 24.sp)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
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
fun BottomNavItem(icon: Painter,  label: String, onClick: () -> Unit) {
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
fun HomeServicesPreview() {
    MyApplicationTheme {
        MainScreen()
    }
}

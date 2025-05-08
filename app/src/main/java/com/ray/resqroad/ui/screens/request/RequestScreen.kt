package com.ray.resqroad.ui.screens.request


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ray.resqroad.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.resqroad.model.User
import com.ray.resqroad.navigation.ROUT_HOME
import com.ray.resqroad.navigation.ROUT_USERDASHBOARD
import com.ray.resqroad.navigation.ROUT_USERHISTORY
import com.ray.resqroad.navigation.ROUT_USERPROFILE
import com.ray.resqroad.ui.theme.mainBlue
import com.ray.resqroad.ui.theme.newOrange
import com.ray.resqroad.ui.theme.trialBlue
import com.ray.resqroad.ui.theme.txtCol
import com.ray.resqroad.ui.theme.whiteBackgr
import com.ray.resqroad.model.ServiceCard
import com.ray.resqroad.navigation.ROUT_ADD_PRODUCT
import com.ray.resqroad.navigation.ROUT_REQUESTDETAILS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen(navController: NavController){


    var selectedService by remember { mutableStateOf<String?>(null) }
    var selectedIndex by remember { mutableStateOf(0) }

    val serviceOptions = listOf(
        ServiceCard("Flat Tire", R.drawable.flat_tire),
        ServiceCard("Out of Fuel", R.drawable.fuel),
        ServiceCard("Battery Jumpstart", R.drawable.car_battery),
        ServiceCard("Locked Out", R.drawable.locked_car),
        ServiceCard("Engine Trouble", R.drawable.car_engine),
        ServiceCard("Tow Required", R.drawable.towtruck)
    )


    Scaffold(
        //TopBar
        topBar = {
            TopAppBar(
                title = { Text("Request Assistance") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate( ROUT_USERDASHBOARD) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainBlue,
                    titleContentColor = whiteBackgr,
                    navigationIconContentColor = whiteBackgr
                )
            )
        },

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = whiteBackgr
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                        navController.navigate(ROUT_USERDASHBOARD)
                    }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "") },
                    label = { Text("History") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        navController.navigate(ROUT_USERHISTORY)
                    }
                )

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        navController.navigate(ROUT_USERPROFILE)
                    }
                )


            }
        },

        //FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = newOrange
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },

        //Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {


                //Main Contents of the page
                Column (
                    modifier = Modifier.fillMaxSize()
                        .background(trialBlue)
                    , horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){

                    Text(text = "What seems to be the problem?", fontSize = 35.sp, color = whiteBackgr)

                    Spacer(modifier = Modifier.height(8.dp))


                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(serviceOptions) { service ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedService = service.name },
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selectedService == service.name) Color(0xFFBBDEFB) else Color.White
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(service.imageResId),
                                        contentDescription = service.name,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = service.name,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }




                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFFFF4500), Color(0xFFFFA500))
                                ),
                                shape = MaterialTheme.shapes.medium
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {

                                selectedService?.let {
                                    navController.navigate(ROUT_ADD_PRODUCT)}

                            },
                            enabled = selectedService != null,

                            modifier = Modifier.fillMaxSize() .padding(start = 20.dp, end = 20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Text("Request Help Now", color = Color.White, fontSize = 20.sp,fontFamily = FontFamily.SansSerif)
                        }
                    }










                }











            }
        }
    )

    //End of scaffold






}


@Preview(showBackground = true)
@Composable
fun RequestScreenPreview(){

    RequestScreen(rememberNavController())
}
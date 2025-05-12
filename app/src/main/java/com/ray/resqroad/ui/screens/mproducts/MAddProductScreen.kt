package com.ray.resqroad.ui.screens.mproducts

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ray.resqroad.R
import com.ray.resqroad.navigation.ROUT_HOME
import com.ray.resqroad.navigation.ROUT_MECHANICDASHBOARD
import com.ray.resqroad.navigation.ROUT_MECH_ADD_PRODUCT
import com.ray.resqroad.navigation.ROUT_MECHPRODUCT_LIST
import com.ray.resqroad.ui.theme.mainBlue
import com.ray.resqroad.ui.theme.whiteBackgr
import com.ray.resqroad.viewmodel.MProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MAddProductScreen(navController: NavController, viewModel: MProductViewModel) {
    var name by remember { mutableStateOf("") }
    var service by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var showMenu by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Image Picker Launcher
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            Log.d("ImagePicker", "Selected image URI: $it")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Get Listed as a Roadside Helper", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = whiteBackgr) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(mainBlue),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = whiteBackgr)
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = whiteBackgr)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Product List") },
                            onClick = {
                                navController.navigate(ROUT_MECHPRODUCT_LIST)
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add Product") },
                            onClick = {
                                navController.navigate(ROUT_MECH_ADD_PRODUCT)
                                showMenu = false
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar20(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Product Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Product Price
                OutlinedTextField(
                    value = service,
                    onValueChange = { service = it },
                    label = { Text("Service Offered") },
                    modifier = Modifier.fillMaxWidth()
                )



                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Phone Number
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                    )


                Spacer(modifier = Modifier.height(16.dp))

                // Image Picker Box
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                        .clickable { imagePicker.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUri),
                            contentDescription = "Selected Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(painter = painterResource(R.drawable.image), contentDescription = "Pick Image")
                            Text("Tap to pick image", color = Color.DarkGray)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Add Product Button

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
                ){

                Button(
                    onClick = {
                            imageUri?.toString()?.let { viewModel.addMechProduct(name,service,location,phone,it) }
                            navController.navigate(ROUT_MECHPRODUCT_LIST)
                              },
                    modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)

                ) {
                    Text("Get Listed",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                }


                }

            }
        }
    )
}

// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBar20(navController: NavController) {
    NavigationBar(
        containerColor = whiteBackgr,
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_MECHANICDASHBOARD) },
            icon = { Icon(Icons.Default.Home, contentDescription = "") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_MECHPRODUCT_LIST) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "") },
            label = { Text("History") }
        )


    }
}
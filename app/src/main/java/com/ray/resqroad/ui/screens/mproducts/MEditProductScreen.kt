package com.ray.resqroad.ui.screens.mproducts

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ray.resqroad.navigation.ROUT_HOME
import com.ray.resqroad.navigation.ROUT_MECHANICDASHBOARD
import com.ray.resqroad.navigation.ROUT_MECH_ADD_PRODUCT
import com.ray.resqroad.navigation.ROUT_MECHPRODUCT_LIST
import com.ray.resqroad.navigation.ROUT_PRODUCT_LIST
import com.ray.resqroad.navigation.ROUT_PRODUCT_LIST_CLONE
import com.ray.resqroad.ui.theme.mainBlue
import com.ray.resqroad.ui.theme.whiteBackgr
import com.ray.resqroad.viewmodel.MProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MEditProductScreen(productId: Int?, navController: NavController, viewModel: MProductViewModel) {
    val context = LocalContext.current
    val mproductList by viewModel.allMechProducts.observeAsState(emptyList())

    // Ensure productId is valid
    val mproduct = remember(mproductList) { mproductList.find { it.id == productId } }

    // Track state variables only when product is found
    var name by remember { mutableStateOf(mproduct?.name ?: "") }
    var service by  remember { mutableStateOf(mproduct?.service?.toString() ?: "") }
    var location by  remember { mutableStateOf(mproduct?.location?.toString() ?: "") }
    var phone by  remember { mutableStateOf(mproduct?.phone?.toString() ?: "") }
    var imagePath by remember { mutableStateOf(mproduct?.imagePath ?: "") }

    var showMenu by remember { mutableStateOf(false) }

    // Image picker
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imagePath = it.toString()
            Toast.makeText(context, "Image Selected!", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirm Details",fontSize = 20.sp, fontWeight = FontWeight.Bold, color = whiteBackgr) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(mainBlue),

                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back",tint = whiteBackgr)
                    }
                },

            )
        },
        bottomBar = { BottomNavigationBar2(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (mproduct != null) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = service,
                    onValueChange = { service = it },
                    label = { Text("Service Offered") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Display Image
                Image(
                    painter = rememberAsyncImagePainter(model = Uri.parse(imagePath)),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                )

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
                )
                {

                Button(
                    onClick = { imagePicker.launch("image/*") },
                    modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                )
                {
                    Text("Change Image",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                }
                }


                Spacer(modifier = Modifier.height(16.dp))


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
                )
                {
                Button(
                    onClick = {
                        val updatedPhone = phone.toString()
                        if (updatedPhone != null) {
                            viewModel.updateMechProduct(mproduct.copy(name = name, service = service,location = location ,phone = updatedPhone, imagePath = imagePath))
                            Toast.makeText(context, "Details Submitted!", Toast.LENGTH_SHORT).show()
                            navController.navigate(ROUT_PRODUCT_LIST_CLONE)
                        } else {
                            Toast.makeText(context, "Invalid Phone Number entered!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("Submit Details",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                }
                }

            } else {
                Text(text = "Details not found", color = MaterialTheme.colorScheme.error)
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    }
}

// Bottom Navigation Bar
@Composable
fun BottomNavigationBar2(navController: NavController) {
    NavigationBar(
        containerColor = whiteBackgr
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_MECHANICDASHBOARD) },
            icon = { Icon(Icons.Default.Home, contentDescription = "") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_HOME) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "") },
            label = { Text("History") }
        )
    }
}
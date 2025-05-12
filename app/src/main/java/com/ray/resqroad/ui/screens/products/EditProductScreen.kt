package com.ray.resqroad.ui.screens.products

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.ray.resqroad.navigation.ROUT_ADD_PRODUCT
import com.ray.resqroad.navigation.ROUT_HOME
import com.ray.resqroad.navigation.ROUT_MECHPRODUCT_LIST
import com.ray.resqroad.navigation.ROUT_MECHPRODUCT_LIST_CLONE
import com.ray.resqroad.navigation.ROUT_PRODUCT_LIST
import com.ray.resqroad.navigation.ROUT_USERDASHBOARD
import com.ray.resqroad.ui.theme.mainBlue
import com.ray.resqroad.ui.theme.whiteBackgr
import com.ray.resqroad.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(productId: Int?, navController: NavController, viewModel: ProductViewModel) {
    val context = LocalContext.current
    val productList by viewModel.allProducts.observeAsState(emptyList())

    // Ensure productId is valid
    val product = remember(productList) { productList.find { it.id == productId } }

    // Track state variables only when product is found
    var carType by remember { mutableStateOf(product?.carType ?: "") }
    var numberPlate by  remember { mutableStateOf(product?.numberPlate?.toString() ?: "") }
    var description by  remember { mutableStateOf(product?.description?.toString() ?: "") }
    var location by  remember { mutableStateOf(product?.location?.toString() ?: "") }
    var phone by  remember { mutableStateOf(product?.phone?.toString() ?: "") }
    var imagePath by remember { mutableStateOf(product?.imagePath ?: "") }
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
                title = { Text("Confirm Request Details",fontSize = 20.sp, fontWeight = FontWeight.Bold, color = whiteBackgr) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = mainBlue,),


                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = whiteBackgr)
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (product != null) {
                OutlinedTextField(
                    value = carType,
                    onValueChange = { carType = it },
                    label = { Text("Car Type") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = numberPlate,
                    onValueChange = { numberPlate = it },
                    label = { Text("Number Plate") },
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

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
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

                ) {
                    Text("Change Image",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White)
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
                        val updatedPhoneNumber = phone.toString()
                        if (updatedPhoneNumber != null) {
                            viewModel.updateProduct(product.copy(carType = carType, numberPlate = numberPlate, location = location ,description = description, phone = updatedPhoneNumber, imagePath = imagePath))
                            Toast.makeText(context, "Details Submitted!", Toast.LENGTH_SHORT).show()
                            navController.navigate(ROUT_MECHPRODUCT_LIST_CLONE)
                        } else {
                            Toast.makeText(context, "Invalid Phone number entered!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)

                ) {
                    Text("Confirm Details",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White)
                }
                }
            }
            else {
                Text(text = "Product not found", color = MaterialTheme.colorScheme.error)
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
            onClick = { navController.navigate(ROUT_USERDASHBOARD) },
            icon = { Icon(Icons.Default.Home, contentDescription = "") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(ROUT_PRODUCT_LIST) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "") },
            label = { Text("History") }
        )
    }
}
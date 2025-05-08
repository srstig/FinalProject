package com.ray.resqroad.ui.screens.dashboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.resqroad.R
import com.ray.resqroad.navigation.ROUT_HOME
import com.ray.resqroad.navigation.ROUT_REQUEST
import com.ray.resqroad.navigation.ROUT_USERPROFILE
import com.ray.resqroad.ui.theme.mainBlue
import com.ray.resqroad.ui.theme.newOrange
import com.ray.resqroad.ui.theme.trialBlue
import com.ray.resqroad.ui.theme.txtCol
import com.ray.resqroad.ui.theme.whiteBackgr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboardScreen(navController: NavController){


    Column(modifier = Modifier
        .fillMaxSize()
        .background(trialBlue)
        .verticalScroll(rememberScrollState())
    ) {

        Box() {
            //Card
            Card(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp),
                colors = CardDefaults.cardColors(newOrange)
            ) {

                TopAppBar(
                    title = { Text(text = "Dashboard Section") },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(ROUT_USERPROFILE)
                        }) {
                            Icon(imageVector = Icons.Default.Person, contentDescription = "")
                        }
                        IconButton(onClick = {
                            navController.navigate(ROUT_HOME)
                        }) {
                            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
                        }

                    }


                    ,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = mainBlue,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )

                )

                Text(text = "ResQRoad",Modifier.padding(20.dp), fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)



            }
            //End of Card

            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(180.dp)
                    .align(alignment = Alignment.BottomCenter)
                    .offset(y = 90.dp)
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(R.drawable.towtruck),
                        contentDescription = "Car",
                        modifier = Modifier.height(150.dp).width(200.dp),
                    )

                    Text(text = "Turning Breakdowns into Breakthroughs", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold)




                }






            }


        }
// End of Box

        Spacer(modifier = Modifier.height(100.dp))

//Row 1
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            //Card 1
            Card (
                modifier = Modifier
                    .width(150.dp)
                    .height(180.dp)
                    .clickable {navController.navigate(ROUT_REQUEST)},
                colors = CardDefaults.cardColors(newOrange)

            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(R.drawable.brokencar),
                        contentDescription = "",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Request Assistance", fontSize = 25.sp, color = Color.White, modifier = Modifier.padding(start = 5.dp, end = (5.dp)))

                }

            }

            //End of Card 1

            Spacer(modifier = Modifier.width(20.dp))



            //Card 2
            Card (
                modifier = Modifier
                    .width(150.dp)
                    .height(180.dp)
                    .clickable {navController.navigate(ROUT_HOME)},
                colors = CardDefaults.cardColors(whiteBackgr)

            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(R.drawable.clipboard),
                        contentDescription = "",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Service History", fontSize = 25.sp, color = txtCol, modifier = Modifier.padding(start = 5.dp, end = (5.dp)))

                }

            }

            //End of Card 2


        }
//End of Row1

        Spacer(modifier = Modifier.height(20.dp))


        //Row 2
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ){
            //Card 1
            Card (
                modifier = Modifier
                    .width(150.dp)
                    .height(180.dp)
                    .clickable {navController.navigate(ROUT_HOME)},
                colors = CardDefaults.cardColors(whiteBackgr)

            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(R.drawable.location),
                        contentDescription = "",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "My Location", fontSize = 25.sp, color = txtCol, modifier = Modifier.padding(start = 5.dp, end = (5.dp)))

                }

            }

            //End of Card 1

            Spacer(modifier = Modifier.width(20.dp))



            //Card 2
            Card (
                modifier = Modifier
                    .width(150.dp)
                    .height(180.dp)
                    .clickable {navController.navigate(ROUT_REQUEST)},
                colors = CardDefaults.cardColors(txtCol)

            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(R.drawable.info),
                        contentDescription = "",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Services Offered", fontSize = 25.sp, color = Color.White, modifier = Modifier.padding(start = 5.dp, end = (5.dp)))

                }

            }

            //End of Card 2


        }
//End of Row 2

    }
}






@Preview(showBackground = true)
@Composable
fun UserDashboardScreenPreview(){

    UserDashboardScreen(rememberNavController())
}
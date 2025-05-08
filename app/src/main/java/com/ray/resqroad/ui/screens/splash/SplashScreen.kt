package com.ray.resqroad.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ray.resqroad.R
import com.ray.resqroad.navigation.ROUT_USERDASHBOARD
import com.ray.resqroad.navigation.ROUT_HOME
import com.ray.resqroad.navigation.ROUT_LOGIN
import com.ray.resqroad.ui.theme.mainBlue
import com.ray.resqroad.ui.theme.newOrange
import com.ray.resqroad.ui.theme.trialBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController){
    //Navigation

    var coroutine = rememberCoroutineScope()

    coroutine.launch {
        delay(2000)
        navController.navigate(ROUT_LOGIN)
    }


    //End of Navigation

    Column (
        modifier = Modifier.fillMaxSize().background(trialBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Image(
            painter = painterResource(R.drawable.towtruck),
            contentDescription = "",
            modifier = Modifier.size(250.dp)
        )

        Text(text = "ResQRoad", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, color = newOrange)

        Text(text = "Turning Breakdowns Into Breakthroughs", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic, color = Color.White)

    }


}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){

   SplashScreen(rememberNavController())
}
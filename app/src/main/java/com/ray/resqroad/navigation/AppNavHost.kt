package com.ray.resqroad.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ray.resqroad.data.UserDatabase
import com.ray.resqroad.repository.UserRepository
import com.ray.resqroad.ui.screens.auth.LoginScreen
import com.ray.resqroad.ui.screens.auth.RegisterScreen
import com.ray.resqroad.ui.screens.clones.EditProductScreenClone
import com.ray.resqroad.ui.screens.clones.MEditProductScreenClone
import com.ray.resqroad.ui.screens.clones.MProductListScreenClone
import com.ray.resqroad.ui.screens.clones.ProductListScreenClone
import com.ray.resqroad.ui.screens.dashboard.MechanicDashboardScreen
import com.ray.resqroad.ui.screens.dashboard.UserDashboardScreen
import com.ray.resqroad.ui.screens.home.HomeScreen
import com.ray.resqroad.ui.screens.mproducts.MAddProductScreen
import com.ray.resqroad.ui.screens.mproducts.MEditProductScreen
import com.ray.resqroad.ui.screens.mproducts.MProductListScreen
import com.ray.resqroad.ui.screens.products.AddProductScreen
import com.ray.resqroad.ui.screens.products.EditProductScreen
import com.ray.resqroad.ui.screens.products.ProductListScreen
import com.ray.resqroad.ui.screens.request.RequestScreen
import com.ray.resqroad.ui.screens.request.ServiceScreen
import com.ray.resqroad.ui.screens.splash.SplashScreen
import com.ray.resqroad.viewmodel.AuthViewModel
import com.ray.resqroad.viewmodel.MProductViewModel
import com.ray.resqroad.viewmodel.ProductViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH,
    productViewModel: ProductViewModel = viewModel(),
    mproductViewModel: MProductViewModel = viewModel(),


    ) {
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(ROUT_SPLASH) {
            SplashScreen (navController)
        }

        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        composable(ROUT_USERDASHBOARD) {
            UserDashboardScreen(navController)
        }

        composable(ROUT_REQUEST) {
            RequestScreen (navController)
        }




        composable(ROUT_MECHANICDASHBOARD) {
            MechanicDashboardScreen (navController)
        }

        composable(ROUT_MECHANICSERVICE) {
            ServiceScreen (navController)
        }


        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }


        // PRODUCTS
        composable(ROUT_ADD_PRODUCT) {
            AddProductScreen(navController, productViewModel)
        }

        composable(ROUT_PRODUCT_LIST) {
            ProductListScreen(navController, productViewModel)
        }

        composable(
            route = ROUT_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(productId, navController, productViewModel)
            }
        }


        // Mechanics
        composable(ROUT_MECH_ADD_PRODUCT) {
            MAddProductScreen(navController, mproductViewModel)
        }

        composable(ROUT_MECHPRODUCT_LIST) {
            MProductListScreen(navController, mproductViewModel)
        }

        composable(
            route = ROUT_MECH_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                MEditProductScreen(productId, navController, mproductViewModel)
            }
        }



        //Clone Screens for History

        composable(ROUT_MECHPRODUCT_LIST_CLONE) {
            MProductListScreenClone(navController, mproductViewModel)
        }

        composable(
            route = ROUT_MECH_EDIT_PRODUCT_CLONE,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                MEditProductScreenClone(productId, navController, mproductViewModel)
            }
        }



        composable(ROUT_PRODUCT_LIST_CLONE) {
            ProductListScreenClone(navController, productViewModel)
        }

        composable(
            route = ROUT_EDIT_PRODUCT_CLONE,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreenClone(productId, navController, productViewModel)
            }
        }





    }
}
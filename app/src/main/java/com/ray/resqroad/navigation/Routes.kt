package com.ray.resqroad.navigation

const val ROUT_USERDASHBOARD = "userdashboard"
const val ROUT_SPLASH = "splash"
const val ROUT_HOME = "home"
const val ROUT_REQUEST = "request"


const val ROUT_MECHANICDASHBOARD = "mechanicdashboard"
const val ROUT_MECHANICSERVICE = "mechanicservice"



const val ROUT_USERHISTORY = "userhistory"
const val ROUT_MECHANICHISTORY = "mechanichistory"




//Authentication
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"


//Products

const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"

// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"


//Products

const val ROUT_MECH_ADD_PRODUCT = "mechadd_product"
const val ROUT_MECHPRODUCT_LIST = "mechproduct_list"
const val ROUT_MECH_EDIT_PRODUCT = "medit_product/{productId}"

// ✅ Helper function for navigation
fun meditProductRoute(productId: Int) = "medit_product/$productId"



//Clone Screens
const val ROUT_PRODUCT_LIST_CLONE = "product_list_clone"
const val ROUT_EDIT_PRODUCT_CLONE = "edit_product_clone/{productId}"

const val ROUT_MECHPRODUCT_LIST_CLONE = "mechproduct_list_clone"
const val ROUT_MECH_EDIT_PRODUCT_CLONE = "medit_product_clone/{productId}"

// ✅ Helper function for navigation
fun meditProductRouteclone(productId: Int) = "medit_product_clone/$productId"

fun editProductRouteclone(productId: Int) = "edit_product_clone/$productId"


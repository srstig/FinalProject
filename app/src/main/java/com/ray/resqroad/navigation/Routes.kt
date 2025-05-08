package com.ray.resqroad.navigation

const val ROUT_USERDASHBOARD = "userdashboard"
const val ROUT_SPLASH = "splash"
const val ROUT_HOME = "home"
const val ROUT_REQUEST = "request"
const val ROUT_REQUESTDETAILS = "requestdetails"
const val ROUT_CHOOSEMECHANIC = "choosemechanic"
const val ROUT_USERHISTORY = "userhistory"
const val ROUT_USERPROFILE = "userprofle"


const val ROUT_MECHANICREGISTRATION = "mechanicregistration"
const val ROUT_MECHANICDASHBOARD = "mechanicdashboard"
const val ROUT_MECHANICSERVICE = "mechanicservice"
const val ROUT_MECHANICRESPONSE = "mechanicresponse"
const val ROUT_MECHANICPROFILE = "mechanicprofle"
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
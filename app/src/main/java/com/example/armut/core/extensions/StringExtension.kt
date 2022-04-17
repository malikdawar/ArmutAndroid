package com.example.armut.core.extensions

import com.example.armut.App
import com.example.armut.R

/**
 * Extension function to noNetworkErrorMessage
 * @author Dawar Malik.
 */
fun noNetworkErrorMessage() =
    App.getAppContext().getString(R.string.message_no_network_connected_str)

/**
 * Extension function to somethingWentWrong
 * @author Dawar Malik.
 */
fun somethingWentWrong() = App.getAppContext().getString(R.string.message_something_went_wrong_str)

package com.brogrammer.rewardme.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(date: Date?): String {
    return if (date != null) {
        val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        formatter.format(date)
    } else {
        "Unknown Date"
    }
}

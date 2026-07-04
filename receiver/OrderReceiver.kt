package com.foodmate.uas.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.foodmate.uas.util.NotificationHelper

class OrderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "com.foodmate.uas.ORDER_SUCCESS") {
            val total = intent.getIntExtra("total_price", 0)
            NotificationHelper.showOrderSuccess(context, total)
        }
    }
}

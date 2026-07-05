package com.example.foodmate_uas.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.foodmate_uas.util.NotificationHelper

class OrderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "com.example.foodmate_uas.ORDER_SUCCESS") {
            val total = intent.getIntExtra("total_price", 0)
            NotificationHelper.showOrderSuccess(context, total)
        }
    }
}

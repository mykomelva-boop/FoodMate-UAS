package com.foodmate.uas.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class OrderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val totalPrice = intent?.getIntExtra("total_price", 0) ?: 0
        Log.d("OrderReceiver", "Order berhasil! Total: Rp$totalPrice")
    }
}

package com.foodmate.uas.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.foodmate.uas.R

object NotificationHelper {
    private const val CHANNEL_ID = "foodmate_order_channel"

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Notifikasi Pesanan", NotificationManager.IMPORTANCE_DEFAULT)
            context.getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        }
    }

    fun showOrderSuccess(context: Context, totalPrice: Int) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_send)
            .setContentTitle("Pesanan berhasil dibuat")
            .setContentText("Total belanja: Rp$totalPrice. Terima kasih sudah pesan di FoodMate!")
            .setAutoCancel(true)
        try {
            NotificationManagerCompat.from(context).notify(1001, builder.build())
        } catch (e: SecurityException) { }
    }
}

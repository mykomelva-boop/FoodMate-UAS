package com.foodmate.uas.network

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.foodmate.uas.model.Promo
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.Executors

object PromoService {
    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())

    interface Callback {
        fun onSuccess(promos: List<Promo>)
        fun onError(message: String)
    }

    fun fetchPromos(context: Context, callback: Callback) {
        executor.execute {
            try {
                Thread.sleep(800) // delay buatan, biar mirip network call
                val jsonText = context.assets.open("promo.json")
                    .let { BufferedReader(InputStreamReader(it)).readText() }

                val jsonArray = JSONArray(jsonText)
                val promos = mutableListOf<Promo>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    promos.add(Promo(obj.getString("title"), obj.getString("description"), obj.getString("discount")))
                }
                mainHandler.post { callback.onSuccess(promos) }
            } catch (e: Exception) {
                mainHandler.post { callback.onError(e.message ?: "Gagal memuat promo") }
            }
        }
    }
}

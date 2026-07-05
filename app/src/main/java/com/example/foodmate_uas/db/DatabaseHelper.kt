package com.example.foodmate_uas.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.foodmate_uas.model.Favorite
import com.example.foodmate_uas.model.Order

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "foodmate.db"
        private const val DB_VERSION = 1
        const val TABLE_ORDERS = "orders"
        const val TABLE_FAVORITES = "favorites"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE $TABLE_ORDERS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                items_summary TEXT NOT NULL,
                total_price INTEGER NOT NULL,
                order_date TEXT NOT NULL
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE $TABLE_FAVORITES (
                food_id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                price INTEGER NOT NULL,
                color_hex TEXT NOT NULL
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FAVORITES")
        onCreate(db)
    }

    fun insertOrder(itemsSummary: String, totalPrice: Int, date: String): Long {
        val values = ContentValues().apply {
            put("items_summary", itemsSummary)
            put("total_price", totalPrice)
            put("order_date", date)
        }
        return writableDatabase.insert(TABLE_ORDERS, null, values)
    }

    fun getAllOrders(): List<Order> {
        val list = mutableListOf<Order>()
        readableDatabase.query(TABLE_ORDERS, null, null, null, null, null, "id DESC").use {
            while (it.moveToNext()) {
                list.add(Order(
                    id = it.getInt(it.getColumnIndexOrThrow("id")),
                    itemsSummary = it.getString(it.getColumnIndexOrThrow("items_summary")),
                    totalPrice = it.getInt(it.getColumnIndexOrThrow("total_price")),
                    date = it.getString(it.getColumnIndexOrThrow("order_date"))
                ))
            }
        }
        return list
    }

    fun isFavorite(foodId: Int): Boolean {
        val cursor = readableDatabase.query(TABLE_FAVORITES, arrayOf("food_id"), "food_id=?",
            arrayOf(foodId.toString()), null, null, null)
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun addFavorite(favorite: Favorite) {
        val values = ContentValues().apply {
            put("food_id", favorite.foodId)
            put("name", favorite.name)
            put("price", favorite.price)
            put("color_hex", favorite.colorHex)
        }
        writableDatabase.insertWithOnConflict(TABLE_FAVORITES, null, values, SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun removeFavorite(foodId: Int) {
        writableDatabase.delete(TABLE_FAVORITES, "food_id=?", arrayOf(foodId.toString()))
    }

    fun getAllFavorites(): List<Favorite> {
        val list = mutableListOf<Favorite>()
        readableDatabase.query(TABLE_FAVORITES, null, null, null, null, null, "food_id DESC").use {
            while (it.moveToNext()) {
                list.add(Favorite(
                    foodId = it.getInt(it.getColumnIndexOrThrow("food_id")),
                    name = it.getString(it.getColumnIndexOrThrow("name")),
                    price = it.getInt(it.getColumnIndexOrThrow("price")),
                    colorHex = it.getString(it.getColumnIndexOrThrow("color_hex"))
                ))
            }
        }
        return list
    }
}
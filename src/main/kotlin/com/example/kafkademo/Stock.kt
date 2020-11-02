package com.example.kafkademo

object Stock {
    // ideally we would fetch stock from a database
    private var stock: Map<String, Int> = mapOf("Apple" to 10, "Orange" to 10)

    fun getStock(): Map<String, Int> {
        return stock
    }

    // for JUnit tests, resets stock to be 10 of each fruit
    fun resetStock() {
        setStock(mapOf("Apple" to 10, "Orange" to 10))
    }

    fun setStock(newStock: Map<String, Int>) {
        this.stock = newStock
    }

    fun updateStock(order: Order) {
        val order = order.getOrder()
        val currentStock = this.getStock()
        val newStock = mutableMapOf<String, Int>()
        for ((item, quantity) in order) {
            newStock[item] = currentStock[item]!! - quantity
        }
        this.setStock(newStock)
    }

    fun getOutOfStockItems(order: Order): List<String> {
        val currentStock = this.getStock()
        val order = order.getOrder()
        val outOfStockItems = mutableListOf<String>()
        for ((item, quantity) in currentStock) {
            if (order[item]!! > quantity) {
                outOfStockItems.add(item)
            }
        }
        return outOfStockItems
    }
}
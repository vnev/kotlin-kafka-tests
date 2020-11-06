package com.example.kafkademo

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("What's your order?")
    val orderString = readLine() ?: exitProcess(0)
    val orderList = orderString.split(" ")

    val order = Order(orderList)
    // setting the stock
    val outOfStockItems = Stock.getOutOfStockItems(order)
    if (outOfStockItems.isEmpty()) {
        // all items in stock, update stock and proceed to get total
        Stock.updateStock(order)
        order.applyDiscount()
        val total = order.getOrderTotal()

        val current = LocalDateTime.now().plusHours(2)
        val formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm")
        val formatted = current.format(formatter)

        println("All items in stock! Your total is \$$total. Estimated delivery by $formatted")
    } else {
        println("We're sorry. Some items were out of stock: $outOfStockItems")
    }
}


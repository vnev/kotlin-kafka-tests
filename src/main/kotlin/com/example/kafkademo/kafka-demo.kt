package com.example.kafkademo

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("What's your order?")
    val order = readLine() ?: exitProcess(0)
    val orderList = order.split(" ")
    val total = getOrderTotal(getOrderPriceMap(applyDiscount(getOrderQuantityMap(orderList))))
    println("Total order cost: \$$total")
}

fun getPrices(): Map<String, Double> {
    return mapOf("Apple" to 0.60, "Orange" to 0.25)
}

fun getOrderQuantityMap(fruits: List<String>): Map<String, Int> {
    val orderQuantityMap = mutableMapOf<String, Int>()
    for (fruit in fruits)
        if (fruit == "Apple" || fruit == "Orange")
            orderQuantityMap[fruit] = orderQuantityMap.getOrDefault(fruit, 0) + 1
    return orderQuantityMap
}

fun getOrderPriceMap(orderQuantityMap: Map<String, Int>): Map<String, Double> {
    val pricesMap = getPrices()
    val orderPriceMap = mutableMapOf<String, Double>()
    for ((k, v) in orderQuantityMap)
        orderPriceMap[k] = pricesMap[k]!!.times(v)
    return orderPriceMap
}

fun getOrderTotal(orderPriceMap: Map<String, Double>): Double {
    var total = 0.0
    for ((_, v) in orderPriceMap)
        total += v
    return total
}

fun applyDiscount(orderQuantityMap: Map<String, Int>): Map<String, Int> {
    val newOrderQuantityMap = mutableMapOf<String, Int>()
    for ((k, v) in orderQuantityMap) {
        if (k == "Apple")
            newOrderQuantityMap[k] = if (v % 2 == 0) (v / 2) else ((v - 1) / 2) + 1
        if (k == "Orange")
            newOrderQuantityMap[k] = if (v % 3 == 0) ((v * 2) / 3) else ((((v-(v % 3)) * 2) / 3)+(v % 3))
    }
    return newOrderQuantityMap
}

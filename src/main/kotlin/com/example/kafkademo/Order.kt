package com.example.kafkademo

class Order {
    // order consists of a map of the item being ordered to its quantity
    // Example: <Apple, 12>, is an order of 12 apples, etc.
    private var order: Map<String, Int> = mapOf<String, Int>()

    fun getOrder(): Map<String, Int> {
        return order
    }

    fun setOrder(newOrder: Map<String, Int>) {
        this.order = newOrder
    }

    constructor(orderString: List<String>) {
        val order = mutableMapOf<String, Int>()
        for (item in orderString) {
            if (item == "Apple" || item == "Orange") {
                order[item] = order.getOrDefault(item, 0) + 1
            }
        }
        this.setOrder(order)
    }

    fun getOrderTotal(): Double {
        val prices = getPrices()
        var price = 0.0
        val order = this.getOrder()
        for ((item, quantity) in order) {
            price += prices[item]!!.times(quantity)
        }
        return price
    }

    fun applyDiscount() {
        val newOrder = mutableMapOf<String, Int>()
        for ((item, quantity) in this.getOrder()) {
            if (item == "Apple")
                newOrder[item] = if (quantity % 2 == 0) (quantity / 2) else ((quantity - 1) / 2) + 1
            if (item == "Orange")
                newOrder[item] = if (quantity % 3 == 0) ((quantity * 2) / 3) else
                                ((((quantity-(quantity % 3)) * 2) / 3)+(quantity % 3))
        }
        this.setOrder(newOrder)
    }

    companion object {
        // ideally would fetch this from a database
        fun getPrices(): Map<String, Double> {
            return mapOf("Apple" to 0.60, "Orange" to 0.25)
        }
    }


}
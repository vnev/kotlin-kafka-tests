package com.example.kafkademo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class KafkaDemoApplicationTests {
	@Test
	fun `test getPrices returns the right price map`() {
		Assertions.assertEquals(Order.getPrices(), mapOf("Apple" to 0.60, "Orange" to 0.25))
	}

	@Test
	fun `test getTotal returns the right value pre-discount`() {
		val orderList = listOf("Apple", "Apple", "Orange", "Apple")
		val order = Order(orderList)
		Assertions.assertEquals(order.getOrderTotal(), 2.05)
	}

	@Test
	fun `test getTotal returns the right value post-discount`() {
		val orderList = listOf("Apple", "Apple", "Orange", "Apple")
		val order = Order(orderList)
		order.applyDiscount()
		Assertions.assertEquals(order.getOrderTotal(), 1.45)
	}

	@Test
	fun `test updateStock sets the stock to the right number`() {
		val orderList = listOf("Apple", "Apple", "Orange", "Apple")
		val order = Order(orderList)
		Stock.updateStock(order)
		Assertions.assertEquals(Stock.getStock(), mapOf("Apple" to 7, "Orange" to 9))
		Stock.resetStock()
	}

	@Test
	fun `test getOutOfStockItems`() {
		val orderList = listOf("Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple", "Apple",
		"Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange")
		val order = Order(orderList)
		val outOfStockItems = Stock.getOutOfStockItems(order)
		Assertions.assertEquals(outOfStockItems, listOf("Orange"))
		Stock.resetStock()
	}

	@Test
	fun `test that random items in an order get ignored`() {
		val orderList = listOf("Apple", "Orange", "Orange", "Banana", "Apple")
		val order = Order(orderList)
		Assertions.assertEquals(mapOf("Apple" to 2, "Orange" to 2), order.getOrder())
	}
}

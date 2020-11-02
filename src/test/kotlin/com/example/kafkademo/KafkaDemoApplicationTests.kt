package com.example.kafkademo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KafkaDemoApplicationTests {
	@Test
	fun `test getPrices returns the right price map`() {
		Assertions.assertEquals(Order.getPrices(), mapOf<String, Double>("Apple" to 0.60, "Orange" to 0.25))
	}

	@Test
	fun `test getTotal returns the right value pre-discount`() {

	}

	@Test
	fun `test getTotal returns the right value post-discount`() {

	}

	@Test
	fun `test updateStock sets the stock to the right number`() {

	}

	@Test
	fun `test getOutOfStockItems`() {

	}

	@Test
	fun `test random items in order get ignored`() {

	}
}

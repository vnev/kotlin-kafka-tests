package com.example.kafkademo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KafkaDemoApplicationTests {
	@Test
	fun testGetPrices() {
		Assertions.assertEquals(getPrices(), mapOf<String, Double>("Apple" to 0.60, "Orange" to 0.25))
	}

	@Test
	fun `test getOrderQuantityMap passes`() {
		val fruits = listOf<String>("Orange", "Apple", "Orange", "Orange", "Apple")
		val orderQuantityMap = getOrderQuantityMap(fruits)
		val expectedResult = mapOf<String, Int>("Orange" to 3, "Apple" to 2)
		Assertions.assertEquals(expectedResult, orderQuantityMap)
	}

	@Test
	fun `test getOrderQuantityMap fails`() {
		val fruits = listOf<String>("Banana", "Apple", "Orange", "Orange", "Apple")
		val orderQuantityMap = getOrderQuantityMap(fruits)
		val expectedResult = mapOf<String, Int>("Orange" to 3, "Apple" to 2)
		Assertions.assertEquals(expectedResult, orderQuantityMap)
	}

}

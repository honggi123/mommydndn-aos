package com.mommydndn.app

import com.mommydndn.app.data.network.model.care.PayPeriodSerializer
import com.mommydndn.app.domain.model.care.PayPeriod
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class JsonSerializerUnitTest {

    @Test
    fun serializerPayPeriodEquals_isCorrect() {
        val expected = PayPeriod.NEGOTIATION

        val expectedJsonString = "\"NEGOTIATION\""

        val jsonString = Json.encodeToString(PayPeriodSerializer, expected)

        val actual = Json.decodeFromString(PayPeriodSerializer, jsonString)

        assertEquals(expectedJsonString, jsonString)

        assertEquals(expected, actual)
    }

    @Test
    fun parseStringAsLocalDate() {
        val dateString = "2023-11-07"

        val parsed = LocalDate.parse(dateString)

        val expected = LocalDate.of(2023, 11, 7)

        assertEquals(expected, parsed)

        assertEquals(dateString, expected.toString())
    }
}
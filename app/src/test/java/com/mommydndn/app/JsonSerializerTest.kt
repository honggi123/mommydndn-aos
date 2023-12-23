package com.mommydndn.app

import com.mommydndn.app.data.network.model.care.PayPeriodSerializer
import com.mommydndn.app.domain.model.care.PayPeriod
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

@Suppress("TestFunctionName")
class JsonSerializerTest {

    @Test
    fun When_SerializePayPeriod_Expect_UppercaseName() {
        PayPeriod.values().toList().forEach { payPeriod ->
            val expected = "\"${payPeriod.name}\""
            val actual = Json.encodeToString(PayPeriodSerializer, payPeriod)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun When_DeserializePayPeriodJson_Expected_CorrectPayPeriod() {
        PayPeriod.values().toList().forEach { expected ->
            val actual = Json.decodeFromString(PayPeriodSerializer, "\"${expected.name}\"")
            assertEquals(expected, actual)
        }
    }
}
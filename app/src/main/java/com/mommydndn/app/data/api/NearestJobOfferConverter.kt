package com.mommydndn.app.data.api

import com.google.gson.JsonParser
import com.mommydndn.app.data.api.model.NearestJobOfferResponse
import com.mommydndn.app.data.model.CaringType
import com.mommydndn.app.data.model.SalaryType
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

class NearestJobOfferConverter : Converter<ResponseBody, NearestJobOfferResponse> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): NearestJobOfferResponse {
        try {
            val json = value.string()
            val jsonObject = JsonParser.parseString(json).asJsonObject

            val caringType = CaringType.valueOf(jsonObject.get("caringTypeCode").asString)
            val salaryType = SalaryType.valueOf(jsonObject.get("salaryTypeCode").asString)

            val jobOfferId = jsonObject.get("jobOfferId").asInt
            val neighborhood = jsonObject.get("neighborhood").asString
            val salary = jsonObject.get("salary").asInt
            val title = jsonObject.get("title").asString


            return NearestJobOfferResponse(
                caringType = caringType,
                salaryType = salaryType,
                jobOfferId = jobOfferId,
                neighborhood = neighborhood,
                salary = salary,
                title = title
            )
        } finally {
            value.close()
        }
    }
}

class NearestJobOfferConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        if (type == NearestJobOfferResponse::class.java) {
            return NearestJobOfferConverter()
        }
        return null
    }
}
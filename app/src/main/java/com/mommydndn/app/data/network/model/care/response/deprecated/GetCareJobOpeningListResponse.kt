package com.mommydndn.app.data.network.model.care.response.deprecated

import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.PayPeriod
import com.mommydndn.app.domain.model.care.WorkPeriod
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.DayOfWeek
import java.time.LocalDate

@Serializable
data class Meta(
    // metadata
    @SerialName("totalCount")
    val totalCount: Long,
    @SerialName("currentPageNum")
    val page: Int,
    @SerialName("requestTimestamp")
    val requestedAt: Long,
)

data class GetCareJobOpeningListResponse(
    @SerialName("jobOfferSummaryList")
    val items: List<GetCareJobOpeningListItem>,
    @SerialName("meta")
    val meta: Meta,
)

@Serializable
data class GetCareJobOpeningListItem(
    @SerialName("jobOfferId")
    val id: Int,
    @SerialName("neighborhood")
    val neighborhoodName: String,
    @SerialName("caringTypeCodeList")
    val careTypes: List<@Serializable(with = CareTypeSerializer::class) CareType>,
    @SerialName("dateList")
    val oneTimeWorkDates: List<@Serializable(with = DateSerializer::class) LocalDate>,
    @SerialName("days")
    val daysOfWeek: List<@Serializable(with = DayOfWeekSerializer::class) DayOfWeek>,
    @SerialName("salary")
    val pay: Int,
    @Serializable(with = PayPeriodSerializer::class)
    @SerialName("salaryTypeCode")
    val payPeriod: PayPeriod,
    @Serializable(with = WorkPeriodSerializer::class)
    @SerialName("taskTypeCode")
    val workPeriod: WorkPeriod,
    // todo: date and time
    @SerialName("startDate")
    val regularWorkStartDate: Long?,
    @SerialName("endDate")
    val regularWorkEndDate: Long?,
    @SerialName("startTime")
    val regularWorkStartTime: String?,
    @SerialName("endTime")
    val regularWorkEndTime: String?,
    val title: String,
    val isClosed: Boolean,
    val isLiked: Boolean,
    val createdAt: Long,
)

object CareTypeSerializer : KSerializer<CareType> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.java.simpleName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): CareType {
        return deserialize(decoder.decodeString())
    }

    private fun deserialize(decodeString: String): CareType {
        return when (decodeString) {
            "PARENTING" -> CareType.CHILD_CARE
            "NURSING" -> CareType.SENIOR_CARE
            "SCHOOL" -> CareType.SCHOOL_TRANSPORTATION
            "HOUSEKEEPING" -> CareType.HOUSEKEEPING
            else -> throw IllegalArgumentException()
        }
    }

    override fun serialize(encoder: Encoder, value: CareType) {
        val name = when (value) {
            CareType.CHILD_CARE -> "PARENTING"
            CareType.SENIOR_CARE -> "NURSING"
            CareType.SCHOOL_TRANSPORTATION -> "SCHOOL"
            CareType.HOUSEKEEPING -> "HOUSEKEEPING"
        }

        encoder.encodeString(name)
    }
}

object PayPeriodSerializer : KSerializer<PayPeriod> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.java.simpleName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): PayPeriod {
        return PayPeriod.valueOf(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: PayPeriod) {
        encoder.encodeString(value.name)
    }
}

object WorkPeriodSerializer : KSerializer<WorkPeriod> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.java.simpleName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): WorkPeriod {
        return when (decoder.decodeString()) {
            "ONETIME" -> WorkPeriod.ONE_TIME
            "REGULAR" -> WorkPeriod.REGULAR
            else -> throw IllegalArgumentException()
        }
    }

    override fun serialize(encoder: Encoder, value: WorkPeriod) {
        val name =
            when (value) {
            WorkPeriod.ONE_TIME -> "ONETIME"
            WorkPeriod.REGULAR -> "REGULAR"
        }

        encoder.encodeString(name)
    }
}

object DayOfWeekSerializer : KSerializer<DayOfWeek> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.java.simpleName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): DayOfWeek {
        return DayOfWeek.values()
            .associateBy { it.name.take(3) }
            .getValue(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: DayOfWeek) {
        encoder.encodeString(value.name.take(3))
    }
}

object DateSerializer : KSerializer<LocalDate> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.java.simpleName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value.toString())
    }
}
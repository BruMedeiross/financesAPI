package com.brunadev.rapidApi.model

import android.os.Parcelable
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Welcome (
   val marketSummaryAndSparkResponse: MarketSummaryAndSparkResponse? = null
) : Parcelable

@Parcelize
data class MarketSummaryAndSparkResponse @JvmOverloads constructor(
   val result: List<ResultApi>? = null,
   val error: @RawValue JsonElement? = null
) : Parcelable

@Parcelize
data class ResultApi (
   val exchangeTimezoneName: String = "",
   val fullExchangeName: String = "",
   val symbol: String? = null,
   val gmtOffSetMilliseconds: Long? = null,
   val exchangeDataDelayedBy: Long? = null,
   val firstTradeDateMilliseconds: Long? = null,
   val language: Language? = null,
   val regularMarketTime: RegularMarket? = null,
   val exchangeTimezoneShortName: String? = null,
   val quoteType: String? = null,
   val marketState: MarketState? = null,
   val market: String? = null,
   val spark: Spark? = null,
   val priceHint: Long? = null,
   val tradeable: Boolean? = null,
   val sourceInterval: Long? = null,
   val exchange: String? = null,
   val shortName: String? = null,
   val region: Region? = null,
   val triggerable: Boolean? = null,
   val regularMarketPreviousClose: RegularMarket? = null
) : Parcelable

enum class Language(val value: String) {
   @SerializedName("en-US") EnUS("en-US");
}

enum class MarketState(val value: String) {
   @SerializedName("PREPRE") Prepre("PREPRE"),
   @SerializedName("REGULAR") Regular("REGULAR");
}

enum class Region(val value: String) {
   @SerializedName("US") Us("US");
}

@Parcelize
data class RegularMarket (
   val raw: Double? = null,
   val fmt: String? = null
) : Parcelable

@Parcelize
data class Spark (
   val previousClose: Double? = null,
   val chartPreviousClose: Double? = null,
   val symbol: String? = null,
   val timestamp: List<Long>? = null,
   val close: List<Double?>? = null,
   val dataGranularity: Long? = null,
   val end: Long? = null,
   val start: Long? = null
) : Parcelable


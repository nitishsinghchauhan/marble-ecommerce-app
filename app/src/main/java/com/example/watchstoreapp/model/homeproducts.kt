package com.example.watchstoreapp.model

// To parse the JSON, install Klaxon and do:
//
//   val welcome4 = Welcome4.fromJson(jsonString)


import android.os.Parcelable
import com.beust.klaxon.*
import kotlinx.android.parcel.Parcelize

private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
    this.converter(object: Converter {
        @Suppress("UNCHECKED_CAST")
        override fun toJson(value: Any)        = toJson(value as T)
        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
    })

private val klaxon = Klaxon()
    .convert(Type::class,           { Type.fromValue(it.string!!) },           { "\"${it.value}\"" })
@Parcelize
data class homeproducts (
    @Json(name = "topRatedProducts_LandingPage")
    val topRatedProductsLandingPage: List<ProductsLandingPage> = emptyList()
):Parcelable {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<homeproducts>(json)
    }
}
@Parcelize
data class ProductsLandingPage (
    val id: Long=0,
    val type: Type?=null,
    val attributes: Attributes = Attributes()
):Parcelable
@Parcelize
data class Attributes (
    val slug: String ="",
    val name: String ="",

    @Json(name = "product_url")
    val productURL: String ="",

    val price: String ="",

    @Json(name = "avg_rating")
    val avgRating: String ="",



    @Json(name = "cost_price")
    val costPrice: String? = null,

    @Json(name = "reviews_count")
    val reviewsCount: Long? = null
):Parcelable




enum class Type(val value: String ="") {
    Products("products");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "products" -> Products
            else       -> throw IllegalArgumentException()
        }
    }
}

package com.example.watchstoreapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class newAllProductsDetailPage (
    val id: Long,
//    val type: AllProductsDetailPageType,
//    val major: Major,
//    val minor: String,
//    val version: String,

    val parentId: Long,
//
//    @Json(name = "display_price")
//    val displayPrice: String,
//
//    @Json(name = "product_url")
//    val productURL: String,

    val attributes: AllProductsDetailPageAttributes,
    val images: List<String>
//    val relationships: AllProductsDetailPageRelationships,
//    val included: AllProductsDetailPageIncluded
): Parcelable

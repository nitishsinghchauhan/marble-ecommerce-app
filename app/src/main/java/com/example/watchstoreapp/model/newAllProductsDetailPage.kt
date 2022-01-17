package com.example.watchstoreapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class newAllProductsDetailPage (
    val id: Long=0,
//    val type: AllProductsDetailPageType,
//    val major: Major,
//    val minor: String,
//    val version: String,

    val parentId: Long=0,
//
//    @Json(name = "display_price")
//    val displayPrice: String,
//
//    @Json(name = "product_url")
//    val productURL: String,

    val attributes: AllProductsDetailPageAttributes =
        AllProductsDetailPageAttributes(),
    val images: List<String> = emptyList()
//    val relationships: AllProductsDetailPageRelationships,
//    val included: AllProductsDetailPageIncluded
): Parcelable

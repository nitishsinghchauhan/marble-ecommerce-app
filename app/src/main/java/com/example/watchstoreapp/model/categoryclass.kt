package com.example.watchstoreapp.model



import android.os.Parcelable
import com.beust.klaxon.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

private val klaxon = Klaxon()

data class categoryclass (
    @Json(name = "taxonomies_LandingPage")
    val taxonomiesLandingPage: List<TaxonomiesLandingPage> = emptyList(),

    val count: Long =0,

    @Json(name = "current_page")
    val currentPage: Long = 0,

    val pages: Long = 0
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<categoryclass>(json)
    }
}

data class TaxonomiesLandingPage (
    val id: Long =0,
    val name: String ="",
    val root: Root = Root()
)

data class Root (
    val id: Long =0,
    val name: String = "",

    @Json(name = "pretty_name")
    val prettyName: String= "",

    val permalink: String = "",

    @Json(name = "parent_id")
    val parentID: Any? = null,

    @Json(name = "taxonomy_id")
    val taxonomyID: Long =0,

    @Json(name = "meta_title")
    val metaTitle: Any? = null,

    @Json(name = "meta_description")
    val metaDescription: Any? = null,

    val taxons: List<Taxon> = emptyList()
)
@Parcelize
data class Taxon (
    val id: Long =0,
    val name: String = "",

    @Json(name = "pretty_name")
    val prettyName: String ="",

    val permalink: String="",

    @Json(name = "parent_id")
    val parentID: Long =0,

    @Json(name = "taxonomy_id")
    val taxonomyID: Long = 0,

    @Json(name = "meta_title")
    val metaTitle: String? = null,

    @Json(name = "meta_description")
    val metaDescription: String? = null,

    val taxons: List<Taxon>? = null,
    val icon: String? = null
):Parcelable
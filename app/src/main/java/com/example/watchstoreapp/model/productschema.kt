package com.example.watchstoreapp.model

// To parse the JSON, install Klaxon and do:
//
//   val welcome9 = productschema.fromJson(jsonString)
import com.beust.klaxon.*

private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
    this.converter(object: Converter {
        @Suppress("UNCHECKED_CAST")
        override fun toJson(value: Any)        = toJson(value as T)
        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
    })

private val klaxon = Klaxon()
//    .convert(Currency::class,                  { Currency.fromValue(it.string!!) },                  { "\"${it.value}\"" })
    .convert(Name::class,                      { Name.fromValue(it.string!!) },                      { "\"${it.value}\"" })
    .convert(Permalink::class,                 { Permalink.fromValue(it.string!!) },                 { "\"${it.value}\"" })
    .convert(PrettyName::class,                { PrettyName.fromValue(it.string!!) },                { "\"${it.value}\"" })
    .convert(AttributesType::class,            { AttributesType.fromValue(it.string!!) },            { "\"${it.value}\"" })
    .convert(ViewableType::class,              { ViewableType.fromValue(it.string!!) },              { "\"${it.value}\"" })
    .convert(DataType::class,                  { DataType.fromValue(it.string!!) },                  { "\"${it.value}\"" })
    .convert(PropertyName::class,              { PropertyName.fromValue(it.string!!) },              { "\"${it.value}\"" })
    .convert(Value::class,                     { Value.fromValue(it.string!!) },                     { "\"${it.value}\"" })
    .convert(Major::class,                     { Major.fromValue(it.string!!) },                     { "\"${it.value}\"" })
    .convert(AllProductsDetailPageType::class, { AllProductsDetailPageType.fromValue(it.string!!) }, { "\"${it.value}\"" })

fun oldtonewclassproduct(productschema1: productschema):newproductschema{
    var newlist=mutableListOf<newAllProductsDetailPage>()
    for(data in productschema1.allProductsDetailPage){
        newlist.add(newAllProductsDetailPage(data.id,data.taxonIDS[2],data.attributes))
    }
    return newproductschema(newlist)
}
data class newproductschema (
    @Json(name = "allProducts_DetailPage")
    val newallProductsDetailPage: List<newAllProductsDetailPage>
)

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
//    val relationships: AllProductsDetailPageRelationships,
//    val included: AllProductsDetailPageIncluded
)







data class productschema (
    @Json(name = "allProducts_DetailPage")
    val allProductsDetailPage: List<AllProductsDetailPage>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<productschema>(json)
    }
}


data class AllProductsDetailPage (
    val id: Long,
//    val type: AllProductsDetailPageType,
//    val major: Major,
//    val minor: String,
//    val version: String,

    @Json(name = "taxon_ids")
    val taxonIDS: List<Long>,
//
//    @Json(name = "display_price")
//    val displayPrice: String,
//
//    @Json(name = "product_url")
//    val productURL: String,

    val attributes: AllProductsDetailPageAttributes,
//    val relationships: AllProductsDetailPageRelationships,
//    val included: AllProductsDetailPageIncluded
)

data class AllProductsDetailPageAttributes (
    val id: Long,
    val name: String,
    val description: String,
    val price: String,

    @Json(name = "display_price")
    val displayPrice: String,

    @Json(name = "available_on")
    val availableOn: String,

    val slug: String,

    @Json(name = "meta_description")
    val metaDescription: String,

    @Json(name = "meta_keywords")
    val metaKeywords: String,

    @Json(name = "shipping_category_id")
    val shippingCategoryID: Long,

    @Json(name = "taxon_ids")
    val taxonIDS: List<Long>,

    @Json(name = "total_on_hand")
    val totalOnHand: Long,

    @Json(name = "avg_rating")
    val avgRating: String,

    @Json(name = "reviews_count")
    val reviewsCount: Long,
//
//    val currency: Currency,
//
//    @Json(name = "currency_symbol")
//    val currencySymbol: Currency,

    @Json(name = "has_variants")
    val hasVariants: Boolean,

    @Json(name = "cost_price")
    val costPrice: String,

    @Json(name = "is_favorited_by_current_user")
    val isFavoritedByCurrentUser: Boolean,

    @Json(name = "product_url")
    val productURL: String
)

//enum class Currency(val value: String) {
//    Currency("₹."),
//    Empty("₹");
//
//    companion object {
//        public fun fromValue(value: String): Currency = when (value) {
//            "₹." -> Currency
//            "₹"  -> Empty
//            else -> throw IllegalArgumentException()
//        }
//    }
//}

data class AllProductsDetailPageIncluded (
    val master: IncludedMaster,
    val variants: List<Any?>,

    @Json(name = "option_types")
    val optionTypes: List<Any?>,

    @Json(name = "product_properties")
    val productProperties: List<ProductProperty>,

    val classifications: List<Classification>
)

data class Classification (
    val data: ClassificationData
)

data class ClassificationData (
    val attributes: PurpleAttributes
)

data class PurpleAttributes (
    @Json(name = "taxon_id")
    val taxonID: Long,

    val position: Long,
    val taxon: Taxon
)

//data class Taxon (
//    val id: Long,
//    val name: Name,
//
//    @Json(name = "pretty_name")
//    val prettyName: PrettyName,
//
//    val permalink: Permalink,
//
//    @Json(name = "parent_id")
//    val parentID: Long,
//
//    @Json(name = "taxonomy_id")
//    val taxonomyID: Long,
//
//    @Json(name = "meta_title")
//    val metaTitle: String,
//
//    @Json(name = "meta_description")
//    val metaDescription: String,
//
//    val taxons: List<Any?>
//) {
//
//}

enum class Name(val value: String) {
    Necklaces("Necklaces");

    companion object {
        public fun fromValue(value: String): Name = when (value) {
            "Necklaces" -> Necklaces
            else        -> throw IllegalArgumentException()
        }
    }
}

enum class Permalink(val value: String) {
    GoldJewelleryBags("Gold Jewellery/bags");

    companion object {
        public fun fromValue(value: String): Permalink = when (value) {
            "Gold Jewellery/bags" -> GoldJewelleryBags
            else                  -> throw IllegalArgumentException()
        }
    }
}

enum class PrettyName(val value: String) {
    GoldJewelleryNecklaces("Gold Jewellery -> Necklaces");

    companion object {
        public fun fromValue(value: String): PrettyName = when (value) {
            "Gold Jewellery -> Necklaces" -> GoldJewelleryNecklaces
            else                          -> throw IllegalArgumentException()
        }
    }
}

data class IncludedMaster (
    val data: MasterData
)

data class MasterData (
    val attributes: FluffyAttributes,
    val relationships: DataRelationships,
    val included: DataIncluded
)

data class FluffyAttributes (
    val id: Long,
    val name: String,
    val sku: String,
    val price: String,
    val weight: String,
    val height: Any? = null,
    val width: Any? = null,
    val depth: Any? = null,

    @Json(name = "is_master")
    val isMaster: Boolean,

    val slug: String,
    val description: String,

    @Json(name = "track_inventory")
    val trackInventory: Boolean,

    @Json(name = "display_price")
    val displayPrice: String,

    @Json(name = "options_text")
    val optionsText: String,

    @Json(name = "in_stock")
    val inStock: Boolean,

    @Json(name = "is_backorderable")
    val isBackorderable: Boolean,

    @Json(name = "is_orderable")
    val isOrderable: Boolean,

    @Json(name = "total_on_hand")
    val totalOnHand: Long,

    @Json(name = "is_destroyed")
    val isDestroyed: Boolean,

    @Json(name = "cost_price")
    val costPrice: String
)

data class DataIncluded (
    @Json(name = "option_values")
    val optionValues: List<Any?>,

    val images: List<Image>
)

data class Image (
    val data: ImageData
)

data class ImageData (
    val attributes: TentacledAttributes
)

data class TentacledAttributes (
    val id: Long,
    val position: Long,

    @Json(name = "attachment_content_type")
    val attachmentContentType: Any? = null,

    @Json(name = "attachment_file_name")
    val attachmentFileName: Any? = null,

    val type: AttributesType,

    @Json(name = "attachment_updated_at")
    val attachmentUpdatedAt: Any? = null,

    @Json(name = "attachment_width")
    val attachmentWidth: Any? = null,

    @Json(name = "attachment_height")
    val attachmentHeight: Any? = null,

    val alt: String,

    @Json(name = "viewable_type")
    val viewableType: ViewableType,

    @Json(name = "viewable_id")
    val viewableID: Long,

    @Json(name = "product_url")
    val productURL: String
)

enum class AttributesType(val value: String) {
    AmazonImage("Amazon::Image");

    companion object {
        public fun fromValue(value: String): AttributesType = when (value) {
            "Amazon::Image" -> AmazonImage
            else            -> throw IllegalArgumentException()
        }
    }
}

enum class ViewableType(val value: String) {
    AmazonVariant("Amazon::Variant");

    companion object {
        public fun fromValue(value: String): ViewableType = when (value) {
            "Amazon::Variant" -> AmazonVariant
            else              -> throw IllegalArgumentException()
        }
    }
}

data class DataRelationships (
    @Json(name = "option_values")
    val optionValues: Classifications,

    val images: Classifications
)

data class Classifications (
    val data: List<DAT>
)

data class DAT (
    val type: DataType,
    val id: Long
)

enum class DataType(val value: String) {
    Classifications("classifications"),
    Images("images"),
    Master("master"),
    ProductProperties("product_properties");

    companion object {
        public fun fromValue(value: String): DataType = when (value) {
            "classifications"    -> Classifications
            "images"             -> Images
            "master"             -> Master
            "product_properties" -> ProductProperties
            else                 -> throw IllegalArgumentException()
        }
    }
}

data class ProductProperty (
    val data: ProductPropertyData
)

data class ProductPropertyData (
    val attributes: StickyAttributes
)

data class StickyAttributes (
    val id: Long,

    @Json(name = "product_id")
    val productID: Long,

    @Json(name = "property_id")
    val propertyID: Long,

    val value: Value,

    @Json(name = "property_name")
    val propertyName: PropertyName
)

enum class PropertyName(val value: String) {
    Material("Material"),
    Size("Size"),
    Type("Type");

    companion object {
        public fun fromValue(value: String): PropertyName = when (value) {
            "Material" -> Material
            "Size"     -> Size
            "Type"     -> Type
            else       -> throw IllegalArgumentException()
        }
    }
}

enum class Value(val value: String) {
    Messenger("Messenger"),
    The1412X12X5("14 1/2' x 12' x 5'"),
    The600DenierPolyester("600 Denier Polyester");

    companion object {
        public fun fromValue(value: String): Value = when (value) {
            "Messenger"            -> Messenger
            "14 1/2' x 12' x 5'"   -> The1412X12X5
            "600 Denier Polyester" -> The600DenierPolyester
            else                   -> throw IllegalArgumentException()
        }
    }
}

enum class Major(val value: String) {
    Granite("granite"),
    ImportedMarbles("importedMarbles"),
    IndianMarbles("indianMarbles");

    companion object {
        public fun fromValue(value: String): Major = when (value) {
            "granite"         -> Granite
            "importedMarbles" -> ImportedMarbles
            "indianMarbles"   -> IndianMarbles
            else              -> throw IllegalArgumentException()
        }
    }
}

data class AllProductsDetailPageRelationships (
    val master: RelationshipsMaster,
    val variants: Classifications,

    @Json(name = "option_types")
    val optionTypes: Classifications,

    @Json(name = "product_properties")
    val productProperties: Classifications,

    val classifications: Classifications
)

data class RelationshipsMaster (
    val data: DAT
)

enum class AllProductsDetailPageType(val value: String) {
    Products("products");

    companion object {
        public fun fromValue(value: String): AllProductsDetailPageType = when (value) {
            "products" -> Products
            else       -> throw IllegalArgumentException()
        }
    }
}

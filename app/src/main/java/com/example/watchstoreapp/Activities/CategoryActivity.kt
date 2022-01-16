package com.example.watchstoreapp.Activities

import android.app.Activity
import android.app.PendingIntent
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.beust.klaxon.Klaxon

import com.example.watchstoreapp.Activities.ui.theme.NavigationDrawerExampleNewTheme
import com.example.watchstoreapp.R
import com.example.watchstoreapp.model.Taxon
import com.example.watchstoreapp.model.categoryclass
import com.example.watchstoreapp.model.oldtonewclassproduct
import com.example.watchstoreapp.model.productschema
import com.example.watchstoreapp.repository.StoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.activity_full_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import com.example.watchstoreapp.viewModel.StoreViewModel as StoreViewModel
import javax.inject.Inject as Inject1


@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {
    @javax.inject.Inject lateinit var repository: StoreRepository



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var taxonP=intent.getParcelableExtra<Taxon>("taxonParent")

        val rd = productschema.fromJson("""{
  "allProducts_DetailPage": [
    {
      "id": 201,
      "type": "products",
      "major": "indianMarbles",
      "minor": "whiteMarbles",
      "version": "flawlessWhite",
      "taxon_ids": [
        1008,
        2,
        42,
        49,
        41
      ],
      "display_price": "210",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F201_main.jpg?alt=media&token=704c8a3e-9b41-4c27-8f6d-5b89682e788c",
      "attributes": {
        "id": 201,
        "name": "Flawless White",
        "description": "<p>Purest white marble and regarded as the epitome of elegance infused luxury, Flawless White’s fine grain and glossy finish makes it a universal favourite.</p>",
        "price": "210",
        "display_price": "210",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "flawlessWhite",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          42,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "5.0",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "210",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F201_main.jpg?alt=media&token=704c8a3e-9b41-4c27-8f6d-5b89682e788c"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 201,
              "name": "Flawless White",
              "sku": "flawlessWhite",
              "price": "210",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "flawlessWhite",
              "description": "<p>Purest white marble and regarded as the epitome of elegance infused luxury, Flawless White’s fine grain and glossy finish makes it a universal favourite.</p>",
              "track_inventory": true,
              "display_price": "210",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "210"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F201_main.jpg?alt=media&token=704c8a3e-9b41-4c27-8f6d-5b89682e788c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F201_use1.jpg?alt=media&token=4e597a9c-c9b3-4811-bb07-2251afe70baf"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F201_use2.jpg?alt=media&token=025f1cce-d58e-4a56-8d1e-7a831aeae1be"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F201_use2.jpg?alt=media&token=0a024adf-74cf-4479-8ac7-d4969cea426f"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 202,
      "type": "products",
      "major": "indianMarbles",
      "minor": "whiteMarbles",
      "version": "premiumMorwadWhite",
      "taxon_ids": [
        1008,
        2,
        42,
        49,
        41
      ],
      "display_price": "108",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F202_main.jpg?alt=media&token=09acb0ef-c80f-4d29-b43b-cdd21c270a0f",
      "attributes": {
        "id": 202,
        "name": "Premium Morwad White",
        "description": "<p>Morwad White Marble is the best option for flooring purpose. It can be also used for the Counter top and wall cladding. Morwad White Marble has stunning White appearance. It has nice strength and durability.</p>",
        "price": "108",
        "display_price": "108",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "premiumMorwadWhite",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          42,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "0.0",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "108",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F202_main.jpg?alt=media&token=09acb0ef-c80f-4d29-b43b-cdd21c270a0f"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 202,
              "name": "Premium Morwad White",
              "sku": "premiumMorwadWhite",
              "price": "108",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "premiumMorwadWhite",
              "description": "<p>Morwad White Marble is the best option for flooring purpose. It can be also used for the Counter top and wall cladding. Morwad White Marble has stunning White appearance. It has nice strength and durability.</p>",
              "track_inventory": true,
              "display_price": "108",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "108"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F202_main.jpg?alt=media&token=09acb0ef-c80f-4d29-b43b-cdd21c270a0f"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F202_use1.jpg?alt=media&token=fb666509-c800-44ea-91d9-9a6c45e7d8d5"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F202_slab.jpg?alt=media&token=c3fad874-adc6-4793-b2d0-3c6e3d4b0471"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 203,
      "type": "products",
      "major": "indianMarbles",
      "minor": "whiteMarbles",
      "version": "makranaWhite",
      "taxon_ids": [
        1008,
        2,
        42,
        49
      ],
      "display_price": "360",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F203_main.jpg?alt=media&token=f63cc2b2-6b16-48d2-95e0-20922a2970b0",
      "attributes": {
        "id": 203,
        "name": "Makrana White",
        "description": "<p>Purest white marble and regarded as the epitome of elegance infused luxury, Makrana White's fine grain and glossy finish makes it a universal favourite.</p>",
        "price": "360",
        "display_price": "360",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "makranaWhite",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          42,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "0.0",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "360",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F203_main.jpg?alt=media&token=f63cc2b2-6b16-48d2-95e0-20922a2970b0"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 203,
              "name": "Makrana White",
              "sku": "makranaWhite",
              "price": "360",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "makranaWhite",
              "description": "<p>Purest white marble and regarded as the epitome of elegance infused luxury, Makrana White’s fine grain and glossy finish makes it a universal favourite.</p>",
              "track_inventory": true,
              "display_price": "360",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "360"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F203_main.jpg?alt=media&token=f63cc2b2-6b16-48d2-95e0-20922a2970b0"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F203_use1.jpg?alt=media&token=f2177e0b-8b1c-478e-8228-070451d2a0ae"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F203_slab.jpg?alt=media&token=953a869b-2a79-4f62-8a1d-c6f6825f8b3d"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 204,
      "type": "products",
      "major": "indianMarbles",
      "minor": "whiteMarbles",
      "version": "whiteStatuario",
      "taxon_ids": [
        1008,
        2,
        42,
        49,
        41
      ],
      "display_price": "135",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F204_main.jpg?alt=media&token=743406c7-8d2d-40c8-b3a7-aea44261430c",
      "attributes": {
        "id": 204,
        "name": "White Statuario",
        "description": "<p>Pure expression of timeless charm and elegance, Statuario Select stands out for this dazzling color, a white mother-of-pearl white shade with gray veins that cross its surface. The bright and delicate tone allows to obtain monochromatic surfaces of great impact in various environments.</p>",
        "price": "135",
        "display_price": "135",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "whiteStatuario",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          42,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "0.0",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "135",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F204_main.jpg?alt=media&token=743406c7-8d2d-40c8-b3a7-aea44261430c"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 204,
              "name": "White Statuario",
              "sku": "whiteStatuario",
              "price": "135",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "whiteStatuario",
              "description": "<p>Pure expression of timeless charm and elegance, Statuario Select stands out for this dazzling color, a white mother-of-pearl white shade with gray veins that cross its surface. The bright and delicate tone allows to obtain monochromatic surfaces of great impact in various environments.</p>",
              "track_inventory": true,
              "display_price": "135",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "135"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F204_main.jpg?alt=media&token=743406c7-8d2d-40c8-b3a7-aea44261430c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F204_use1.jpg?alt=media&token=758f8a81-cd41-488d-a783-d39abf9b2f84"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F204_use2.jpg?alt=media&token=b409d21c-e0a7-4832-83f6-d00d0cbb2e6e"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F204_slab.jpg?alt=media&token=d5313e44-c477-4926-a267-36837722200e"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 205,
      "type": "products",
      "major": "indianMarbles",
      "minor": "whiteMarbles",
      "version": "opalWhite",
      "taxon_ids": [
        1008,
        2,
        42,
        49,
        41
      ],
      "display_price": "270",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F205_main.jpg?alt=media&token=54333154-835e-4b47-8a49-06376ce82310",
      "attributes": {
        "id": 205,
        "name": "Opal White",
        "description": "<p>Pure expression of timeless charm and elegance, Opal White stands out for this dazzling color, a white mother-of-pearl white shade with gray veins that cross its surface. The bright and delicate tone allows to obtain monochromatic surfaces of great impact in various environments.</p>",
        "price": "270",
        "display_price": "270",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "opalWhite",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          42,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "0.0",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "270",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F205_main.jpg?alt=media&token=54333154-835e-4b47-8a49-06376ce82310"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 205,
              "name": "Opal White",
              "sku": "opalWhite",
              "price": "270",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "opalWhite",
              "description": "<p>Pure expression of timeless charm and elegance, Opal White stands out for this dazzling color, a white mother-of-pearl white shade with gray veins that cross its surface. The bright and delicate tone allows to obtain monochromatic surfaces of great impact in various environments.</p>",
              "track_inventory": true,
              "display_price": "270",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "270"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F205_main.jpg?alt=media&token=54333154-835e-4b47-8a49-06376ce82310"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F205_use1.jpg?alt=media&token=a19e0954-895e-4b8a-8fa4-bdb1aea4ce46"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F205_use2.jpg?alt=media&token=8af862cb-019c-4040-81e1-d3a85cd9fb2c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F205_slab.jpg?alt=media&token=ddb80ff3-7289-4c4f-a41a-b9c4afce4aec"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 206,
      "type": "products",
      "major": "indianMarbles",
      "minor": "pinkIndianMarbles",
      "version": "udaipurPink",
      "taxon_ids": [
        1008,
        2,
        43,
        49
      ],
      "display_price": "28",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F206_main.jpg?alt=media&token=08da92c6-8ae0-4bb4-88f6-553b9cfcef3a",
      "attributes": {
        "id": 206,
        "name": "Udaipur Pink",
        "description": "<p>This is the finest and superior quality of Indian Pink Marble. </p>",
        "price": "28",
        "display_price": "28",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "udaipurPink",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          43,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "0.0",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "28",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F206_main.jpg?alt=media&token=08da92c6-8ae0-4bb4-88f6-553b9cfcef3a"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 206,
              "name": "Udaipur Pink",
              "sku": "udaipurPink",
              "price": "28",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "udaipurPink",
              "description": "<p>This is the finest and superior quality of Indian Pink Marble.</p>",
              "track_inventory": true,
              "display_price": "28",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "28"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F206_main.jpg?alt=media&token=08da92c6-8ae0-4bb4-88f6-553b9cfcef3a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F206_use1.jpg?alt=media&token=8963cfd9-3ba7-4a09-88df-962a1717335b"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F206_use2.jpg?alt=media&token=3185fb3f-eea0-45d1-acf3-464225ad6b19"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F206_slab.jpg?alt=media&token=58df1d8f-8e84-4808-9278-5362be345aeb"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 207,
      "type": "products",
      "major": "indianMarbles",
      "minor": "pinkIndianMarbles",
      "version": "carrotPink",
      "taxon_ids": [
        1008,
        2,
        43,
        49
      ],
      "display_price": "38",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F207_main.jpg?alt=media&token=1ab46a6a-ba01-4c86-b553-fa8ef98380e8",
      "attributes": {
        "id": 207,
        "name": "Carrot Pink",
        "description": "<p>This is the finest and superior quality of Indian Pink Marble. </p>",
        "price": "38",
        "display_price": "38",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "carrotPink",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          43,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "0.0",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "38",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F207_main.jpg?alt=media&token=1ab46a6a-ba01-4c86-b553-fa8ef98380e8"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 207,
              "name": "Carrot Pink",
              "sku": "carrotPink",
              "price": "38",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "carrotPink",
              "description": "<p>This is the finest and superior quality of Indian Pink Marble.</p>",
              "track_inventory": true,
              "display_price": "38",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "38"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F207_main.jpg?alt=media&token=1ab46a6a-ba01-4c86-b553-fa8ef98380e8"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F207_use1.jpg?alt=media&token=8a46d156-3aa5-4bf4-91b5-4f62c7ef652a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F207_use2.jpg?alt=media&token=00c1d928-4898-40e2-88af-84c3cc181088"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F207_slab.jpg?alt=media&token=ca8bac32-2b20-4bb0-8ff6-7b593f5c95d2"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 208,
      "type": "products",
      "major": "indianMarbles",
      "minor": "greenIndianMarble",
      "version": "darkGreenIndianMarble",
      "taxon_ids": [
        1008,
        2,
        44,
        49
      ],
      "display_price": "41",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F208_main.jpg?alt=media&token=41b9588d-4c5d-4f82-adf9-f2a74e727533",
      "attributes": {
        "id": 208,
        "name": "Dark Green Marble",
        "description": "<p>This is the finest and superior quality of Indian Green Marble. </p>",
        "price": "41",
        "display_price": "41",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "darkGreenIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          44,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "41",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F208_main.jpg?alt=media&token=41b9588d-4c5d-4f82-adf9-f2a74e727533"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 208,
              "name": "Dark Green Marble",
              "sku": "darkGreenIndianMarble",
              "price": "41",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "darkGreenIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Green Marble.</p>",
              "track_inventory": true,
              "display_price": "41",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "41"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F208_main.jpg?alt=media&token=41b9588d-4c5d-4f82-adf9-f2a74e727533"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F208_use1.jpg?alt=media&token=0ba75064-13b2-4178-9a08-a9c83930d964"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F208_use2.jpg?alt=media&token=bdb39a93-63aa-474d-8f71-b7d6094197d5"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F208_slab.jpg?alt=media&token=c43546f7-77a3-48cc-8299-227a1e0c978f"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2Fslab.jpg?alt=media&token=72ee930e-0537-4c86-8b10-5a69982933e2"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 209,
      "type": "products",
      "major": "indianMarbles",
      "minor": "greenIndianMarble",
      "version": "spiderGreenIndianMarble",
      "taxon_ids": [
        1008,
        2,
        44,
        49
      ],
      "display_price": "50",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F209_main.jpg?alt=media&token=4ce94dc6-e51d-418d-b207-deeb7e5c7d6b",
      "attributes": {
        "id": 209,
        "name": "Spider Green Marble",
        "description": "<p>This is the finest and superior quality of Indian Green Marble. </p>",
        "price": "50",
        "display_price": "50",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "spiderGreenIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          44,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "50",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F209_main.jpg?alt=media&token=4ce94dc6-e51d-418d-b207-deeb7e5c7d6b"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 209,
              "name": "Spider Green Marble",
              "sku": "spiderGreenIndianMarble",
              "price": "50",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "spiderGreenIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Green Marble.</p>",
              "track_inventory": true,
              "display_price": "50",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "50"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F209_main.jpg?alt=media&token=4ce94dc6-e51d-418d-b207-deeb7e5c7d6b"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F209_use1.jpg?alt=media&token=d6e3e841-ac83-42c4-b82d-d018795ca969"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F209_use2.jpg?alt=media&token=1ee04fa1-a144-40e1-93ee-051a418379e1"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F209_use3.jpg?alt=media&token=388aea3c-f41b-46e5-8ed7-27a6f7105ea0"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F209_slab.jpg?alt=media&token=3fa35985-6764-4fab-99bc-f5168a9a902e"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 210,
      "type": "products",
      "major": "indianMarbles",
      "minor": "greenIndianMarble",
      "version": "rainForestIndianMarble",
      "taxon_ids": [
        1008,
        2,
        44,
        49
      ],
      "display_price": "80",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F210_main.jpg?alt=media&token=7bef2a8b-18f8-4cf1-ab02-09dfe3b01dc7",
      "attributes": {
        "id": 210,
        "name": "Rain Forest Marble",
        "description": "<p>This is the finest and superior quality of Indian Green Marble. </p>",
        "price": "80",
        "display_price": "80",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "rainForestIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          44,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "80",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F210_main.jpg?alt=media&token=7bef2a8b-18f8-4cf1-ab02-09dfe3b01dc7"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 210,
              "name": "Rain Forest Marble",
              "sku": "rainForestIndianMarble",
              "price": "80",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "rainForestIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Green Marble.</p>",
              "track_inventory": true,
              "display_price": "80",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "80"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F210_main.jpg?alt=media&token=7bef2a8b-18f8-4cf1-ab02-09dfe3b01dc7"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F210_use1.jpg?alt=media&token=7cfb5960-64c2-443d-85d8-becaca3521d1"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F210_use2.jpg?alt=media&token=e249c0a9-86e7-4b5b-b8e9-489b523ff0d1"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F210_use3.jpg?alt=media&token=91d76a80-ed0f-4e14-bf35-d83f3146b9cf"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F210_slab.jpg?alt=media&token=4ce5c8bb-781e-4fc3-b960-c9e1620e9308"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 212,
      "type": "products",
      "major": "indianMarbles",
      "minor": "goldIndianMarble",
      "version": "itaGoldIndianMarble",
      "taxon_ids": [
        1008,
        2,
        45,
        49
      ],
      "display_price": "59",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F212_main.jpg?alt=media&token=98de4213-4aff-408f-93a3-24a5792cb06b",
      "attributes": {
        "id": 212,
        "name": "Ita Gold Marble",
        "description": "<p>This is the finest and superior quality of Indian Gold Marble. </p>",
        "price": "59",
        "display_price": "59",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "itaGoldIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          45,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "59",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F212_main.jpg?alt=media&token=98de4213-4aff-408f-93a3-24a5792cb06b"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 212,
              "name": "Ita Gold Marble",
              "sku": "itaGoldIndianMarble",
              "price": "59",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "itaGoldIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Gold Marble.</p>",
              "track_inventory": true,
              "display_price": "59",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "59"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F212_main.jpg?alt=media&token=98de4213-4aff-408f-93a3-24a5792cb06b"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F212_use1.jpg?alt=media&token=717f0eb9-ebed-475d-86fe-d3d84d7fcab2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F212_use2.jpg?alt=media&token=a8420a53-7535-4ed5-b06b-e7edc2cff224"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F212_slab.jpg?alt=media&token=ec3dcbed-733b-4db6-ab5d-e5f51c968b75"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 213,
      "type": "products",
      "major": "indianMarbles",
      "minor": "goldIndianMarble",
      "version": "goldioIndianMarble",
      "taxon_ids": [
        1008,
        2,
        45,
        49
      ],
      "display_price": "60",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F213_main.jpg?alt=media&token=0b95d500-9f58-418a-b845-b1cd8de091ec",
      "attributes": {
        "id": 213,
        "name": "Goldio Marble",
        "description": "<p>This is the finest and superior quality of Indian Gold Marble. </p>",
        "price": "60",
        "display_price": "60",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "goldioIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          45,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "60",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F213_main.jpg?alt=media&token=0b95d500-9f58-418a-b845-b1cd8de091ec"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 213,
              "name": "Goldio Marble",
              "sku": "goldioIndianMarble",
              "price": "60",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "goldioIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Gold Marble.</p>",
              "track_inventory": true,
              "display_price": "60",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "60"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F213_main.jpg?alt=media&token=0b95d500-9f58-418a-b845-b1cd8de091ec"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F213_use1.jpg?alt=media&token=45cf3e14-d64f-4c13-83ce-f62283817e44"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F213_use2PNG.jpg?alt=media&token=b2464466-f74a-411e-9cf4-8d953d762923"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F213_use3.jpg?alt=media&token=2d4d189a-9385-4dd4-8d47-998d2a97bdd6"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F213_slab.jpg?alt=media&token=4b5d4340-817e-4497-863b-9688bce14627"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 214,
      "type": "products",
      "major": "indianMarbles",
      "minor": "goldIndianMarble",
      "version": "rainForestGoldIndianMarble",
      "taxon_ids": [
        1008,
        2,
        45,
        49
      ],
      "display_price": "85",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F214_main.jpg?alt=media&token=66bd0a50-c6aa-42d8-add3-1c062fbcb7c6",
      "attributes": {
        "id": 214,
        "name": "Rain Forest Gold",
        "description": "<p>This is the finest and superior quality of Indian Gold Marble. </p>",
        "price": "85",
        "display_price": "85",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "rainForestGoldIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          45,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "85",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F214_main.jpg?alt=media&token=66bd0a50-c6aa-42d8-add3-1c062fbcb7c6"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 214,
              "name": "Rain Forest Gold",
              "sku": "rainForestGoldIndianMarble",
              "price": "85",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "rainForestGoldIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Gold Marble.</p>",
              "track_inventory": true,
              "display_price": "85",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "85"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F214_main.jpg?alt=media&token=66bd0a50-c6aa-42d8-add3-1c062fbcb7c6"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F214_use1.jpg?alt=media&token=6b91fa00-2f5f-4fd2-a917-8a55c485fc5c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F214_use2.jpg?alt=media&token=4f944825-7504-4629-a976-9502b84615b4"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F214_use3.jpg?alt=media&token=54ec0990-c9d6-4cd5-88c9-165c4729b1c2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F214_slab.jpg?alt=media&token=a30fd67c-9a28-44c6-a1e9-2bbbdfd01e5a"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 215,
      "type": "products",
      "major": "indianMarbles",
      "minor": "goldIndianMarble",
      "version": "antiqueForestGoldIndianMarble",
      "taxon_ids": [
        1008,
        2,
        45,
        49
      ],
      "display_price": "95",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F215_main.jpg?alt=media&token=cbff103c-23bd-4009-a5ac-43e415d0e7ce",
      "attributes": {
        "id": 215,
        "name": "Antique Rain Forest",
        "description": "<p>This is the finest and superior quality of Indian Gold Marble. </p>",
        "price": "95",
        "display_price": "95",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "antiqueForestGoldIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          45,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "95",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F215_main.jpg?alt=media&token=cbff103c-23bd-4009-a5ac-43e415d0e7ce"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 215,
              "name": "Antique Rain Forest",
              "sku": "antiqueForestGoldIndianMarble",
              "price": "95",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "antiqueForestGoldIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Gold Marble.</p>",
              "track_inventory": true,
              "display_price": "95",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "95"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F215_main.jpg?alt=media&token=cbff103c-23bd-4009-a5ac-43e415d0e7ce"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F215_use2.jpg?alt=media&token=493ef9d9-3d33-49f8-bbde-7cfc2c9fece1"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F215_use3.jpg?alt=media&token=5b0d2deb-2bb3-48bf-98f7-b9dc21efe3a7"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F215_use4.jpg?alt=media&token=974cad2f-241e-4ad9-8c8b-e36c17abf697"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F215_slab.jpg?alt=media&token=ca42c676-06ea-4e94-a0bf-ec5f8772db4b"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 216,
      "type": "products",
      "major": "indianMarbles",
      "minor": "goldIndianMarble",
      "version": "floweryGoldIndianMarble",
      "taxon_ids": [
        1008,
        2,
        45,
        49
      ],
      "display_price": "90",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F216_main.jpg?alt=media&token=c260fc25-76ed-4900-8942-a4fc7362d0d1",
      "attributes": {
        "id": 216,
        "name": "Flowery Gold",
        "description": "<p>This is the finest and superior quality of Indian Gold Marble. </p>",
        "price": "90",
        "display_price": "90",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "floweryGoldIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          45,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "90",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F216_main.jpg?alt=media&token=c260fc25-76ed-4900-8942-a4fc7362d0d1"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 216,
              "name": "Flowery Gold",
              "sku": "floweryGoldIndianMarble",
              "price": "90",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "floweryGoldIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Gold Marble.</p>",
              "track_inventory": true,
              "display_price": "90",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "90"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F216_main.jpg?alt=media&token=c260fc25-76ed-4900-8942-a4fc7362d0d1"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F216_use1.jpg?alt=media&token=fa5ce860-98d3-431a-a201-e23105e6b425"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F216_use2.jpg?alt=media&token=02293073-4e65-4101-9698-7755af17b15d"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F216_slab.jpg?alt=media&token=d331d1f8-f4f3-46e0-8158-a4e37256968e"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 217,
      "type": "products",
      "major": "indianMarbles",
      "minor": "brownIndianMarble",
      "version": "makranaBrownAlbetaIndianMarble",
      "taxon_ids": [
        1008,
        2,
        46,
        49,
        41
      ],
      "display_price": "225",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F217_main.jpg?alt=media&token=a4efb15f-b602-4fec-b23b-8fce0fe29c84",
      "attributes": {
        "id": 217,
        "name": "Makrana Brown Albeta",
        "description": "<p>This is the finest and superior quality of Indian Brown Marble. </p>",
        "price": "225",
        "display_price": "225",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "makranaBrownAlbetaIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          46,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "225",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F217_main.jpg?alt=media&token=a4efb15f-b602-4fec-b23b-8fce0fe29c84"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 217,
              "name": "Makrana Brown Albeta",
              "sku": "makranaBrownAlbetaIndianMarble",
              "price": "225",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "makranaBrownAlbetaIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Brown Marble.</p>",
              "track_inventory": true,
              "display_price": "225",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "225"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F217_main.jpg?alt=media&token=a4efb15f-b602-4fec-b23b-8fce0fe29c84"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F217_slab.jpg?alt=media&token=bffefc96-33fe-4d97-a7db-0150b35b6e80"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F217_slab2.jpg?alt=media&token=dfdfaa44-92ad-4baf-99e4-13645beafd64"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 218,
      "type": "products",
      "major": "indianMarbles",
      "minor": "brownIndianMarble",
      "version": "makranaBrownDungriIndianMarble",
      "taxon_ids": [
        1008,
        2,
        46,
        49
      ],
      "display_price": "90",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F218_main.jpg?alt=media&token=987d792b-11e8-4b90-9394-2d679713a985",
      "attributes": {
        "id": 218,
        "name": "Makrana Dungri Albeta",
        "description": "<p>This is the finest and superior quality of Indian Brown Marble. </p>",
        "price": "90",
        "display_price": "90",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "makranaBrownDungriIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          46,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "90",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F218_main.jpg?alt=media&token=987d792b-11e8-4b90-9394-2d679713a985"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 218,
              "name": "Makrana Dungri Albeta",
              "sku": "makranaBrownDungriIndianMarble",
              "price": "90",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "makranaBrownDungriIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Brown Marble.</p>",
              "track_inventory": true,
              "display_price": "90",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "90"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F218_main.jpg?alt=media&token=987d792b-11e8-4b90-9394-2d679713a985"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F218_use1PNG.jpg?alt=media&token=ba676dc5-f24f-461b-bbf5-aa79f74c60a1"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F218_slab.jpg?alt=media&token=d76cf5c8-6a28-454f-8bd3-0a1074d6ee1e"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F218_slab2.jpg?alt=media&token=3d15479b-c189-496f-9b21-a3607478d929"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 219,
      "type": "products",
      "major": "indianMarbles",
      "minor": "brownIndianMarble",
      "version": "agariyaBrownIndianMarble",
      "taxon_ids": [
        1008,
        2,
        46,
        49
      ],
      "display_price": "40",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F219_main.jpg?alt=media&token=6aeb9514-85a7-4d8d-bae0-6d9f89ef7cf0",
      "attributes": {
        "id": 219,
        "name": "Agariya Brown",
        "description": "<p>This is the finest and superior quality of Indian Brown Marble. </p>",
        "price": "40",
        "display_price": "40",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "agariyaBrownIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          46,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "40",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F219_main.jpg?alt=media&token=6aeb9514-85a7-4d8d-bae0-6d9f89ef7cf0"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 219,
              "name": "Agariya Brown",
              "sku": "agariyaBrownIndianMarble",
              "price": "40",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "agariyaBrownIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Brown Marble.</p>",
              "track_inventory": true,
              "display_price": "40",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "40"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F219_main.jpg?alt=media&token=6aeb9514-85a7-4d8d-bae0-6d9f89ef7cf0"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F219_slab1.jpg?alt=media&token=da979a82-a042-454c-af97-e7c792661729"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F219_slab2.jpg?alt=media&token=796fe606-a212-4636-8474-b7bf834174c6"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F219_slab3.jpg?alt=media&token=244bee1e-8023-4a70-b6b5-e3f5a97cd9d7"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 220,
      "type": "products",
      "major": "indianMarbles",
      "minor": "brownIndianMarble",
      "version": "fantasyBrownIndianMarble",
      "taxon_ids": [
        1008,
        2,
        46,
        49
      ],
      "display_price": "36",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F220_main.jpg?alt=media&token=ddd172f8-fd07-443e-a739-471b817fb395",
      "attributes": {
        "id": 220,
        "name": "Fantasy Brown",
        "description": "<p>This is the finest and superior quality of Indian Brown Marble. </p>",
        "price": "36",
        "display_price": "36",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "fantasyBrownIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          46,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "36",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F220_main.jpg?alt=media&token=ddd172f8-fd07-443e-a739-471b817fb395"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 220,
              "name": "Fantasy Brown",
              "sku": "fantasyBrownIndianMarble",
              "price": "36",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "fantasyBrownIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Brown Marble.</p>",
              "track_inventory": true,
              "display_price": "36",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "36"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F220_main.jpg?alt=media&token=ddd172f8-fd07-443e-a739-471b817fb395"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F220_use1.jpg?alt=media&token=bfdeabc5-38bd-4363-8e82-ad803580eec5"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F220_use2.jpg?alt=media&token=8d71e0e8-4d2e-4d4f-82b2-a62e1a853d3c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F220_use3.jpg?alt=media&token=d251c894-fdcc-4530-b469-1d70e2a05cd4"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F220_slab.jpg?alt=media&token=b1af88e1-2ccf-470a-8f50-13090d154211"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 221,
      "type": "products",
      "major": "indianMarbles",
      "minor": "brownIndianMarble",
      "version": "torrentoBrownIndianMarble",
      "taxon_ids": [
        1008,
        2,
        46,
        49
      ],
      "display_price": "26",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F221_main.jpg?alt=media&token=3bd1a0ad-556b-4e1a-b073-6989f77f15f4",
      "attributes": {
        "id": 221,
        "name": "Torrento Brown",
        "description": "<p>This is the finest and superior quality of Indian Brown Marble. </p>",
        "price": "26",
        "display_price": "26",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "torrentoBrownIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          46,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "26",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F221_main.jpg?alt=media&token=3bd1a0ad-556b-4e1a-b073-6989f77f15f4"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 221,
              "name": "Torrento Brown",
              "sku": "torrentoBrownIndianMarble",
              "price": "26",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "torrentoBrownIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Brown Marble.</p>",
              "track_inventory": true,
              "display_price": "26",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "26"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F221_main.jpg?alt=media&token=3bd1a0ad-556b-4e1a-b073-6989f77f15f4"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F221_use1.jpg?alt=media&token=9e2d6db4-3c0c-4b12-9219-6df17bc0f9c7"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F221_use2.jpg?alt=media&token=42fa23d9-e7db-4312-95ae-697657cf0af5"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F221_slab.jpg?alt=media&token=192833e2-968a-4b08-afe9-e08d9854e087"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F221_slab2.jpg?alt=media&token=7e542a7b-2545-4053-9f9b-10ad257a031e"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 222,
      "type": "products",
      "major": "indianMarbles",
      "minor": "makranaIndianMarble",
      "version": "makranaWhiteIndianMarble",
      "taxon_ids": [
        1008,
        2,
        47,
        49,
        41
      ],
      "display_price": "360",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_main.jpg?alt=media&token=fd7dc8e3-bfec-403b-802d-499f5b079f50",
      "attributes": {
        "id": 222,
        "name": "Makrana White",
        "description": "<p>This is the finest and superior quality of Makrana White Marble. </p>",
        "price": "360",
        "display_price": "360",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "makranaWhiteIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          47,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "360",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_main.jpg?alt=media&token=fd7dc8e3-bfec-403b-802d-499f5b079f50"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 222,
              "name": "Makrana White",
              "sku": "makranaWhiteIndianMarble",
              "price": "360",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "makranaWhiteIndianMarble",
              "description": "<p>This is the finest and superior quality of Makrana White Marble.</p>",
              "track_inventory": true,
              "display_price": "360",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "360"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_main.jpg?alt=media&token=fd7dc8e3-bfec-403b-802d-499f5b079f50"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_use1.jpg?alt=media&token=e14c201a-f4fa-4391-98ce-25fe9c5c27d2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_use2.jpg?alt=media&token=31347dc4-87c2-4187-b540-fc1f95b5071a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_use3.jpg?alt=media&token=c275630c-0645-4d3f-9d12-1ee0b47c8972"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_use4.jpg?alt=media&token=1c521df9-d15f-447f-94b7-6479205cc220"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 6,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_slab.jpg?alt=media&token=2188a1ee-0bf2-41ab-bba2-e0db6312e15b"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 223,
      "type": "products",
      "major": "indianMarbles",
      "minor": "makranaIndianMarble",
      "version": "makranaDungriIndianMarble",
      "taxon_ids": [
        1008,
        2,
        47,
        49
      ],
      "display_price": "72",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F223_mainPNG.jpg?alt=media&token=716e3b98-e0eb-48d4-b9ff-aceaea50d4bc",
      "attributes": {
        "id": 223,
        "name": "Makrana Dungri",
        "description": "<p>This is the finest and superior quality of Makrana Dungri Marble. </p>",
        "price": "72",
        "display_price": "72",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "makranaDungriIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          47,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "72",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F223_mainPNG.jpg?alt=media&token=716e3b98-e0eb-48d4-b9ff-aceaea50d4bc"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 223,
              "name": "Makrana Dungri",
              "sku": "makranaDungriIndianMarble",
              "price": "72",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "makranaDungriIndianMarble",
              "description": "<p>This is the finest and superior quality of Makrana Dungri Marble.</p>",
              "track_inventory": true,
              "display_price": "72",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "72"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F223_mainPNG.jpg?alt=media&token=716e3b98-e0eb-48d4-b9ff-aceaea50d4bc"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F223_use1.jpg?alt=media&token=d354a7fe-ade8-4fb8-a4f6-0ffcea6c9210"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F223_use2.jpg?alt=media&token=8c952505-8c81-48bd-a539-d2dcf98a611a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F223_slabPNG.jpg?alt=media&token=6a98f4ef-90f8-44df-bdae-50ba10bdf121"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 224,
      "type": "products",
      "major": "indianMarbles",
      "minor": "makranaIndianMarble",
      "version": "kumariIndianMarble",
      "taxon_ids": [
        1008,
        2,
        47,
        49
      ],
      "display_price": "41",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F224_main.jpg?alt=media&token=f37dcf8c-d9cc-44ae-8586-5abc87972545",
      "attributes": {
        "id": 224,
        "name": "Kumari Marble",
        "description": "<p>This is the finest and superior quality of Kumari Marble. </p>",
        "price": "41",
        "display_price": "41",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "kumariIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          47,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "41",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F224_main.jpg?alt=media&token=f37dcf8c-d9cc-44ae-8586-5abc87972545"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 224,
              "name": "Kumari Marble",
              "sku": "kumariIndianMarble",
              "price": "41",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "kumariIndianMarble",
              "description": "<p>This is the finest and superior quality of Kumari Marble.</p>",
              "track_inventory": true,
              "display_price": "41",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "41"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F224_main.jpg?alt=media&token=f37dcf8c-d9cc-44ae-8586-5abc87972545"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F224_use1.jpg?alt=media&token=42e0fcf5-119a-4284-b21e-e6d8e7159403"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F224_slab.jpg?alt=media&token=ecadb7d0-0595-477d-a01a-378325f65471"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 225,
      "type": "products",
      "major": "indianMarbles",
      "minor": "makranaIndianMarble",
      "version": "albetaWhiteIndianMarble",
      "taxon_ids": [
        1008,
        2,
        47,
        49,
        41
      ],
      "display_price": "350-1000",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F225_main.jpg?alt=media&token=528c3124-38be-4a27-a71d-37e487ccc739",
      "attributes": {
        "id": 225,
        "name": "Albeta White",
        "description": "<p>This is the finest and superior quality of Albeta White. </p>",
        "price": "350-1000",
        "display_price": "350-1000",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "albetaWhiteIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          47,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "350-1000",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F225_main.jpg?alt=media&token=528c3124-38be-4a27-a71d-37e487ccc739"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 225,
              "name": "Albeta White",
              "sku": "albetaWhiteIndianMarble",
              "price": "350-1000",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "albetaWhiteIndianMarble",
              "description": "<p>This is the finest and superior quality of Albeta White.</p>",
              "track_inventory": true,
              "display_price": "350-1000",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "350-1000"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F225_main.jpg?alt=media&token=528c3124-38be-4a27-a71d-37e487ccc739"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F225_use1.jpg?alt=media&token=d9f6ded6-8bde-4ce6-a90f-5ac54d9e9290"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F225_use2.jpg?alt=media&token=e4deb173-0527-45f0-92bb-de10368a94b4"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F225_use3.jpg?alt=media&token=9ea41dbb-69eb-462d-9ea9-2c1d41b7b6a9"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F225_slab.jpg?alt=media&token=8b318ddf-5bd3-47d8-a85b-adeb54dd663c"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 226,
      "type": "products",
      "major": "indianMarbles",
      "minor": "makranaIndianMarble",
      "version": "tajWhiteIndianMarble",
      "taxon_ids": [
        1008,
        2,
        47,
        49,
        41
      ],
      "display_price": "550",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_main.jpg?alt=media&token=fd7dc8e3-bfec-403b-802d-499f5b079f50",
      "attributes": {
        "id": 226,
        "name": "Taj Mahal White",
        "description": "<p>This is the finest and superior quality of Taj Mahal White Marble. </p>",
        "price": "550",
        "display_price": "550",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "tajWhiteIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          47,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "550",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_main.jpg?alt=media&token=fd7dc8e3-bfec-403b-802d-499f5b079f50"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 226,
              "name": "Taj Mahal White",
              "sku": "tajWhiteIndianMarble",
              "price": "550",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "tajWhiteIndianMarble",
              "description": "<p>This is the finest and superior quality of Taj Mahal White Marble.</p>",
              "track_inventory": true,
              "display_price": "550",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "550"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_main.jpg?alt=media&token=fd7dc8e3-bfec-403b-802d-499f5b079f50"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_use1.jpg?alt=media&token=e14c201a-f4fa-4391-98ce-25fe9c5c27d2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_use2.jpg?alt=media&token=31347dc4-87c2-4187-b540-fc1f95b5071a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_use3.jpg?alt=media&token=c275630c-0645-4d3f-9d12-1ee0b47c8972"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_use4.jpg?alt=media&token=1c521df9-d15f-447f-94b7-6479205cc220"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 6,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F222_slab.jpg?alt=media&token=2188a1ee-0bf2-41ab-bba2-e0db6312e15b"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 227,
      "type": "products",
      "major": "indianMarbles",
      "minor": "blackIndianMarble",
      "version": "greyCarraraIndianMarble",
      "taxon_ids": [
        1008,
        2,
        48,
        49
      ],
      "display_price": "50",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F227_main.jpg?alt=media&token=24c3bb6b-23cb-44d5-a274-81489b866f9a",
      "attributes": {
        "id": 227,
        "name": "Grey Carrara Marble",
        "description": "<p>This is the finest and superior quality of Grey Carrara Marble. </p>",
        "price": "50",
        "display_price": "50",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "greyCarraraIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          48,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "50",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F227_main.jpg?alt=media&token=24c3bb6b-23cb-44d5-a274-81489b866f9a"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 227,
              "name": "Grey Carrara Marble",
              "sku": "greyCarraraIndianMarble",
              "price": "50",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "greyCarraraIndianMarble",
              "description": "<p>This is the finest and superior quality of Grey Carrara Marble.</p>",
              "track_inventory": true,
              "display_price": "50",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "50"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F227_main.jpg?alt=media&token=24c3bb6b-23cb-44d5-a274-81489b866f9a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F227_use1.jpg?alt=media&token=fa98faf0-7024-46c9-bf52-3a6c15ff7346"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F227_use2.jpg?alt=media&token=e915ec03-106d-4cfa-97f0-f11c488a8081"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F227_slab.jpg?alt=media&token=dbf07642-9b1f-4e44-9465-f9113b3140e2"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 228,
      "type": "products",
      "major": "indianMarbles",
      "minor": "blackIndianMarble",
      "version": "indianBlackIndianMarble",
      "taxon_ids": [
        1008,
        2,
        48,
        49
      ],
      "display_price": "50",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F228_main.jpg?alt=media&token=9697f877-d09b-4965-b77c-5e19dc6e0d47",
      "attributes": {
        "id": 228,
        "name": "Indian Black Marble",
        "description": "<p>This is the finest and superior quality of Indian Black Marble. </p>",
        "price": "50",
        "display_price": "50",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "indianBlackIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          48,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "50",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F228_main.jpg?alt=media&token=9697f877-d09b-4965-b77c-5e19dc6e0d47"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 228,
              "name": "Indian Black Marble",
              "sku": "indianBlackIndianMarble",
              "price": "50",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "indianBlackIndianMarble",
              "description": "<p>This is the finest and superior quality of Indian Black Marble.</p>",
              "track_inventory": true,
              "display_price": "50",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "50"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F228_main.jpg?alt=media&token=9697f877-d09b-4965-b77c-5e19dc6e0d47"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F228_use1.jpg?alt=media&token=4fea8440-7901-4127-8249-aa6f4e54228f"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F228_use2.jpg?alt=media&token=a160ffe5-380f-481f-b72b-46b8bd53410c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F228_slab.jpg?alt=media&token=b0b6a1f1-14dd-451b-8fcd-ffcb8a92d302"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 229,
      "type": "products",
      "major": "indianMarbles",
      "minor": "blackIndianMarble",
      "version": "indianBlackMarquinoIndianMarble",
      "taxon_ids": [
        1008,
        2,
        48,
        49,
        41
      ],
      "display_price": "135",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F229_main.jpg?alt=media&token=5df21948-1c19-4654-a4c6-424572a7043b",
      "attributes": {
        "id": 229,
        "name": "Black Marquino",
        "description": "<p>This is the finest and superior quality of Black Marquino. </p>",
        "price": "135",
        "display_price": "135",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "indianBlackMarquinoIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          48,
          49,
          41
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "135",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F229_main.jpg?alt=media&token=5df21948-1c19-4654-a4c6-424572a7043b"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 229,
              "name": "Black Marquino",
              "sku": "indianBlackMarquinoIndianMarble",
              "price": "135",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "indianBlackMarquinoIndianMarble",
              "description": "<p>This is the finest and superior quality of Black Marquino.</p>",
              "track_inventory": true,
              "display_price": "135",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "135"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F229_main.jpg?alt=media&token=5df21948-1c19-4654-a4c6-424572a7043b"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F229_use1.jpg?alt=media&token=f26a9723-49c5-4518-9818-611557c1b43d"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F229_use2.jpg?alt=media&token=6324985b-ad47-4ae7-9712-dfd1155fcbb1"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F229_use3.jpg?alt=media&token=36a87ca7-bfd8-4245-9e6c-239a0dece397"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F229_slab.jpg?alt=media&token=bf16d670-9c15-4887-bf06-1dcdb8f9725d"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 230,
      "type": "products",
      "major": "indianMarbles",
      "minor": "blackIndianMarble",
      "version": "nadiBlackIndianMarble",
      "taxon_ids": [
        1008,
        2,
        48,
        49
      ],
      "display_price": "27",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F230_main.jpg?alt=media&token=6f305313-50d9-4812-b430-8bc236d057d2",
      "attributes": {
        "id": 230,
        "name": "Nadi Black",
        "description": "<p>This is the finest and superior quality of Nadi Black. </p>",
        "price": "27",
        "display_price": "27",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "nadiBlackIndianMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          2,
          48,
          49
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "27",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F230_main.jpg?alt=media&token=6f305313-50d9-4812-b430-8bc236d057d2"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 230,
              "name": "Nadi Black",
              "sku": "nadiBlackIndianMarble",
              "price": "27",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "nadiBlackIndianMarble",
              "description": "<p>This is the finest and superior quality of Nadi Black.</p>",
              "track_inventory": true,
              "display_price": "27",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "27"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F230_main.jpg?alt=media&token=6f305313-50d9-4812-b430-8bc236d057d2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F230_use1.jpg?alt=media&token=581bbf8c-1030-4975-b37c-69793fcd019b"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F230_use2.jpg?alt=media&token=d3f35ffc-fa6d-4281-add3-6e0669b522cc"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F230_slab.jpg?alt=media&token=2be972a6-04aa-45e6-946b-924e5f365ab1"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 301,
      "type": "products",
      "major": "importedMarbles",
      "minor": "beigeImportedMarble",
      "version": "armaniBeigeMarble",
      "taxon_ids": [
        1008,
        3,
        58
      ],
      "display_price": "225",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F301_main.jpg?alt=media&token=e0b4f04a-fe61-48b7-9d66-a1b33b9cb1e5",
      "attributes": {
        "id": 301,
        "name": "Armani Beige",
        "description": "<p>This is the finest and superior quality of Armani Beige. </p>",
        "price": "225",
        "display_price": "225",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "armaniBeigeMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          58
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "225",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F301_main.jpg?alt=media&token=e0b4f04a-fe61-48b7-9d66-a1b33b9cb1e5"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 301,
              "name": "Armani Beige",
              "sku": "armaniBeigeMarble",
              "price": "225",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "armaniBeigeMarble",
              "description": "<p>This is the finest and superior quality of Armani Beige.</p>",
              "track_inventory": true,
              "display_price": "225",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "225"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F301_main.jpg?alt=media&token=e0b4f04a-fe61-48b7-9d66-a1b33b9cb1e5"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F301_use1.jpg?alt=media&token=db2a830c-c1e8-46c9-8d99-c89f8a58c1c0"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F301_use2.jpg?alt=media&token=a270522e-12dc-4cd0-a0a4-651486ebf8f2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F301_use3.jpg?alt=media&token=1b380cea-2014-40cf-a38d-f496e6740a33"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 302,
      "type": "products",
      "major": "importedMarbles",
      "minor": "beigeImportedMarble",
      "version": "biancoFusionMarble",
      "taxon_ids": [
        1008,
        3,
        58
      ],
      "display_price": "275",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F302_main.jpg?alt=media&token=b5995d98-aa9e-430c-83f4-b44accc60887",
      "attributes": {
        "id": 302,
        "name": "Bianco Fusion",
        "description": "<p>This is the finest and superior quality of Bianco Fusion. </p>",
        "price": "275",
        "display_price": "275",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "biancoFusionMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          58
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "275",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F302_main.jpg?alt=media&token=b5995d98-aa9e-430c-83f4-b44accc60887"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 302,
              "name": "Bianco Fusion",
              "sku": "biancoFusionMarble",
              "price": "275",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "biancoFusionMarble",
              "description": "<p>This is the finest and superior quality of Bianco Fusion.</p>",
              "track_inventory": true,
              "display_price": "275",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "275"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F302_main.jpg?alt=media&token=b5995d98-aa9e-430c-83f4-b44accc60887"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F302_use1.jpg?alt=media&token=8deb41c7-afa6-4955-9071-21288ea4ff78"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F302_use2.jpg?alt=media&token=e6b6a1ac-6dd1-4f37-9053-8de88b8b16ba"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F302_slab.jpg?alt=media&token=ed0d294e-64d5-4ac5-a900-fb840fba4a59"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 303,
      "type": "products",
      "major": "importedMarbles",
      "minor": "beigeImportedMarble",
      "version": "botticinoImportedMarble",
      "taxon_ids": [
        1008,
        3,
        58,
        57
      ],
      "display_price": "195",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F303_main.jpg?alt=media&token=c34b7e42-1443-448f-8531-ea8cf6173806",
      "attributes": {
        "id": 303,
        "name": "Botticino",
        "description": "<p>This is the finest and superior quality of Botticino. Botticino Marble is the best and premium quality Italian Marble.</p>",
        "price": "195",
        "display_price": "195",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "botticinoImportedMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          58,
          57
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "195",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F303_main.jpg?alt=media&token=c34b7e42-1443-448f-8531-ea8cf6173806"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 303,
              "name": "Botticino",
              "sku": "botticinoImportedMarble",
              "price": "195",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "botticinoImportedMarble",
              "description": "<p>This is the finest and superior quality of Botticino.<Botticino Marble is the best and premium quality Italian Marble./p>",
              "track_inventory": true,
              "display_price": "195",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "195"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F303_main.jpg?alt=media&token=c34b7e42-1443-448f-8531-ea8cf6173806"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F303_use1.jpg?alt=media&token=986c17fc-eb01-47d0-8d37-82bff2ed41b3"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F303_use2.jpg?alt=media&token=57992ea7-9c45-4783-ae83-a91a627a5d2b"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F303_use3.jpg?alt=media&token=1c7bff48-61de-43aa-b0e9-a366c7f72dd2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F303_slab.jpg?alt=media&token=b3311123-a324-4b69-81e5-7ed596e8c931"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 304,
      "type": "products",
      "major": "importedMarbles",
      "minor": "beigeImportedMarble",
      "version": "brecciaImportedMarble",
      "taxon_ids": [
        1008,
        3,
        58
      ],
      "display_price": "120",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F304_main.jpg?alt=media&token=a7c18b0c-d0e7-4e98-8671-acb145909dae",
      "attributes": {
        "id": 304,
        "name": "Breccia",
        "description": "<p>This is the finest and superior quality of Breccia. </p>",
        "price": "120",
        "display_price": "120",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "brecciaImportedMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          58
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "120",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F304_main.jpg?alt=media&token=a7c18b0c-d0e7-4e98-8671-acb145909dae"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 304,
              "name": "Breccia",
              "sku": "brecciaImportedMarble",
              "price": "120",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "brecciaImportedMarble",
              "description": "<p>This is the finest and superior quality of Breccia.</p>",
              "track_inventory": true,
              "display_price": "120",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "120"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F304_main.jpg?alt=media&token=a7c18b0c-d0e7-4e98-8671-acb145909dae"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F304_use1.jpg?alt=media&token=ca2aa39c-b3a3-4802-918c-8991632c4c55"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F304_use2.jpg?alt=media&token=5554fa5a-462c-427d-96a0-f1462d97677c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F304_use3.jpg?alt=media&token=54b10bd5-da55-48b8-a253-9cf96a9bcd01"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F304_slab.jpg?alt=media&token=57586db4-9232-454d-9149-b151521f5888"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 305,
      "type": "products",
      "major": "importedMarbles",
      "minor": "beigeImportedMarble",
      "version": "classicBeigeImportedMarble",
      "taxon_ids": [
        1008,
        3,
        58
      ],
      "display_price": "120",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F305_main.jpg?alt=media&token=17a8a8f4-d7ec-484c-8dc0-51159fad9edf",
      "attributes": {
        "id": 305,
        "name": "Classic Beige",
        "description": "<p>This is the finest and superior quality of Classic Beige. </p>",
        "price": "120",
        "display_price": "120",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "classicBeigeImportedMarble",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          58
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "120",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F305_main.jpg?alt=media&token=17a8a8f4-d7ec-484c-8dc0-51159fad9edf"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 305,
              "name": "Classic Beige",
              "sku": "classicBeigeImportedMarble",
              "price": "120",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "classicBeigeImportedMarble",
              "description": "<p>This is the finest and superior quality of Classic Beige.</p>",
              "track_inventory": true,
              "display_price": "120",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "120"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F305_main.jpg?alt=media&token=17a8a8f4-d7ec-484c-8dc0-51159fad9edf"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F305_use1.jpg?alt=media&token=d32ad454-8cc1-49fd-a957-626cc68c6029"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F305_use2.jpg?alt=media&token=ebd6b2b1-fcd7-48c0-8757-c671e44f7789"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F305_use3.jpg?alt=media&token=3019371e-ae4a-41ae-8774-56aef7d639ce"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F305_slab.jpg?alt=media&token=e0afdc89-dc69-43c0-a8fb-a03fba10a035"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 306,
      "type": "products",
      "major": "importedMarbles",
      "minor": "travertineImported",
      "version": "beigeTravertine",
      "taxon_ids": [
        1008,
        3,
        59,
        57
      ],
      "display_price": "228",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F306_main.jpg?alt=media&token=25e28b74-70fe-4a5c-a177-a992c292e60e",
      "attributes": {
        "id": 306,
        "name": "Beige Travertine",
        "description": "<p>This is the finest and superior quality of Beige Travertine. </p>",
        "price": "228",
        "display_price": "228",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "beigeTravertine",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          59
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "228",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F306_main.jpg?alt=media&token=25e28b74-70fe-4a5c-a177-a992c292e60e"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 306,
              "name": "Beige Travertine",
              "sku": "beigeTravertine",
              "price": "228",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "beigeTravertine",
              "description": "<p>This is the finest and superior quality of Beige Travertine.</p>",
              "track_inventory": true,
              "display_price": "228",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "228"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F306_main.jpg?alt=media&token=25e28b74-70fe-4a5c-a177-a992c292e60e"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F306_use1.jpg?alt=media&token=573b1048-27e0-4794-a840-33527621eada"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F306_use2.jpg?alt=media&token=05b10a30-b78f-4517-adee-b33d8ea614e2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F306_use3.jpg?alt=media&token=a8fff2dd-0492-4ea6-950b-cc3cc545c772"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F306_slab.jpg?alt=media&token=183412c7-7079-47d1-b360-55d38f0c9bf6"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 307,
      "type": "products",
      "major": "importedMarbles",
      "minor": "travertineImported",
      "version": "greySilverTravertine",
      "taxon_ids": [
        1008,
        3,
        59
      ],
      "display_price": "174",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F307_main.jpg?alt=media&token=b7f10ea5-a7f1-403c-ad60-67669c8a55f8",
      "attributes": {
        "id": 307,
        "name": "Grey Silver Travertine",
        "description": "<p>This is the finest and superior quality of Grey Silver Travertine. </p>",
        "price": "174",
        "display_price": "174",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "greySilverTravertine",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          59
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "174",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F307_main.jpg?alt=media&token=b7f10ea5-a7f1-403c-ad60-67669c8a55f8"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 307,
              "name": "Grey Silver Travertine",
              "sku": "greySilverTravertine",
              "price": "174",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "greySilverTravertine",
              "description": "<p>This is the finest and superior quality of Grey Silver Travertine.</p>",
              "track_inventory": true,
              "display_price": "174",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "174"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F307_main.jpg?alt=media&token=b7f10ea5-a7f1-403c-ad60-67669c8a55f8"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F307_use1.jpg?alt=media&token=b7b6b597-c0fe-42eb-b11c-0fc6271bf318"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F307_use2.jpg?alt=media&token=d7a940e3-a7a5-460c-9e9a-8850346d68fc"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F307_slab.jpg?alt=media&token=36786e4d-5333-41c5-8dc2-ec900bb71479"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 308,
      "type": "products",
      "major": "importedMarbles",
      "minor": "travertineImported",
      "version": "nocheTravertine",
      "taxon_ids": [
        1008,
        3,
        59
      ],
      "display_price": "118",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F308_main.jpg?alt=media&token=ae542706-6655-4dbf-aabb-11e3b8803663",
      "attributes": {
        "id": 308,
        "name": "Noche Travertine",
        "description": "<p>This is the finest and superior quality of Noche Travertine. </p>",
        "price": "118",
        "display_price": "118",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "nocheTravertine",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          59
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "118",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F308_main.jpg?alt=media&token=ae542706-6655-4dbf-aabb-11e3b8803663"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 308,
              "name": "Noche Travertine",
              "sku": "nocheTravertine",
              "price": "118",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "nocheTravertine",
              "description": "<p>This is the finest and superior quality of Noche Travertine.</p>",
              "track_inventory": true,
              "display_price": "118",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "118"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F308_main.jpg?alt=media&token=ae542706-6655-4dbf-aabb-11e3b8803663"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F308_use1.jpg?alt=media&token=5a43635f-a7c1-42df-b747-ba3953cf988a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F308_use2.jpg?alt=media&token=9e84296f-f2e4-4cd1-86a9-ac7d6aee1fde"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F308_use3.jpg?alt=media&token=eca287dd-e1e5-4e21-86c3-7c18309b86d7"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F308_slab.jpg?alt=media&token=b804a21b-c0cd-46ec-96a0-8fa8fd7b624e"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 309,
      "type": "products",
      "major": "importedMarbles",
      "minor": "travertineImported",
      "version": "redTravertine",
      "taxon_ids": [
        1008,
        3,
        59
      ],
      "display_price": "135",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F309_mainPNG.jpg?alt=media&token=b039652f-3601-4b08-8a7e-b5cb477fb682",
      "attributes": {
        "id": 309,
        "name": "Red Travertine",
        "description": "<p>This is the finest and superior quality of Red Travertine. </p>",
        "price": "135",
        "display_price": "135",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "redTravertine",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          59
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "135",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F309_mainPNG.jpg?alt=media&token=b039652f-3601-4b08-8a7e-b5cb477fb682"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 309,
              "name": "Red Travertine",
              "sku": "redTravertine",
              "price": "135",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "redTravertine",
              "description": "<p>This is the finest and superior quality of Red Travertine.</p>",
              "track_inventory": true,
              "display_price": "135",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "135"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F309_mainPNG.jpg?alt=media&token=b039652f-3601-4b08-8a7e-b5cb477fb682"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F309_use1.jpg?alt=media&token=a41b3aa5-e6a0-4316-a1db-52347e658bdf"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F309_use2.jpg?alt=media&token=cac136f9-e688-4480-89b6-aae8dc420894"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F309_use3PNG.jpg?alt=media&token=86b645eb-3c68-4413-b10d-374b4d910700"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F309_slab.jpg?alt=media&token=49ecae04-0fb1-4954-8a8c-5ff38ea4baa1"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 310,
      "type": "products",
      "major": "importedMarbles",
      "minor": "travertineImported",
      "version": "silverTravertine",
      "taxon_ids": [
        1008,
        3,
        59
      ],
      "display_price": "270",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F310_main.jpg?alt=media&token=28aca823-5119-4013-b469-f7c85ecbe6e1",
      "attributes": {
        "id": 310,
        "name": "Silver Travertine",
        "description": "<p>This is the finest and superior quality of Silver Travertine. </p>",
        "price": "270",
        "display_price": "270",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "silverTravertine",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          59
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "270",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F310_main.jpg?alt=media&token=28aca823-5119-4013-b469-f7c85ecbe6e1"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 310,
              "name": "Silver Travertine",
              "sku": "silverTravertine",
              "price": "270",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "silverTravertine",
              "description": "<p>This is the finest and superior quality of Silver Travertine.</p>",
              "track_inventory": true,
              "display_price": "270",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "270"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F310_main.jpg?alt=media&token=28aca823-5119-4013-b469-f7c85ecbe6e1"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F310_use1.jpg?alt=media&token=7245934a-37a1-42bb-bdc6-ccdaf0deda05"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F310_use2.jpg?alt=media&token=8ccbe5c1-b9cc-4b63-9e52-35665d572510"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F310_use3.jpg?alt=media&token=be130788-b3be-41b7-ad2b-82412246e852"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F310_slab.jpg?alt=media&token=c43f7810-188b-469c-8014-7f75b04f8ab1"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 311,
      "type": "products",
      "major": "importedMarbles",
      "minor": "exoticImported",
      "version": "azulMacaubas",
      "taxon_ids": [
        1008,
        3,
        56
      ],
      "display_price": "300",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F311_main.jpg?alt=media&token=cb028423-c41e-44ca-a66c-4e62d42281a0",
      "attributes": {
        "id": 311,
        "name": "Azul Macaubas",
        "description": "<p>This is the finest and superior quality of Azul Macaubas. </p>",
        "price": "300",
        "display_price": "300",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "azulMacaubas",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          56
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "300",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F311_main.jpg?alt=media&token=cb028423-c41e-44ca-a66c-4e62d42281a0"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 311,
              "name": "Azul Macaubas",
              "sku": "azulMacaubas",
              "price": "300",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "azulMacaubas",
              "description": "<p>This is the finest and superior quality of Azul Macaubas.</p>",
              "track_inventory": true,
              "display_price": "300",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "300"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F311_main.jpg?alt=media&token=cb028423-c41e-44ca-a66c-4e62d42281a0"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F311_use1.jpg?alt=media&token=19db0527-5e4e-45c0-b59d-6589754db308"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F311_use2.jpg?alt=media&token=d68e3270-0cb1-4d89-a926-1d9ae1eb564a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F311_use3.jpg?alt=media&token=5b064058-7efb-4614-96e1-f397bbb40bb0"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F311_slab.jpg?alt=media&token=0907c13b-337b-459e-9500-063492d4ac66"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 312,
      "type": "products",
      "major": "importedMarbles",
      "minor": "exoticImported",
      "version": "belvedere",
      "taxon_ids": [
        1008,
        3,
        56
      ],
      "display_price": "180",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F312_main.jpg?alt=media&token=47ca633d-9b2d-4d55-8b51-a4b86ff0f83e",
      "attributes": {
        "id": 312,
        "name": "Belvedere",
        "description": "<p>This is the finest and superior quality of Belvedere. </p>",
        "price": "180",
        "display_price": "180",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "belvedere",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          56
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "180",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F312_main.jpg?alt=media&token=47ca633d-9b2d-4d55-8b51-a4b86ff0f83e"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 312,
              "name": "Belvedere",
              "sku": "belvedere",
              "price": "180",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "belvedere",
              "description": "<p>This is the finest and superior quality of Belvedere.</p>",
              "track_inventory": true,
              "display_price": "180",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "180"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F312_main.jpg?alt=media&token=47ca633d-9b2d-4d55-8b51-a4b86ff0f83e"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F312_use1.jpg?alt=media&token=b1435db3-e867-476b-8516-7b1a5abeb25d"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F312_use2.jpg?alt=media&token=00ad55a6-576a-46a2-b3cd-feb68eb46512"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F312_slab.jpg?alt=media&token=f268aec8-c3be-47bf-abe5-048d91725996"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 313,
      "type": "products",
      "major": "importedMarbles",
      "minor": "exoticImported",
      "version": "blackMarinaceExoticImported",
      "taxon_ids": [
        1008,
        3,
        56
      ],
      "display_price": "135",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F313_main.jpg?alt=media&token=a7dfe712-dcb0-4858-b0d6-1f5a3e99de69",
      "attributes": {
        "id": 313,
        "name": "Black Marinace",
        "description": "<p>This is the finest and superior quality of Black Marinace. </p>",
        "price": "135",
        "display_price": "135",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "blackMarinaceExoticImported",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          56
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "135",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F313_main.jpg?alt=media&token=a7dfe712-dcb0-4858-b0d6-1f5a3e99de69"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 313,
              "name": "Black Marinace",
              "sku": "blackMarinaceExoticImported",
              "price": "135",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "blackMarinaceExoticImported",
              "description": "<p>This is the finest and superior quality of Black Marinace.</p>",
              "track_inventory": true,
              "display_price": "135",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "135"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F313_main.jpg?alt=media&token=a7dfe712-dcb0-4858-b0d6-1f5a3e99de69"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F313_use1.jpg?alt=media&token=a1998634-7977-4348-b9a7-d40936d1383c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F313_use2.jpg?alt=media&token=79fbb8e3-1c13-4d75-bf67-ec7e6fed551f"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F313_use3.jpg?alt=media&token=92211e46-887c-471d-9c51-36221d213b89"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F313_slab.jpg?alt=media&token=fc9f0d10-4a04-431b-a918-be845ffb2d43"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 314,
      "type": "products",
      "major": "importedMarbles",
      "minor": "exoticImported",
      "version": "blueBahiaExoticImported",
      "taxon_ids": [
        1008,
        3,
        56,
        12
      ],
      "display_price": "375",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F314_main.jpg?alt=media&token=fc75493a-6550-4ec7-8895-c8cec776c0d0",
      "attributes": {
        "id": 314,
        "name": "Blue Bahia",
        "description": "<p>This is the finest and superior quality of Blue Bahia. </p>",
        "price": "375",
        "display_price": "375",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "blueBahiaExoticImported",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          56,
          12
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "375",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F314_main.jpg?alt=media&token=fc75493a-6550-4ec7-8895-c8cec776c0d0"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 314,
              "name": "Blue Bahia",
              "sku": "blueBahiaExoticImported",
              "price": "375",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "blueBahiaExoticImported",
              "description": "<p>This is the finest and superior quality of Blue Bahia.</p>",
              "track_inventory": true,
              "display_price": "375",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "375"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F314_main.jpg?alt=media&token=fc75493a-6550-4ec7-8895-c8cec776c0d0"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F314_use1.jpg?alt=media&token=05960324-05b9-4d95-9965-e2935d2b512b"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F314_use2.jpg?alt=media&token=e951fdc9-afa4-4f63-b757-3c85891ccb09"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F314_slab.jpg?alt=media&token=9628ed8e-5245-48fc-b717-7d7cbf499bd0"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 315,
      "type": "products",
      "major": "importedMarbles",
      "minor": "exoticImported",
      "version": "botanicGreenExoticImported",
      "taxon_ids": [
        1008,
        3,
        56
      ],
      "display_price": "138",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F315_main.jpg?alt=media&token=fd249348-c6c6-4ff9-9c5b-bf183b84b7bc",
      "attributes": {
        "id": 315,
        "name": "Botanic Green",
        "description": "<p>This is the finest and superior quality of Botanic Green. </p>",
        "price": "138",
        "display_price": "138",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "botanicGreenExoticImported",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          56
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "138",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F315_main.jpg?alt=media&token=fd249348-c6c6-4ff9-9c5b-bf183b84b7bc"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 315,
              "name": "Botanic Green",
              "sku": "botanicGreenExoticImported",
              "price": "138",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "botanicGreenExoticImported",
              "description": "<p>This is the finest and superior quality of Botanic Green.</p>",
              "track_inventory": true,
              "display_price": "138",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "138"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F315_main.jpg?alt=media&token=fd249348-c6c6-4ff9-9c5b-bf183b84b7bc"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F315_use1.jpg?alt=media&token=d9256ebb-f7f0-4ad8-b6e0-60783e49ae05"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F315_use2.jpg?alt=media&token=bb31d960-21b5-4463-965e-22ab28cd4c8d"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F315_slab.jpg?alt=media&token=f58315de-fbe0-4501-82ff-2f5e037fb9d8"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 316,
      "type": "products",
      "major": "importedMarbles",
      "minor": "italianImported",
      "version": "dynaItalianImported",
      "taxon_ids": [
        1008,
        3,
        57
      ],
      "display_price": "200",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F316_main.jpg?alt=media&token=c9f3311c-7312-47a9-9be1-e26b6429abc9",
      "attributes": {
        "id": 316,
        "name": "Dyna Italian Marble",
        "description": "<p>Dyna Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look </p>",
        "price": "200",
        "display_price": "200",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "dynaItalianImported",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          57
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "200",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F316_main.jpg?alt=media&token=c9f3311c-7312-47a9-9be1-e26b6429abc9"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 316,
              "name": "Dyna Italian Marble",
              "sku": "dynaItalianImported",
              "price": "200",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "dynaItalianImported",
              "description": "<p>Dyna Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look</p>",
              "track_inventory": true,
              "display_price": "200",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "200"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F316_main.jpg?alt=media&token=c9f3311c-7312-47a9-9be1-e26b6429abc9"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F316_use1.jpg?alt=media&token=f07e1e19-4789-420c-86df-fc82a7245d1f"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F316_use2.jpg?alt=media&token=fd596a86-6955-4fa5-93b6-876a1cbee991"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F316_use3.jpg?alt=media&token=265a0274-b526-470e-b2c6-c8db11edeb2e"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F316_slab.jpg?alt=media&token=53f6e8be-d622-4d6c-941e-cc4c613746b8"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 317,
      "type": "products",
      "major": "importedMarbles",
      "minor": "italianImported",
      "version": "cremaMarfilItalianImported",
      "taxon_ids": [
        1008,
        3,
        57
      ],
      "display_price": "220",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F317_main.jpg?alt=media&token=afbfb31d-7ebc-4603-ab8c-c031fa8898ed",
      "attributes": {
        "id": 317,
        "name": "Crema Marfil",
        "description": "<p>Crema Marfil Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look </p>",
        "price": "220",
        "display_price": "220",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "cremaMarfilItalianImported",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          57
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "220",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F317_main.jpg?alt=media&token=afbfb31d-7ebc-4603-ab8c-c031fa8898ed"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 317,
              "name": "Crema Marfil",
              "sku": "cremaMarfilItalianImported",
              "price": "220",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "cremaMarfilItalianImported",
              "description": "<p>Crema Marfil Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look</p>",
              "track_inventory": true,
              "display_price": "220",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "220"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F317_main.jpg?alt=media&token=afbfb31d-7ebc-4603-ab8c-c031fa8898ed"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F317_use1.jpg?alt=media&token=594af653-c195-424e-a4f7-d70b00c4a45d"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F317_use2.jpg?alt=media&token=dc8fa758-22d6-4eb6-a16c-531696851a8c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F317_use3.jpg?alt=media&token=b8d3adfe-40a2-49da-ab0f-163ced3f5431"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F317_slab.jpg?alt=media&token=94e114cb-1624-4c4d-8509-e462a371e0e0"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 318,
      "type": "products",
      "major": "importedMarbles",
      "minor": "italianImported",
      "version": "perlatoRoyalItalianImported",
      "taxon_ids": [
        1008,
        3,
        57
      ],
      "display_price": "190",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F318_main.jpg?alt=media&token=b431d646-6a74-4754-854b-c05e984537b0",
      "attributes": {
        "id": 318,
        "name": "Perlato Royal",
        "description": "<p>Perlato Royal Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look </p>",
        "price": "190",
        "display_price": "190",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "perlatoRoyalItalianImported",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          57
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "190",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F318_main.jpg?alt=media&token=b431d646-6a74-4754-854b-c05e984537b0"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 318,
              "name": "Perlato Royal",
              "sku": "perlatoRoyalItalianImported",
              "price": "190",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "perlatoRoyalItalianImported",
              "description": "<p>Perlato Royal Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look</p>",
              "track_inventory": true,
              "display_price": "190",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "190"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F318_main.jpg?alt=media&token=b431d646-6a74-4754-854b-c05e984537b0"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F318_use1.jpg?alt=media&token=166459b6-82af-4971-a111-609bfab6761c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F318_use2.jpg?alt=media&token=e9ff83b8-d673-43d5-8ca0-531750851ab9"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F318_slab.jpg?alt=media&token=e3622b8f-6aa5-43a9-a4e3-c2b01ae4b440"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 319,
      "type": "products",
      "major": "importedMarbles",
      "minor": "italianImported",
      "version": "silviaItalianImported",
      "taxon_ids": [
        1008,
        3,
        57
      ],
      "display_price": "115",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F319_main.jpg?alt=media&token=135a21ab-6e30-467d-9859-524d8210b32f",
      "attributes": {
        "id": 319,
        "name": "Silvia",
        "description": "<p>Silvia Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look </p>",
        "price": "115",
        "display_price": "115",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "silviaItalianImported",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          57
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "115",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F319_main.jpg?alt=media&token=135a21ab-6e30-467d-9859-524d8210b32f"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 319,
              "name": "Silvia",
              "sku": "silviaItalianImported",
              "price": "115",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "silviaItalianImported",
              "description": "<p>Silvia Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look</p>",
              "track_inventory": true,
              "display_price": "115",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "115"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F319_main.jpg?alt=media&token=135a21ab-6e30-467d-9859-524d8210b32f"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F319_use1.jpg?alt=media&token=dd89c285-8a0d-418a-8475-c35ec20f91f6"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F319_use2.jpg?alt=media&token=4fb2f9d6-b44b-4f95-8f3a-6e82773f046a"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F319_use3.jpg?alt=media&token=2872c19c-22f3-43f9-bb85-26da459203e8"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F319_slab.jpg?alt=media&token=74529580-4d0c-4b72-9e50-464671f2a9b0"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 320,
      "type": "products",
      "major": "importedMarbles",
      "minor": "italianImported",
      "version": "vietnamWhiteItalianImported",
      "taxon_ids": [
        1008,
        3,
        57
      ],
      "display_price": "295",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F320_main.jpg?alt=media&token=a2347f90-527d-471a-a779-64215f7540c2",
      "attributes": {
        "id": 320,
        "name": "Vietnam White",
        "description": "<p>Vietnam White Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look </p>",
        "price": "295",
        "display_price": "295",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "vietnamWhiteItalianImported",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          3,
          57
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "295",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F320_main.jpg?alt=media&token=a2347f90-527d-471a-a779-64215f7540c2"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 320,
              "name": "Vietnam White",
              "sku": "vietnamWhiteItalianImported",
              "price": "295",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "vietnamWhiteItalianImported",
              "description": "<p>Vietnam White Marble - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look</p>",
              "track_inventory": true,
              "display_price": "295",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "295"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F320_main.jpg?alt=media&token=a2347f90-527d-471a-a779-64215f7540c2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F320_use1.jpg?alt=media&token=431d06e1-6b47-4442-918d-f9bdc2e478b9"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F320_use2.jpg?alt=media&token=a75ed29c-6f5f-43e8-9c0a-7b2bda6c9fa7"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F320_use3.jpg?alt=media&token=3911bd93-1145-4b0b-9590-135461760900"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 27,
                      "position": 5,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F320_slab.jpg?alt=media&token=8f4b3d8d-17ce-4dbb-a4fd-6d3bdc689a0a"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 402,
      "type": "products",
      "major": "granite",
      "minor": "blackGranite",
      "version": "absoluteBlackGranite",
      "taxon_ids": [
        1008,
        4,
        73
      ],
      "display_price": "200",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F402_main.jpg?alt=media&token=7f2a7fa2-187c-411c-bb37-8f14ccc5f567",
      "attributes": {
        "id": 402,
        "name": "Absolute Black",
        "description": "<p>Absolute Black Granite - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look </p>",
        "price": "200",
        "display_price": "200",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "absoluteBlackGranite",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          4,
          73
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹.",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "200",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F402_main.jpg?alt=media&token=7f2a7fa2-187c-411c-bb37-8f14ccc5f567"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 402,
              "name": "Absolute Black",
              "sku": "absoluteBlackGranite",
              "price": "200",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "absoluteBlackGranite",
              "description": "<p>Absolute Black Granite - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look</p>",
              "track_inventory": true,
              "display_price": "200",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "200"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F402_main.jpg?alt=media&token=7f2a7fa2-187c-411c-bb37-8f14ccc5f567"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F402_use1.jpg?alt=media&token=644249e0-6c0b-44ea-a648-f83911934eb2"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F402_use2.jpg?alt=media&token=f5056637-2fa1-44ed-bd26-0d8758786a5f"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F402_slab.jpg?alt=media&token=2be2951c-8648-4a06-9f18-e3be9a2858f3"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 403,
      "type": "products",
      "major": "granite",
      "minor": "whiteGranite",
      "version": "galaxyWhiteGranite",
      "taxon_ids": [
        1008,
        4,
        74
      ],
      "display_price": "200",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F403_main.jpg?alt=media&token=cd4897a8-872a-44b9-abae-b20415afd44e",
      "attributes": {
        "id": 403,
        "name": "Galaxy White",
        "description": "<p>Galaxy White Granite - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look </p>",
        "price": "200",
        "display_price": "200",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "galaxyWhiteGranite",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          4,
          74
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹.",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "200",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F403_main.jpg?alt=media&token=cd4897a8-872a-44b9-abae-b20415afd44e"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 403,
              "name": "Galaxy White",
              "sku": "galaxyWhiteGranite",
              "price": "200",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "galaxyWhiteGranite",
              "description": "<p>Galaxy White Granite - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look</p>",
              "track_inventory": true,
              "display_price": "200",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "200"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F403_main.jpg?alt=media&token=cd4897a8-872a-44b9-abae-b20415afd44e"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F403_use1.jpg?alt=media&token=1c6f8895-1f1d-4c79-979d-c4edad99515c"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F403_use2.jpg?alt=media&token=763e43b1-aaa1-48bc-af0b-73609c3ca8ab"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 26,
                      "position": 4,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F403_slab.jpg?alt=media&token=be1dfa27-38ab-480e-b696-1b48445fbfc4"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    },
    {
      "id": 401,
      "type": "products",
      "major": "granite",
      "minor": "redGranite",
      "version": "SindooriRedGranite",
      "taxon_ids": [
        1008,
        4,
        75
      ],
      "display_price": "150",
      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F401_main.jpg?alt=media&token=42ef1922-b271-46ce-8b98-6bf7fa7e8884",
      "attributes": {
        "id": 401,
        "name": "Sindoori Red",
        "description": "<p>Sindoori Red Granite - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look </p>",
        "price": "150",
        "display_price": "150",
        "available_on": "2019-11-04T00:00:00.000Z",
        "slug": "SindooriRedGranite",
        "meta_description": "",
        "meta_keywords": "",
        "shipping_category_id": 1,
        "taxon_ids": [
          1008,
          4,
          75
        ],
        "total_on_hand": 9,
        "avg_rating": "4.5",
        "reviews_count": 0,
        "currency": "₹.",
        "currency_symbol": "₹",
        "has_variants": false,
        "cost_price": "150",
        "is_favorited_by_current_user": false,
        "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F401_main.jpg?alt=media&token=42ef1922-b271-46ce-8b98-6bf7fa7e8884"
      },
      "relationships": {
        "master": {
          "data": {
            "type": "master",
            "id": 2
          }
        },
        "variants": {
          "data": []
        },
        "option_types": {
          "data": []
        },
        "product_properties": {
          "data": [
            {
              "type": "product_properties",
              "id": 28
            },
            {
              "type": "product_properties",
              "id": 29
            },
            {
              "type": "product_properties",
              "id": 30
            }
          ]
        },
        "classifications": {
          "data": [
            {
              "type": "classifications",
              "id": 2
            }
          ]
        }
      },
      "included": {
        "master": {
          "data": {
            "attributes": {
              "id": 401,
              "name": "Sindoori Red",
              "sku": "SindooriRedGranite",
              "price": "150",
              "weight": "0.0",
              "height": null,
              "width": null,
              "depth": null,
              "is_master": true,
              "slug": "SindooriRedGranite",
              "description": "<p>Sindoori Red Granite - The choice that can never go wrong. You can apply it in your bedroom, it will always give a super luxurious look</p>",
              "track_inventory": true,
              "display_price": "150",
              "options_text": "",
              "in_stock": true,
              "is_backorderable": true,
              "is_orderable": true,
              "total_on_hand": 9,
              "is_destroyed": false,
              "cost_price": "150"
            },
            "relationships": {
              "option_values": {
                "data": []
              },
              "images": {
                "data": [
                  {
                    "type": "images",
                    "id": 23
                  }
                ]
              }
            },
            "included": {
              "option_values": [],
              "images": [
                {
                  "data": {
                    "attributes": {
                      "id": 23,
                      "position": 1,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F401_main.jpg?alt=media&token=42ef1922-b271-46ce-8b98-6bf7fa7e8884"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 24,
                      "position": 2,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F401_use1.jpg?alt=media&token=73dd9413-8ff7-4feb-84ba-c6d075bf75f8"
                    }
                  }
                },
                {
                  "data": {
                    "attributes": {
                      "id": 25,
                      "position": 3,
                      "attachment_content_type": null,
                      "attachment_file_name": null,
                      "type": "Amazon::Image",
                      "attachment_updated_at": null,
                      "attachment_width": null,
                      "attachment_height": null,
                      "alt": "",
                      "viewable_type": "Amazon::Variant",
                      "viewable_id": 2,
                      "product_url": "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F401_slab.jpg?alt=media&token=7672b9c4-a183-4d89-a69a-699c0404ab90"
                    }
                  }
                }
              ]
            }
          }
        },
        "variants": [],
        "option_types": [],
        "product_properties": [
          {
            "data": {
              "attributes": {
                "id": 28,
                "product_id": 2,
                "property_id": 9,
                "value": "Messenger",
                "property_name": "Type"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 29,
                "product_id": 2,
                "property_id": 10,
                "value": "14 1/2' x 12' x 5'",
                "property_name": "Size"
              }
            }
          },
          {
            "data": {
              "attributes": {
                "id": 30,
                "product_id": 2,
                "property_id": 11,
                "value": "600 Denier Polyester",
                "property_name": "Material"
              }
            }
          }
        ],
        "classifications": [
          {
            "data": {
              "attributes": {
                "taxon_id": 3,
                "position": 2,
                "taxon": {
                  "id": 3,
                  "name": "Necklaces",
                  "pretty_name": "Gold Jewellery -> Necklaces",
                  "permalink": "Gold Jewellery/bags",
                  "parent_id": 1,
                  "taxonomy_id": 1,
                  "meta_title": "",
                  "meta_description": "",
                  "taxons": []
                }
              }
            }
          }
        ]
      }
    }
  ]
}""")
        val nd = oldtonewclassproduct(rd!!)
        CoroutineScope(Dispatchers.Main).launch {
            if (nd != null) {
                repository.addrandom(nd)
            }
        }



















        setContent {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                Text(
                    "Showing ${taxonP!!.taxons!!.size} Categories",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.whitneymedium)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary)
                )
                Spacer(modifier = Modifier.height(15.dp))
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()){
                    items(taxonP!!.taxons!!){ taxon-> catcard(taxon = taxon,this@CategoryActivity)} }

            }
    }





}
    @Composable
    fun catcard(taxon: Taxon,context:Context) {

        val intent= Intent(this,DashboardActivity::class.java). putExtra("id",taxon.id)

        Card(modifier= Modifier.clickable { setResult(1,intent);finish() }

            .fillMaxWidth()
            .height(50.dp),
            backgroundColor= Color(0x66dddddd),
            shape = RoundedCornerShape(8.dp), elevation = 0.dp
        ){
            Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp), contentAlignment = Alignment.CenterStart) {
                Text(
                    taxon.name,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.whitneymedium)),
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))


    }




}


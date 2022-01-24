package com.example.watchstoreapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watchstoreapp.Activities.LoginActivity
import com.example.watchstoreapp.R
import com.example.watchstoreapp.databinding.FragmentDetailsBinding
import com.example.watchstoreapp.databinding.FragmentProfileBinding
import com.example.watchstoreapp.model.categoryclass
import com.example.watchstoreapp.model.homeproducts
import com.example.watchstoreapp.model.oldtonewclassproduct
import com.example.watchstoreapp.model.productschema
import com.example.watchstoreapp.repository.StoreRepository
import com.example.watchstoreapp.utils.SharedPreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferenceManager: SharedPreferenceManager
    private lateinit var auth: FirebaseAuth
    @Inject
    lateinit var storeRepository: StoreRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferenceManager = SharedPreferenceManager(requireActivity())
        auth= Firebase.auth
        val userData:Array<String> = sharedPreferenceManager.getUserData()
        binding.buttonmaterial.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.name.visibility=View.GONE
        binding.mobile.visibility=View.GONE
        binding.buttonmaterial.visibility=View.GONE
        if (auth.currentUser==null){
            binding.buttonmaterial.visibility= View.VISIBLE
        } else{
            binding.apply {
            name.text = userData[0]
                name.visibility=View.VISIBLE
            mobile.text = userData[1]
                mobile.visibility=View.VISIBLE
             }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val nd= categoryclass.fromJson("""{
        "taxonomies_LandingPage": [
            {
                "id": 1,
                "name": "Categories",
                "root": {
                    "id": 1,
                    "name": "Categories",
                    "pretty_name": "Categories",
                    "permalink": "categories",
                    "parent_id": null,
                    "taxonomy_id": 1,
                    "meta_title": null,
                    "meta_description": null,
                    "taxons": [
                        {
                            "id": 2,
                            "name": "Indian Marbles",
                            "pretty_name": "Categories -> Indian Marbles",
                            "permalink": "indianMarbles",
                            "parent_id": 1,
                            "taxonomy_id": 1,
                            "meta_title": "",
                            "meta_description": "",
                            "taxons": [
                                {
                                    "id": 41,
                                    "name": "Exclusive Indian Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> Exclusive Indian Marbles",
                                    "permalink": "exclusiveIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 42,
                                    "name": "White Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> White Marbles",
                                    "permalink": "whiteIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 43,
                                    "name": "Pink Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> Pink Marbles",
                                    "permalink": "pinkIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 44,
                                    "name": "Green Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> Green Marbles",
                                    "permalink": "greenIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 45,
                                    "name": "Gold Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> Gold Marbles",
                                    "permalink": "goldIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 46,
                                    "name": "Brown Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> Brown Marbles",
                                    "permalink": "brownIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 47,
                                    "name": "Makrana Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> Makrana Marbles",
                                    "permalink": "makranaIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 48,
                                    "name": "Black Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> Black Marbles",
                                    "permalink": "blackIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 49,
                                    "name": "All Indian Marbles",
                                    "pretty_name": "Categories -> Indian Marbles -> All Indian Marbles",
                                    "permalink": "allIndianMarbles",
                                    "parent_id": 2,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                }
                                
                            ],
                            "icon": null
                        },
                        {
                            "id": 3,
                            "name": "Imported Marbles",
                            "pretty_name": "Categories -> Imported Marbles",
                            "permalink": "importedMarbles",
                            "parent_id": 1,
                            "taxonomy_id": 1,
                            "meta_title": "",
                            "meta_description": "",
                            "taxons": [
                                {
                                    "id": 56,
                                    "name": "Exotic Marbles",
                                    "pretty_name": "Categories -> Imported Marbles -> Exotic Imported Marbles",
                                    "permalink": "exoticImportedMarbles",
                                    "parent_id": 3,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 57,
                                    "name": "Italian Marbles",
                                    "pretty_name": "Categories -> Imported Marbles -> Italian Marbles",
                                    "permalink": "italianImportedMarbles",
                                    "parent_id": 3,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 58,
                                    "name": "Beige Marbles",
                                    "pretty_name": "Categories -> Imported Marbles -> Beige Marbles",
                                    "permalink": "beigeImportedMarbles",
                                    "parent_id": 3,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 59,
                                    "name": "Travertine",
                                    "pretty_name": "Categories -> Imported Marbles -> Travertine",
                                    "permalink": "travertineImportedMarbles",
                                    "parent_id": 3,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                }
                            ],
                            "icon": null
                        },
                        {
                            "id": 3,
                            "name": "Granite",
                            "pretty_name": "Categories -> Granite",
                            "permalink": "granite",
                            "parent_id": 1,
                            "taxonomy_id": 1,
                            "meta_title": "",
                            "meta_description": "",
                            "taxons": [
                                {
                                    "id": 73,
                                    "name": "Black Granite",
                                    "pretty_name": "Categories -> Granite -> Black Granite",
                                    "permalink": "blackGranite",
                                    "parent_id": 3,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 74,
                                    "name": "White Granite",
                                    "pretty_name": "Categories -> Granite -> White Granite",
                                    "permalink": "whiteGranite",
                                    "parent_id": 3,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                },
                                {
                                    "id": 75,
                                    "name": "Red Granite",
                                    "pretty_name": "Categories -> Granite -> Red Granite",
                                    "permalink": "redGranite",
                                    "parent_id": 3,
                                    "taxonomy_id": 1,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "icon": null
                                }
                            ],
                            "icon": null
                        }
                    ]
                }
            },
            {
                "id": 4,
                "name": "Brands",
                "root": {
                    "id": 15,
                    "name": "Brands",
                    "pretty_name": "Brands",
                    "permalink": "brands",
                    "parent_id": null,
                    "taxonomy_id": 4,
                    "meta_title": null,
                    "meta_description": null,
                    "taxons": [
                        {
                            "id": 16,
                            "name": "IKEA",
                            "pretty_name": "Brands -> IKEA",
                            "permalink": "brands\/ikea",
                            "parent_id": 15,
                            "taxonomy_id": 4,
                            "meta_title": "",
                            "meta_description": "",
                            "taxons": [
                                {
                                    "id": 43,
                                    "name": "Living",
                                    "pretty_name": "Brands -> IKEA -> Living",
                                    "permalink": "brands\/ikea\/living",
                                    "parent_id": 16,
                                    "taxonomy_id": 4,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "taxons": [],
                                    "icon": null
                                },
                                {
                                    "id": 44,
                                    "name": "New node",
                                    "pretty_name": "Brands -> IKEA -> New node",
                                    "permalink": "brands\/ikea\/new-node",
                                    "parent_id": 16,
                                    "taxonomy_id": 4,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "taxons": [],
                                    "icon": null
                                },
                                {
                                    "id": 45,
                                    "name": "Bedroom",
                                    "pretty_name": "Brands -> IKEA -> Bedroom",
                                    "permalink": "brands\/ikea\/bedroom",
                                    "parent_id": 16,
                                    "taxonomy_id": 4,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "taxons": [],
                                    "icon": null
                                },
                                {
                                    "id": 46,
                                    "name": "Study",
                                    "pretty_name": "Brands -> IKEA -> Study",
                                    "permalink": "brands\/ikea\/study",
                                    "parent_id": 16,
                                    "taxonomy_id": 4,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "taxons": [],
                                    "icon": null
                                }
                            ],
                            "icon": null
                        },
                        {
                            "id": 17,
                            "name": "T.J.maxx",
                            "pretty_name": "Brands -> T.J.maxx",
                            "permalink": "brands\/tjmaxx",
                            "parent_id": 15,
                            "taxonomy_id": 4,
                            "meta_title": "",
                            "meta_description": "",
                            "taxons": [
                                {
                                    "id": 47,
                                    "name": "Living",
                                    "pretty_name": "Brands -> T.J.maxx -> Living",
                                    "permalink": "brands\/tjmaxx\/living",
                                    "parent_id": 17,
                                    "taxonomy_id": 4,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "taxons": [],
                                    "icon": null
                                },
                                {
                                    "id": 48,
                                    "name": "Study",
                                    "pretty_name": "Brands -> T.J.maxx -> Study",
                                    "permalink": "brands\/tjmaxx\/study",
                                    "parent_id": 17,
                                    "taxonomy_id": 4,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "taxons": [],
                                    "icon": null
                                },
                                {
                                    "id": 49,
                                    "name": "Dining",
                                    "pretty_name": "Brands -> T.J.maxx -> Dining",
                                    "permalink": "brands\/tjmaxx\/dining",
                                    "parent_id": 17,
                                    "taxonomy_id": 4,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "taxons": [],
                                    "icon": null
                                },
                                {
                                    "id": 50,
                                    "name": "Bedroom",
                                    "pretty_name": "Brands -> T.J.maxx -> Bedroom",
                                    "permalink": "brands\/tjmaxx\/bedroom",
                                    "parent_id": 17,
                                    "taxonomy_id": 4,
                                    "meta_title": null,
                                    "meta_description": null,
                                    "taxons": [],
                                    "icon": null
                                }
                            ],
                            "icon": null
                        },
                        {
                            "id": 18,
                            "name": "Whirlpool",
                            "pretty_name": "Brands -> Whirlpool",
                            "permalink": "brands\/whirlpool",
                            "parent_id": 15,
                            "taxonomy_id": 4,
                            "meta_title": "",
                            "meta_description": "",
                            "taxons": [],
                            "icon": null
                        }
                    ]
                }
            },
            {
                "id": 3,
                "name": "New Arrivals",
                "root": {
                    "id": 12,
                    "name": "New Arrivals",
                    "pretty_name": "New Arrivals",
                    "permalink": "todays-deals",
                    "parent_id": null,
                    "taxonomy_id": 3,
                    "meta_title": null,
                    "meta_description": null,
                    "taxons": []
                }
            }
        ],
        "count": 3,
        "current_page": 1,
        "pages": 1
}
           
           
           
       """.trimIndent())



        GlobalScope.launch {
        storeRepository.addcatjson(nd!!)}

    }

}
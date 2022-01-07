package com.example.watchstoreapp.fragments

import MySliderImageAdapter
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchstoreapp.IBadgeUpdater
import com.example.watchstoreapp.INavListener
import com.example.watchstoreapp.R
import com.example.watchstoreapp.adapters.ICategoryListener
import com.example.watchstoreapp.adapters.IProductListener
import com.example.watchstoreapp.adapters.ProductAdapter
import com.example.watchstoreapp.databinding.FragmentHomeBinding
import com.example.watchstoreapp.model.*
import com.example.watchstoreapp.utils.Constant
import com.example.watchstoreapp.viewModel.StoreViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material.DropdownMenuItem as DropdownMenuItem1
import androidx.compose.ui.res.colorResource as colorResource
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


@AndroidEntryPoint
class HomeFragment : Fragment(), ICategoryListener, IProductListener {
    lateinit var listener: INavListener
    private val storeViewModel: StoreViewModel by activityViewModels()


    //    lateinit var catAdapter: CategoryAdapter
    lateinit var productAdapter: ProductAdapter
    lateinit var binding: FragmentHomeBinding
    lateinit var rv: View
    lateinit var iBadgeUpdater: IBadgeUpdater
    lateinit var offerInstance: ProductItem
    private var prodlivelist= MutableLiveData<ArrayList<ProductItem>>()
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            listener = activity as INavListener
            iBadgeUpdater = activity as IBadgeUpdater
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + "error implementing")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            delay(800)
            storeViewModel.catList.observe(requireActivity(), Observer { list ->
                Log.i("list size", list.size.toString())
//                catAdapter.updateList(list)
            })
            storeViewModel.productList.observe(requireActivity(), Observer { list ->
                Log.i("product list size", list.size.toString())
                prodlivelist.postValue(list)
                productAdapter.updateList(list)

            })


        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        listener.showHideNavigations(true)
        rv = binding.root
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            val params = rv.layoutParams
            params.height = Constant.getUsableHeight(requireActivity())
            rv.layoutParams = params
        } catch (e: Exception) {
            Log.d("ListView1", "2 GroupInfoFragment Height Exception: $e")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        storeViewModel.getAllCategories()
//        storeViewModel.getProductByCategory("1")

//        demo list of category
        val C1 = listOf<CategoryGrand>(

            CategoryGrand("Indian Marbles", listOf(child("Exclusive Indian Marbles"), child("Green Marbles"),child("Makarna White Marbles"))),
            CategoryGrand("adadfd", listOf(child("cfdssdf"), child("dadfdf"))),
            CategoryGrand("adadfd", listOf(child("cfdssdf"), child("dadfdf"))),
        )


        fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
            val adapter = MySliderImageAdapter()
            adapter.renewItems(images)
            imageSlider.setSliderAdapter(adapter)
            imageSlider.isAutoCycle = true
            imageSlider.startAutoCycle()
        }

        val imageSlider = binding.slider
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2Fbanner_2.jpg?alt=media&token=682fdc90-3e02-4f7a-a02b-ddecbd8a2671")
        imageList.add("https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2Fbanner-3.jpg?alt=media&token=e14beca9-9a11-4d2f-bcd5-acdb4e39e6cc")
        imageList.add("https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2Fbanner-4.jpg?alt=media&token=76003fea-e0e5-4b82-bb31-b8bcafd95092")
        setImageInSlider(imageList, imageSlider)



        binding.categoryCv.setContent {
            val prolist by prodlivelist.observeAsState(emptyList())
            Column {
                catmainbox(C1)
                Text(text = "New Arrivals", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_medium)), color = Color.Black, fontWeight=FontWeight.Bold,modifier = Modifier.padding(top = 18.dp, bottom = 12.dp))
                if (prolist.isEmpty()) {
                    CircularProgressIndicator(color = colorResource(id = R.color.primary))
                } else {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), horizontalArrangement = Arrangement.spacedBy(8.dp)){items(prolist){pro->productcard(pro = pro)} }
                }


                Text(text = "Trending Now", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_medium)), color = Color.Black,fontWeight=FontWeight.Bold, modifier = Modifier.padding(top = 18.dp, bottom = 12.dp))
                if (prolist.isEmpty()) {
                    CircularProgressIndicator(color = colorResource(id = R.color.primary))
                } else {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), horizontalArrangement = Arrangement.spacedBy(8.dp)){items(prolist){pro->productcard(pro = pro)} }
                }


                Text(text = "Top Rated Products", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_medium)), color = Color.Black,fontWeight=FontWeight.Bold, modifier = Modifier.padding(top = 18.dp, bottom = 12.dp))
                if (prolist.isEmpty()) {
                    CircularProgressIndicator(color = colorResource(id = R.color.primary))
                } else {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), horizontalArrangement = Arrangement.spacedBy(8.dp)){items(prolist){pro->productcard(pro = pro)} }
                }
                Spacer(modifier = Modifier.height(10.dp))

            }

        }

        
        


//        setupCategoryRV()
        setupProductRV()



        //delay(3000)




        

        iBadgeUpdater.updateBadge()
        createOfferInstance()
        binding.offerButton.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeToDetailsFragment(
                    offerInstance
                )
            )
        }
//        Log.i("binding.categoryRv.visibility",binding.categoryRv.visibility.toString())
//        Log.i("binding.productListRv.visibility",binding.productListRv.visibility.toString())
    }


//    override fun onResume() {
//        super.onResume()
//        Log.i("onResume","onResume")
//        Log.i("Height",Constant.getUsableHeight(requireActivity()).toString())
//        catAdapter.notifyDataSetChanged()
//        productAdapter.notifyDataSetChanged()
//    }


    //    private fun setupCategoryRV() {
//        catAdapter = CategoryAdapter(this)
//        binding.categoryRv.apply {
//            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
//            hasFixedSize()
//            adapter = catAdapter
//        }
//    }
    private fun setupProductRV() {
        productAdapter = ProductAdapter(this)
        binding.productListRv.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            hasFixedSize()
            adapter = productAdapter
            isNestedScrollingEnabled = false
        }
    }

    override fun onCategoryClick(category: CategoryItem) {
        storeViewModel.getProductByCategory(category.id!!)
    }

    override fun onProductItemClicked(product: ProductItem) {
        findNavController().navigate(HomeFragmentDirections.actionHomeToDetailsFragment(product))
    }

    private fun createOfferInstance() {
        offerInstance = ProductItem(
            "1",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry's standard dummy text.",
            "7",
            "https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2F320_use1.jpg?alt=media&token=431d06e1-6b47-4442-918d-f9bdc2e478b9",
            "Fossil Men's Golden Watch with Great Discount",
            "70",
            "200",
            6,
            false,

            )
    }


    @OptIn(ExperimentalAnimationApi::class)

    @Composable
    fun catmainbox(listgrand:List<CategoryGrand>) {
        var pexpanded by remember { mutableStateOf(false) }
        val icon = if (pexpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.ArrowDropDown

        Column() {


            OutlinedButton(
                onClick = { pexpanded = !pexpanded },
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, color = colorResource(id = R.color.primary)),

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        "SHOP BY CATEGORY",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.whitneymedium)),
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.primary)
                    )
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = colorResource(id = R.color.primary)
                    )
                }

            }

            AnimatedVisibility(
                visible = pexpanded,
                enter = fadeIn(
                    // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                    initialAlpha = 0.4f
                ),
                exit = fadeOut(
                    // Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 250)
                )
            ) {

            Row(modifier = Modifier
                .padding(start = 5.dp, top = 5.dp, bottom = 20.dp)
                .horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(5.dp) ) {
                listgrand.forEach(){
                    categoryGrand ->  grandtable(grand = categoryGrand)
                }

            }

           }
        }
    }

    @Composable
    fun grandtable(grand: CategoryGrand) {
        var expanded by remember { mutableStateOf(false) }
        val icon = if (expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.ArrowDropDown
            Box(Modifier.wrapContentWidth()) {


            Button(
                onClick = { expanded = !expanded },colors=ButtonDefaults.buttonColors(
                    colorResource(
                        id = R.color.primary
                    )
                ),
                modifier = Modifier
                    .width(180.dp)
                    .height(48.dp),

               
                shape = RoundedCornerShape(24.dp),

            ) {

                Text(
                    grand.gname,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.whitneymedium)),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(Color.White)
            ) {
                val color = colorResource(id = R.color.primary)
                grand.childlist.forEach { child ->
                    DropdownMenuItem1(onClick = { /*TODO*/ }, modifier = Modifier.drawBehind {
                        val strokeWidth = 10f
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            color=color,
                            Offset(60f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }) {
                        Text(
                            child.cname,
                            fontSize =16.sp,
                            fontFamily = FontFamily(Font(R.font.whitneymedium)),
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.primary)
                        )

                    }
                }
            }


        }
    }

    @Composable
    fun productcard(pro:ProductItem) {

        Card(
            modifier = Modifier
                .width(180.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp),
            elevation = 5.dp, backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .height(220.dp)
                    .width(180.dp)
            ) {
                CoilImage(
                    imageModel =pro.img,
                    modifier = Modifier
                        .height(180.dp)
                        .width(180.dp),contentScale= ContentScale.Crop,alignment=Alignment.Center,
                    // shows a shimmering effect when loading an image.
                    shimmerParams = ShimmerParams(
                        baseColor = Color.White,
                        highlightColor = Color.LightGray,
                        durationMillis = 350,
                        dropOff = 0.65f,
                        tilt = 20f
                    ),
                    // shows an error text message when request failed.
                    failure = {
                        Text(text = "image request failed.")
                    })

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(12.dp), contentAlignment = Alignment.Center,
                ) {
                    Text(text = pro.name?:"",
                        color = Color.Black,fontWeight=FontWeight.W400,fontFamily = FontFamily(Font(R.font.whitneymedium)),
                        fontSize = 14.sp)

                }


            }
        }
    }



}





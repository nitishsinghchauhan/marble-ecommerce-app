package com.example.watchstoreapp.fragments

import MySliderImageAdapter
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
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
import androidx.compose.material.DropdownMenuItem as DropdownMenuItem1
import androidx.compose.ui.res.colorResource as colorResource
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watchstoreapp.Activities.CategoryActivity
import com.example.watchstoreapp.repository.StoreRepository
import com.gowtham.ratingbar.RatingBar
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(){
    lateinit var listener: INavListener
    private val storeViewModel: StoreViewModel by activityViewModels()
    @Inject
    lateinit var repository: StoreRepository


    //    lateinit var catAdapter: CategoryAdapter
//    lateinit var productAdapter: ProductAdapter
    lateinit var binding: FragmentHomeBinding
    lateinit var rv: View
    lateinit var iBadgeUpdater: IBadgeUpdater
    lateinit var offerInstance: ProductItem
    private var prolist= mutableStateListOf<ProductsLandingPage>()
    private var catlist= mutableStateListOf<Taxon>()
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
        storeViewModel.getAllCategories()
        storeViewModel.getTopratedPro()
//        storeViewModel.getProductByCategory("1")
        GlobalScope.launch(Dispatchers.Main) {

            delay(1000)
            storeViewModel.catList.observe(requireActivity(), Observer { taxons ->
                 for (taxon in taxons){
                  catlist.add(taxon)}
//                Log.d("catlist", categoryTaxonLiveList.toString())


            })
            storeViewModel.productListTopRated.observe(requireActivity(), Observer { list ->
                for (ll in list){
                    prolist.add(ll)}
//                Log.i("product list size", list.size.toString())
//                prodlivelistToprated.postValue(list)
//                productAdapter.updateList(list)

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

//      list of category



        fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
            val adapter = MySliderImageAdapter()
            adapter.renewItems(images)
            imageSlider.setSliderAdapter(adapter)
            imageSlider.isAutoCycle = true
            imageSlider.startAutoCycle()
        }
        val imageBanner1="https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2FpromoBanner1.png?alt=media&token=7ebf8cc6-ea83-47f7-bfb9-59978f0b5c43"
        val imageBanner2="https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2FpromoBanner2.png?alt=media&token=00cf9fe8-b289-4652-a47d-32b88825246a"
        val imageSlider = binding.slider
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2Fbanner_2.jpg?alt=media&token=682fdc90-3e02-4f7a-a02b-ddecbd8a2671")
        imageList.add("https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2Fbanner-3.jpg?alt=media&token=e14beca9-9a11-4d2f-bcd5-acdb4e39e6cc")
        imageList.add("https://firebasestorage.googleapis.com/v0/b/amazon-1538415571879.appspot.com/o/amazon%2FlandingPage%2Fbanner-4.jpg?alt=media&token=76003fea-e0e5-4b82-bb31-b8bcafd95092")
        setImageInSlider(imageList, imageSlider)


//        binding.banner1.setContent { CoilImage(modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .clip(shape = RoundedCornerShape(3.dp)), imageModel = imageBanner1, contentScale = ContentScale.FillWidth) }
        binding.categoryCv.setContent {
            Column {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp)
                        .background(Color.White)) {
                catbox(catlist)}
                Text(text = "New Arrivals", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = Color.Black, fontWeight=FontWeight.Bold,modifier = Modifier.padding(top = 18.dp, bottom = 12.dp, start = 10.dp))
                if (prolist.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                        CircularProgressIndicator(color = colorResource(id = R.color.primary))
                    }
                } else {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(start=10.dp)){items(prolist){pro->productcardnew(pro = pro)} }
                }


                Text(text = "Trending Now", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = Color.Black,fontWeight=FontWeight.Bold, modifier = Modifier.padding(top = 28.dp, bottom = 12.dp, start = 10.dp))
                if (prolist.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                        CircularProgressIndicator(color = colorResource(id = R.color.primary))
                    }
                } else {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(start=10.dp)){items(prolist){pro->productcardnew(pro = pro)} }
                }


                Text(text = "Top Rated Products", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = Color.Black,fontWeight=FontWeight.Bold, modifier = Modifier.padding(top = 28.dp, bottom = 12.dp, start = 10.dp))
                if (prolist.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                        CircularProgressIndicator(color = colorResource(id = R.color.primary))}
                } else {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(start=10.dp)){items(prolist){pro->productcardnew(pro = pro)} }
                }
                Spacer(modifier = Modifier.height(30.dp))

            }

        }


        binding.cvcomp.setContent {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {


                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(3.dp, 3.dp, 0.dp, 0.dp))
                        .background(Color(0x66dddddd))
                        .padding(20.dp), horizontalAlignment = Alignment.Start) {

                    Text(modifier = Modifier.padding(5.dp),text = "Our experts are available 24/7:", fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = colorResource(id = R.color.secondary_text))
                    Row(modifier= Modifier
                        .wrapContentWidth()
                        .padding(5.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_DIAL);
                            intent.data = Uri.parse("tel:9082073532");
                            startActivity(intent);
                        }
                    ) { Icon(
                        Icons.Rounded.Call,
                        contentDescription = null, tint = colorResource(id = R.color.secondary_text), modifier = Modifier.size(18.dp))
                        Text(text = " 9082073532", fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = colorResource(id = R.color.secondary_text))
                    }
                    Row(modifier= Modifier
                        .wrapContentWidth()
                        .padding(5.dp)
                        .clickable {
                            val intent = Intent(
                                Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto", "abc@gmail.com", null
                                )
                            );
                            startActivity(intent);
                        }) { Icon(
                        Icons.Rounded.Email,
                        contentDescription = null, tint = colorResource(id = R.color.secondary_text), modifier = Modifier.size(18.dp))
                        Text(text = " Email Us", fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = colorResource(id = R.color.secondary_text))
                    }
                    Row(modifier= Modifier
                        .wrapContentWidth()
                        .padding(5.dp)
                        .clickable {
                            val phoneNumber = "+919082073532"
                            val url = "https://api.whatsapp.com/send?phone=$phoneNumber"
                            try {
                                val sendIntent = Intent(
                                    Intent.ACTION_SENDTO,
                                    Uri.parse("smsto:" + "" + phoneNumber)
                                );
                                sendIntent.setPackage("com.whatsapp");
                                startActivity(sendIntent);
                            } catch (e: Exception) {
                                e.printStackTrace();
                                Toast
                                    .makeText(
                                        activity,
                                        "Unable to fetch Whatsapp",
                                        Toast.LENGTH_LONG
                                    )
                                    .show();

                            }
                        }) { Icon(
                        painterResource(id = R.drawable.ic_icons8_whatsapp),
                        contentDescription = null, tint = colorResource(id = R.color.secondary_text), modifier = Modifier.size(18.dp))
                        Text(text = " Chat On Whatsapp", fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = colorResource(id = R.color.secondary_text))
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(modifier = Modifier.padding(5.dp),text = "CORPORATE OFFICE:\n" +
                            "EXA Marble\n" + "Makrana Road, Madanganj - Kishangarh,\n" + "Rajasthan, India, 305801",
                        fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = colorResource(id = R.color.secondary_text))

                    Spacer(modifier = Modifier.height(25.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Icon(painter = painterResource(id = R.drawable.ic_icons8_instagram), contentDescription = null, tint = Color(0xff6c757d),
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    startActivity(
                                        Intent(Intent.ACTION_VIEW).setData(
                                            Uri.parse("https://www.instagram.com/examarble/")
                                        )
                                    )
                                })
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(painter = painterResource(id = R.drawable.ic_icons8_facebook), contentDescription = null,tint = Color(0xff6c757d),
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    startActivity(
                                        Intent(Intent.ACTION_VIEW).setData(
                                            Uri.parse("https://www.facebook.com/EXA-Marble-101644845083761/")
                                        )
                                    )
                                })

                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp), horizontalArrangement = Arrangement.Center) {
                        Text(text = "Privacy \nPolicy ", fontSize = 13.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = colorResource(id = R.color.secondary_text),
                            modifier = Modifier.width(40.dp).clickable {
                                startActivity(
                                    Intent(Intent.ACTION_VIEW).setData(
                                        Uri.parse("https://examarble.com/info/Privacy%20Policy")
                                    )
                                )
                            }, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = " About\n Us", fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.whitneymedium)), color = colorResource(id = R.color.secondary_text),
                            modifier = Modifier.width(40.dp).clickable {
                                startActivity(
                                    Intent(Intent.ACTION_VIEW).setData(
                                        Uri.parse("https://examarble.com/info/About%20Us")
                                    )
                                )
                            }, textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.height(40.dp))


                }
                
//                CoilImage(modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight()
//                    .clip(shape = RoundedCornerShape(0.dp, 0.dp, 3.dp, 3.dp)), imageModel = imageBanner2, contentScale = ContentScale.FillWidth)
//
//
//              }



                }
        }

        
        


//        setupCategoryRV()
//        setupProductRV()



        //delay(3000)




        

        iBadgeUpdater.updateBadge()
        createOfferInstance()
//        binding.offerButton.setOnClickListener {
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeToDetailsFragment()
//            )
//        }
//        Log.i("binding.categoryRv.visibility",binding.categoryRv.visibility.toString())
//        Log.i("binding.productListRv.visibility",binding.productListRv.visibility.toString())
        fab1.setOnClickListener {
            val phoneNumber = "+919082073532"
            val url = "https://api.whatsapp.com/send?phone=$phoneNumber"
            try { val sendIntent =Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+""+phoneNumber));
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        }
        catch (e:Exception){
            e.printStackTrace();
            Toast.makeText(activity,"Unable to fetch Whatsapp",Toast.LENGTH_LONG).show();

        }
        }


    }






//    override fun onResume() {
//        super.onResume()
//        Log.i("onResume","onResume")
//        Log.i("Height",Constant.getUsableHeight(requireActivity()).toString())
//        catAdapter.notifyDataSetChanged()
//        productAdapter.notifyDataSetChanged()
//    }
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    var id =0L
    id= data!!.getLongExtra("id",0L)
    Log.d("onactivityresult",id.toString())
    if (id != 0L){
        findNavController().navigate(HomeFragmentDirections.actionHomeToSuccessFragment(id))
    }
}



//    override fun onCategoryClick(category: CategoryItem) {
//        storeViewModel.getProductByCategory(category.id!!)
//    }

//    override fun onProductItemClicked(product: ProductItem) {
//        findNavController().navigate(HomeFragmentDirections.actionHomeToDetailsFragment(product))
//    }

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


//    @OptIn(ExperimentalAnimationApi::class)
//
//    @Composable
//    fun catmainbox(listgrand:List<CategoryGrand>) {
//        var pexpanded by remember { mutableStateOf(false) }
//        val icon = if (pexpanded)
//            Icons.Filled.KeyboardArrowUp
//        else
//            Icons.Filled.ArrowDropDown
//
//        Column() {
//
//
//            OutlinedButton(
//                onClick = { pexpanded = !pexpanded },
//                modifier = Modifier.fillMaxWidth(),
//                border = BorderStroke(1.dp, color = colorResource(id = R.color.primary)),
//
//                ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(3.dp), horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                )
//                {
//                    Text(
//                        "SHOP BY CATEGORY",
//                        fontSize = 16.sp,
//                        fontFamily = FontFamily(Font(R.font.whitneymedium)),
//                        fontWeight = FontWeight.SemiBold,
//                        color = colorResource(id = R.color.primary)
//                    )
//                    Icon(
//                        imageVector = icon,
//                        contentDescription = null,
//                        tint = colorResource(id = R.color.primary)
//                    )
//                }
//
//            }
//
//            AnimatedVisibility(
//                visible = pexpanded,
//                enter = fadeIn(
//                    // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
//                    initialAlpha = 0.4f
//                ),
//                exit = fadeOut(
//                    // Overwrites the default animation with tween
//                    animationSpec = tween(durationMillis = 250)
//                )
//            ) {
//
//            Row(modifier = Modifier
//                .padding(start = 5.dp, top = 5.dp, bottom = 20.dp)
//                .horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(5.dp) ) {
//                listgrand.forEach(){
//                    categoryGrand ->  grandtable(grand = categoryGrand)
//                }
//
//            }
//
//           }
//        }
//    }
//
//    @Composable
//    fun grandtable(grand: CategoryGrand) {
//        var expanded by remember { mutableStateOf(false) }
//        val icon = if (expanded)
//            Icons.Filled.KeyboardArrowUp
//        else
//            Icons.Filled.ArrowDropDown
//            Box(Modifier.wrapContentWidth()) {
//
//
//            Button(
//                onClick = { expanded = !expanded },colors=ButtonDefaults.buttonColors(
//                    colorResource(
//                        id = R.color.primary
//                    )
//                ),
//                modifier = Modifier
//                    .width(180.dp)
//                    .height(40.dp),
//
//
//                shape = RoundedCornerShape(8.dp),
//
//            ) {
//
//                Text(
//                    grand.gname,
//                    fontSize = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.whitneymedium)),
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White
//                )
//                Icon(
//                    imageVector = icon,
//                    contentDescription = null,
//                    tint = Color.White
//                )
//            }
//
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//                modifier = Modifier
//                    .wrapContentWidth()
//                    .wrapContentHeight()
//                    .background(Color.White)
//            ) {
//                val color = colorResource(id = R.color.primary)
//                grand.childlist.forEach { child ->
//                    DropdownMenuItem1(onClick = { findNavController().navigate(HomeFragmentDirections.actionHomeToSuccessFragment()) }
//                        , modifier = Modifier.drawBehind {
//                        val strokeWidth = 5f
//                        val y = size.height - strokeWidth / 2
//                        drawLine(
//                            color=color,
//                            Offset(60f, y),
//                            Offset(size.width, y),
//                            strokeWidth
//                        )
//                    }) {
//                        Text(
//                            child.cname,
//                            fontSize =14.sp,
//                            fontFamily = FontFamily(Font(R.font.whitneymedium)),
//                            fontWeight = FontWeight.Normal,
//                            color = colorResource(id = R.color.secondary_text)
//                        )
//
//                    }
//                }
//            }
//
//
//        }
//    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun productcard(pro:ProductItem) {

        Card(
            modifier = Modifier
                .width(150.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp),
            elevation = 5.dp, backgroundColor = Color.White,
//            onClick = {(findNavController().navigate(HomeFragmentDirections.actionHomeToDetailsFragment()))},
        ) {
            Column(
                modifier = Modifier
                    .height(185.dp)
                    .width(150.dp)
            ) {
                CoilImage(
                    imageModel =pro.img,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp),contentScale= ContentScale.Crop,alignment=Alignment.Center,
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
                        .padding(8.dp), contentAlignment = Alignment.Center,
                ) {
                    Text(text = pro.name?:"",
                        color = Color.Black,fontWeight=FontWeight.W400,fontFamily = FontFamily(Font(R.font.whitneymedium)),
                        fontSize = 14.sp)

                }


            }
        }
    }

    @Composable
    fun catbox(grands: List<Taxon>){
        Card(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            backgroundColor= Color(0x66dddddd),
            shape = RoundedCornerShape(10.dp), elevation = 0.dp

        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    "SHOP BY CATEGORY",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.whitneymedium)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary)
                )
                Spacer(modifier = Modifier.height(15.dp))
                if (grands.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                        CircularProgressIndicator(color = colorResource(id = R.color.primary))
                    }
                } else {
                    LazyRow(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) { items(grands) { grand -> catimgview(grand = grand) } }
                }
            }
            }
        }



    @Composable
    fun catimgview(grand: Taxon){
        val intent=Intent(activity,CategoryActivity::class.java).apply { putExtra("taxonParent",grand) }
        Column(Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {

            CoilImage(
            imageModel =grand.icon,
            modifier = Modifier
                .size(75.dp)
                .clip(shape = CircleShape)
                .border(2.dp, color = Color.LightGray, CircleShape)
                .clickable { startActivityForResult(intent, 1) }
                ,
            contentScale= ContentScale.Crop,alignment=Alignment.Center,
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
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = grand.name,
            color = Color.Gray,fontWeight=FontWeight.W400,fontFamily = FontFamily(Font(R.font.whitneymedium)),
            fontSize = 12.sp)
        }

    }



    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun productcardnew(pro:ProductsLandingPage) {
        var loader by remember{ mutableStateOf(false)}

        Card(
            modifier = Modifier
                .clickable {
                    loader = true; GlobalScope.launch(Dispatchers.Main) {
                    val prodata = repository.getProductById(pro.id);Log.d(
                    "datacurrentdetails",
                    prodata.toString()
                );loader = false;
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeToDetailsFragment(
                            prodata
                        )
                    )
                }
                }
                .width(170.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp),
            elevation = 5.dp, backgroundColor = Color.White,
//            onClick = {(findNavController().navigate(SuccessFragmentDirections.actionSuccessFragmentToDetailsFragment()))},
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()

            ) {
                CoilImage(
                    imageModel =pro.attributes.productURL,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                    ,contentScale= ContentScale.Crop,alignment= Alignment.Center,
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
                        .background(Color(0x66dddddd))
                        .fillMaxWidth()
                        .padding(5.dp), contentAlignment = Alignment.TopCenter
                ) {
                    Text(text = pro.attributes.name,
                        color = colorResource(id = R.color.secondary_text),fontWeight= FontWeight.W400,fontFamily = FontFamily(
                            Font(
                                R.font.whitneymedium)
                        ), maxLines = 2,
                        fontSize = 16.sp)
                    if (loader){
                    CircularProgressIndicator(modifier = Modifier.size(18.dp),color = colorResource(id = R.color.primary))}

                }
                Row(
                    Modifier
                        .background(Color(0x66dddddd))
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 10.dp, top = 10.dp, bottom = 5.dp, end = 5.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(Modifier.wrapContentWidth()) {

                        Text(
                            text = "₹${pro.attributes.price}",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                            color = colorResource(id = R.color.primary)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "₹${pro.attributes.price}",
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                            color = colorResource(id = R.color.darker_gray)
                        )
                    }
                    RatingBar( padding = 0.dp, onRatingChanged = {}, inactiveColor = Color.LightGray, size=14.dp,value=pro.attributes.avgRating.toFloat(), isIndicator = true,onValueChange ={}  )

                }


            }
        }
    }


}









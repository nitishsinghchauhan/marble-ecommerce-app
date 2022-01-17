package com.example.watchstoreapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchstoreapp.R
import com.example.watchstoreapp.adapters.IProductListener
import com.example.watchstoreapp.adapters.ProductAdapter
import com.example.watchstoreapp.databinding.FragmentSuccessBinding
import com.example.watchstoreapp.model.ProductItem
import com.example.watchstoreapp.model.newAllProductsDetailPage
import com.example.watchstoreapp.viewModel.StoreViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.squareup.okhttp.internal.framed.Header
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_success.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//This is basically successfully product fetched fragment after clicking desired category
@AndroidEntryPoint
class SuccessFragment : Fragment() {
    private val storeViewModel: StoreViewModel by activityViewModels()
    val argmnts : SuccessFragmentArgs by navArgs()


    lateinit var binding: FragmentSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storeViewModel.getProductByCategory(argmnts.id)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccessBinding.inflate(layoutInflater)
        return binding.root
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prolist= MutableLiveData <List<newAllProductsDetailPage>>()

        GlobalScope.launch(Dispatchers.Main) {
            storeViewModel.productListCategorywise.observe(requireActivity(), Observer { list ->
                Log.i("product list size", list.size.toString())
                prolist.postValue(list)
            })
        }

        binding.cvpro.setContent {
            val prostate by prolist.observeAsState(initial = emptyList())

                if (prostate.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = colorResource(id = R.color.primary))
                    }
                } else {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp) ){
                        Text(
                            "Showing ${prostate.size} Products",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.whitneymedium)),
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.primary)
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                             LazyVerticalGrid( modifier = Modifier
                                 .fillMaxHeight()
                                 .fillMaxWidth(), contentPadding = PaddingValues(top=10.dp,bottom = 90.dp),cells = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                  items(prostate) { pro -> productcardcat(pro = pro) }
                             }


                         }
                }



        }


    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun productcardcat(pro:newAllProductsDetailPage) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
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
                        .fillMaxWidth()
                        .padding(5.dp), contentAlignment = Alignment.TopCenter,
                    ) {
                    Text(text = pro.attributes.name,
                        color = colorResource(id = R.color.secondary_text),fontWeight= FontWeight.W400,fontFamily = FontFamily(
                            Font(
                                R.font.whitneymedium)
                        ), maxLines = 2,
                        fontSize = 16.sp)

                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 10.dp, top = 10.dp, bottom = 5.dp), horizontalArrangement = Arrangement.Start) {
                    Text(text = "₹${pro.attributes.price}", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_bold)), color = colorResource(id = R.color.secondary_dark) )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "₹${pro.attributes.displayPrice.toInt()*115/100}", style = TextStyle(textDecoration = TextDecoration.LineThrough),fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_bold)), color = colorResource(id = R.color.darker_gray))


                }


            }
        }
    }









}
//    override fun onProductItemClicked(product: ProductItem) {
//        findNavController().navigate(SuccessFragmentDirections.actionSuccessFragmentToDetailsFragment(product))
//    }

//    private fun setupProductRV() {
//        productAdapter = ProductAdapter(this)
//        binding.productListRv1.apply {
//            layoutManager = GridLayoutManager(requireActivity(), 2)
//            hasFixedSize()
//            adapter = productAdapter
//        }
//    }
//}
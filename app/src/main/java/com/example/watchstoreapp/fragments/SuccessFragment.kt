package com.example.watchstoreapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_success.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//This is basically successfully product fetched fragment after clicking desired category
@AndroidEntryPoint
class SuccessFragment : Fragment() {
    private val storeViewModel: StoreViewModel by activityViewModels()


    lateinit var binding: FragmentSuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccessBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
            storeViewModel.productList.observe(requireActivity(), Observer { list ->
                Log.i("product list size", list.size.toString())

            })
        }

        binding.cvpro.setContent {



        }


    }

//    @OptIn(ExperimentalMaterialApi::class)
//    @Composable
//    fun productcard(pro:newAllProductsDetailPage) {
//
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight(),
//            shape = RoundedCornerShape(8.dp),
//            elevation = 5.dp, backgroundColor = Color.White, onClick = {(findNavController().navigate(
//                SuccessFragmentDirections.actionSuccessFragmentToDetailsFragment(pro)
//            ))},
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight()
//
//            ) {
//                CoilImage(
//                    imageModel =pro.attributes.productURL,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight()
//                        ,contentScale= ContentScale.Crop,alignment= Alignment.Center,
//                    // shows a shimmering effect when loading an image.
//                    shimmerParams = ShimmerParams(
//                        baseColor = Color.White,
//                        highlightColor = Color.LightGray,
//                        durationMillis = 350,
//                        dropOff = 0.65f,
//                        tilt = 20f
//                    ),
//                    // shows an error text message when request failed.
//                    failure = {
//                        Text(text = "image request failed.")
//                    })
//
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(5.dp), contentAlignment = Alignment.TopStart,
//                ) {
//                    Text(text = pro.name?:"",
//                        color = Color.Black,fontWeight= FontWeight.W400,fontFamily = FontFamily(
//                            Font(
//                                R.font.whitneymedium)
//                        ), maxLines = 2,
//                        fontSize = 14.sp)
//
//                }
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight()
//                        .padding(start = 10.dp, top = 10.dp, bottom = 5.dp)) {
//                    Text(text = "", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_bold)), color = colorResource(id = R.color.secondary_dark) )
//
//                }
//
//
//            }
//        }
//    }
//
//







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
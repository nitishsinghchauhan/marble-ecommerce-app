package com.example.watchstoreapp.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.util.lerp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.watchstoreapp.IBadgeUpdater
import com.example.watchstoreapp.INavListener
import com.example.watchstoreapp.R
import com.example.watchstoreapp.databinding.FragmentDetailsBinding
import com.example.watchstoreapp.model.ProductsLandingPage
import com.example.watchstoreapp.model.newAllProductsDetailPage
import com.example.watchstoreapp.model.productschema
import com.example.watchstoreapp.utils.Constant
import com.example.watchstoreapp.viewModel.StoreViewModel
import com.google.accompanist.pager.*
import com.gowtham.ratingbar.RatingBar
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.product_item.*
import kotlin.math.absoluteValue



//@AndroidEntryPoint
class DetailsFragment : Fragment() {
    lateinit var listener: INavListener
    private val args: DetailsFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailsBinding
    lateinit var rv:View
    private val storeViewModel: StoreViewModel by activityViewModels()
    lateinit var iBadgeUpdater:IBadgeUpdater
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            listener = activity as INavListener
            iBadgeUpdater = activity as IBadgeUpdater
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + "error implementing")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        listener.showHideNavigations(false)
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
        onBackPress()


        binding.cvdetails.setContent {
            horizontalScroll(data = args.productDetails)
        }



//        var product:ProductItem = ProductItem()
//        totalQuantity = product.quantity
//        binding.apply {
//            title.text = product.name
//            discountedPrice.text = "$"+product.offer
//            mrp.text = "$"+product.price
//            quantity.text = "1"
//            desc.text = product.description
//            Log.i("product.img",product.img.toString())
//                Glide.with(watchImage.context).load(product.img).into(watchImage)
//            if (product.isFavorite == true){
//                binding.fav.setImageResource(R.drawable.fav_selected)
//            }else{
//                binding.fav.setImageResource(R.drawable.fav)
//            }
//        }
//        binding.watchImage.setOnClickListener {
//            val intent = Intent(requireActivity(), FullScreenActivity::class.java)
//            intent.putExtra("imgPath",product.img)
//            requireActivity().startActivity(intent)
//        }
//        binding.back.setOnClickListener {
//           callHomeFragment()
//
//        }
//        binding.fav.setOnClickListener {
//            if (product.isFavorite == true){
//                binding.fav.setImageResource(R.drawable.fav)
//                product.isFavorite = false
//            }else{
//                binding.fav.setImageResource(R.drawable.fav_selected)
//                product.isFavorite = true
//            }
//            storeViewModel.updateFav(product)
//
//        }
//        binding.plus.setOnClickListener {
//            if (productCount!!<totalQuantity!!){
//                productCount= productCount!! + 1
//                binding.quantity.text = productCount.toString()
//            }
//            setTextColor()
//        }
//        binding.minus.setOnClickListener {
//            if (productCount!!>1){
//                productCount= productCount!! - 1
//                binding.quantity.text = productCount.toString()
//            }
//            setTextColor()
        }

//        binding.addToCart.setOnClickListener {
//            product.quantity = productCount
//            storeViewModel.addToCart(product)
//            Toast.makeText(requireActivity(),"Product added successfully in Cart", Toast.LENGTH_SHORT).show()
//            iBadgeUpdater.updateBadge()
//        }


//    }
//    private fun setTextColor(){
//        if(productCount==1){
//            binding.minus.setTextColor(Color.GRAY)
//            binding.plus.setTextColor(Color.WHITE)
//        }else if(productCount == totalQuantity){
//            binding.minus.setTextColor(Color.WHITE)
//            binding.plus.setTextColor(Color.GRAY)
//        }else{
//            binding.plus.setTextColor(Color.WHITE)
//            binding.minus.setTextColor(Color.WHITE)
//        }
//    }

    private fun callHomeFragment(){
        findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHome())
    }

    private fun onBackPress(){
        activity?.onBackPressedDispatcher?.addCallback(requireActivity(), object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.i("back pressed12","back pressed")
                callHomeFragment()

            }
        })
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun horizontalScroll(data : newAllProductsDetailPage){

        Column(Modifier.verticalScroll(rememberScrollState())) {
            val pagerState = rememberPagerState()
            HorizontalPager(
                count = data.images.size, modifier = Modifier
                    .fillMaxWidth()
                    .height(473.dp), state = pagerState
            ) { page ->
                Card(
                    Modifier
                        .graphicsLayer {
                            // Calculate the absolute offset for the current page from the
                            // scroll position. We use the absolute value which allows us to mirror
                            // any effects for both directions
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                            // We animate the scaleX + scaleY, between 85% and 100%
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }

                            // We animate the alpha, between 50% and 100%
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                ) {
                    Box() {

                        Card(
                            modifier = Modifier.fillMaxWidth(),

                            elevation = 5.dp
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(473.dp)
                                    .fillMaxWidth()
                            ) {
                                CoilImage(
                                    imageModel = data.images[page],
                                    contentDescription = "artist",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(473.dp),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop

                                )

                            }
                        }
                    }
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = colorResource(id = R.color.primary),
                inactiveColor = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp, bottom = 20.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0x66dddddd)),
//            onClick = {(findNavController().navigate(SuccessFragmentDirections.actionSuccessFragmentToDetailsFragment()))},
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()

                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp), contentAlignment = Alignment.TopStart
                    ) {
                        Text(
                            text = data.attributes.name,
                            color = colorResource(id = R.color.primary),
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.whitneymedium
                                )
                            ),
                            maxLines = 2,
                            fontSize = 18.sp
                        )

                    }

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 0.dp, top = 0.dp, bottom = 5.dp, end = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(Modifier.wrapContentWidth()) {

                            Text(
                                text = "₹${data.attributes.price}",
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                                color = colorResource(id = R.color.primary)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "₹${data.attributes.displayPrice}",
                                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                                color = colorResource(id = R.color.darker_gray)
                            )
                        }
                        RatingBar(
                            padding = 0.dp,
                            onRatingChanged = {},
                            inactiveColor = Color.LightGray,
                            size = 18.dp,
                            value = data.attributes.avgRating.toFloat(),
                            isIndicator = true,
                            onValueChange = {})

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(vertical = 5.dp), contentAlignment = Alignment.TopStart
                    ) {
                        Text(
                            text = data.attributes.description.removePrefix("<p>").removeSuffix("</p>"),
                            color = colorResource(id = R.color.secondary_text),
                            fontWeight = FontWeight.W400,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.whitneymedium
                                )
                            ),
                            fontSize = 16.sp
                        )

                    }
                    Spacer(modifier = Modifier.height(100.dp))


                }
            }
            

        }

    }


}
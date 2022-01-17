package com.example.watchstoreapp.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.util.lerp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.watchstoreapp.IBadgeUpdater
import com.example.watchstoreapp.INavListener
import com.example.watchstoreapp.R
import com.example.watchstoreapp.databinding.FragmentDetailsBinding
import com.example.watchstoreapp.utils.Constant
import com.example.watchstoreapp.viewModel.StoreViewModel
import com.google.accompanist.pager.*
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
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), verticalArrangement = Arrangement.Top) {
                horizontalScroll(imgs = args.productDetails.images)}
            Spacer(modifier = Modifier.height(100.dp))
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
    fun horizontalScroll(imgs:List<String>){

        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(), verticalArrangement = Arrangement.Top) {
            val pagerState = rememberPagerState()

            HorizontalPager(
                count = imgs.size, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                , state = pagerState
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
                            ).also{ scale ->
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
                            modifier = Modifier.width(320.dp),

                            elevation = 5.dp
                        ) {

                                CoilImage(
                                    imageModel = imgs[page],
                                    contentDescription = "artist",
                                    modifier= Modifier
                                        .width(320.dp)
                                        .aspectRatio(1f),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop

                                )
                        }
                    }
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor= colorResource(id = R.color.primary),
                inactiveColor = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }

    }



}
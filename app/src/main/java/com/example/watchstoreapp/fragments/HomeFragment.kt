package com.example.watchstoreapp.fragments

import MySliderImageAdapter
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watchstoreapp.IBadgeUpdater
import com.example.watchstoreapp.INavListener
import com.example.watchstoreapp.R
import com.example.watchstoreapp.adapters.*
import com.example.watchstoreapp.databinding.FragmentHomeBinding
import com.example.watchstoreapp.model.CategoryItem
import com.example.watchstoreapp.model.ProductItem
import com.example.watchstoreapp.model.SliderData
import com.example.watchstoreapp.utils.Constant
import com.example.watchstoreapp.viewModel.StoreViewModel
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//@AndroidEntryPoint
class HomeFragment : Fragment(), ICategoryListener, IProductListener {
    lateinit var listener: INavListener
    private val storeViewModel: StoreViewModel by activityViewModels()
    lateinit var catAdapter: CategoryAdapter
    lateinit var productAdapter: ProductAdapter
    lateinit var binding:FragmentHomeBinding
    lateinit var rv:View
    lateinit var iBadgeUpdater: IBadgeUpdater
    lateinit var offerInstance: ProductItem
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













        setupCategoryRV()
        setupProductRV()
        storeViewModel.getAllCategories()
        storeViewModel.getProductByCategory("1")
        //delay(3000)
        GlobalScope.launch(Dispatchers.Main) {
            delay(800)
            storeViewModel.catList.observe(requireActivity(), Observer { list->
                Log.i("list size", list.size.toString())
                catAdapter.updateList(list)
            })
            storeViewModel.productList.observe(requireActivity(), Observer { list->
                Log.i("product list size", list.size.toString())
                productAdapter.updateList(list)
            })


        }

        iBadgeUpdater.updateBadge()
        createOfferInstance()
        binding.offerButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToDetailsFragment(offerInstance))
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


    private fun setupCategoryRV() {
        catAdapter = CategoryAdapter(this)
        binding.categoryRv.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            adapter = catAdapter
        }
    }
    private fun setupProductRV() {
        productAdapter = ProductAdapter(this)
        binding.productListRv.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            hasFixedSize()
            adapter = productAdapter
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
            "https://firebasestorage.googleapis.com/v0/b/watchstore-9974c.appspot.com/o/watchImages%2Foffer.png?alt=media&token=9dd83b1c-d0d4-463a-ac18-ca5739fc3506",
            "Fossil Men's Golden Watch with Great Discount",
            "70",
            "200",
            6,
            false,

        )
    }

}
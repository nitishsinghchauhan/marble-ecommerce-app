package com.example.watchstoreapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchstoreapp.adapters.IProductListener
import com.example.watchstoreapp.adapters.ProductAdapter
import com.example.watchstoreapp.databinding.FragmentSuccessBinding
import com.example.watchstoreapp.model.ProductItem
import com.example.watchstoreapp.viewModel.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_success.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//This is basically successfully product fetched fragment after clicking desired category
@AndroidEntryPoint
class SuccessFragment : Fragment() {
    private val storeViewModel: StoreViewModel by activityViewModels()

    lateinit var productAdapter: ProductAdapter

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

                productAdapter.updateList(list) })
        }

        binding.cvpro.setContent {



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
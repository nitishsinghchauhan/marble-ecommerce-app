package com.example.watchstoreapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchstoreapp.model.*
//import com.example.watchstoreapp.model.CarttItem
import com.example.watchstoreapp.repository.StoreRepository
import com.example.watchstoreapp.utils.Constant
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val repository: StoreRepository,
private val db: FirebaseFirestore
):ViewModel() {

    private var _catList = MutableLiveData<List<Taxon>>()
    val catList: MutableLiveData<List<Taxon>> get() = _catList

    private var _productList = MutableLiveData<ArrayList<ProductItem>>()
    val productList: LiveData<ArrayList<ProductItem>> get() = _productList
    private var _productListCategorywise = MutableLiveData<ArrayList<newAllProductsDetailPage>>()
    val productListCategorywise: LiveData<ArrayList<newAllProductsDetailPage>> get() = _productListCategorywise

    private var _productListTopRated = MutableLiveData<ArrayList<ProductsLandingPage>>()
    val productListTopRated: LiveData<ArrayList<ProductsLandingPage>> get() = _productListTopRated




//    private var _cartList = MutableLiveData<ArrayList<CarttItem>>()
//    val cartList: LiveData<ArrayList<CarttItem>> get() = _cartList

    private var _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    init {
       // getAllCategories()
    }

    fun getAllCategories(){
        viewModelScope.launch {
            var data= ArrayList<Taxon>(emptyList())
            repository.getcategorytable()
                .get().addOnSuccessListener { result ->

                    val result=result.toObject(categoryclass::class.java)!!

                    result.taxonomiesLandingPage[0].root.taxons.forEach{listdata->data.add(listdata)}
                    _catList.postValue(data)

                }.addOnFailureListener {Log.d("getcategorytable","failed")

                }


//            Log.d("viewmodelcat", data.toString())
        }

    }

//    fun getProductByCategory(catId:String){
//        viewModelScope.launch {
////            var data:ArrayList<ProductItem>?=null
////            if(catId=="All"){
////                data = repository.getProductByCategory("All")
////            }else{
////                data = repository.getProductByCategory(catId)
////            }
//            var data = repository.getProductByCategory(catId)
//            delay(500)
//            _productList.postValue(data!!)
//        }
//    }

    fun updateFav(product:ProductItem){
        viewModelScope.launch {
            repository.updateFavorite(product)
        }

    }


//    fun getCartItems(){
//        viewModelScope.launch {
//            _cartList.postValue(repository.getCartItems())
//        }
//
//    }

//    fun addToCart(product:ProductItem){
//        viewModelScope.launch {
//            repository.addToCart(product)
//        }
//    }

//    fun deleteAllCartItems(list: ArrayList<CarttItem>){
//        viewModelScope.launch {
//            repository.deleteAllCardItems(list)
//        }
//    }
//    suspend fun deleteAllCartItems(list: ArrayList<CarttItem>){
//            repository.deleteAllCardItems(list)
//        }
//    fun deleteCartItem(productId:String){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteCartItem(productId)
//
//        }
//
//    }

    fun getTopratedPro(){
        viewModelScope.launch {
            var list = ArrayList<ProductsLandingPage>(emptyList())
                repository.getProductToprated().get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val data = document.toObject(ProductsLandingPage::class.java)
                        list.add(data)
                        Log.d("datarepo",document.toString())
                    }
                    _productListTopRated.postValue(list)
                    //Log.d("main product", list.size.toString())
                }
                .addOnFailureListener { exception ->
                    Log.w("main", "Error getting documents top rated products.", exception)
                }
//            delay(500)

        }
    }


    fun getProductByCategory(catId:Long){
        viewModelScope.launch {
            var list= ArrayList<newAllProductsDetailPage>(emptyList())
        repository.getProductByCategorywise(catId)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.toObject(newAllProductsDetailPage::class.java)
                    list.add(data)
                    Log.d("datarepo",document.toString())

                }
                _productListCategorywise.postValue(list)
//                Log.d("products main", _productListCategorywise.toString())
            }
            .addOnFailureListener { exception ->
                Log.w("main", "Error getting documents.", exception)
            }
//            delay(500)

        }
    }







    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)

        }
    }


    fun getUserDetails(mobile: String) {

            Log.i("mobile",mobile)

            val collection = db.collection(Constant.USER_TABEL)
            collection.whereEqualTo("mobile", mobile)
                .get()
                .addOnSuccessListener { documents ->

                    if (documents.size()==0){
                        //Log.d("No User", documents.size().toString())
                        _user.postValue(User("1","1"))
                        //Log.d("No User Dummy Data", documents.size().toString())
                    }
                    for (document in documents) {
                        var data = document.toObject(User::class.java)
                        _user.postValue(data)
                        //Log.d("valid User", data.toString())
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Error", "Error getting documents: ", exception)
                }




    }
}
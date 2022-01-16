package com.example.watchstoreapp.repository

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.material.TopAppBar
import com.example.watchstoreapp.model.*
//import com.example.watchstoreapp.model.CarttItem
import com.example.watchstoreapp.utils.Constant
import com.google.android.gms.tasks.Task
import com.google.api.Context
import com.google.api.Distribution
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.*

import javax.inject.Inject

class StoreRepository @Inject constructor(private val db:FirebaseFirestore){

    suspend fun getAllCategories():ArrayList<CategoryItem>{
        var list: ArrayList<CategoryItem> = ArrayList()

        db.collection(Constant.CATEGORY_TABEL)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
//                    Log.d("main", "${document.id} => ${document.data}")
                    //Log.d("main", "${document.id} => ${document.data["id"]} ==>${document.data["name"]}")
                    val data = document.toObject(CategoryItem::class.java)
                    //Log.d("main cat", data.toString())
                    //list.toMutableList().add(data)
                    list.add(data)

                }
                //Log.d("main cat", list.size.toString())
            }
            .addOnFailureListener { exception ->
                Log.w("main", "Error getting documents.", exception)
            }


        return list
    }

    suspend fun getProductByCategory(catId:String):ArrayList<ProductItem>{
        var list: ArrayList<ProductItem> = ArrayList()
        val collection = db.collection(Constant.PRODUCT_TABEL)
        //Log.i("catId",catId)
        val task: Task<QuerySnapshot>
        if (catId == "1"){
            task = collection.get()
        }else{
            task = collection.whereEqualTo("cat_id", catId).get()
        }
            task
            .addOnSuccessListener { result ->
                for (document in result) {
                val data = document.toObject(ProductItem::class.java)
                    //Log.d("main product", data.toString())
                    //list.toMutableList().add(data)
                    list.add(data)

                }
                //Log.d("main product", list.size.toString())
            }
            .addOnFailureListener { exception ->
                Log.w("main", "Error getting documents.", exception)
            }


        return list
    }



    suspend fun updateFavorite(product:ProductItem){
        val collection = db.collection(Constant.PRODUCT_TABEL)
        collection.document(product.id!!).set(product)
            .addOnSuccessListener { Log.d("Fav updated succesfully", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("Error", "Error writing document", e) }
    }

//    suspend fun getCartItems():ArrayList<CarttItem>{
//        var list: ArrayList<CarttItem> = ArrayList()
//
//        db.collection(Constant.CART_TABLE)
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                 val data = document.toObject(CarttItem::class.java)
//                    list.add(data)
//
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("main", "Error getting documents.", exception)
//            }
//
//
//        return list
//    }
//    suspend fun addToCart(product: ProductItem){
//        db.collection(Constant.CART_TABLE)
//            .document(product.id!!)
//            .set(product)
//            .addOnSuccessListener { Log.d("added succesfully", "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w("Error", "Error writing document", e) }
//    }
//
//    suspend fun deleteAllCardItems(list: ArrayList<CarttItem>){
//        withContext(Dispatchers.IO){
//        val collection = db.collection(Constant.CART_TABLE)
//        Log.i("list",list.size.toString())
//       for (item in list){
//           Log.i("item id",item.id.toString())
//           collection.document(item.id!!).delete()
//               .addOnSuccessListener { Log.d("deleted succesfully", "DocumentSnapshot successfully written!") }
//               .addOnFailureListener { e -> Log.w("Error", "Error writing document", e) }
//       }
//        }
//    }
//
//    suspend fun deleteCartItem(productId:String){
//        withContext(Dispatchers.IO){
//            val collection = db.collection(Constant.CART_TABLE)
//            collection.document(productId).delete()
//                .addOnSuccessListener {
//                    Log.d("Item deleted", "DocumentSnapshot successfully written!")
//
//                }
//                .addOnFailureListener { e -> Log.w("Error", "Error writing document", e) }
//        }
//
//    }


    suspend fun getcategorytable(): List<Taxon> {
         var catdata:MutableList<Taxon> = mutableListOf()
        db.collection("new").document("category").get().addOnSuccessListener { result ->

            val result=result.toObject(categoryclass::class.java)!!

            result.taxonomiesLandingPage[0].root.taxons.forEach{listdata->catdata.add(listdata)}
            Log.d("catrepo",catdata.toString())

        }.addOnFailureListener {Log.d("getcategorytable","failed")

        }

        Log.d("catrepo",catdata.toString())
        return catdata
    }





//    suspend fun addproductscompletedetails(rd:newproductschema){
//        Log.d("random", "addrandom: procss started")
//        withContext(Dispatchers.IO){
//            for((index,data) in rd.newallProductsDetailPage.withIndex()) {
//
//                db.collection("allproductsdetails").document(data.attributes.name).set(data)
//                    .addOnSuccessListener { Log.d("random", "addrandom: procss succeed $index") }
//                    .addOnFailureListener { Log.d("random", "addrandom: procss failed $index") }
//            }
//        }}
suspend fun addrandom(rd:newproductschema){
    Log.d("random", "addrandom: procss started")
    withContext(Dispatchers.IO){
        for((index,data) in rd.newallProductsDetailPage.withIndex()) {

            db.collection("allproductsdetails").document(data.attributes.name).set(data)
                .addOnSuccessListener { Log.d("random", "addrandom: procss succeed $index") }
                .addOnFailureListener { Log.d("random", "addrandom: procss failed $index") }
        }
    }}


    suspend fun addUser(user: User){
        withContext(Dispatchers.IO){
            val collection = db.collection(Constant.USER_TABEL)
            collection.document(user.mobile).set(user)
                .addOnSuccessListener {
                    Log.d("User added", "DocumentSnapshot successfully written!")
                }
                .addOnFailureListener { e -> Log.w("Error", "Error writing document", e) }
        }
    }


    suspend fun getUserByMobile(user: User){
        withContext(Dispatchers.IO){
            val collection = db.collection(Constant.USER_TABEL)
            collection.document(user.mobile).set(user)
                .addOnSuccessListener {
                    Log.d("User added", "DocumentSnapshot successfully written!")
                }
                .addOnFailureListener { e -> Log.w("Error", "Error writing document", e) }
        }
    }



}
package com.example.watchstoreapp.Activities

import android.app.Activity
import android.app.PendingIntent
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.beust.klaxon.Klaxon

import com.example.watchstoreapp.Activities.ui.theme.NavigationDrawerExampleNewTheme
import com.example.watchstoreapp.R
import com.example.watchstoreapp.model.Taxon
import com.example.watchstoreapp.model.categoryclass
import com.example.watchstoreapp.model.oldtonewclassproduct
import com.example.watchstoreapp.model.productschema
import com.example.watchstoreapp.repository.StoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.activity_full_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import com.example.watchstoreapp.viewModel.StoreViewModel as StoreViewModel
import javax.inject.Inject as Inject1


@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {
    @javax.inject.Inject lateinit var repository: StoreRepository
    var taxonP=intent.getParcelableExtra<Taxon>("taxonParent")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        CoroutineScope(Dispatchers.Main).launch {
//            if (nd != null) {
//                repository.addrandom(nd)
//            }
//        }



















        setContent {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                Text(
                    "Showing ${taxonP!!.taxons!!.size} Categories",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.whitneymedium)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary)
                )
                Spacer(modifier = Modifier.height(15.dp))
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()){
                    items(taxonP!!.taxons!!){ taxon-> catcard(taxon = taxon,this@CategoryActivity)} }

            }
    }





}
    @Composable
    fun catcard(taxon: Taxon,context:Context) {

        val intent= Intent(this,DashboardActivity::class.java). putExtra("id",taxon.id)

        Card(modifier= Modifier.clickable { setResult(1,intent);finish() }

            .fillMaxWidth()
            .height(50.dp),
            backgroundColor= Color(0x66dddddd),
            shape = RoundedCornerShape(8.dp), elevation = 0.dp
        ){
            Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp), contentAlignment = Alignment.CenterStart) {
                Text(
                    taxon.name,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.whitneymedium)),
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))


    }




}


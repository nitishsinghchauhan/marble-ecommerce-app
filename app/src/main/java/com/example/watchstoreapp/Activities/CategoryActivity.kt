package com.example.watchstoreapp.Activities

import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchstoreapp.Activities.ui.theme.NavigationDrawerExampleNewTheme
import com.example.watchstoreapp.R
import com.example.watchstoreapp.model.categoryclass
import com.example.watchstoreapp.repository.StoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_full_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject as Inject1


@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {
    @javax.inject.Inject lateinit var repository: StoreRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)













        setContent {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {
                Text(
                    "Showing 24 Categories",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.whitneymedium)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary)
                )
                Spacer(modifier = Modifier.height(15.dp))


            }
    }





}}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")


}


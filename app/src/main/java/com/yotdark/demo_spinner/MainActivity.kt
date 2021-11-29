package com.yotdark.demo_spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val spinner1: Spinner by lazy {
        findViewById(R.id.spinner1)
    }
    private val spinner2: Spinner by lazy {
        findViewById(R.id.spinner2)
    }
    private val spinner3: Spinner by lazy {
        findViewById(R.id.spinner3)
    }
    private val button: Button by lazy {
        findViewById(R.id.submit)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinner()
        submitClick()
    }


    private fun initSpinner(){

        val peopleList = mutableListOf<HashMap<String, Any>>().apply {
            add(hashMapOf("name" to "남자", "tag" to "man", "image" to R.drawable.spinner_item_man))
            add(hashMapOf("name" to "여자", "tag" to "woman", "image" to R.drawable.spinner_item_woman))
        }
        val regionList = mutableListOf<HashMap<String, Any>>().apply {
            add(hashMapOf("name" to "서울시", "tag" to "REGION_01"))
            add(hashMapOf("name" to "부산광역시", "tag" to "REGION_02"))
            add(hashMapOf("name" to "인천광역시", "tag" to "REGION_03"))
            add(hashMapOf("name" to "제주특별자치도", "tag" to "REGION_04"))
        }
        val ageList = mutableListOf<HashMap<String, Any>>().apply {
            add(hashMapOf("name" to "20살", "tag" to "20"))
            add(hashMapOf("name" to "21살", "tag" to "21"))
            add(hashMapOf("name" to "22살", "tag" to "22"))
            add(hashMapOf("name" to "23살", "tag" to "23"))
        }

        spinner1.adapter = SpinnerAdapter(this@MainActivity, R.layout.spinner_item, peopleList, MyConstant.SPINNER_OPTION1)
        spinner2.adapter = SpinnerAdapter(this@MainActivity, R.layout.spinner_item, regionList, MyConstant.SPINNER_OPTION2)
        spinner3.adapter = SpinnerAdapter(this@MainActivity, R.layout.spinner_item, ageList, MyConstant.SPINNER_OPTION2)
    }

    private fun submitClick(){
        button.setOnClickListener {
            Toast.makeText(this@MainActivity,
                "성별: ${(spinner1.selectedItem as HashMap<*,*>)["name"]}\n" +
                "거주지: ${(spinner2.selectedItem as HashMap<*,*>)["name"]}\n" +
                "나이: ${(spinner3.selectedItem as HashMap<*,*>)["name"]}\n", Toast.LENGTH_LONG).show()
        }
    }


}
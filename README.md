# 🏃[Android] Spinner demo

## ✏️ Study

## 🌍 Setting

- CompileSdk = 30
- Minsdk = 24
- TargetSdk = 30

## 🤨 Why

- 흔히 웹에서 상용하는 `select` 태그 를 모바일에서 구현하기 위해서 사용

## 🙋 Try 

- Spinner View 사용하기
- 옵션별 Spinner item custom 하기

## ✏️ Spinner Adapter 를 통해 이미지를 보여 줄 View 구성

> spinner_item.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/spinner_header"
        android:layout_width="30dp"
        android:layout_height="30dp"
        android:src="@drawable/spinner_item_woman"
        android:visibility="gone"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@id/spinner_content"
        app:layout_constraintBottom_toBottomOf="@id/spinner_content"
        tools:visibility="visible" />

    <TextView
        android:id="@+id/spinner_content"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="15dp"
        android:text="내용"
        android:textSize="20sp"
        app:layout_constraintStart_toEndOf="@id/spinner_header"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageView
        android:id="@+id/spinner_tail"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="15dp"
        android:src="@drawable/spinner_item_down_24"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="@id/spinner_content"
        app:layout_constraintBottom_toBottomOf="@id/spinner_content"
        android:visibility="gone"
        tools:visibility="visible"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```

## ✏️ Spinner Adapter Class 작성

```kotlin
package com.yotdark.demo_spinner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

class SpinnerAdapter(
    context: Context,
    @LayoutRes private val res: Int,
    private val infoList: MutableList<HashMap<String ,Any>>,
    private val option: String
): ArrayAdapter<HashMap<String, Any>>(context,res,infoList) {

    override fun getItem(position: Int) = infoList[position]
    override fun getCount() = infoList.size

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater
            .from(context)
            .inflate(res,parent,false)

        val spinnerHeader: ImageView = view.findViewById(R.id.spinner_header)
        val spinnerContent: TextView = view.findViewById(R.id.spinner_content)
        val spinnerTail: ImageView = view.findViewById(R.id.spinner_tail)

        spinnerContent.text = infoList[position]["name"].toString()
        spinnerTail.visibility = View.VISIBLE

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater
            .from(context)
            .inflate(res, parent, false)

        val spinnerHeader: ImageView = view.findViewById(R.id.spinner_header)
        val spinnerContent: TextView = view.findViewById(R.id.spinner_content)
        val spinnerTail: ImageView = view.findViewById(R.id.spinner_tail)

        when(option){
            MyConstant.SPINNER_OPTION1 -> {
                spinnerHeader.visibility = View.VISIBLE
                spinnerHeader.setImageDrawable(ContextCompat.getDrawable(context, infoList[position]["image"].toString().toInt()))
            }
        }

        spinnerContent.text = infoList[position]["name"].toString()

        return view
    }

}
```

### 옵션 값 받아 item layout custom 해보기

```kotlin
override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater
            .from(context)
            .inflate(res, parent, false)

        val spinnerHeader: ImageView = view.findViewById(R.id.spinner_header)
        val spinnerContent: TextView = view.findViewById(R.id.spinner_content)
        val spinnerTail: ImageView = view.findViewById(R.id.spinner_tail)

        when(option){
            MyConstant.SPINNER_OPTION1 -> {
                spinnerHeader.visibility = View.VISIBLE
                spinnerHeader.setImageDrawable(ContextCompat.getDrawable(context, infoList[position]["image"].toString().toInt()))
            }
        }

        spinnerContent.text = infoList[position]["name"].toString()

        return view
    }
```

- MyConstant.SPINNER_OPTION = 정의해 놓은 상수

### Spinner 선택 값 찍어보기

```kotlin
private fun submitClick(){
    button.setOnClickListener {
        Toast.makeText(this@MainActivity,
            "성별: ${(spinner1.selectedItem as HashMap<*,*>)["name"]}\n" +
            "거주지: ${(spinner2.selectedItem as HashMap<*,*>)["name"]}\n" +
            "나이: ${(spinner3.selectedItem as HashMap<*,*>)["name"]}\n", 																  Toast.LENGTH_LONG).show()
    }
}
```


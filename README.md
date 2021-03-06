# π[Android] Spinner demo

## βοΈ Study

- https://yotdark.tistory.com/51
- [[Android] μλλ‘μ΄λ Spinner(μ€νΌλ) μ¬μ©νκΈ° (1)](https://github.com/Junnnnnnnnnnn/android_study/blob/master/List_Spinner/%5BAndroid%5D%20%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%20Spinner(%EC%8A%A4%ED%94%BC%EB%84%88)%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0%20(1).md)
- [[Android] μλλ‘μ΄λ Spinner(μ€νΌλ) μ¬μ©νκΈ° (2)](https://github.com/Junnnnnnnnnnn/android_study/blob/master/List_Spinner/%5BAndroid%5D%20%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%20Spinner(%EC%8A%A4%ED%94%BC%EB%84%88)%20%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0%20(2).md)
## π Setting

- CompileSdk = 30
- Minsdk = 24
- TargetSdk = 30

## π€¨ Why

- νν μΉμμ μμ©νλ `select` νκ·Έ λ₯Ό λͺ¨λ°μΌμμ κ΅¬ννκΈ° μν΄μ μ¬μ©

## π Try 

- Spinner View μ¬μ©νκΈ°
- μ΅μλ³ Spinner item custom νκΈ°

## βοΈ Spinner Adapter λ₯Ό ν΅ν΄ μ΄λ―Έμ§λ₯Ό λ³΄μ¬ μ€ View κ΅¬μ±

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
        android:text="λ΄μ©"
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

## βοΈ Spinner Adapter Class μμ±

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

### μ΅μ κ° λ°μ item layout custom ν΄λ³΄κΈ°

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

- MyConstant.SPINNER_OPTION = μ μν΄ λμ μμ

### Spinner μ ν κ° μ°μ΄λ³΄κΈ°

```kotlin
private fun submitClick(){
    button.setOnClickListener {
        Toast.makeText(this@MainActivity,
            "μ±λ³: ${(spinner1.selectedItem as HashMap<*,*>)["name"]}\n" +
            "κ±°μ£Όμ§: ${(spinner2.selectedItem as HashMap<*,*>)["name"]}\n" +
            "λμ΄: ${(spinner3.selectedItem as HashMap<*,*>)["name"]}\n", 																  Toast.LENGTH_LONG).show()
    }
}
```


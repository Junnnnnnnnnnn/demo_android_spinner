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
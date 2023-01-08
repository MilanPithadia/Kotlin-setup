package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerAdapter<T : Any, B : ViewDataBinding>(
    private val dataSet: MutableList<T>,
    @LayoutRes val layoutID: Int,
    private val onBind: (T, B) -> Unit,
) : RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutID, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item, onBind)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun <T : Any, B : ViewDataBinding> bind(item: T, bindingInterface1: (T, B) -> Unit) {
            val binding = DataBindingUtil.bind<B>(view)!!
            bindingInterface1(item, binding)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun getItemList(): List<T> {
        return dataSet;
    }

    fun addData(list: List<T>, isClear: Boolean) {
        if (isClear)
            this.dataSet.clear()
        this.dataSet.addAll(list)
        notifyDataSetChanged()
    }
}
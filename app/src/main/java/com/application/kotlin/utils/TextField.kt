package com.application.kotlin.utils

import android.content.Context
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.application.kotlin.R

class TextField @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    var editText : EditText? = null
    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.text_field)
        val view: View = inflate(context,R.layout.text_field_item,this)


        editText = view.findViewById<EditText>(R.id.etText)
        val textView = view.findViewById<TextView>(R.id.tvPlaceholder)


        textView.text = attributes.getString(R.styleable.text_field_placeholder)
        editText!!.hint = attributes.getString(R.styleable.text_field_hint)
        editText!!.setText(attributes.getString(R.styleable.text_field_text))
        val isFocusable = attributes.getBoolean(R.styleable.text_field_focus, true)
        val isDropdown = attributes.getBoolean(R.styleable.text_field_isDropdown, false)
        val isDisablePlaceholder = attributes.getBoolean(R.styleable.text_field_isDisablePlaceholder, false)
        if (!isFocusable) {
            editText!!.isFocusableInTouchMode = false
            editText!!.isLongClickable = false
        }
        if (isDropdown) {
            editText!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)
            editText!!.compoundDrawablePadding = 10
        }
        if (isDisablePlaceholder) {
            textView.visibility = GONE
        }
        val type = attributes.getString(R.styleable.text_field_input_type)
        if (type == null) {

        }
        if (type == resources.getString(R.string.type_email)) {
            editText!!.inputType = 32
        } else if (type == resources.getString(R.string.type_phone)) {
            editText!!.inputType = 3
            editText!!.keyListener = DigitsKeyListener.getInstance("1234567890")
        }
    }
    fun setOnclick(v : View.OnClickListener) {
        this.editText!!.setOnClickListener {
            v.onClick(this)
        }
    }

    fun getText() : String
    {
        return editText!!.text.toString()
    }

    fun setText(s : String)
    {
        return editText!!.setText(s)
    }

}
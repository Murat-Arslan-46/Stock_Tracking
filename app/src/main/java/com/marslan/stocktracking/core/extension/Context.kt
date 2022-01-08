package com.marslan.stocktracking.core.extension

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.marslan.stocktracking.R
import com.marslan.stocktracking.services.model.Product
import com.marslan.stocktracking.databinding.DialogAddProductBinding
import com.marslan.stocktracking.databinding.DialogEditProductBinding
import java.lang.Exception

fun Context.toast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.addProductScreen(listener: (Product) -> Unit) {
    val view = DialogAddProductBinding.inflate(LayoutInflater.from(this), null, false)

    val dialog = AlertDialog.Builder(this)
        .setView(view.root)
        .setCancelable(false)
        .create()

    view.addProductName.foregroundTintList = ColorStateList.valueOf(getColor(R.color.red))
    view.addProductSuccess.backgroundTintList = ColorStateList.valueOf(getColor(R.color.grey_text))
    view.addProductSuccess.isEnabled = false

    view.addProductSuccess.setOnClickListener {
        val price =
            try {
                view.addProductPrice.text.toString()
                    .toDouble()
            } catch (e: Exception) {
                0.0
            }
        listener(Product((0..999999999).random(), view.addProductName.text.toString(), price, 0))
        dialog.dismiss()
    }
    view.addProductCancel.setOnClickListener { dialog.dismiss() }
    view.addProductName.addTextChangedListener {
        if (it.isNullOrBlank()) {
            view.addProductName.foregroundTintList = ColorStateList.valueOf(getColor(R.color.red))
            view.addProductSuccess.backgroundTintList =
                ColorStateList.valueOf(getColor(R.color.grey_text))
            view.addProductSuccess.isEnabled = false
        } else {
            view.addProductName.foregroundTintList =
                ColorStateList.valueOf(getColor(R.color.transparent))
            view.addProductSuccess.backgroundTintList =
                ColorStateList.valueOf(getColor(R.color.green))
            view.addProductSuccess.isEnabled = true
        }
    }
    view.addProductPrice.addTextChangedListener {
        if (!it.isNullOrBlank() && it.any { c -> c == ',' || c == ' ' || c == '-' }) {
            val result = it.toString()
                .replace(",", "")
                .replace(" ", "")
                .replace("-", "")
            view.addProductPrice.setText(result)
            view.addProductPrice.setSelection(result.length)
        }
    }
    dialog.window?.setBackgroundDrawableResource(R.drawable.background_radius_10dp_transparent)
    dialog.window?.attributes?.gravity = Gravity.TOP
    dialog.show()
}

fun Context.editProductScreen(product: Product, listener: (Product) -> Unit) {
    val view = DialogEditProductBinding.inflate(LayoutInflater.from(this), null, false)

    val dialog = AlertDialog.Builder(this)
        .setView(view.root)
        .setCancelable(false)
        .create()

    view.editProductName.setText(product.name)
    view.editProductPrice.setText(product.price.toString())
    view.editProductCount.setText(product.count.toString())
    view.editProductSuccess.setOnClickListener {
        product.price =
            try { view.editProductPrice.text.toString().toDouble() }
            catch (e: Exception) { product.price }
        product.count =
            try { view.editProductCount.text.toString().toInt() }
            catch (e: Exception) { product.count }
        product.name =
            if (view.editProductName.text.isNullOrBlank())
                product.name
            else
                view.editProductName.text.toString()
        listener(product)
        dialog.dismiss()
    }
    view.editProductCancel.setOnClickListener { dialog.dismiss() }
    view.editProductPrice.addTextChangedListener {
        if (!it.isNullOrBlank() && it.any { c -> c == ',' || c == ' ' || c == '-' }) {
            val result = it.toString()
                .replace(",", "")
                .replace(" ", "")
                .replace("-", "")
            view.editProductPrice.setText(result)
            view.editProductPrice.setSelection(result.length)
        }
    }
    view.editProductCount.addTextChangedListener {
        if (!it.isNullOrBlank() && it.any { c -> c == '.' || c == ',' || c == ' ' || c == '-' }) {
            val result = it.toString()
                .replace(",", "")
                .replace(" ", "")
                .replace("-", "")
                .replace(".", "")
            view.editProductCount.setText(result)
            view.editProductCount.setSelection(result.length)
        }
    }
    dialog.window?.setBackgroundDrawableResource(R.drawable.background_radius_10dp_transparent)
    dialog.window?.attributes?.gravity = Gravity.TOP
    dialog.show()

}
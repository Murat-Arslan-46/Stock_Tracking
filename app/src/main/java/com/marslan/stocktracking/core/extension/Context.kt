package com.marslan.stocktracking.core.extension

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.marslan.stocktracking.R
import com.marslan.stocktracking.core.component.CustomerSpinnerAdapter
import com.marslan.stocktracking.core.component.ProductSpinnerAdapter
import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.databinding.DialogAddCustomerBinding
import com.marslan.stocktracking.databinding.DialogAddOrderBinding
import com.marslan.stocktracking.databinding.DialogAddProductBinding
import com.marslan.stocktracking.databinding.DialogEditProductBinding
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.services.model.Product
import java.util.*

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
            try {
                view.editProductPrice.text.toString().toDouble()
            } catch (e: Exception) {
                product.price
            }
        product.count =
            try {
                view.editProductCount.text.toString().toInt()
            } catch (e: Exception) {
                product.count
            }
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

fun Context.addCustomerScreen(listener: (Customer) -> Unit) {
    val view = DialogAddCustomerBinding.inflate(LayoutInflater.from(this), null, false)

    val dialog = AlertDialog.Builder(this)
        .setView(view.root)
        .setCancelable(false)
        .create()

    view.addCustomerSuccess.setOnClickListener {
        listener(
            Customer(
                (0..999999999).random(),
                view.addCustomerName.text.toString(),
                "null",
                "null",
                listOf(view.addCustomerPhone.text.toString()),
                "null"
            )
        )
        dialog.dismiss()
    }
    view.addCustomerCancel.setOnClickListener { dialog.dismiss() }
    view.addCustomerPhone.addTextChangedListener {

        if (!it.isNullOrBlank() && (it.any { c -> c == '.' || c == ',' || c == ' ' || c == '-' } || it.length > 11)) {
            val result = it.toString()
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace(".", "")
                .replace(",", "")
                .replace("-", "")
            view.addCustomerPhone.setText(
                if (result.length < 11) result else result.substring(
                    0,
                    11
                )
            )
            view.addCustomerPhone.setSelection(result.length)
        }
    }
    dialog.window?.setBackgroundDrawableResource(R.drawable.background_radius_10dp_transparent)
    dialog.window?.attributes?.gravity = Gravity.TOP
    dialog.show()

}


fun Context.addOrderScreen(listener: (Order) -> Unit) {
    val view = DialogAddOrderBinding.inflate(LayoutInflater.from(this), null, false)
    var customer: Customer? = null
    var product: Product? = null
    val dialog = AlertDialog.Builder(this)
        .setView(view.root)
        .setCancelable(false)
        .create()
    view.customerSpinner.adapter =
        CustomerSpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            DataHelper.getCustomers()?.toTypedArray() ?: arrayOf()
        )
    view.customerSpinner.setOnItemClickListener { _, _, position, _ ->
        customer = DataHelper.getCustomers()?.get(position)
    }
    view.productSpinner.adapter =
        ProductSpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            DataHelper.getProducts()?.toTypedArray() ?: arrayOf()
        )
    view.customerSpinner.setOnItemClickListener { _, _, position, _ ->
        product = DataHelper.getProducts()?.get(position)
    }
    view.addOrderSuccess.setOnClickListener {
        listener(
            Order(
                (0..999999999).random(),
                customer?.id ?: 1,
                "me",
                listOf(product ?: Product(1, "w", 2.0, 10)),
                Date().time
            )
        )
        dialog.dismiss()
    }
    view.addOrderCancel.setOnClickListener { dialog.dismiss() }
    dialog.window?.setBackgroundDrawableResource(R.drawable.background_radius_10dp_transparent)
    dialog.window?.attributes?.gravity = Gravity.TOP
    dialog.show()

}
package com.marslan.stocktracking.core.helper

import com.marslan.stocktracking.services.model.*

class DataHelper {

    companion object{
        var dataModel: DataModel = DataModel()
        var onChangeListener: DataLiveHelper? = null

        fun setCustomers(customers: List<Customer>){
            dataModel.customers = customers
            onChangeListener?.onChangeCustomer()
        }
        fun getCustomers() = dataModel.customers

        fun setProducts(products: List<Product>){
            dataModel.products = products
            onChangeListener?.onChangeProduct()
        }
        fun getProducts() = dataModel.products

        fun setInvoices(invoices: List<Invoice>){
            dataModel.invoices = invoices
            onChangeListener?.onChangeInvoice()
        }
        fun getInvoices() = dataModel.invoices

        fun setOrders(orders: List<Order>){
            dataModel.orders = orders
            onChangeListener?.onChangeOrder()
        }
        fun getOrders() = dataModel.orders

        fun setSettings(settings: Settings){ dataModel.settings = settings }
        fun getSettings() = dataModel.settings
    }
}
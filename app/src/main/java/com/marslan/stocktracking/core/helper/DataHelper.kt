package com.marslan.stocktracking.core.helper

import com.marslan.stocktracking.services.model.*

class DataHelper {

    companion object{
        var dataModel: DataModel = DataModel()
        var onChangeListener: DataLiveHelper? = null

        fun loadData(dataModel: DataModel){ this.dataModel = dataModel }

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

        fun setInvoices(invoices: HashMap<String,List<Invoice>>){ dataModel.invoices = invoices }
        fun getInvoices(date: String) = dataModel.invoices?.get(date)

        fun setSettings(settings: Settings){ dataModel.settings = settings }
        fun getSettings() = dataModel.settings
    }
}
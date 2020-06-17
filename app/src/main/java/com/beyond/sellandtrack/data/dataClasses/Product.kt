package com.beyond.sellandtrack.data.dataClasses

import java.io.Serializable

data class Product(
    val item_code: String,
    val name: String,
    val price : String,
    val quantity : String,
    val userId : String
) : Serializable{
    constructor() : this ("","","","","")
}
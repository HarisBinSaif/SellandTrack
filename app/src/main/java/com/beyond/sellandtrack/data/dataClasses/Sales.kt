package com.beyond.sellandtrack.data.dataClasses

import java.io.Serializable
import java.util.*

data class Sales(
        val receipt_id: String,
        val customer_id: String?,
        val date: Date?,
        val products: List<Product>,
        val total: String,
        val uid: String
): Serializable {
    constructor() : this ("","",null, emptyList(),"","")
}
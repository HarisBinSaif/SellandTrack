package com.beyond.sellandtrack.data.dataClasses

import java.io.Serializable

data class Customer (
    val name : String,
    val number: String,
    val uid: String
) : Serializable {
    constructor() : this ("","","")
}
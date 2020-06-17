package com.beyond.sellandtrack.data.dataClasses

data class User(
    val uid: String?,
    val name: String,
    val email: String,
    val firstlogin:Boolean
){
    constructor() : this("", "", "", false)
}
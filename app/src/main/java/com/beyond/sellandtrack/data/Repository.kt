package com.beyond.sellandtrack.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.beyond.sellandtrack.data.dataClasses.Customer
import com.beyond.sellandtrack.data.dataClasses.Product
import com.beyond.sellandtrack.data.dataClasses.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*

class Repository {
    private var userInfo: User? = null
    private var auth = FirebaseAuth.getInstance()
    var user: FirebaseUser? = auth.currentUser
    private var firestoreDB: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(
                email,
                password
        ).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                user = auth.currentUser
            }

        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return user
    }

    fun getUserUid(): String {
        return user!!.uid
    }

    fun signOut() {
        auth.signOut()
    }


    suspend fun addProduct(product: Product) {
        firestoreDB.collection("products").document(product.item_code!!).set(product)
    }

    suspend fun getProducts(): Query {
        val reference = firestoreDB.collection("products").whereEqualTo("userId", getUserUid())
        return reference
    }

    suspend fun changeQuantity(itemCode: String, newQty: String) {
        firestoreDB.collection("products").document(itemCode).update("quantity", newQty)
    }

    suspend fun removeProduct(itemCode: String) {
        firestoreDB.collection("products").document(itemCode).delete()
    }


    suspend fun addCustomer(customer: Customer) {
        firestoreDB.collection("customers").document(customer.number).set(customer)
    }

    suspend fun getCustomers(): Query {
        val reference = firestoreDB.collection("customers").whereEqualTo("uid", getUserUid())
        return reference
    }

    suspend fun deleteCustomer(number: String) {
        firestoreDB.collection("customers").document(number).delete()
    }

    suspend fun getProductInfo(itemCode: String): Query {
       return firestoreDB.collection("products").whereEqualTo("item_code","1004")
    }


}
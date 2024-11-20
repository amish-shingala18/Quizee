package com.example.quizeeapp.data.helper

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthenticationHelper {
    companion object{
        val authHelper = AuthenticationHelper()
    }
    private val authentication = FirebaseAuth.getInstance()
    var user : FirebaseUser?=null
    suspend fun signUp(email:String, password:String): String? {
        var msg: String? = null
        try {
            authentication.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                msg = "success"
            }.addOnFailureListener {
                msg = it.message
            }.await()
        }catch (fb: FirebaseAuthUserCollisionException){
            msg = "Email Already Registered"
        }catch (e: FirebaseAuthException) {
            msg = "Please enter valid Email Id or Password"
        }catch (e: FirebaseNetworkException){
            msg = "Internet not Connected"
        }catch(e:Exception){
            msg = e.message
        }
        return msg
    }
    suspend fun signIn(email:String, password:String): String? {
        var msg: String? = null
        try {
            authentication.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                msg = "Success"
            }.addOnFailureListener {
                msg = it.message
            }.await()
        }catch (e: FirebaseAuthException){
            msg = "Please enter valid Email Id or Password"
        }catch (e: FirebaseNetworkException){
            msg = "Internet not Connected"
        }catch(e:Exception){
            msg = e.message
        }
        return msg
    }
    fun logout(){
        authentication.signOut()
    }
    fun checkUser()
    {
        user = authentication.currentUser
    }
}
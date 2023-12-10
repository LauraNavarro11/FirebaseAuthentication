package com.laura.firelogin.data

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.laura.firelogin.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthService @Inject constructor(private val firebaseAuth: FirebaseAuth, @ApplicationContext private val context:Context) {
    suspend fun login(user:String,password:String): FirebaseUser? {
        return firebaseAuth.signInWithEmailAndPassword(user,password).await().user
    }

    suspend fun register(email:String,password: String):FirebaseUser? {
        return suspendCancellableCoroutine {cancellableContinuation ->
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val user=it.user
                cancellableContinuation.resume(user)

            }
                .addOnFailureListener{
                    cancellableContinuation.resumeWithException(it)

                }
        }

    }

    fun isUserLogged(): Boolean {
        return  getCurrentUser()!= null

    }
    private fun getCurrentUser() = firebaseAuth.currentUser

    fun logout() {
        firebaseAuth.signOut()
    }

}
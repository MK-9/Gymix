package com.gymix.survey.signInsignUp

import androidx.compose.runtime.Immutable

sealed class User {
    @Immutable
    data class LoggedInUser(val email: String) : User()
    object GuestUser : User()
    object NoUserLoggedIn : User()
}

object UserRepository {
    private var _user: User = User.NoUserLoggedIn
    val user get() = _user

    fun signIn(email: String, password: String) {
        _user = User.LoggedInUser(email)
    }

    fun signUp(email: String, password: String) {
        _user = User.LoggedInUser(email)
    }

    fun signInAsGuest() {
        _user = User.GuestUser
    }
}
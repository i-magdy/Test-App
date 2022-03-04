package com.devwarex.testapp.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

class UserModel(){

    var name: String = ""
    var email: String = ""
    var uid: String = ""
    var role: String = "customer"
    @ServerTimestamp
    val timestamp: Date? = null

    constructor(
        name: String,
        email: String,
        uid: String
    ): this() {
      this.name = name
      this.email = email
      this.uid = uid

    }
}

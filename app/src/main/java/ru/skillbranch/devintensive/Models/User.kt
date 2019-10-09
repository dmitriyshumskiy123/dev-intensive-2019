package ru.skillbranch.devintensive.Models

import ru.skillbranch.devintensive.Utils.Utils
import java.util.*
import kotlin.concurrent.fixedRateTimer

data class User (
    val id: String,
    val firstName: String?,
    var lastName: String?,
    val avatar: String?,
    val rating: Int = 0,
    val respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
    )
{

    constructor(id: String, firstName: String?, lastName: String?): this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null)
    constructor(id:String): this(id, "John", "Doe $id")
    init {
        println(
            "It's a Alive!!!\n" +
            "${if (lastName == "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName"}\n"
                    /*+ "${getIntro()}"*/
        )
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User{
            lastId++
            /*val parts: List<String>? = fullName?.split(" ")
            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)*/
            //return User(id = "$lastId", firstName = firstName, lastName = lastName)
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}
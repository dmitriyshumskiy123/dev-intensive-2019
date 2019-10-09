package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.Extensions.TimeUnits
import ru.skillbranch.devintensive.Extensions.add
import ru.skillbranch.devintensive.Extensions.format
import ru.skillbranch.devintensive.Extensions.toUserView
import ru.skillbranch.devintensive.Models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /*@Test
        fun test_instance() {
            val user = User("1")
            val user2 = User("2", "John", "Doe")
            val user3 = User ("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

        user.printMe()
        user2.printMe()
        user3.printMe()

        println("$user $user2 $user3")
        }*/
    @Test
    fun test_factory() {
        //val user = User.makeUser(fullName = "John Cena")
        //val user2 = User.makeUser(fullName = "John Mick")
        val user3 = User.makeUser(fullName = "John Silverhand")
        val user2 = user3.copy(id="2", lastName="Cena", lastVisit=Date())
        //В ДЗ здесь надо будет починить ввод/возврат null
        println(user2)
    }
    @Test
    fun test_decomposition()
    {
        val user11 = User.makeUser("John Wick")

        fun getUserInfo() = user11

        val (id, firstName, lastName) = getUserInfo()
        println("$id, $firstName, $lastName")
        println("${user11.component1()}, ${user11.component2()}, ${user11.component3()}")

    }
    @Test
    fun test_copy()
    {
        val user = User.makeUser("John Wick")
        //val user2 = user.copy(id="2", lastVisit = Date())
        //var user2 = user.copy()
        val user2 = user.copy(lastVisit = Date())
        val user3 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user4 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println(
            """
               ${user.lastVisit?.format()},
               ${user2.lastVisit?.format()},
               ${user3.lastVisit?.format()},
               ${user4.lastVisit?.format()}
            """.trimIndent()
        )

        /*if(user.equals(user2))
        {
            println("equals data and hash \n${user.hashCode()} $user \n ${user2.hashCode()} $user2")
        }
        else{
            println("not equals data and hash \n${user.hashCode()} $user \n ${user2.hashCode()} $user2")
        }

        //user2 = user //ссылка
        //сравнение ссылок происходит с помощью ===
        if(user === user2)
        {
            println("equals address\n${System.identityHashCode(user)} $user \n " +
                    "${System.identityHashCode(user2)} $user2")
        }
        else{
            println("not equals address\n${System.identityHashCode(user)} $user \n " +
                    "${System.identityHashCode(user2)} $user2")
        }

        //если мы будем изменять user2, то точно так же будет изменяться и user
        /*user2.lastName = "Doe"
        println("$user \n$user2")*/*/
    }

    @Test
    fun test_data_maping(){
        val user = User.makeUser("Dmitriy Sergeevich")
        val newUser = user.copy(lastVisit = Date().add(-4, TimeUnits.DAY))
        println(newUser)

        val userView = user.toUserView() //удобное преобразование дата-класса к конкретному объекту
        userView.printMe()

    }
    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("Dmitriy Sergeevich")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url message", type = "image")

        /*when(textMessage)
        {
            is BaseMessage -> println("this is base message")
            is TextMessage -> println("this is text message")
            is ImageMessage -> println("this is image message")
        }*/
        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
    }
}

package ru.skillbranch.devintensive.Extensions

import android.provider.ContactsContract
import ru.skillbranch.devintensive.Models.User
import ru.skillbranch.devintensive.Models.UserView
import ru.skillbranch.devintensive.Utils.Utils
import java.util.*

fun User.toUserView(): UserView{

    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if(lastVisit == null) "Еще ни разу не был" else if (isOnline) "Online"
    else "Последний раз был ${lastVisit/*.numanizeDiff()*/}" //ф-ция возвращает разницу м-ду врем. текущ экз-ра и врем., кот. передано в кач.арг.

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = initials,
        avatar = avatar,
        status = status)
}


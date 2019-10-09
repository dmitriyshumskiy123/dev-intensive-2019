package ru.skillbranch.devintensive.Models

import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var image: String
) : BaseMessage (id, from, chat, isIncoming, date) {
    override fun formatMessage(): String = "id:$id ${from?.firstName} ${if(isIncoming) "Получил" else "Отправил"}" +
            "  изображение \"$image\" ${date}"
    //override fun formatMessage(): String = "id:$id ${from?.firstName} ${if(isIncoming) "Получил" else "Отправил"}" +
            //"  изображение \"$image\" ${date.humanizeDiff()}"
}
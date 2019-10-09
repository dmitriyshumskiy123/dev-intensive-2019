package ru.skillbranch.devintensive.Models

class Chat(
    val id: String,
    val members: MutableList<User> = mutableListOf(),
    val message: MutableList<BaseMessage> = mutableListOf()
) {
}
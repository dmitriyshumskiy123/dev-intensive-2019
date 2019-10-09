package ru.skillbranch.devintensive.Extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*


/*private fun Date.convertFromDuration(): Any {

}*/

fun String.truncate(str: String, num: Int = 16) : String
{
    if (str.length >= num) return str
    else {
        var str1 = str.substring(0, num-1)
        if (str1[num-1] == ' ') str1 = str1.substring(0, num-2)
        str1 += "..."
        return str1
    }
}

package ru.skillbranch.devintensive.Utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>
    {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        //return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var str = payload.toLowerCase().replace('а', 'a').replace('б', 'b')
            .replace('в', 'v').replace('г', 'g').replace('д', 'd')
            .replace('е', 'e').replace('ё', 'e').replace("ж", "zh")
            .replace('з', 'z').replace('и', 'i').replace('й', 'i')
            .replace('к', 'k').replace('л', 'l').replace('м', 'm')
            .replace('н', 'n').replace('о', 'o').replace('п', 'p')
            .replace('р', 'r').replace('c', 's').replace('т', 't')
            .replace('у', 'y').replace('ф', 'f').replace('х', 'h')
            .replace('ц', 'c').replace("ч", "ch").replace("ш", "sh")
            .replace("щ", "sh").replace("ъ", "").replace('ы','i')
            .replace("ь", "").replace('э', 'e').replace("ю", "yu")
            .replace("я", "ya")

        var strName = str.substringBefore(" ").capitalize()
        var strSurname = str.substringAfter(" ").capitalize()
        return strName + divider + strSurname
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var a = firstName?.substring(1, 1)?:null
        var b = lastName?.substring(1,1)?:null
        if (a != null && b != null) return a+b
        else if (a != null && b == null) return a
        else if (a == null && b != null) return b
        else return null
    }
}
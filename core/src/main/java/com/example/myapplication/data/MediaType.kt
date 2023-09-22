package com.example.myapplication.data

class MediaType constructor(val type: Int, val extra1: String? = null, val extra2: String? = null) {

    override fun toString(): String {
        return "$type$SEPARATOR$extra1$SEPARATOR$extra2"
    }

    companion object {
        private val SEPARATOR = "|"
        val MEDIA_TYPE_LOCAL = 1
        val MEDIA_TYPE_CURRENT = 2
        val MEDIA_TYPE_HISTORY = 3
        val MEDIA_TYPE_SEARCH = 4
        val MEDIA_TYPE_ALBUME = 5
        val MEDIA_TYPE_ARTIST = 6

        val LOCAL_MEDIA = MediaType(MEDIA_TYPE_LOCAL).toString()

        val CURRENT_MEDIA = MediaType(MEDIA_TYPE_CURRENT).toString()

        fun getMediaType(parentId:String):Int {
            var arr = parentId.split("|")
            return arr[0].toInt()
        }
    }


}
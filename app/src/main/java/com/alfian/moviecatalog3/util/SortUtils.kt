package com.alfian.moviecatalog3.util

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val BEST_VOTE = "Best"
    const val WORST_VOTE = "Worst"
    const val RANDOM_VOTE = "Random"
    const val MOVIE_ENTITY = "movieentity"
    const val TV_SHOW_ENTITY = "tvshowentity"

    fun getSortedQuery(filter : String,tableName: String): SimpleSQLiteQuery{
        val simpleQuery = StringBuilder().append("SELECT * FROM $tableName ")
        when(filter){
            BEST_VOTE -> simpleQuery.append("ORDER BY rating DESC")
            WORST_VOTE -> simpleQuery.append("ORDER BY rating ASC")
            RANDOM_VOTE -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}
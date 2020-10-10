package com.yazao.wan.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.yazao.wan.data.db.HomeArticleDetail

@Dao
interface HomeArticleCacheDao {
    @Query("select * from home_article_cache")
    fun fetchAllHomeArticleCache(): PagingSource<Int, HomeArticleDetail>

}

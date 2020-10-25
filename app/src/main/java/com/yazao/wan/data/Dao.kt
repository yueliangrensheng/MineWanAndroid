package com.yazao.wan.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yazao.wan.data.db.HomeArticleDetail
import com.yazao.wan.data.db.HomeArticleRemoteKey

@Dao
interface HomeArticleCacheDao {
    @Query("select * from home_article_cache")
    fun fetchAllHomeArticleCache(): PagingSource<Int, HomeArticleDetail>

    @Query("select * from home_article_remote_key where article_id = :artId")
    suspend fun remoteKeyByArtId(artId: Int): HomeArticleRemoteKey?

    @Query("delete from home_article_remote_key")
    suspend fun clearHomeArticleRemoteKey()

    @Query("delete from home_article_cache")
    suspend fun clearHomeArticleCache()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheHomeArticles(articles: MutableList<HomeArticleDetail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheHomeArticleRemoteKey(keys: List<HomeArticleRemoteKey>) {

    }


}

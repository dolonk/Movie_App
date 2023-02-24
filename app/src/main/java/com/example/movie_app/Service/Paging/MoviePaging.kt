package com.example.movie_app.Service.Paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie_app.Service.Domain.Movie
import com.example.movie_app.Service.Utils.Constants
import com.example.movie_app.Service.Retrofit.MovieInterface

class MoviePaging(val s: String, val movieInterface: MovieInterface) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = movieInterface.getAllMovies(s, page, Constants.API_KEY)

            LoadResult.Page(
                data = response.body()?.Search!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.body()?.Search?.isEmpty()!!) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
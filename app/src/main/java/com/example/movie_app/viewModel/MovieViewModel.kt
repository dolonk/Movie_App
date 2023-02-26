package com.example.movie_app.viewModel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.movie_app.Paging.MoviePaging
import com.example.movie_app.Retrofit.MovieInterface
import com.example.movie_app.Utils.Events
import com.example.movie_app.Utils.Status
import com.example.movie_app.domain.MovieDetails
import com.example.movie_app.repository.MovieDetailsRepository
import com.example.movie_app.Utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieInterface: MovieInterface,
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    private val query = MutableLiveData<String>()

    val list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(query, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }

    private val _movieDetails = MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails: LiveData<Events<Result<MovieDetails>>> = _movieDetails


    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(
            Events(
                Result(
                    Status.LOADING,
                    null,
                    null
                )
            )
        )
        _movieDetails.postValue(Events(movieDetailsRepository.getMovieDetails(imdbId)))
    }
}
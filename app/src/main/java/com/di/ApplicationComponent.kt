package com.di

import android.app.Application
import com.presentation.movie_detail_ui.MovieDetailFragment
import com.presentation.movies_list_ui.MoviesListFragment
import com.presentation.search_ui.SearchFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(movieList : MoviesListFragment)
    fun inject(movieDetail: MovieDetailFragment)
    fun inject(search: SearchFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent

    }
}
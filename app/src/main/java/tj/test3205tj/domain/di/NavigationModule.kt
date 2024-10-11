package tj.test3205tj.domain.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Singleton
    @Provides
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun getNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun getMainRouter(cicerone: Cicerone<Router>): Router = cicerone.router

}
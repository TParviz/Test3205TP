package tj.test3205tj.presentation

import tj.test3205tj.presentation.download.DownloadRepsFragment
import tj.test3205tj.presentation.search.SearchFragment
import tj.test3205tj.presentation.splash.SplashFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun splash() = FragmentScreen { SplashFragment() }
    fun search() = FragmentScreen { SearchFragment() }
    fun downloads() = FragmentScreen { DownloadRepsFragment() }
}
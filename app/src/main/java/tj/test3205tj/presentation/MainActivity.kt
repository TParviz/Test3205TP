package tj.test3205tj.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tj.test3205tj.R
import tj.test3205tj.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator = AppNavigator(this, R.id.container)

    private val binding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val screensCache = mutableMapOf<Int, FragmentScreen>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            router.replaceScreen(Screens.splash())
            delay(1000)
            initView()
        }
    }

    private fun initView() = with(binding) {
        navView.visibility = View.VISIBLE

        navView.setOnItemSelectedListener { item ->
            screensCache.getOrElse(item.itemId) {
                val screen = when (item.itemId) {
                    R.id.search -> Screens.search()
                    R.id.downloads -> Screens.downloads()

                    else -> throw RuntimeException("Unknown menu item clicked")
                }
                screensCache[item.itemId] = screen
                screen
            }.let { screen ->
                router.replaceScreen(screen)
            }
            true
        }
        navView.selectedItemId = R.id.search
        navView.menu.performIdentifierAction(R.id.search, 0)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
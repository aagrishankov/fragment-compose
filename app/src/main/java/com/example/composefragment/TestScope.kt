package com.example.composefragment

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.compose.AndroidFragment
import androidx.fragment.compose.rememberFragmentState
import androidx.navigation.NavController
import kotlinx.parcelize.Parcelize
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Composable
fun MainFragment(navController: NavController) {

    val handlerCompose = createNavigationHandler {
        navController.navigate(it)
    }

    val args = bundleOf(
        "id" to 90,
        "name" to "test",
    )

    SideEffect {
        Log.d("FragmentTest", "composeNav: $args")
    }

    val fragmentState = rememberFragmentState()

    AndroidFragment<FragmentTest>(
        modifier = Modifier.fillMaxSize(),
        fragmentState = fragmentState,
        arguments = handlerCompose.toBundle(args),

    )
}

sealed class Screens(val route: String) {

    data object Screen1 : Screens("screen1?arg1={arg1}&arg2={arg2}") {
        operator fun invoke(arg1: String, arg2: String) = "scree1?arg1=$arg1&arg2=$arg2"
    }

}

class FragmentTest : Fragment() {

    private val composeNav: NavigationHandlerCompose? by navComposeHandler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id") ?: -1
        val name = arguments?.getString("name").orEmpty()

        Log.d("FragmentTest", "composeNav: $composeNav")
        Log.d("FragmentTest", "Arguments: id = $id, name = $name")

        view.findViewById<TextView>(R.id.tv_title)?.setOnClickListener {
            composeNav?.navigate(navitatetoBLueScreen(id, name))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_red, container, false)
    }
}

//***

class FragmentArgumentLazy(
    private val fragment: Fragment,
) : Lazy<NavigationHandlerCompose?> {

    private var cached: NavigationHandlerCompose? = null

    override val value: NavigationHandlerCompose?
        get() {
            var nav = cached
            if (nav == null) {
                nav = fragment.arguments?.toNavigateComposeHandler()
                cached = nav
            }
            return nav
        }

    override fun isInitialized() = cached != null
}

@MainThread
fun Fragment.navComposeHandler() = FragmentArgumentLazy(this)

fun createNavigationHandler(navigate: (String) -> Unit): NavigationHandlerCompose {
    return object : NavigationHandlerCompose() {
        override fun navigate(route: String) {
            navigate(route)
        }
    }
}

fun Bundle?.toNavigateComposeHandler(): NavigationHandlerCompose? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getParcelable(NavigationHandlerCompose.LISTENER_KEY, NavigationHandlerCompose::class.java)
    } else {
        this?.getParcelable(NavigationHandlerCompose.LISTENER_KEY) as? NavigationHandlerCompose
    }
}

@Parcelize
open class NavigationHandlerCompose : Parcelable {

    companion object {
        const val LISTENER_KEY = "LISTENER_KEY"
    }

    open fun navigate(route: String) = Unit
}

fun NavigationHandlerCompose.toBundle(arg: Bundle = Bundle()): Bundle {
    return arg.apply {
        putParcelable(NavigationHandlerCompose.LISTENER_KEY, this@toBundle)
    }
}
package com.example.composefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment

class StartFragment : Fragment() {
    
    companion object {
        
        const val LISTENER_KEY = "listener_key"
    }
    
    private var listener: StartListener? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        listener = arguments?.getParcelable(LISTENER_KEY)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(inflater.context).apply {
        isTransitionGroup = true
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        
        setContent {
            Column(
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(text = "Splash Fragment with Composable-view")
                Button(onClick = { listener?.navigateToBlueScreen?.invoke() }) {
                    Text(text = "Navigate to Blue fragment")
                }
                Button(onClick = { listener?.navigateToRedScreen?.invoke() }) {
                    Text(text = "Navigate to Red fragment")
                }
                Button(onClick = { listener?.navigateToRedScreenByDeeplink?.invoke() }) {
                    Text(text = "Navigate to Red fragment by deeplink")
                }
            }
        }
    }
}

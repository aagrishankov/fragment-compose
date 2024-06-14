package com.example.composefragment.red

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composefragment.R

class RedFragment : Fragment() {
    
    companion object {
        
        const val DEEPLINK_KEY = "deeplink_key"
    }
    
    private var fromDeeplink: Boolean? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        fromDeeplink = arguments?.getBoolean(DEEPLINK_KEY)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.fragment_red, container, false)
}

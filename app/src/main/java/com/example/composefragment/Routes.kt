package com.example.composefragment

const val START_SCREEN = "start"
const val BLUE_SCREEN = "blue?arg1={arg1}&arg2={arg2}"
const val RED_SCREEN = "red"
const val RED_DEEPLINK = "https://kotlinlang.org/"

fun navitatetoBLueScreen(arg1: Int, arg2: String) = "blue?arg1=${arg1}&arg2=${arg2}"

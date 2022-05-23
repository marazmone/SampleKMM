package com.marazmone.samplekmm

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
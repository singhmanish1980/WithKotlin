package com.example.myapplication

import dagger.Component

@Component(modules = [MyModule::class])
interface MyComponent{
    fun inject(myViewModel:MyViewModel)
}
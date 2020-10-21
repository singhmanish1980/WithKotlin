package com.example.myapplication

enum class MyInjector {
    INSTANCE;
    lateinit var component: MyComponent
    fun getMyComponent(): MyComponent {
        if (component == null)
            component = DaggerMyComponent.builder().build()

        return component
    }


}

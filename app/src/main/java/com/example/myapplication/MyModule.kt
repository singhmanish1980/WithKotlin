package com.example.myapplication

import dagger.Module

@Module
class MyModule{
    fun provideRepository():MyRepository{
        return MyRepository()
    }
}
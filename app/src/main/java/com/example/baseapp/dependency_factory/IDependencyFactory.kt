package com.example.baseapp.dependency_factory

interface IDependencyFactory<T> {
    fun weatherRepository(type:T):Any
}
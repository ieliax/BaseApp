package com.example.baseapp.base

interface BaseContract {
    interface Model{
        interface onFinishedListener<T>{
            fun onSuccess(v:T)
            fun onFailure()
        }
    }

    interface View<T>{
       fun setPresenter(presenter:T)
    }

    interface Presenter{
        fun onViewCreated()
        fun onDestroy()
    }


}
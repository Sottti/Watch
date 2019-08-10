package com.sotti.watch.network.common

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkCommonModule = module {
    single { getCommonRetrofit(androidContext()) }
}
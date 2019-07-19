package com.sotti.watch.about.view

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun injectAboutModules() = loadAboutModules

val loadAboutModules by lazy {
    loadKoinModules(aboutViewModule)
}

internal val aboutViewModule = module {
    viewModel { AboutViewModel() }
}
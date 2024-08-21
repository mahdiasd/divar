package com.divar.ui.extension

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList


fun <T> immutableListOf(): ImmutableList<T> = listOf<T>().toImmutableList()
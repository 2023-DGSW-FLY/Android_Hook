package com.innosync.di.qualifier

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenOkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicOkhttpClient
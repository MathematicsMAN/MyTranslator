object Version {
    const val retrofit = "2.9.0"
    const val okHttp = "4.9.2"

    const val ktx = "1.6.0"
    const val appCompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.1"
    const val swipeRefreshLayout = "1.1.0"
    const val coroutines = "1.5.0"
    const val koin = "3.1.2"
    const val coil = "1.2.1"
    const val room = "2.3.0"
    const val androidJUnit = "1.1.3"
    const val espresso = "3.4.0"
    const val junit = "4.+"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"
}

object Android {
    const val CoreKtx = "androidx.core:core-ktx:${Version.ktx}"
    const val AppCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val Material = "com.google.android.material:material:${Version.material}"
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val SwipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"
}

object Kotlin {
    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val CoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
}

object Koin {
    // Основная библиотека
    const val KoinCore = "io.insert-koin:koin-core:${Version.koin}"

    // Koin для поддержки Android (Scope,ViewModel ...)
    const val KoinAndroid = "io.insert-koin:koin-android:${Version.koin}"

    // Для совместимости с Java
    const val KoinCompat = "io.insert-koin:koin-android-compat:${Version.koin}"
}

object Coil {
    const val Coil = "io.coil-kt:coil:${Version.coil}"
}

object Room {
    const val RoomRuntime = "androidx.room:room-runtime:${Version.room}"
    const val RoomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val RoomKtx = "androidx.room:room-ktx:${Version.room}"
}

object Tests {
    const val JUnit = "junit:junit:${Version.junit}"
    const val AndroidJUnit = "androidx.test.ext:junit:${Version.androidJUnit}"
    const val Espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
}
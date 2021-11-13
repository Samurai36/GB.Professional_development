import org.gradle.api.JavaVersion

object Config {
    const val application_Id = "viktor.khlebnikov.gb.gbprofrazrab"
    const val compile_sdk = 31
    const val min_sdk = 21
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Modules {
    const val app = ":app"
    const val core = ":Core"
    const val model = ":Model"
    const val repository = ":repository"
    const val utils = ":utils"

    //Features
    const val history = ":history"
}

object Versions {
    const val retrofitDefault = "2.9.0"
    const val loggingInterceptor = "4.9.1"
    const val rxjava2Adapter = "1.0.0"
    const val roomDefault = "2.3.0"
    const val picasso = "2.71828"
    const val glideDefault = "4.11.0"
    const val swiperefreshlayout = "1.1.0"
    const val coil = "0.11.0"
    const val coroutinesDefault = "1.5.1"
    const val coroutinesAdapter = "0.9.2"
    const val koinDefault = "3.1.2"
    const val rxAndroid = "2.1.5'"
    const val rxjava = "2.2.10"
    const val coreKtx = "1.7.0"
    const val stdlib = "1.5.21"
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintlayout = "2.1.1"
    const val junit = "4.13.2"
    const val androidJunit = "1.1.3"
    const val espressoCore = "3.4.0"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:${Versions.androidJunit}"
    const val espressoCore = "androidx.test.espresso:${Versions.espressoCore}"
}

object Android {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object RxJava {
    const val rxAndroid = "io.reactivex.rxjava2:${Versions.rxAndroid}"
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koinDefault}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinDefault}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koinDefault}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitDefault}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitDefault}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val rxjava2Adapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxjava2Adapter}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomDefault}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomDefault}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomDefault}"
}

object Images {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideDefault}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    const val glideCompiler= "com.github.bumptech.glide:compiler:${Versions.glideDefault}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Coroutines {
    const val coroutinesCore = "com.squareup.retrofit2:retrofit:${Versions.coroutinesDefault}"
    const val coroutinesAndroid = "com.squareup.retrofit2:retrofit:${Versions.coroutinesDefault}"
    const val coroutinesAdapter = "com.squareup.retrofit2:retrofit:${Versions.coroutinesAdapter}"
    const val coroutinesKotlinx = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.coroutinesDefault}"
}
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

dependencies {

    implementation(project(Modules.model))

    implementation(Retrofit.retrofit)
    implementation(Retrofit.converterGson)
//    implementation(Retrofit.rxjava2Adapter)
    implementation(Retrofit.loggingInterceptor)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    implementation(Android.appcompat)
    implementation(Android.coreKtx)
    implementation(Android.stdlib)
    implementation(Android.material)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.androidJunit)
    androidTestImplementation(TestImpl.espressoCore)
}
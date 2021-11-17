plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android-extensions")
}

dependencies {

    implementation(project(Modules.model))
    implementation(project(Modules.utils))

    implementation(Coroutines.coroutinesCore)
    implementation(Coroutines.coroutinesAndroid)
    implementation(Coroutines.coroutinesKotlinx)

    implementation(Android.appcompat)
    implementation(Android.stdlib)
    implementation(Android.coreKtx)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.androidJunit)
    androidTestImplementation(TestImpl.espressoCore)
}
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {

    implementation(project(Modules.utils))

    implementation(Retrofit.converterGson)

    implementation(Android.appcompat)
    implementation(Android.material)
    implementation(Android.coreKtx)
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.androidJunit)
    androidTestImplementation(TestImpl.espressoCore)
}
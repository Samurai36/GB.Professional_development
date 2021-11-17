plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}


dependencies {

    implementation(Android.appcompat)
    implementation(Android.stdlib)
    implementation(Android.coreKtx)
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.androidJunit)
    androidTestImplementation(TestImpl.espressoCore)
}
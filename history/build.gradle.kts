plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {

    implementation(project(Modules.model))
    implementation(project(Modules.core))
    implementation(project(Modules.repository))
    implementation(project(Modules.utils))

    implementation(Coroutines.coroutinesCore)
    implementation(Coroutines.coroutinesKotlinx)
    implementation(Coroutines.coroutinesAndroid)
    implementation(Coroutines.coroutinesAdapter)

    implementation(Images.glide)
    implementation(Images.coil)
    implementation(Images.picasso)
    implementation(Images.swiperefreshlayout)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)
    implementation(Koin.koinAndroidCompat)

    implementation(Android.appcompat)
    implementation(Android.material)
    implementation(Android.coreKtx)
    implementation(Android.stdlib)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.androidJunit)
    androidTestImplementation(TestImpl.espressoCore)
}
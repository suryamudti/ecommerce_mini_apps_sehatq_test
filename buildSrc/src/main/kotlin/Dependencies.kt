const val kotlinVersion = "1.3.31"

// project app level
object BuildPlugins {
    object Versions {
        const val buildToolsVersion = "3.4.1"
        const val gmsPluginVersion = "4.0.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val gmsGradlePlugin = "com.google.gms:google-services:${Versions.gmsPluginVersion}"
    const val androidApplication = "com.android.application"
    const val androidDynamicFeeature = "com.android.dynamic-feature"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinSerialization = "kotlinx-serialization"
    const val gms = "com.google.gms.google-services"
}

// module app level
object AppConfigurations {
//    const val applicationId = "id.pamoyanan_dev.irfanirawansukirman"
    const val applicationId = "id.surya"
    const val applicationName = "1.0.0"
    const val applicationBuild: Int = 1

    object ofNumberSdk {
        const val minimum: Int = 21
        const val compile: Int = 28
        const val maximum = compile
    }
}

object GoogleLibraries {
    private object Versions {
        const val jetpack = "1.0.0-beta01"
        const val constraintLayout = "1.1.2"
        const val ktx = "1.1.0-alpha05"
        const val supportLibrary = "28.0.0"
        const val multiDex = "1.0.3"
        const val playService = "16.0.1"
        const val firebase = "16.1.0"
        const val crashlytics = "2.9.5"
        const val room = "1.1.1"
        const val aac = "1.1.1"
        const val kCoroutines = "1.0.1"
    }

    const val appCompatv7 = "com.android.support:appcompat-v7:${Versions.supportLibrary}"
    const val appSupportv4 = "com.android.support:support-v4:${Versions.supportLibrary}"
    const val supportDesign = "com.android.support:design:${Versions.supportLibrary}"
    const val recyclerView = "com.android.support:recyclerview-v7:${Versions.supportLibrary}"
    const val cardView = "com.android.support:cardview-v7:${Versions.supportLibrary}"
    const val multidex = "com.android.support:multidex:${Versions.multiDex}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val kotlinStdLib7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val kotlinStdLib8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kCoroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kCoroutines}"
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.9.1"
    const val kotlinCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

    const val playMaps = "com.google.android.gms:play-services-maps:${Versions.playService}"
    const val playLocation = "com.google.android.gms:play-services-location:${Versions.playService}"
    const val playAuth = "com.google.android.gms:play-services-auth:${Versions.playService}"

    const val fireMessaging = "com.google.firebase:firebase-messaging:${Versions.firebase}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebase}"

    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    const val aacRoomRuntime = "android.arch.persistence.room:runtime:${Versions.room}"
    const val aacRoomRx = "android.arch.persistence.room:rxjava2:${Versions.room}"
    const val aacRoomCompiler = "android.arch.persistence.room:compiler:${Versions.room}"
    const val aacLcRuntime = "android.arch.lifecycle:runtime:${Versions.aac}"
    const val aacLcExtensions = "android.arch.lifecycle:extensions:${Versions.aac}"
    const val aacLcCompiler = "android.arch.lifecycle:compiler:${Versions.aac}"
    const val aacLcJava8 = "android.arch.lifecycle:common-java8:${Versions.aac}"

    const val dataBindingCompiler = "com.android.databinding:compiler:${BuildPlugins.androidGradlePlugin}"

    // x version
    const val kotlinKtxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val appCompatvX = "androidx.appcompat:appcompat:${Versions.jetpack}"
}

object GeneralLibraries {
    private object Versions {
        const val retrofit = "2.3.0"
        const val okhttp = "3.12.0"
        const val glide = "4.8.0"
        const val circleImageView = "2.2.0"
        const val zxingCore = "3.2.1"
        const val zxingEmbedded = "3.5.0"
        const val dagger2 = "2.16"
        const val rxJava = "2.2.2"
        const val rxAndroid = "2.1.0"
        const val timber = "4.5.1"
        const val stetho = "1.5.0"
        const val facebook = "4.38.1"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitAdapterRx2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebook}"
    }

    object Image {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageView}"
        const val zxingCore = "com.google.zxing:core:${Versions.zxingCore}"
        const val zxingEmbedded = "com.journeyapps:zxing-android-embedded:${Versions.zxingEmbedded}"
    }

    object DependenciesInjection {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger2}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger2}"
        const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger2}"
        const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger2}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger2}"
    }

    object Async {
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    }

    object Debugging {
        const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    }

    object Utils {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val gson = "com.google.code.gson:gson:2.8.5"
    }
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val junitRunner = "1.0.1"
        const val testRunner = "1.1.0-alpha4"
        const val espresso = "3.1.0-alpha4"
        const val mockitoCore = ""
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val junitRunner = "com.android.support.test:runner:${Versions.junitRunner}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"

    // x version
    const val testRunnerX = "androidx.test:runner:${Versions.testRunner}"
    const val espressoCoreX = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
plugins {
    //id("com.android.application") version "8.7.0" apply false
    id("com.android.library") version "8.7.0" apply false
    //id("org.jetbrains.kotlin.android") version "2.0.20" apply false
    id ("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0-RC"

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
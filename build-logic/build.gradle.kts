plugins {
    `kotlin-dsl`
}

group = "io.github.mmolosay"

repositories {
    mavenCentral()
    mavenLocal()
    google()
    gradlePluginPortal()
    maven {
        url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        name = "Compose for Desktop DEV"
    }
}

// TODO: change to compileOnly
// TODO: use versions catalog
// https://github.com/JetBrains/compose-multiplatform/blob/master/VERSIONING.md#kotlin-compatibility
dependencies {
    // TODO: update versions
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.2.1") // id("org.jetbrains.compose")
}

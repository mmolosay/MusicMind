plugins {
    `kotlin-dsl`
}

group = "io.github.mmolosay"

repositories {
    mavenLocal()
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

// TODO: change to compileOnly
// TODO: use versions catalog
dependencies {
    // TODO: update versions
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.2.1")
}

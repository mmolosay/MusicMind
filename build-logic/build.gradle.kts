plugins {
    `kotlin-dsl`
}

group = "com.mmolosay.mtheory.buildlogic"

repositories {
    mavenLocal()
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

// TODO: change to compileOnly
// TODO: use versions catalog
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.2.1")
}

gradlePlugin {
    plugins {
        register("multiplatformCompose") {
            id = "mtheory.multiplatform.compose"
            implementationClass = "MultiplatformComposePlugin"
        }
        register("multiplatformKotlin") {
            id = "mtheory.multiplatform.kotlin"
            implementationClass = "MultiplatformKotlinPlugin"
        }
    }
}

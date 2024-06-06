import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization").version("1.9.22")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    maven { url = uri("https://jitpack.io") }

    flatDir {
        dirs("lib")
    }
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("com.fazecast:jSerialComm:2.7.0")
    implementation(":kserialpooler-2.0")
    implementation(":polling-essentials-2.0")
    implementation("cafe.adriel.voyager:voyager-navigator-desktop:1.0.0-rc02")
    implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator-desktop:1.0.0-rc02")
    implementation("cafe.adriel.voyager:voyager-transitions-desktop:1.0.0-rc02")
    implementation("cafe.adriel.voyager:voyager-tab-navigator-desktop:1.0.0-rc02")
    implementation("io.github.microutils:kotlin-logging:1.8.3")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("ch.qos.logback:logback-core:1.2.3")
    implementation("org.jetbrains.exposed:exposed:0.17.14")
    implementation("org.xerial:sqlite-jdbc:3.39.3.0")
    implementation("org.apache.poi:poi:5.0.0")
    implementation("org.apache.poi:poi-ooxml:5.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:1.1.1")

}

compose.desktop {
    application {
        mainClass = "ru.avem.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "urel_kspem"
            packageVersion = "1.0.0"
        }
    }
}

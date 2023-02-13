import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.7.10"
}

val group = "jp.faketuna"
val pluginVersion = "0.0.3a"
val mcVersion = "1.19.3"
java.sourceCompatibility=JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")

}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation(kotlin("stdlib-jdk8"))
    compileOnly(files("libs/Skript-2.6.4.jar"))
    compileOnly("io.papermc.paper:paper-api:${mcVersion}-R0.1-SNAPSHOT")
}

bukkit {
    main = "jp.faketuna.addon.skriptwebapi.SkriptWebAPI"

    apiVersion = "1.19"

    name = "SKeb"
    authors = listOf("ft")
    description = "the Web request and API Server addon for Skript"
    version = pluginVersion
    depend = listOf("Skript")
    prefix = "SKeb"

    commands {
        register("skebtest") {
            description = "test command"
            aliases = listOf("skebt")
            usage = "usage"
        }
        // ...
    }

    /*
    permissions {
        register("per.*") {
            children = listOf("per.m")
        }
        register("per.m") {
            description = "Permission description"
            default = BukkitPluginDescription.Permission.Default.OP
        }
    }
     */
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
tasks {
    "shadowJar"(ShadowJar::class) {
        archiveBaseName.set("SkriptWebAPI")
        archiveClassifier.set("")
        archiveVersion.set(pluginVersion)
    }
}
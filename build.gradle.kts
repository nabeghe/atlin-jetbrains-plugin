plugins {
    id("org.jetbrains.intellij") version "1.17.2"
    id("java")
}

group = "com.herminal.atlin"
version = "1.3.0"

repositories {
    mavenCentral()
}

intellij {
    version.set("2023.3")
    type.set("IU")
    plugins.set(listOf())
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("253.*")
    }
}

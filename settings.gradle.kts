pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = ("Markdown-to-AnnotatedString")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "library",
)

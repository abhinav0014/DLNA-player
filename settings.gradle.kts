dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://artifactory.distri-mind.fr/ui/native/gradle-release/")
    }
}

rootProject.name = "StreamSphere"
include(":app")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "UF2-Activitat_9"
include("src:main:Test")
findProject(":src:main:Test")?.name = "Test"

import org.gradle.jvm.tasks.Jar

plugins {
    idea
    application
    id("java")
}


group = "me.seasickproductions"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("net.dv8tion:JDA:5.0.0-beta.20")
    implementation("commons-cli:commons-cli:1.5.0")


}

tasks.test {
    useJUnitPlatform()
}

//application {
// mainClass.set("org.mineacademy.Main")


val fatJar = task("fatJar", type = Jar::class){
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Implementation-Title"] = "MineAcademyBot"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "org.mineacademy.Main"

    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
    destinationDirectory.set(layout.buildDirectory.dir("dist"))

}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}


tasks.withType<Test> {
    dependsOn(":domain:test")
}

//tasks {
//    test {
//        dependsOn(":domain:test")
//    }
//}

dependencies {
    implementation(project(":domain"))
    runtimeOnly(project(":db"))

    testImplementation(testFixtures(project(":domain")))
}
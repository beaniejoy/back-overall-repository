dependencies {
    implementation(project(":domain"))
    runtimeOnly(project(":db"))

    testImplementation(testFixtures(project(":domain")))
}
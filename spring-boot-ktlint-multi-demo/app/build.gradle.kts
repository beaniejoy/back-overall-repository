import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask

afterEvaluate {
    project.tasks.apply {
        // check all root & sub-modules ktLint
        this.withType<KtLintCheckTask> {
            dependsOn(
                listOf(
                    ":ktlintCheck",
                    ":domain:ktlintCheck",
                    ":persistence:ktlintCheck"
                )
            )
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":persistence"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}

# ktlint for monolithic spring boot project

## ktlint plugin

- https://github.com/JLLeitschuh/ktlint-gradle (gradle wrapper plugin)
- https://github.com/pinterest/ktlint (ktlint)

## gradle setting

```kotlin
plugin {
    id("org.jlleitschuh.gradle.ktlint").version("12.1.0")
}
```

```shell
$ ./gradlew clean build
```
build시 ktlint 작업 포함

```shell
$ ./gradlew ktlintCheck
```

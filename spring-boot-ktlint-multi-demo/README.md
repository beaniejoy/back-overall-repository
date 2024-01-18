# ktlint setup for multi-module project

- sub-modules
  - app
  - domain
  - persistence

<br>

## gradle ktlint 관련 task 수행
```shell
$ ./gradlew clean :app:ktlintCheck
$ ./gradlew clean ktlintCheck
```
위 두 개는 같은 내용으로 작업을 수행합니다.

```shell
./gradlew clean :app:build -x test --info | grep -i "> Task :.*ktlint"
```
build 진행시 수행되는 ktlint task 내용을 확인할 수 있습니다.

<br>

## git commit ktlint 등록

```shell
$ ./gradlew addKtlintCheckGitPreCommitHook
```

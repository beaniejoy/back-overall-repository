# Jackson bind Test

## 📌 코틀린 Jackson Bind


### 기본 생성자 없으면 binding 실패
```kotlin
data class MemberRequestKtDto1(
    var id: Long = 0L,
    var name: String,
    var address: String,
    var email: String
)
```
- 이렇게 주생성자 인자 중 default value 지정되지 않는 것이 하나라도 있으면 **기본 생성자 생성 X**
- 이런 경우 순수 `ObjectMapper`를 이용해 binding 한다면 에러 발생

```groovy
implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
```
```kotlin
mapper = jacksonObjectMapper()
mapper = ObjectMapper().registerKotlinModule()
mapper = ObjectMapper().registerModule(KotlinModule()) // deprecated
```
- `jackson-module-kotlin` 모듈을 `ObjectMapper`에 등록하면 data class에 대해서 기본생성자를 생성해줌

### 주생성자 private 인자
- `jackson-module-kotlin` 모듈이 주생성자 private 인자에 대해서도 binding 해줌
  - 각 필드마다 `@field:JsonProperty(...)`를 지정해준 것과 비슷

### 스프링 부트 MVC에서의 binding
- `HttpMessageConverter`에서 사용되는 Jackson 관련 `ObjectMapper`에는 `KotlinModule`이 따로 등록되어 있지는 않음
- data class에 기본생성자가 없어도 스프링 부트는 기본적으로 `ParameterNamesModule`를 포함하고 있기 때문에 converting 잘 이루어짐
- KotlinModule은 등록되어 있지 않아서 주생성자 인자 중에 private 접근자가 붙어 있는 필드에 대해서는 binding이 안된다.
  - request body에서 json 데이터 중 private 접근자 인자와 매핑되는 경우 binding 실패 -> 에러 발생
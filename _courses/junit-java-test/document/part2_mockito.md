# Part 2. Mockito

## Mockito 소개
- Mock: 진짜 객체와 비슷하게 동작핟지만 프로그래머가 직접 객체의 행동을 관리
- Mockito: Mock을 사용할 수 있는 인터페이스
- mock을 다루게 되면 unit test 얘기가 빠질 수 없음
  - https://martinfowler.com/bliki/UnitTest.html

<br>

## Mock 객체 만들기
```kotlin
val mockMemberService = mock(MemberService::class.java)
val mockStudyRepository = mock(StudyRepository::class.java)
```
- `Mockito.mock` 사용해서 mock 객체 생성

```kotlin
@ExtendWith(MockitoExtension::class)
internal class StudyServiceTest {
  @Mock
  private lateinit var memberService: MemberService

  @Mock
  private lateinit var studyRepository: StudyRepository
  
  //...
}
```
- `@Mock` 어노테이션 이용
- 어노테이션 사용시 `MockitoExtension` 확장 플러그인 적용해야함

<br>

## Mock 객체 Stubbing
```kotlin
val optional = mockMemberService.findById(1L)
assertEquals(optional, Optional.empty<Member>()) // mock 객체는 기본적으로 Optional.empty로 반환
```
- mock 객체에서 stubbing 하지 않은 상태로 mock 객체 메소드 조회하면 각각 기본값을 반환한다.
  - Object: `null` 
  - Optional: `Optional.empty()`
  - primitive type: 각 타입의 기본값
- 이것은 java의 JUnit5 mockito에 해당하는 내용(mockK는 다른 듯)

### MockK 차이점
- Mockito
```kotlin
// ####### stubbing #######
val member = Member.createMember(id = 1L, email = "beanie@test.com")
`when`(mockMemberService.findById(any())).thenReturn(Optional.of(member))
```
`any()` 사용시 에러 발생
```text
java.lang.NullPointerException: any() must not be null
```
- MockK
```kotlin
val member = Member.createMember(id = 1L, email = "beanie@test.com")
every { mockKMemberService.findById(any()) } returns Optional.of(member) // any() 사용 가능
```
- 여기서 사용되는 `any()`는 mockK inline fun

<br>

## BDD 스타일 Mockito API

### BDD
- Behavior-driven Development(BDD)
- 애플리케이션이 어떻게 행동해야 하는지에 대한 공통된 이해를 구성하는 방법
- `TDD`에서 착안
- given - when - then

### 기존 mockito when
- mockito api에서 제공해주는 `when`은 사실 BDD의 given 단계에 해당
- 그런데 메소드명 자체가 `given`하고 어울리지 않는다.
- BDD Mockito API에서 `given`으로 사용할 수 있게 해준다.
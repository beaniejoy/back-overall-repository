package io.beaniejoy.junittest.study

import io.beaniejoy.junittest.domain.Member
import io.beaniejoy.junittest.domain.Study
import io.beaniejoy.junittest.member.MemberService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.*

@ExtendWith(MockKExtension::class, MockitoExtension::class)
internal class StudyServiceTest {
    @Mock
    private lateinit var mockMemberService: MemberService

    @Mock
    private lateinit var mockStudyRepository: StudyRepository

    @MockK
    private lateinit var mockKMemberService: MemberService

    @MockK
    private lateinit var mockKStudyRepository: StudyRepository

    @Test
    fun createStudyService() {
        val studyService = StudyService(mockMemberService, mockStudyRepository)
        assertNotNull(studyService)

        // Mockito 기본 반환값
        val optional = mockMemberService.findById(1L)
        assertEquals(optional, Optional.empty<Member>()) // mock 객체는 기본적으로 Optional.empty로 반환
        mockMemberService.validate(2L) // 아무 일도 일어나지 않음

        // ####### stubbing #######
        val member = Member.createMember(id = 1L, email = "beanie@test.com")
        `when`(mockMemberService.findById(1L)).thenReturn(Optional.of(member)) // any() 에러
        assertEquals("beanie@test.com", mockMemberService.findById(1L).get().email)

        // ####### stubbing void methods with exceptions #######
        doThrow(IllegalArgumentException()).`when`(mockMemberService).validate(1L)
        assertThrows<IllegalArgumentException> {
            mockMemberService.validate(1L)
        }

        // ####### stubbing consecutive calls #######
        `when`(mockMemberService.findById(2L))
            .thenReturn(Optional.of(member))
            .thenThrow(RuntimeException())
            .thenReturn(Optional.empty())

        assertEquals("beanie@test.com", mockMemberService.findById(2L).get().email) // 첫 번째 호출
        assertThrows<RuntimeException> { mockMemberService.findById(2L) } // 두 번째 호출(thenThrow)
        assertEquals(Optional.empty<Member>(), mockMemberService.findById(2L)) // 세 번째 호출

//        val study = Study(limitCount = 10, name = "java")
//        studyService.createNewStudy(1L, study)
    }

    @Test
    fun createStudyServiceMockK() {
        val studyService = StudyService(mockKMemberService, mockKStudyRepository)
        assertNotNull(studyService)

        val member = Member.createMember(id = 1L, email = "beanie@test.com")
        every { mockKMemberService.findById(any()) } returns Optional.of(member) // any() 사용 가능

        assertEquals("beanie@test.com", mockKMemberService.findById(1L).get().email)
        assertEquals("beanie@test.com", mockKMemberService.findById(2L).get().email)
    }

    @Test
    fun createNewStudyTest() {
        // 1. Given
        val studyService = StudyService(mockMemberService, mockStudyRepository)
        assertNotNull(studyService)

        val member = Member.createMember(id = 1L, email = "beanie@test.com")
         `when`(mockMemberService.findById(1L)).thenReturn(Optional.of(member))

        val study = Study(10, "테스트")
         `when`(mockStudyRepository.save(study)).thenReturn(study)

        // 2. When
        studyService.createNewStudy(1L, study)

        // 3. Then
        // 실제 service 코드 호출 대로 verify
        verify(mockMemberService).findById(1L)
        verify(mockStudyRepository).save(study) // 여기서는 any 가능
        verify(mockMemberService).notify(study)
        verify(mockMemberService).notify(member)
        verify(mockMemberService, never()).validate(1L)

        // 메소드 실행 순서에 대한 검증
        val inOrder = inOrder(mockMemberService)
        inOrder.verify(mockMemberService).notify(study)
        inOrder.verify(mockMemberService).notify(member)

        // 모든 메소드 interactions가 다 마치고 끝났는지 검증
        verifyNoMoreInteractions(mockMemberService)

        assertEquals(member, study.owner)
    }

    // BDD Mockito API에 맞춘 mock 테스트
    @Test
    fun createNewStudyTestWithBDDMockito() {
        // 1. Given
        val studyService = StudyService(mockMemberService, mockStudyRepository)
        assertNotNull(studyService)

        val member = Member.createMember(id = 1L, email = "beanie@test.com")
        given(mockMemberService.findById(1L)).willReturn(Optional.of(member)) // BDD 맞게 given 사용

        val study = Study(10, "테스트")
        given(mockStudyRepository.save(study)).willReturn(study) // BDD 맞게 given 사용

        // 2. When
        studyService.createNewStudy(1L, study)

        // 3. Then
        // 실제 service 코드 호출 대로 verify
        then(mockMemberService).should(times(1)).findById(1L)
        then(mockStudyRepository).should().save(study) // 여기서는 any 가능
        then(mockMemberService).should().notify(study)
        then(mockMemberService).should().notify(member)
        then(mockMemberService).should(never()).validate(1L)

        // 메소드 실행 순서에 대한 검증
        val inOrder = inOrder(mockMemberService)
        then(mockMemberService).should(inOrder).notify(study)
        then(mockMemberService).should(inOrder).notify(member)

        // 모든 메소드 interactions가 다 마치고 끝났는지 검증
        then(mockMemberService).shouldHaveNoMoreInteractions()

        assertEquals(member, study.owner)
    }
}
package io.beaniejoy.resttemplatebasic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberRequestDto {
    /*
    * private field 만으로 binding 이 일어나지 않는다.
    */
    private Long id;
    private String name;
    private String address;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // kk, 'T'HH, HH 전부 다름
    private LocalDateTime createdAt;
    private String createdBy;


    public MemberRequestDto() {
    }

    public MemberRequestDto(Long id, String name, String address, String email, LocalDate birthDate, LocalDateTime createdAt, String createdBy) {
        System.out.println("constructor");
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    /*
    * getter 가 없고 public 이 아닌 field 만 존재한다면 binding 이 되지 않는다.
    * "getXxxx()" -> "xxxx" 에 해당하는 맴버변수에 binding 됨
    * "getXxxx()" 로 설정되어 있으면 맴버변수도 "xxxx"로 통일해야 함 (안 그럼 매핑이 안됨)
    */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /*
    * return "beanie default address" <- 이렇게 return 값을 고정된 값으로 지정하면
    * response data json 에 지정된 고장 값으로 내보내지게 된다. ({"address": "beanie default address"})
    * 고정된 값으로 지정되어도 getAddress() 에 해당하는 address POJO field에는 값이 할당된다.
     */
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    /*
    * getter 없이 setter만 있어도 가능
    * getter 없이 setter만 설정한 경우 response에 대해서 json으로 직렬화해주지 않는다.
    * (getId() 가 없는 경우 response json data에 "id: 1"이 포함 안됨)
    * setter는 POJO 객체로 역질렬화(request 받았을 때)하는 경우에 적용된다.
    * "setXxxx()" -> {"xxxx": "value"} 이렇게 request data json의 key 이름으로 매핑이 된다.
    */
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* 만약 setName(), setHelloName()이 같은 필드를 setting 한다면
    * {"name": "beanie", "helloName", "helle beanie"} 둘다 name field에 값을 할당해준다.
    * request json data 에서 가장 나중에 오는 key 가 name 값을 덮어씌우게 된다.
    *
    * public void setHelloName(String name) {
    *    this.name = name;
    * }
    */

//    public void setAddress(String address) {
//        this.address = address;
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "MemberRequestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                "}";
    }
}
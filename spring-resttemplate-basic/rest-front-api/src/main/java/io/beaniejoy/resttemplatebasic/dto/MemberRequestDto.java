package io.beaniejoy.resttemplatebasic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberRequestDto {
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
    */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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
    * */
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
                '}';
    }
}
package io.beaniejoy.resttemplatebasic.dto;

public class MemberRequestDto1 {
    /*
     * public 필드에 대해서는 binding 이 이루어진다.
     * private field 만으로 binding 이 일어나지 않는다.
     */
    public Long id;
    public String name;
    public String address;
    public String email;
}

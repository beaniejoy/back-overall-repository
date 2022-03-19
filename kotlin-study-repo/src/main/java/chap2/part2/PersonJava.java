package chap2.part2;

/*
* JavaBean 형태
* field + access method > property
*/
public class PersonJava {
    private String name;
    private String address;

    public PersonJava(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
}

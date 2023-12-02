package Tutorials;

public class Classes {
    private final String firstName;
    private final String lastName;
    private final int age;

    public Classes(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String firstName(){
        return firstName;
    }

    public String lastName(){
        return lastName;
    }

    public int age() {
        return age;
    }
}

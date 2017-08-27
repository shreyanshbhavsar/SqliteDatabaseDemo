package shreyansh.bhavsar.sqlitestudentdemo;

/**
 * Created by shreyansh.bhavsar on 8/27/2017.
 */

public class Student {

    private final String name;
    private final String city;
    private final int age;


    public Student(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }
}

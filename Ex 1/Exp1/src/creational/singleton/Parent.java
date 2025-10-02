package creational.singleton;

public class Parent {
    private String name;
    private String studentName;

    public Parent(String name, String studentName) {
        this.name = name;
        this.studentName = studentName;
    }

    public String getName() {
        return name;
    }

    public String getStudentName() {
        return studentName;
    }
}

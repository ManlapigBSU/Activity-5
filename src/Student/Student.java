package Student;

public class Student {

    private String name;
    private GradeLevel grade;

    public Student(String name, GradeLevel grade) {

        this.name = name;
        this.grade = grade;

    }

    public String getName() {
        return name;
    }

    public GradeLevel getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(GradeLevel grade) {
        this.grade = grade;
    }
}

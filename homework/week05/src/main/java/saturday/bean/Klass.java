package saturday.bean;

public class Klass {
    private Student student;

    public Klass(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Klass{" +
                "student=" + student +
                '}';
    }
}

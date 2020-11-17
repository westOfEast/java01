package saturday.bean;

public class School {

    private Klass klass;

    public School(Klass klass) {
        this.klass = klass;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    @Override
    public String toString() {
        return "School{" +
                "klass=" + klass +
                '}';
    }
}

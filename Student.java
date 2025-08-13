import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int rollNo;
    private double marks;

    public Student(String name, int rollNo, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public double getMarks() {
        return marks;
    }

    public double getPercentage() {
        return marks; // assuming marks out of 100
    }

    public String getGrade() {
        double percentage = getPercentage();
        if (percentage >= 90) return "A";
        else if (percentage >= 75) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + ", Name: " + name +
               ", Marks: " + marks + ", Grade: " + getGrade();
    }
}

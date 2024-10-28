import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}

class Course {
    private String code;
    private String title;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return code + " - " + title;
    }
}

class Enrollment {
    private Student student;
    private Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }

    @Override
    public String toString() {
        return student.getName() + " enrolled in " + course.getTitle();
    }
}

public class StudentManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField courseCodeField;
    private JTextField courseTitleField;
    private JTextArea displayArea;

    public StudentManagementSystem() {
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());

        studentIdField = new JTextField(10);
        studentNameField = new JTextField(10);
        courseCodeField = new JTextField(10);
        courseTitleField = new JTextField(10);
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        JButton registerStudentButton = new JButton("Register Student");
        JButton addCourseButton = new JButton("Add Course");
        JButton enrollButton = new JButton("Enroll Student");

        registerStudentButton.addActionListener(e -> registerStudent());
        addCourseButton.addActionListener(e -> addCourse());
        enrollButton.addActionListener(e -> enrollStudent());

        frame.add(new JLabel("Student ID:"));
        frame.add(studentIdField);
        frame.add(new JLabel("Student Name:"));
        frame.add(studentNameField);
        frame.add(registerStudentButton);

        frame.add(new JLabel("Course Code:"));
        frame.add(courseCodeField);
        frame.add(new JLabel("Course Title:"));
        frame.add(courseTitleField);
        frame.add(addCourseButton);

        frame.add(enrollButton);
        frame.add(new JScrollPane(displayArea));

        frame.setVisible(true);
    }

    private void registerStudent() {
        String id = studentIdField.getText();
        String name = studentNameField.getText();
        if (!id.isEmpty() && !name.isEmpty()) {
            students.add(new Student(id, name));
            displayArea.append("Registered Student: " + name + "\n");
            studentIdField.setText("");
            studentNameField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Please enter both ID and Name.");
        }
    }

    private void addCourse() {
        String code = courseCodeField.getText();
        String title = courseTitleField.getText();
        if (!code.isEmpty() && !title.isEmpty()) {
            courses.add(new Course(code, title));
            displayArea.append("Added Course: " + title + "\n");
            courseCodeField.setText("");
            courseTitleField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Please enter both Code and Title.");
        }
    }

    private void enrollStudent() {
        String studentId = studentIdField.getText();
        String courseCode = courseCodeField.getText();

        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);

        if (student != null && course != null) {
            enrollments.add(new Enrollment(student, course));
            displayArea.append("Enrolled: " + student.getName() + " in " + course.getTitle() + "\n");
        } else {
            if (student == null) {
                JOptionPane.showMessageDialog(null, "Student not found. Please register the student first.");
            }
            if (course == null) {
                JOptionPane.showMessageDialog(null, "Course not found. Please add the course first.");
            }
        }

        studentIdField.setText("");
        courseCodeField.setText("");
    }

    private Student findStudent(String id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    private Course findCourse(String code) {
        return courses.stream().filter(c -> c.getCode().equals(code)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementSystem::new);
    }
}

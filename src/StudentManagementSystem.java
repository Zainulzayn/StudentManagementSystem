import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


class Student {
    String name;
    String email;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Course {
    String title;
    ArrayList<Student> enrolledStudents;

    public Course(String title) {
        this.title = title;
        this.enrolledStudents = new ArrayList<>();
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }
}


public class StudentManagementSystem {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    // Method to add student
    public void addStudent(String name, String email) {
        Student student = new Student(name, email);
        students.add(student);
    }


    public void addCourse(String title) {
        Course course = new Course(title);
        courses.add(course);
    }


    public void enrollStudent(String studentName, String courseTitle) {
        for (Course course : courses) {
            if (course.title.equals(courseTitle)) {
                for (Student student : students) {
                    if (student.name.equals(studentName)) {
                        course.enrollStudent(student);
                        return;
                    }
                }
            }
        }
    }

    // GUI Setup
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 20, 165, 25);
        panel.add(nameText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 50, 165, 25);
        panel.add(emailText);

        JButton registerButton = new JButton("Register Student");
        registerButton.setBounds(10, 80, 150, 25);
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent(nameText.getText(), emailText.getText());
                JOptionPane.showMessageDialog(null, "Student Registered: " + nameText.getText());
            }
        });

    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.createAndShowGUI();
    }
}


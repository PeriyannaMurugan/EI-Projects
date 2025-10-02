package creational.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EducationManager {
    private static volatile EducationManager instance;
    private static final Logger logger = Logger.getLogger(EducationManager.class.getName());

    private String instituteName;
    private List<Student> students;
    private List<Faculty> faculties;
    private List<Parent> parents;

    private EducationManager() {
        this.instituteName = "Global Education Institute";
        this.students = new ArrayList<>();
        this.faculties = new ArrayList<>();
        this.parents = new ArrayList<>();
        logger.info("EducationManager initialized for: " + instituteName);
    }

    public static EducationManager getInstance() {
        if (instance == null) {
            synchronized (EducationManager.class) {
                if (instance == null) {
                    instance = new EducationManager();
                }
            }
        }
        return instance;
    }

    public void displayInstitute() {
        System.out.println("Institute: " + instituteName);
    }

    // --- Management Methods ---
    public void enrollStudent(Student student) {
        students.add(student);
        logger.info("Student enrolled: " + student.getName());
        System.out.println("Student " + student.getName() + " enrolled in " + student.getCourse());
    }

    public void hireFaculty(Faculty faculty) {
        faculties.add(faculty);
        logger.info("Faculty hired: " + faculty.getName());
        System.out.println("Faculty " + faculty.getName() + " hired for " + faculty.getSubject());
    }

    public void registerParent(Parent parent) {
        parents.add(parent);
        logger.info("Parent registered: " + parent.getName());
        System.out.println("Parent " + parent.getName() + " linked to student " + parent.getStudentName());
    }

    // --- Display Data ---
    public void showAllStudents() {
        System.out.println("---- Students ----");
        for (Student s : students) {
            System.out.println(s.getName() + " -> " + s.getCourse());
        }
    }

    public void showAllFaculties() {
        System.out.println("---- Faculties ----");
        for (Faculty f : faculties) {
            System.out.println(f.getName() + " -> " + f.getSubject());
        }
    }

    public void showAllParents() {
        System.out.println("---- Parents ----");
        for (Parent p : parents) {
            System.out.println(p.getName() + " (Parent of " + p.getStudentName() + ")");
        }
    }
}

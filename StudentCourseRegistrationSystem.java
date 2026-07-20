import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolled;
    String schedule;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    boolean isAvailable() {
        return enrolled < capacity;
    }

    int availableSlots() {
        return capacity - enrolled;
    }

    void display() {
        System.out.println("-----------------------------------------");
        System.out.println("Course Code : " + courseCode);
        System.out.println("Title       : " + title);
        System.out.println("Description : " + description);
        System.out.println("Schedule    : " + schedule);
        System.out.println("Capacity    : " + capacity);
        System.out.println("Available   : " + availableSlots());
    }
}

class Student {
    String studentId;
    String name;
    ArrayList<Course> registeredCourses;

    Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
            return;
        }

        if (!course.isAvailable()) {
            System.out.println("No seats available");
            return;
        }

        registeredCourses.add(course);
        course.enrolled++;
        System.out.println("Course registered successfully");
    }

    void dropCourse(String courseCode) {
        Iterator<Course> iterator = registeredCourses.iterator();

        while (iterator.hasNext()) {
            Course c = iterator.next();
            if (c.courseCode.equalsIgnoreCase(courseCode)) {
                iterator.remove();
                c.enrolled--;
                System.out.println("Course dropped successfully!");
                return;
            }
        }

        System.out.println("You are not registered for this course.");
    }

    void viewRegisteredCourses() {
        System.out.println("\nRegistered Courses:");

        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }

        for (Course c : registeredCourses) {
            System.out.println(c.courseCode + " - " + c.title);
        }
    }
}

public class StudentCourseRegistrationSystem {

    static ArrayList<Course> courses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Sample Courses
        courses.add(new Course("CS101", "Java Programming",
                "Introduction to Java", 3, "Mon 10:00 AM"));

        courses.add(new Course("CS102", "Data Structures",
                "Linear & Non-Linear DS", 2, "Tue 2:00 PM"));

        courses.add(new Course("CS103", "Database Systems",
                "SQL and DBMS", 4, "Wed 11:00 AM"));

        courses.add(new Course("CS104", "Operating Systems",
                "Process & Memory Management", 2, "Thu 1:00 PM"));

        // Student Details
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        Student student = new Student(id, name);

        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println(" STUDENT COURSE REGISTRATION");
            System.out.println("=================================");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    displayCourses();
                    break;

                case 2:
                    displayCourses();
                    System.out.print("\nEnter Course Code: ");
                    String registerCode = sc.nextLine();

                    Course course = findCourse(registerCode);

                    if (course != null)
                        student.registerCourse(course);
                    else
                        System.out.println("Course not found!");

                    break;

                case 3:
                    student.viewRegisteredCourses();
                    System.out.print("\nEnter Course Code to Drop: ");
                    String dropCode = sc.nextLine();
                    student.dropCourse(dropCode);
                    break;

                case 4:
                    student.viewRegisteredCourses();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    static void displayCourses() {

        System.out.println("\nAVAILABLE COURSES\n");

        for (Course c : courses)
            c.display();
    }

    static Course findCourse(String code) {

        for (Course c : courses) {
            if (c.courseCode.equalsIgnoreCase(code))
                return c;
        }

        return null;
    }
}
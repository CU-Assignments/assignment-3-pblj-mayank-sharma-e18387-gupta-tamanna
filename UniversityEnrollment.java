import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class UniversityEnrollment {
    private static final int MAX_ENROLLMENT = 5;
    private static Map<String, Integer> courseEnrollment = new HashMap<>();
    private static Map<String, String> prerequisites = new HashMap<>();

    static {
        prerequisites.put("Advanced Java", "Core Java");
        courseEnrollment.put("Core Java", 2);
        courseEnrollment.put("Advanced Java", 4);
    }

    public static void enrollStudent(String course, boolean prerequisiteCompleted) throws CourseFullException, PrerequisiteNotMetException {
        if (prerequisites.containsKey(course) && !prerequisiteCompleted) {
            throw new PrerequisiteNotMetException("Error: PrerequisiteNotMetException - Complete " + prerequisites.get(course) + " before enrolling in " + course);
        }

        int enrolled = courseEnrollment.getOrDefault(course, 0);
        if (enrolled >= MAX_ENROLLMENT) {
            throw new CourseFullException("Error: CourseFullException - " + course + " is already full.");
        }

        courseEnrollment.put(course, enrolled + 1);
        System.out.println("Enrollment successful! You are now enrolled in " + course);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enroll in Course: ");
        String course = scanner.nextLine();
        
        boolean prerequisiteCompleted = false;
        if (prerequisites.containsKey(course)) {
            System.out.print("Have you completed " + prerequisites.get(course) + "? (yes/no): ");
            prerequisiteCompleted = scanner.nextLine().equalsIgnoreCase("yes");
        }
        
        try {
            enrollStudent(course, prerequisiteCompleted);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        } finally {
            //System.out.println("Current course enrollments: " + courseEnrollment);//
            scanner.close();
        }
    }
}

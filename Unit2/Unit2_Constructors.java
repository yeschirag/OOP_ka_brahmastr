/*
 * UNIT 2: CONSTRUCTORS AND METHOD OVERLOADING
 * 
 * Yo Chirag! Constructors are like your promises - they initialize things but don't always deliver. 😏
 * Method overloading is when you give the same name to different things... like calling all bugs 'features'.
 * 
 * Remember: A constructor called without 'new' is like motivation without coffee - doesn't work! ☕
 * 
 * THEORY:
 * CONSTRUCTOR: Special method used to initialize objects
 * - Same name as class
 * - No return type (not even void)
 * - Called automatically when object is created
 * 
 * Types of Constructors:
 * 1. DEFAULT CONSTRUCTOR - No parameters
 * 2. PARAMETERIZED CONSTRUCTOR - With parameters
 * 3. COPY CONSTRUCTOR - Copies values from another object
 * 
 * Constructor Chaining: Calling one constructor from another using this()
 * 
 * METHOD OVERLOADING: Multiple methods with same name but different parameters
 * - Different number of parameters
 * - Different types of parameters
 * - Different order of parameters
 */


// ============================================
// EXAMPLE 1: Different Types of Constructors
// ============================================

class Rectangle {
    private double length;
    private double width;
    private String color;
    
    
    // DEFAULT CONSTRUCTOR
    Rectangle() {
        length = 1.0;
        width = 1.0;
        color = "White";
        System.out.println("Default constructor called");
        System.out.println("Creating rectangle: " + length + " x " + width + ", Color: " + color);
    }
    
    
    // PARAMETERIZED CONSTRUCTOR (2 parameters)
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        this.color = "White";  // Default color
        System.out.println("Parameterized constructor (2 params) called");
        System.out.println("Creating rectangle: " + length + " x " + width + ", Color: " + color);
    }
    
    
    // PARAMETERIZED CONSTRUCTOR (3 parameters)
    Rectangle(double length, double width, String color) {
        this.length = length;
        this.width = width;
        this.color = color;
        System.out.println("Parameterized constructor (3 params) called");
        System.out.println("Creating rectangle: " + length + " x " + width + ", Color: " + color);
    }
    
    
    // COPY CONSTRUCTOR - Creates new object from existing object
    Rectangle(Rectangle other) {
        this.length = other.length;
        this.width = other.width;
        this.color = other.color;
        System.out.println("Copy constructor called");
        System.out.println("Copied rectangle: " + length + " x " + width + ", Color: " + color);
    }
    
    
    // Method to calculate area
    double calculateArea() {
        return length * width;
    }
    
    
    // Method to display details
    void display() {
        System.out.println("Rectangle Details:");
        System.out.println("Length: " + length);
        System.out.println("Width: " + width);
        System.out.println("Color: " + color);
        System.out.println("Area: " + calculateArea());
    }
}


// ============================================
// EXAMPLE 2: Constructor Chaining
// ============================================

class Student {
    private int rollNumber;
    private String name;
    private String course;
    private double fees;
    
    
    // Constructor 1 - Default
    Student() {
        this(0, "Unknown", "General", 0.0);  // Calling constructor 4
        System.out.println("Default constructor executed");
    }
    
    
    // Constructor 2 - With roll number only
    Student(int rollNumber) {
        this(rollNumber, "Unknown", "General", 0.0);  // Calling constructor 4
        System.out.println("Constructor with roll number executed");
    }
    
    
    // Constructor 3 - With roll number and name
    Student(int rollNumber, String name) {
        this(rollNumber, name, "General", 0.0);  // Calling constructor 4
        System.out.println("Constructor with roll number and name executed");
    }
    
    
    // Constructor 4 - All parameters (Master constructor)
    Student(int rollNumber, String name, String course, double fees) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.course = course;
        this.fees = fees;
        System.out.println("Master constructor executed");
        System.out.println("Student created: " + name + " (Roll No: " + rollNumber + ")");
    }
    
    
    // Display method
    void displayInfo() {
        System.out.println("\nStudent Information:");
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Fees: $" + fees);
    }
}


// ============================================
// EXAMPLE 3: Method Overloading (Compile-time Polymorphism)
// ============================================

class MathOperations {
    
    // Overloaded method 1 - Add two integers
    int add(int a, int b) {
        System.out.println("Method: add(int, int)");
        int result = a + b;
        System.out.println(a + " + " + b + " = " + result);
        return result;
    }
    
    
    // Overloaded method 2 - Add three integers
    int add(int a, int b, int c) {
        System.out.println("Method: add(int, int, int)");
        int result = a + b + c;
        System.out.println(a + " + " + b + " + " + c + " = " + result);
        return result;
    }
    
    
    // Overloaded method 3 - Add two doubles
    double add(double a, double b) {
        System.out.println("Method: add(double, double)");
        double result = a + b;
        System.out.println(a + " + " + b + " = " + result);
        return result;
    }
    
    
    // Overloaded method 4 - Add array of integers
    int add(int[] numbers) {
        System.out.println("Method: add(int[])");
        int sum = 0;
        System.out.print("Adding array: ");
        for (int num : numbers) {
            System.out.print(num + " ");
            sum += num;
        }
        System.out.println("\nSum = " + sum);
        return sum;
    }
    
    
    // Overloaded method 5 - Different parameter order
    void display(String message, int number) {
        System.out.println("Method: display(String, int)");
        System.out.println("Message: " + message + ", Number: " + number);
    }
    
    
    void display(int number, String message) {
        System.out.println("Method: display(int, String)");
        System.out.println("Number: " + number + ", Message: " + message);
    }
    
    
    // Overloaded multiply methods
    int multiply(int a, int b) {
        return a * b;
    }
    
    
    double multiply(double a, double b) {
        return a * b;
    }
    
    
    int multiply(int a, int b, int c) {
        return a * b * c;
    }
    
    
    // Method with varargs - flexible number of parameters
    int sum(int... numbers) {
        System.out.println("Method: sum(int... numbers) - Varargs");
        int total = 0;
        System.out.print("Summing: ");
        for (int num : numbers) {
            System.out.print(num + " ");
            total += num;
        }
        System.out.println("= " + total);
        return total;
    }
}


// ============================================
// Edge Cases and Special Scenarios
// ============================================

class ConstructorEdgeCases {
    private int value;
    private String name;
    
    
    // Constructor with validation
    ConstructorEdgeCases(int value, String name) {
        // Edge case: Validating input in constructor
        if (value < 0) {
            System.out.println("Warning: Negative value detected. Setting to 0.");
            this.value = 0;
        } else {
            this.value = value;
        }
        
        // Edge case: Null check
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Warning: Empty name detected. Setting default.");
            this.name = "Default";
        } else {
            this.name = name;
        }
        
        System.out.println("Object created with validation: " + this.name + " = " + this.value);
    }
    
    
    void display() {
        System.out.println("Value: " + value + ", Name: " + name);
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit2_Constructors {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 2: CONSTRUCTORS AND OVERLOADING - COMPREHENSIVE EXAMPLES");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: DIFFERENT TYPES OF CONSTRUCTORS ==========
        System.out.println("EXAMPLE 1: DIFFERENT TYPES OF CONSTRUCTORS");
        System.out.println("-".repeat(70));
        
        System.out.println("\n1. Creating rectangle with DEFAULT constructor:");
        Rectangle rect1 = new Rectangle();
        System.out.println();
        
        System.out.println("2. Creating rectangle with PARAMETERIZED constructor (2 params):");
        Rectangle rect2 = new Rectangle(5.0, 3.0);
        System.out.println();
        
        System.out.println("3. Creating rectangle with PARAMETERIZED constructor (3 params):");
        Rectangle rect3 = new Rectangle(7.0, 4.0, "Blue");
        System.out.println();
        
        System.out.println("4. Creating rectangle with COPY constructor:");
        Rectangle rect4 = new Rectangle(rect3);  // Copying rect3
        System.out.println();
        
        // Displaying all rectangles
        rect1.display();
        System.out.println();
        rect2.display();
        System.out.println();
        rect3.display();
        System.out.println();
        rect4.display();
        System.out.println();
        
        
        // ========== EXAMPLE 2: CONSTRUCTOR CHAINING ==========
        System.out.println("\nEXAMPLE 2: CONSTRUCTOR CHAINING (this() keyword)");
        System.out.println("-".repeat(70));
        
        System.out.println("\n1. Calling default constructor:");
        Student student1 = new Student();
        student1.displayInfo();
        System.out.println();
        
        System.out.println("\n2. Calling constructor with roll number:");
        Student student2 = new Student(101);
        student2.displayInfo();
        System.out.println();
        
        System.out.println("\n3. Calling constructor with roll number and name:");
        Student student3 = new Student(102, "Alice");
        student3.displayInfo();
        System.out.println();
        
        System.out.println("\n4. Calling constructor with all parameters:");
        Student student4 = new Student(103, "Bob", "Computer Science", 5000.0);
        student4.displayInfo();
        System.out.println();
        
        
        // ========== EXAMPLE 3: METHOD OVERLOADING ==========
        System.out.println("\nEXAMPLE 3: METHOD OVERLOADING (Compile-time Polymorphism)");
        System.out.println("-".repeat(70));
        
        MathOperations math = new MathOperations();
        
        System.out.println("\n1. Overloading based on NUMBER of parameters:");
        math.add(10, 20);
        System.out.println();
        math.add(10, 20, 30);
        System.out.println();
        
        System.out.println("\n2. Overloading based on TYPE of parameters:");
        math.add(15, 25);        // int version
        System.out.println();
        math.add(15.5, 25.5);    // double version
        System.out.println();
        
        System.out.println("\n3. Overloading with array parameter:");
        int[] numbers = {5, 10, 15, 20, 25};
        math.add(numbers);
        System.out.println();
        
        System.out.println("\n4. Overloading based on PARAMETER ORDER:");
        math.display("Hello", 42);
        System.out.println();
        math.display(42, "World");
        System.out.println();
        
        System.out.println("\n5. Testing multiply methods:");
        System.out.println("multiply(5, 3) = " + math.multiply(5, 3));
        System.out.println("multiply(5.5, 2.0) = " + math.multiply(5.5, 2.0));
        System.out.println("multiply(2, 3, 4) = " + math.multiply(2, 3, 4));
        System.out.println();
        
        System.out.println("\n6. Using varargs (flexible parameters):");
        math.sum(1, 2, 3);
        System.out.println();
        math.sum(10, 20, 30, 40, 50);
        System.out.println();
        math.sum(100);  // Single argument
        System.out.println();
        
        
        // ========== EDGE CASES ==========
        System.out.println("\nEDGE CASES AND SPECIAL SCENARIOS:");
        System.out.println("-".repeat(70));
        
        System.out.println("\n1. Constructor with input validation:");
        ConstructorEdgeCases obj1 = new ConstructorEdgeCases(100, "Valid");
        System.out.println();
        
        System.out.println("2. Constructor handling negative value:");
        ConstructorEdgeCases obj2 = new ConstructorEdgeCases(-50, "Negative");
        System.out.println();
        
        System.out.println("3. Constructor handling null/empty string:");
        ConstructorEdgeCases obj3 = new ConstructorEdgeCases(75, null);
        System.out.println();
        
        ConstructorEdgeCases obj4 = new ConstructorEdgeCases(90, "");
        System.out.println();
        
        
        // ========== SUMMARY ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("CONSTRUCTORS:");
        System.out.println("  1. Default constructor (no parameters)");
        System.out.println("  2. Parameterized constructor (with parameters)");
        System.out.println("  3. Copy constructor (creates copy of object)");
        System.out.println("  4. Constructor chaining (using this())");
        System.out.println("  5. Constructor with validation");
        System.out.println();
        System.out.println("METHOD OVERLOADING:");
        System.out.println("  1. Different number of parameters");
        System.out.println("  2. Different types of parameters");
        System.out.println("  3. Different order of parameters");
        System.out.println("  4. Array parameters");
        System.out.println("  5. Varargs (variable arguments)");
        System.out.println();
        System.out.println("KEY POINTS:");
        System.out.println("  - Constructors have same name as class");
        System.out.println("  - Constructors have no return type");
        System.out.println("  - Method overloading is compile-time polymorphism");
        System.out.println("  - Return type alone is NOT sufficient for overloading");
        System.out.println("=".repeat(70));
    }
}

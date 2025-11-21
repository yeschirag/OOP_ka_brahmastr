/*
 * UNIT 4: INHERITANCE AND POLYMORPHISM
 * 
 * Listen up Chirag! Inheritance is about getting stuff from your parents.
 * In Java, it's code. In real life, it's probably just student debt. 💸
 * 
 * Polymorphism means 'many forms' - like how your resume has many forms depending on the job!
 * (We call it 'customization', not 'lying') 😎
 * 
 * THEORY:
 * 
 * INHERITANCE: Mechanism where one class acquires properties of another class
 * - IS-A relationship
 * - Code reusability
 * - Supports polymorphism
 * 
 * Types of Inheritance:
 * 1. SINGLE INHERITANCE - Class extends one superclass
 * 2. MULTILEVEL INHERITANCE - Chain of inheritance (A -> B -> C)
 * 3. HIERARCHICAL INHERITANCE - Multiple classes extend one superclass
 * 4. MULTIPLE INHERITANCE - Not supported directly (use interfaces)
 * 5. HYBRID INHERITANCE - Combination (use interfaces)
 * 
 * POLYMORPHISM: Ability to take multiple forms
 * 1. COMPILE-TIME (Static) - Method Overloading
 * 2. RUNTIME (Dynamic) - Method Overriding
 * 
 * Key Keywords:
 * - extends: For inheritance
 * - super: Access parent class members
 * - @Override: Indicates method overriding
 */


// ============================================
// EXAMPLE 1: Single and Multilevel Inheritance
// ============================================

// Base class (Superclass/Parent)
class Animal {
    protected String name;
    protected int age;
    protected String species;
    
    
    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
        System.out.println("Animal constructor called: " + name);
    }
    
    
    public void eat() {
        System.out.println(name + " is eating...");
    }
    
    
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }
    
    
    public void makeSound() {
        System.out.println(name + " makes a sound");
    }
    
    
    public void displayInfo() {
        System.out.println("\n=== Animal Info ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Species: " + species);
    }
}


// Derived class 1 - SINGLE INHERITANCE (Dog extends Animal)
class Dog extends Animal {
    protected String breed;  // Changed to protected so Puppy can access
    
    
    public Dog(String name, int age, String breed) {
        super(name, age, "Canine");  // Calling parent constructor
        this.breed = breed;
        System.out.println("Dog constructor called: " + breed);
    }
    
    
    // METHOD OVERRIDING - Runtime Polymorphism
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
    
    
    // Additional method specific to Dog
    public void fetch() {
        System.out.println(name + " is fetching the ball!");
    }
    
    
    // Overriding displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();  // Calling parent method
        System.out.println("Breed: " + breed);
        System.out.println("===================\n");
    }
}


// Derived class 2 - MULTILEVEL INHERITANCE (Puppy extends Dog extends Animal)
class Puppy extends Dog {
    private int monthsOld;
    
    
    public Puppy(String name, int monthsOld, String breed) {
        super(name, 0, breed);  // Call parent (Dog) constructor
        this.monthsOld = monthsOld;
        System.out.println("Puppy constructor called: " + monthsOld + " months old");
    }
    
    
    // Overriding method from Dog
    @Override
    public void makeSound() {
        System.out.println(name + " makes puppy sounds: Yip! Yip!");
    }
    
    
    // Additional method
    public void play() {
        System.out.println(name + " is playing energetically!");
    }
    
    
    @Override
    public void displayInfo() {
        System.out.println("\n=== Puppy Info ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + monthsOld + " months");
        System.out.println("Species: " + species);
        System.out.println("Breed: " + ((Dog) this).breed);
        System.out.println("===================\n");
    }
}


// ============================================
// EXAMPLE 2: Hierarchical Inheritance
// ============================================

// Base class
class Employee {
    protected int employeeId;
    protected String name;
    protected double baseSalary;
    
    
    public Employee(int employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
        System.out.println("Employee created: " + name);
    }
    
    
    // Method to be overridden
    public double calculateSalary() {
        return baseSalary;
    }
    
    
    public void displayInfo() {
        System.out.println("\n=== Employee Details ===");
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: $" + baseSalary);
    }
    
    
    public void work() {
        System.out.println(name + " is working...");
    }
}


// Multiple classes extending same parent - HIERARCHICAL INHERITANCE

class Manager extends Employee {
    private double bonus;
    private int teamSize;
    
    
    public Manager(int employeeId, String name, double baseSalary, double bonus, int teamSize) {
        super(employeeId, name, baseSalary);
        this.bonus = bonus;
        this.teamSize = teamSize;
        System.out.println("Manager assigned with team size: " + teamSize);
    }
    
    
    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
    
    
    @Override
    public void work() {
        System.out.println(name + " is managing team of " + teamSize + " members");
    }
    
    
    public void conductMeeting() {
        System.out.println(name + " is conducting a team meeting");
    }
    
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Manager");
        System.out.println("Bonus: $" + bonus);
        System.out.println("Team Size: " + teamSize);
        System.out.println("Total Salary: $" + calculateSalary());
        System.out.println("========================\n");
    }
}


class Developer extends Employee {
    private String programmingLanguage;
    private int projectsCompleted;
    
    
    public Developer(int employeeId, String name, double baseSalary, String programmingLanguage) {
        super(employeeId, name, baseSalary);
        this.programmingLanguage = programmingLanguage;
        this.projectsCompleted = 0;
        System.out.println("Developer specializes in: " + programmingLanguage);
    }
    
    
    @Override
    public double calculateSalary() {
        // Bonus based on projects completed
        return baseSalary + (projectsCompleted * 500);
    }
    
    
    @Override
    public void work() {
        System.out.println(name + " is coding in " + programmingLanguage);
    }
    
    
    public void completeProject() {
        projectsCompleted++;
        System.out.println(name + " completed a project! Total: " + projectsCompleted);
    }
    
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Developer");
        System.out.println("Language: " + programmingLanguage);
        System.out.println("Projects Completed: " + projectsCompleted);
        System.out.println("Total Salary: $" + calculateSalary());
        System.out.println("========================\n");
    }
}


class Intern extends Employee {
    private String university;
    private int duration;  // in months
    
    
    public Intern(int employeeId, String name, double baseSalary, String university, int duration) {
        super(employeeId, name, baseSalary);
        this.university = university;
        this.duration = duration;
        System.out.println("Intern from: " + university);
    }
    
    
    @Override
    public double calculateSalary() {
        // Interns get hourly wage
        return baseSalary * 0.5;  // 50% of base salary
    }
    
    
    @Override
    public void work() {
        System.out.println(name + " is learning and assisting in projects");
    }
    
    
    public void attendTraining() {
        System.out.println(name + " is attending training session");
    }
    
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Role: Intern");
        System.out.println("University: " + university);
        System.out.println("Duration: " + duration + " months");
        System.out.println("Total Salary: $" + calculateSalary());
        System.out.println("========================\n");
    }
}


// ============================================
// EXAMPLE 3: Polymorphism (Compile-time and Runtime)
// ============================================

class Calculator {
    
    // ===== COMPILE-TIME POLYMORPHISM (Method Overloading) =====
    
    // Method 1: Add two integers
    public int add(int a, int b) {
        System.out.println("Adding two integers: " + a + " + " + b);
        return a + b;
    }
    
    
    // Method 2: Add three integers
    public int add(int a, int b, int c) {
        System.out.println("Adding three integers: " + a + " + " + b + " + " + c);
        return a + b + c;
    }
    
    
    // Method 3: Add two doubles
    public double add(double a, double b) {
        System.out.println("Adding two doubles: " + a + " + " + b);
        return a + b;
    }
    
    
    // Method 4: Add array of integers
    public int add(int[] numbers) {
        System.out.print("Adding array: ");
        int sum = 0;
        for (int num : numbers) {
            System.out.print(num + " ");
            sum += num;
        }
        System.out.println();
        return sum;
    }
    
    
    // Method 5: Concatenate strings (overloading with different types)
    public String add(String a, String b) {
        System.out.println("Concatenating strings: \"" + a + "\" + \"" + b + "\"");
        return a + b;
    }
}


// ===== RUNTIME POLYMORPHISM (Method Overriding) =====

class Shape {
    protected String name;
    
    
    public Shape(String name) {
        this.name = name;
    }
    
    
    // Method to be overridden
    public double calculateArea() {
        return 0.0;
    }
    
    
    public void draw() {
        System.out.println("Drawing " + name);
    }
    
    
    public void displayInfo() {
        System.out.println("Shape: " + name + ", Area: " + calculateArea());
    }
}


class Circle extends Shape {
    private double radius;
    
    
    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }
    
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    
    @Override
    public void draw() {
        System.out.println("Drawing circle with radius: " + radius);
    }
}


class Square extends Shape {
    private double side;
    
    
    public Square(double side) {
        super("Square");
        this.side = side;
    }
    
    
    @Override
    public double calculateArea() {
        return side * side;
    }
    
    
    @Override
    public void draw() {
        System.out.println("Drawing square with side: " + side);
    }
}


class Triangle extends Shape {
    private double base;
    private double height;
    
    
    public Triangle(double base, double height) {
        super("Triangle");
        this.base = base;
        this.height = height;
    }
    
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
    
    
    @Override
    public void draw() {
        System.out.println("Drawing triangle with base: " + base + ", height: " + height);
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit4_InheritancePolymorphism {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 4: INHERITANCE AND POLYMORPHISM");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: SINGLE AND MULTILEVEL INHERITANCE ==========
        System.out.println("EXAMPLE 1: Single and Multilevel Inheritance");
        System.out.println("-".repeat(70));
        
        System.out.println("\n1. Single Inheritance (Dog extends Animal):");
        Dog dog = new Dog("Buddy", 5, "Golden Retriever");
        dog.displayInfo();
        dog.eat();           // Inherited from Animal
        dog.makeSound();     // Overridden in Dog
        dog.fetch();         // Specific to Dog
        System.out.println();
        
        System.out.println("\n2. Multilevel Inheritance (Puppy extends Dog extends Animal):");
        Puppy puppy = new Puppy("Charlie", 3, "Beagle");
        puppy.displayInfo();
        puppy.eat();         // Inherited from Animal
        puppy.makeSound();   // Overridden in Puppy
        puppy.fetch();       // Inherited from Dog
        puppy.play();        // Specific to Puppy
        System.out.println();
        
        
        // ========== EXAMPLE 2: HIERARCHICAL INHERITANCE ==========
        System.out.println("\nEXAMPLE 2: Hierarchical Inheritance");
        System.out.println("-".repeat(70));
        
        System.out.println("\nCreating employees:");
        Manager manager = new Manager(101, "Alice Johnson", 80000, 20000, 10);
        Developer dev1 = new Developer(102, "Bob Smith", 70000, "Java");
        Developer dev2 = new Developer(103, "Carol Davis", 75000, "Python");
        Intern intern = new Intern(104, "David Lee", 30000, "MIT", 6);
        
        System.out.println("\nDisplaying information:");
        manager.displayInfo();
        dev1.displayInfo();
        dev2.displayInfo();
        intern.displayInfo();
        
        System.out.println("\nDemonstrating work methods:");
        manager.work();
        manager.conductMeeting();
        
        dev1.work();
        dev1.completeProject();
        dev1.completeProject();
        System.out.println("Developer salary after projects: $" + dev1.calculateSalary());
        
        intern.work();
        intern.attendTraining();
        System.out.println();
        
        
        // ========== EXAMPLE 3: POLYMORPHISM ==========
        System.out.println("\nEXAMPLE 3: Polymorphism (Compile-time and Runtime)");
        System.out.println("-".repeat(70));
        
        System.out.println("\n1. COMPILE-TIME POLYMORPHISM (Method Overloading):");
        Calculator calc = new Calculator();
        
        System.out.println("Result: " + calc.add(10, 20));
        System.out.println("Result: " + calc.add(10, 20, 30));
        System.out.println("Result: " + calc.add(15.5, 24.5));
        System.out.println("Result: " + calc.add(new int[]{1, 2, 3, 4, 5}));
        System.out.println("Result: " + calc.add("Hello", "World"));
        System.out.println();
        
        System.out.println("\n2. RUNTIME POLYMORPHISM (Method Overriding):");
        
        // Parent reference holding child objects
        Shape shape1 = new Circle(5.0);
        Shape shape2 = new Square(4.0);
        Shape shape3 = new Triangle(6.0, 8.0);
        
        // Array of Shape references
        Shape[] shapes = {shape1, shape2, shape3};
        
        System.out.println("\nDemonstrating runtime polymorphism:");
        for (Shape shape : shapes) {
            shape.draw();              // Calls overridden method
            shape.displayInfo();       // Uses overridden calculateArea()
            System.out.println();
        }
        
        
        // ========== POLYMORPHISM WITH EMPLOYEE HIERARCHY ==========
        System.out.println("\n3. Runtime Polymorphism with Employee Hierarchy:");
        
        Employee[] employees = {
            new Manager(201, "Emma Wilson", 90000, 25000, 15),
            new Developer(202, "Frank Brown", 72000, "JavaScript"),
            new Intern(203, "Grace Taylor", 28000, "Stanford", 4)
        };
        
        System.out.println("\nProcessing all employees polymorphically:");
        double totalSalary = 0;
        
        for (Employee emp : employees) {
            emp.displayInfo();
            emp.work();
            totalSalary += emp.calculateSalary();
            System.out.println();
        }
        
        System.out.println("Total company salary expense: $" + totalSalary);
        System.out.println();
        
        
        // ========== SUMMARY ==========
        System.out.println("=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("\nINHERITANCE TYPES:");
        System.out.println("  1. Single Inheritance: Dog extends Animal");
        System.out.println("  2. Multilevel Inheritance: Puppy extends Dog extends Animal");
        System.out.println("  3. Hierarchical: Manager, Developer, Intern extend Employee");
        System.out.println("\nPOLYMORPHISM:");
        System.out.println("  1. Compile-time (Static): Method Overloading");
        System.out.println("     - Same method name, different parameters");
        System.out.println("     - Resolved at compile time");
        System.out.println("  2. Runtime (Dynamic): Method Overriding");
        System.out.println("     - Subclass provides specific implementation");
        System.out.println("     - Resolved at runtime based on object type");
        System.out.println("\nKEY BENEFITS:");
        System.out.println("  - Code reusability through inheritance");
        System.out.println("  - Flexibility through polymorphism");
        System.out.println("  - Maintainability and extensibility");
        System.out.println("  - IS-A relationship modeling");
        System.out.println("=".repeat(70));
    }
}

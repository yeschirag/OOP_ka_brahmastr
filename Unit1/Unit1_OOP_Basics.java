/*
 * UNIT 1: INTRODUCTION TO OOP
 * 
 * Hey Chirag! Welcome to OOP - where we pretend objects in code are as real as our job security! 😅
 * Fun fact: OOP was invented so AI could learn to replace us more efficiently. Thanks, abstraction!
 * 
 * THEORY: Object-Oriented Programming (OOP) is a programming paradigm based on the concept of "objects"
 * (You know, like how your future employer is an "object" that might not exist soon... vibe coding, am I right?)
 * 
 * Four Pillars of OOP (aka The Four Horsemen of "Why did I choose CS?"):
 * 1. ENCAPSULATION - Bundling data and methods that operate on that data within a single unit (class)
 * 2. ABSTRACTION - Hiding complex implementation details and showing only necessary features
 * 3. INHERITANCE - Creating new classes from existing classes (IS-A relationship)
 * 4. POLYMORPHISM - Ability of objects to take multiple forms (method overloading and overriding)
 * 
 * Benefits of OOP:
 * - Code Reusability (through inheritance)
 * - Modularity (organize code into objects)
 * - Flexibility (polymorphism allows flexibility)
 * - Maintainability (easier to modify and extend)
 * - Security (data hiding through encapsulation)
 */


// ============================================
// EXAMPLE 1: Basic OOP Concepts - Encapsulation
// ============================================

class Student {
    // Private data members (Encapsulation - data hiding)
    private String name;
    private int rollNumber;
    private double marks;
    
    
    // Constructor
    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    
    
    // Public methods to access private data (Encapsulation)
    public String getName() {
        return name;
    }
    
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    
    public double getMarks() {
        return marks;
    }
    
    
    public void setMarks(double marks) {
        // Validation - benefit of encapsulation
        if (marks >= 0 && marks <= 100) {
            this.marks = marks;
        } else {
            System.out.println("Invalid marks! Must be between 0 and 100");
        }
    }
    
    
    // Method to display student details
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Marks: " + marks);
        System.out.println();
    }
}


// ============================================
// EXAMPLE 2: Abstraction and Modularity
// ============================================

class BankAccount {
    // Private data - users don't need to know internal implementation
    private String accountNumber;
    private String holderName;
    private double balance;
    private int transactionCount;
    
    
    // Constructor
    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactionCount = 0;
    }
    
    
    // Abstract method - user doesn't need to know how deposit works internally
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionCount++;
            System.out.println("Deposited: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
    
    
    // Abstract method - complex logic hidden from user
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionCount++;
            System.out.println("Withdrawn: $" + amount);
            System.out.println("New Balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }
    
    
    // Simple interface for checking balance
    public double getBalance() {
        return balance;
    }
    
    
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: $" + balance);
        System.out.println("Total Transactions: " + transactionCount);
        System.out.println();
    }
}


// ============================================
// EXAMPLE 3: Code Reusability and Inheritance
// ============================================

// Base class (Parent/Super class)
class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    
    
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    
    // Method that can be reused by child classes
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }
    
    
    public void startEngine() {
        System.out.println("Vehicle engine started!");
    }
}


// Derived class 1 (Child/Sub class) - demonstrating code reusability
class Car extends Vehicle {
    private int numberOfDoors;
    
    
    public Car(String brand, String model, int year, int numberOfDoors) {
        // Reusing parent constructor
        super(brand, model, year);
        this.numberOfDoors = numberOfDoors;
    }
    
    
    // Extending parent functionality
    @Override
    public void displayInfo() {
        super.displayInfo();  // Reusing parent method
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Type: Car");
    }
}


// Derived class 2 - demonstrating code reusability
class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    
    
    public Motorcycle(String brand, String model, int year, boolean hasSidecar) {
        super(brand, model, year);  // Reusing parent constructor
        this.hasSidecar = hasSidecar;
    }
    
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // Reusing parent method
        System.out.println("Has Sidecar: " + (hasSidecar ? "Yes" : "No"));
        System.out.println("Type: Motorcycle");
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit1_OOP_Basics {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(60));
        System.out.println("UNIT 1: OOP BASICS - COMPREHENSIVE EXAMPLES");
        System.out.println("=".repeat(60));
        System.out.println();
        
        
        // ========== EXAMPLE 1: ENCAPSULATION ==========
        System.out.println("EXAMPLE 1: ENCAPSULATION (Data Hiding & Security)");
        System.out.println("-".repeat(60));
        
        Student student1 = new Student("Alice Johnson", 101, 85.5);
        student1.displayInfo();
        
        // Trying to set valid marks
        System.out.println("Setting valid marks (92.0):");
        student1.setMarks(92.0);
        student1.displayInfo();
        
        // Trying to set invalid marks - encapsulation provides validation
        System.out.println("Attempting to set invalid marks (150):");
        student1.setMarks(150);
        student1.displayInfo();
        
        System.out.println();
        
        
        // ========== EXAMPLE 2: ABSTRACTION ==========
        System.out.println("EXAMPLE 2: ABSTRACTION (Hiding Complexity)");
        System.out.println("-".repeat(60));
        
        BankAccount account = new BankAccount("ACC001", "Bob Smith", 1000.0);
        account.displayAccountInfo();
        
        // User doesn't need to know internal implementation
        System.out.println("Performing transactions:");
        account.deposit(500.0);
        System.out.println();
        
        account.withdraw(200.0);
        System.out.println();
        
        account.withdraw(2000.0);  // Testing insufficient balance
        System.out.println();
        
        account.displayAccountInfo();
        System.out.println();
        
        
        // ========== EXAMPLE 3: CODE REUSABILITY (INHERITANCE) ==========
        System.out.println("EXAMPLE 3: CODE REUSABILITY through INHERITANCE");
        System.out.println("-".repeat(60));
        
        System.out.println("Creating Car object (reusing Vehicle code):");
        Car car = new Car("Toyota", "Camry", 2023, 4);
        car.displayInfo();
        car.startEngine();  // Inherited method
        System.out.println();
        
        System.out.println("Creating Motorcycle object (reusing Vehicle code):");
        Motorcycle bike = new Motorcycle("Harley-Davidson", "Street 750", 2022, false);
        bike.displayInfo();
        bike.startEngine();  // Inherited method
        System.out.println();
        
        
        // ========== BENEFITS SUMMARY ==========
        System.out.println("=".repeat(60));
        System.out.println("BENEFITS OF OOP DEMONSTRATED:");
        System.out.println("=".repeat(60));
        System.out.println("1. ENCAPSULATION: Protected student marks with validation");
        System.out.println("2. ABSTRACTION: Hidden complex banking operations");
        System.out.println("3. CODE REUSABILITY: Vehicle class reused by Car and Motorcycle");
        System.out.println("4. MODULARITY: Each class has specific responsibility");
        System.out.println("5. MAINTAINABILITY: Easy to modify individual classes");
        System.out.println("6. SECURITY: Private data members prevent direct access");
        System.out.println("=".repeat(60));
    }
}

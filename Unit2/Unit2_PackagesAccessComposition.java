/*
 * UNIT 2: PACKAGES, ACCESS SPECIFIERS, AND COMPOSITION
 * 
 * THEORY:
 * 
 * PACKAGES: Group of related classes and interfaces
 * - Provide namespace management
 * - Provide access protection
 * - Example: java.util, java.io, java.lang
 * 
 * ACCESS SPECIFIERS: Control visibility of classes, methods, and variables
 * 1. PUBLIC - Accessible from anywhere
 * 2. PRIVATE - Accessible only within the same class
 * 3. PROTECTED - Accessible within package and subclasses
 * 4. DEFAULT (no modifier) - Accessible within package only
 * 
 * COMPOSITION: HAS-A relationship
 * - Creating complex objects from simpler ones
 * - Object contains reference to another object
 * - Example: Car HAS-A Engine
 */


// ============================================
// EXAMPLE 1: Access Specifiers - All Four Types
// ============================================

class BankAccount {
    // PUBLIC - accessible from anywhere
    public String accountNumber;
    
    // PRIVATE - accessible only within this class
    private double balance;
    
    // PROTECTED - accessible within package and subclasses
    protected String accountType;
    
    // DEFAULT (package-private) - accessible within same package only
    String branchCode;
    
    
    public BankAccount(String accountNumber, double balance, String accountType, String branchCode) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.branchCode = branchCode;
    }
    
    
    // PUBLIC method - can be called from anywhere
    public void displayPublicInfo() {
        System.out.println("Public Info - Account Number: " + accountNumber);
    }
    
    
    // PRIVATE method - can only be called within this class
    private void calculateInterest() {
        double interest = balance * 0.05;
        System.out.println("Interest (Private calculation): $" + interest);
    }
    
    
    // PROTECTED method - accessible in package and subclasses
    protected void displayAccountType() {
        System.out.println("Account Type (Protected): " + accountType);
    }
    
    
    // DEFAULT method - accessible within package only
    void displayBranchCode() {
        System.out.println("Branch Code (Default/Package): " + branchCode);
    }
    
    
    // PUBLIC method accessing PRIVATE method
    public void showCompleteDetails() {
        System.out.println("\n=== Complete Account Details ===");
        System.out.println("Account Number (public): " + accountNumber);
        System.out.println("Balance (private): $" + balance);
        System.out.println("Account Type (protected): " + accountType);
        System.out.println("Branch Code (default): " + branchCode);
        calculateInterest();  // Calling private method
        System.out.println("================================\n");
    }
    
    
    // PUBLIC getter for private balance
    public double getBalance() {
        return balance;
    }
    
    
    // PUBLIC setter for private balance with validation
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
            System.out.println("Balance updated to: $" + balance);
        } else {
            System.out.println("Invalid balance! Cannot be negative.");
        }
    }
}


// ============================================
// EXAMPLE 2: Composition (HAS-A Relationship)
// ============================================

// Component class 1: Engine
class Engine {
    private String type;
    private int horsepower;
    private double displacement;
    
    
    public Engine(String type, int horsepower, double displacement) {
        this.type = type;
        this.horsepower = horsepower;
        this.displacement = displacement;
        System.out.println("Engine created: " + type);
    }
    
    
    public void start() {
        System.out.println("Engine starting... Vroom!");
    }
    
    
    public void stop() {
        System.out.println("Engine stopping...");
    }
    
    
    public void displayInfo() {
        System.out.println("  Engine Type: " + type);
        System.out.println("  Horsepower: " + horsepower + " HP");
        System.out.println("  Displacement: " + displacement + " L");
    }
}


// Component class 2: Wheels
class Wheels {
    private int count;
    private String type;
    
    
    public Wheels(int count, String type) {
        this.count = count;
        this.type = type;
        System.out.println("Wheels created: " + count + " " + type + " wheels");
    }
    
    
    public void rotate() {
        System.out.println("Wheels rotating...");
    }
    
    
    public void displayInfo() {
        System.out.println("  Wheel Count: " + count);
        System.out.println("  Wheel Type: " + type);
    }
}


// Composite class: Car HAS-A Engine and HAS-A Wheels
class Car {
    private String brand;
    private String model;
    private Engine engine;    // Composition - Car HAS-A Engine
    private Wheels wheels;    // Composition - Car HAS-A Wheels
    
    
    // Constructor
    public Car(String brand, String model, Engine engine, Wheels wheels) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.wheels = wheels;
        System.out.println("Car assembled: " + brand + " " + model);
    }
    
    
    public void start() {
        System.out.println("\nStarting " + brand + " " + model + "...");
        engine.start();    // Delegating to Engine
        wheels.rotate();   // Delegating to Wheels
        System.out.println("Car is ready to drive!");
    }
    
    
    public void stop() {
        System.out.println("\nStopping " + brand + " " + model + "...");
        engine.stop();     // Delegating to Engine
        System.out.println("Car stopped.");
    }
    
    
    public void displayFullDetails() {
        System.out.println("\n=== Car Details ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("\nEngine Details:");
        engine.displayInfo();
        System.out.println("\nWheel Details:");
        wheels.displayInfo();
        System.out.println("===================\n");
    }
}


// ============================================
// EXAMPLE 3: Complex Composition with Multiple Levels
// ============================================

// Component: Address
class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    
    
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    
    public void display() {
        System.out.println("    " + street);
        System.out.println("    " + city + ", " + state + " " + zipCode);
    }
    
    
    public String getFullAddress() {
        return street + ", " + city + ", " + state + " " + zipCode;
    }
}


// Component: Department
class Department {
    private String name;
    private String code;
    private int employeeCount;
    
    
    public Department(String name, String code) {
        this.name = name;
        this.code = code;
        this.employeeCount = 0;
    }
    
    
    public void addEmployee() {
        employeeCount++;
    }
    
    
    public void display() {
        System.out.println("    Department: " + name);
        System.out.println("    Department Code: " + code);
        System.out.println("    Employees: " + employeeCount);
    }
    
    
    public String getName() {
        return name;
    }
}


// Composite class: Employee HAS-A Address and HAS-A Department
class Employee {
    private String name;
    private int id;
    private Address address;        // Composition - Employee HAS-A Address
    private Department department;  // Composition - Employee HAS-A Department
    private double salary;
    
    
    public Employee(String name, int id, Address address, Department department, double salary) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.department = department;
        this.salary = salary;
        
        department.addEmployee();  // Update department employee count
        System.out.println("Employee created: " + name);
    }
    
    
    public void displayInfo() {
        System.out.println("\n=== Employee Information ===");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Salary: $" + salary);
        System.out.println("\n  Address:");
        address.display();
        System.out.println("\n  Department Info:");
        department.display();
        System.out.println("============================\n");
    }
    
    
    public void relocate(Address newAddress) {
        System.out.println("\n" + name + " is relocating...");
        System.out.println("Old address: " + address.getFullAddress());
        this.address = newAddress;
        System.out.println("New address: " + newAddress.getFullAddress());
    }
    
    
    public void transfer(Department newDepartment) {
        System.out.println("\n" + name + " is transferring...");
        System.out.println("From: " + department.getName());
        this.department = newDepartment;
        newDepartment.addEmployee();
        System.out.println("To: " + newDepartment.getName());
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit2_PackagesAccessComposition {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 2: PACKAGES, ACCESS SPECIFIERS, COMPOSITION");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: ACCESS SPECIFIERS ==========
        System.out.println("EXAMPLE 1: ACCESS SPECIFIERS (public, private, protected, default)");
        System.out.println("-".repeat(70));
        
        BankAccount account = new BankAccount("ACC12345", 10000.0, "Savings", "BR001");
        
        // Accessing PUBLIC member
        System.out.println("\n1. Accessing PUBLIC member:");
        System.out.println("Account Number (public): " + account.accountNumber);
        account.displayPublicInfo();
        
        // Accessing PRIVATE member through PUBLIC getter
        System.out.println("\n2. Accessing PRIVATE member through PUBLIC getter:");
        System.out.println("Balance (private, via getter): $" + account.getBalance());
        
        // Cannot access private directly - this would cause error:
        // System.out.println(account.balance);  // ERROR!
        
        // Setting PRIVATE member through PUBLIC setter
        System.out.println("\n3. Modifying PRIVATE member through PUBLIC setter:");
        account.setBalance(15000.0);
        account.setBalance(-500.0);  // Testing validation
        
        // Accessing PROTECTED method (works in same package)
        System.out.println("\n4. Accessing PROTECTED method (same package):");
        account.displayAccountType();
        
        // Accessing DEFAULT method (works in same package)
        System.out.println("\n5. Accessing DEFAULT method (same package):");
        account.displayBranchCode();
        
        // Display all details
        System.out.println("\n6. Complete details:");
        account.showCompleteDetails();
        
        
        // ========== EXAMPLE 2: COMPOSITION (HAS-A) ==========
        System.out.println("\nEXAMPLE 2: COMPOSITION - HAS-A Relationship");
        System.out.println("-".repeat(70));
        
        System.out.println("\nCreating car components:");
        Engine v8Engine = new Engine("V8", 450, 5.0);
        Wheels sportWheels = new Wheels(4, "Sport");
        
        System.out.println("\nAssembling car:");
        Car myCar = new Car("Ford", "Mustang", v8Engine, sportWheels);
        
        myCar.displayFullDetails();
        
        System.out.println("\nTesting car operations:");
        myCar.start();
        myCar.stop();
        
        System.out.println("\n\nCreating another car:");
        Engine hybridEngine = new Engine("Hybrid", 200, 2.5);
        Wheels ecoWheels = new Wheels(4, "Eco");
        Car car2 = new Car("Toyota", "Prius", hybridEngine, ecoWheels);
        
        car2.displayFullDetails();
        
        
        // ========== EXAMPLE 3: COMPLEX COMPOSITION ==========
        System.out.println("\nEXAMPLE 3: COMPLEX COMPOSITION (Multiple Levels)");
        System.out.println("-".repeat(70));
        
        // Creating departments
        System.out.println("\nCreating departments:");
        Department engineering = new Department("Engineering", "ENG");
        Department marketing = new Department("Marketing", "MKT");
        
        // Creating employees with addresses and departments
        System.out.println("\nCreating employees:");
        
        Address addr1 = new Address("123 Main St", "New York", "NY", "10001");
        Employee emp1 = new Employee("Alice Johnson", 101, addr1, engineering, 85000);
        
        Address addr2 = new Address("456 Oak Ave", "Boston", "MA", "02101");
        Employee emp2 = new Employee("Bob Smith", 102, addr2, engineering, 75000);
        
        Address addr3 = new Address("789 Pine Rd", "Chicago", "IL", "60601");
        Employee emp3 = new Employee("Carol Davis", 103, addr3, marketing, 70000);
        
        // Display employee information
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EMPLOYEE DETAILS:");
        emp1.displayInfo();
        emp2.displayInfo();
        emp3.displayInfo();
        
        // Testing object methods
        System.out.println("=".repeat(70));
        System.out.println("TESTING EMPLOYEE OPERATIONS:");
        
        // Relocating an employee
        Address newAddr = new Address("999 Elm St", "San Francisco", "CA", "94101");
        emp1.relocate(newAddr);
        
        // Transferring an employee
        emp2.transfer(marketing);
        
        System.out.println("\n\nUpdated employee information:");
        emp1.displayInfo();
        emp2.displayInfo();
        
        
        // ========== SUMMARY ==========
        System.out.println("=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("\nACCESS SPECIFIERS:");
        System.out.println("  1. PUBLIC    - Accessible from anywhere");
        System.out.println("  2. PRIVATE   - Accessible only within same class");
        System.out.println("  3. PROTECTED - Accessible within package and subclasses");
        System.out.println("  4. DEFAULT   - Accessible within same package only");
        System.out.println("\nCOMPOSITION (HAS-A Relationship):");
        System.out.println("  1. Car HAS-A Engine (simple composition)");
        System.out.println("  2. Car HAS-A Wheels (simple composition)");
        System.out.println("  3. Employee HAS-A Address (complex composition)");
        System.out.println("  4. Employee HAS-A Department (complex composition)");
        System.out.println("\nKEY POINTS:");
        System.out.println("  - Use private for data hiding (encapsulation)");
        System.out.println("  - Use public for methods that should be accessible");
        System.out.println("  - Composition allows building complex objects from simpler ones");
        System.out.println("  - Composition is more flexible than inheritance");
        System.out.println("=".repeat(70));
    }
}

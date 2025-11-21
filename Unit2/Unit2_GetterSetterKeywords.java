/*
 * UNIT 2: ACCESSOR/MUTATOR METHODS (Getters/Setters), KEYWORDS, AND FINALS
 * 
 * THEORY:
 * 
 * ACCESSOR METHODS (Getters): Methods to READ private data
 * - Naming convention: getPropertyName()
 * - Returns the value of private variable
 * 
 * MUTATOR METHODS (Setters): Methods to MODIFY private data
 * - Naming convention: setPropertyName(value)
 * - Allows validation before setting value
 * 
 * KEYWORDS IN JAVA:
 * - this: Refers to current object
 * - static: Belongs to class, not object
 * - final: Creates constants (variables, methods, classes)
 * - super: Refers to parent class
 * 
 * FINAL KEYWORD:
 * - final variable: Constant (cannot be changed)
 * - final method: Cannot be overridden
 * - final class: Cannot be inherited
 */


import java.util.ArrayList;
import java.util.List;


// ============================================
// EXAMPLE 1: Getter and Setter Methods with Validation
// ============================================

class Person {
    // Private data members (encapsulation)
    private String name;
    private int age;
    private String email;
    private double height;  // in meters
    private double weight;  // in kg
    
    
    // Constructor
    public Person(String name, int age, String email) {
        this.name = name;
        setAge(age);        // Using setter for validation
        setEmail(email);    // Using setter for validation
        this.height = 0.0;
        this.weight = 0.0;
    }
    
    
    // GETTER for name
    public String getName() {
        return name;
    }
    
    
    // SETTER for name with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
            System.out.println("Name updated to: " + name);
        } else {
            System.out.println("Error: Name cannot be empty!");
        }
    }
    
    
    // GETTER for age
    public int getAge() {
        return age;
    }
    
    
    // SETTER for age with validation
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
            System.out.println("Age updated to: " + age);
        } else {
            System.out.println("Error: Invalid age! Must be between 0 and 150.");
        }
    }
    
    
    // GETTER for email
    public String getEmail() {
        return email;
    }
    
    
    // SETTER for email with validation
    public void setEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
            System.out.println("Email updated to: " + email);
        } else {
            System.out.println("Error: Invalid email format!");
        }
    }
    
    
    // GETTER for height
    public double getHeight() {
        return height;
    }
    
    
    // SETTER for height with validation
    public void setHeight(double height) {
        if (height > 0 && height < 3.0) {  // reasonable human height
            this.height = height;
            System.out.println("Height updated to: " + height + " meters");
        } else {
            System.out.println("Error: Invalid height!");
        }
    }
    
    
    // GETTER for weight
    public double getWeight() {
        return weight;
    }
    
    
    // SETTER for weight with validation
    public void setWeight(double weight) {
        if (weight > 0 && weight < 500) {  // reasonable human weight
            this.weight = weight;
            System.out.println("Weight updated to: " + weight + " kg");
        } else {
            System.out.println("Error: Invalid weight!");
        }
    }
    
    
    // Calculated getter - BMI (Body Mass Index)
    public double getBMI() {
        if (height > 0 && weight > 0) {
            return weight / (height * height);
        }
        return 0.0;
    }
    
    
    // Display method
    public void displayInfo() {
        System.out.println("\n=== Person Information ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Height: " + height + " m");
        System.out.println("Weight: " + weight + " kg");
        if (height > 0 && weight > 0) {
            System.out.printf("BMI: %.2f\n", getBMI());
        }
        System.out.println("=========================\n");
    }
}


// ============================================
// EXAMPLE 2: 'this' and 'static' Keywords
// ============================================

class Counter {
    // Instance variable
    private int instanceCount;
    
    // Static variable - shared by all objects
    private static int totalCount = 0;
    
    // Static final constant
    public static final int MAX_COUNT = 1000;
    
    
    // Constructor using 'this' keyword
    public Counter() {
        this.instanceCount = 0;
        totalCount++;
        System.out.println("Counter object created. Total objects: " + totalCount);
    }
    
    
    // Constructor with parameter - 'this' differentiates between parameter and instance variable
    public Counter(int instanceCount) {
        this.instanceCount = instanceCount;  // 'this' refers to instance variable
        totalCount++;
    }
    
    
    // Instance method
    public void increment() {
        this.instanceCount++;  // 'this' is optional but explicit
        totalCount++;          // static variable accessed
        
        if (this.instanceCount > MAX_COUNT) {
            System.out.println("Warning: Instance count exceeded maximum!");
        }
    }
    
    
    // Method returning 'this' for method chaining
    public Counter incrementAndReturn() {
        this.instanceCount++;
        totalCount++;
        return this;  // Returning current object for chaining
    }
    
    
    // Instance method
    public void displayInstanceCount() {
        System.out.println("Instance count: " + this.instanceCount);
    }
    
    
    // Static method - can only access static members
    public static void displayTotalCount() {
        System.out.println("Total count (all objects): " + totalCount);
        // System.out.println(this.instanceCount);  // ERROR! Can't use 'this' in static method
    }
    
    
    // Static method to compare static constant
    public static void displayMaxCount() {
        System.out.println("Maximum allowed count: " + MAX_COUNT);
    }
    
    
    // Method demonstrating 'this' for passing current object
    public void compare(Counter other) {
        System.out.println("Comparing two counters:");
        System.out.println("  This counter: " + this.instanceCount);
        System.out.println("  Other counter: " + other.instanceCount);
        
        if (this.instanceCount > other.instanceCount) {
            System.out.println("  This counter is greater");
        } else if (this.instanceCount < other.instanceCount) {
            System.out.println("  Other counter is greater");
        } else {
            System.out.println("  Both counters are equal");
        }
    }
}


// ============================================
// EXAMPLE 3: 'final' Keyword - Variables, Methods, and Classes
// ============================================

// Final variable example
class MathConstants {
    // Final instance variables - must be initialized
    public final double PI = 3.14159;
    public final int DAYS_IN_WEEK = 7;
    
    // Final static variables (class constants)
    public static final double EULER_NUMBER = 2.71828;
    public static final int MONTHS_IN_YEAR = 12;
    
    // Blank final variable - must be initialized in constructor
    public final String COMPANY_NAME;
    
    
    // Constructor to initialize blank final
    public MathConstants(String companyName) {
        this.COMPANY_NAME = companyName;
        // Once set, cannot be changed
    }
    
    
    public void displayConstants() {
        System.out.println("PI: " + PI);
        System.out.println("Days in week: " + DAYS_IN_WEEK);
        System.out.println("Euler's number: " + EULER_NUMBER);
        System.out.println("Months in year: " + MONTHS_IN_YEAR);
        System.out.println("Company: " + COMPANY_NAME);
    }
    
    
    // Attempting to modify final variable causes error
    public void tryModify() {
        // PI = 3.14;  // ERROR! Cannot assign value to final variable
        // COMPANY_NAME = "New Name";  // ERROR! Cannot modify final variable
    }
}


// Base class with final and non-final methods
class Vehicle {
    protected String brand;
    
    
    public Vehicle(String brand) {
        this.brand = brand;
    }
    
    
    // Regular method - can be overridden
    public void start() {
        System.out.println("Vehicle starting...");
    }
    
    
    // FINAL method - CANNOT be overridden
    public final void displayBrand() {
        System.out.println("Brand: " + brand);
    }
    
    
    // FINAL method with implementation that should never change
    public final double calculateSafetyRating() {
        return 5.0;  // Base safety rating
    }
}


// Subclass
class Car extends Vehicle {
    
    public Car(String brand) {
        super(brand);
    }
    
    
    // Overriding allowed for regular methods
    @Override
    public void start() {
        System.out.println("Car starting with ignition...");
    }
    
    
    // Cannot override final method - this would cause error:
    // @Override
    // public void displayBrand() {  // ERROR! Cannot override final method
    //     System.out.println("Car brand: " + brand);
    // }
}


// FINAL CLASS - CANNOT be inherited
final class Configuration {
    private static final String VERSION = "1.0.0";
    private static final boolean DEBUG_MODE = false;
    
    
    public static void displayConfig() {
        System.out.println("Version: " + VERSION);
        System.out.println("Debug Mode: " + DEBUG_MODE);
    }
}


// Cannot extend final class - this would cause error:
// class ExtendedConfiguration extends Configuration {  // ERROR!
// }


// Demonstrating final with collections
class FinalCollectionsDemo {
    // Final reference - the reference cannot change, but the object contents can
    private final List<String> items = new ArrayList<>();
    
    
    public void addItem(String item) {
        items.add(item);  // This is allowed
        System.out.println("Added: " + item);
    }
    
    
    public void displayItems() {
        System.out.println("Items: " + items);
    }
    
    
    public void demonstrateFinalReference() {
        // items = new ArrayList<>();  // ERROR! Cannot reassign final reference
        
        // But we can modify the object itself
        items.clear();
        items.add("New Item");
        System.out.println("Modified collection: " + items);
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit2_GetterSetterKeywords {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 2: GETTER/SETTER, KEYWORDS (this, static, final)");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: GETTER AND SETTER METHODS ==========
        System.out.println("EXAMPLE 1: ACCESSOR (Getter) and MUTATOR (Setter) Methods");
        System.out.println("-".repeat(70));
        
        Person person1 = new Person("Alice", 25, "alice@email.com");
        person1.displayInfo();
        
        System.out.println("Testing setters with VALID values:");
        person1.setHeight(1.65);
        person1.setWeight(60.0);
        person1.displayInfo();
        
        System.out.println("\nTesting setters with INVALID values:");
        person1.setAge(200);         // Invalid age
        person1.setEmail("invalid");  // Invalid email
        person1.setHeight(5.0);      // Invalid height
        person1.setWeight(-10);      // Invalid weight
        
        System.out.println("\nUsing getters:");
        System.out.println("Name: " + person1.getName());
        System.out.println("Age: " + person1.getAge());
        System.out.println("Calculated BMI: " + person1.getBMI());
        System.out.println();
        
        
        // ========== EXAMPLE 2: 'this' and 'static' KEYWORDS ==========
        System.out.println("\nEXAMPLE 2: 'this' and 'static' Keywords");
        System.out.println("-".repeat(70));
        
        Counter.displayMaxCount();  // Static method called without object
        System.out.println();
        
        System.out.println("Creating Counter objects:");
        Counter counter1 = new Counter();
        Counter counter2 = new Counter(50);
        Counter counter3 = new Counter();
        System.out.println();
        
        System.out.println("Incrementing counters:");
        counter1.increment();
        counter1.increment();
        counter1.increment();
        counter1.displayInstanceCount();
        System.out.println();
        
        counter2.increment();
        counter2.displayInstanceCount();
        System.out.println();
        
        System.out.println("Static method (accessing static variable):");
        Counter.displayTotalCount();
        System.out.println();
        
        System.out.println("Method chaining using 'this':");
        counter3.incrementAndReturn()
                .incrementAndReturn()
                .incrementAndReturn();
        counter3.displayInstanceCount();
        System.out.println();
        
        System.out.println("Using 'this' to compare objects:");
        counter1.compare(counter2);
        System.out.println();
        
        
        // ========== EXAMPLE 3: 'final' KEYWORD ==========
        System.out.println("\nEXAMPLE 3: 'final' Keyword (Constants, Methods, Classes)");
        System.out.println("-".repeat(70));
        
        System.out.println("\n1. Final Variables (Constants):");
        MathConstants constants = new MathConstants("TechCorp");
        constants.displayConstants();
        System.out.println();
        
        System.out.println("2. Final Methods (Cannot be overridden):");
        Vehicle vehicle = new Vehicle("Generic");
        vehicle.start();
        vehicle.displayBrand();
        System.out.println();
        
        Car car = new Car("Toyota");
        car.start();           // Overridden method
        car.displayBrand();    // Final method from parent (cannot override)
        System.out.println("Safety Rating: " + car.calculateSafetyRating());
        System.out.println();
        
        System.out.println("3. Final Class (Cannot be inherited):");
        Configuration.displayConfig();
        System.out.println();
        
        System.out.println("4. Final Reference with Collections:");
        FinalCollectionsDemo demo = new FinalCollectionsDemo();
        demo.addItem("Item 1");
        demo.addItem("Item 2");
        demo.addItem("Item 3");
        demo.displayItems();
        System.out.println("\nModifying final reference contents:");
        demo.demonstrateFinalReference();
        System.out.println();
        
        
        // ========== SUMMARY ==========
        System.out.println("=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("\nGETTER/SETTER METHODS:");
        System.out.println("  - Accessor (getter): Read private data");
        System.out.println("  - Mutator (setter): Modify private data with validation");
        System.out.println("  - Benefits: Data encapsulation, validation, calculated properties");
        System.out.println("\nKEYWORDS:");
        System.out.println("  1. 'this' keyword:");
        System.out.println("     - Refers to current object");
        System.out.println("     - Differentiates between parameters and instance variables");
        System.out.println("     - Used for method chaining");
        System.out.println("     - Pass current object to other methods");
        System.out.println("\n  2. 'static' keyword:");
        System.out.println("     - Belongs to class, not individual objects");
        System.out.println("     - Shared across all instances");
        System.out.println("     - Can be accessed without creating object");
        System.out.println("\n  3. 'final' keyword:");
        System.out.println("     - final variable: Constant (cannot be reassigned)");
        System.out.println("     - final method: Cannot be overridden in subclass");
        System.out.println("     - final class: Cannot be inherited");
        System.out.println("     - final reference: Reference cannot change (but object can)");
        System.out.println("=".repeat(70));
    }
}

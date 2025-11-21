/*
 * UNIT 3: INTERFACES AND ABSTRACT CLASSES
 * 
 * THEORY:
 * 
 * INTERFACE: Contract that classes must follow
 * - Contains abstract methods (no implementation before Java 8)
 * - All methods are public and abstract by default
 * - Variables are public, static, and final by default
 * - A class can implement multiple interfaces (multiple inheritance)
 * - From Java 8: Can have default and static methods
 * - From Java 9: Can have private methods
 * 
 * ABSTRACT CLASS: Partially implemented class
 * - Can have abstract methods (without implementation)
 * - Can have concrete methods (with implementation)
 * - Can have constructors
 * - Can have instance variables
 * - A class can extend only ONE abstract class
 * - Cannot be instantiated directly
 * 
 * WHEN TO USE:
 * - Interface: When you want to define a contract (what to do)
 * - Abstract Class: When you want to provide partial implementation (how to do)
 */


// ============================================
// EXAMPLE 1: Interfaces with Multiple Implementations
// ============================================

// Interface 1: Payment processing
interface Payment {
    // Abstract methods (public and abstract by default)
    void processPayment(double amount);
    boolean validatePayment(double amount);
    String getPaymentMethod();
    
    // Default method (Java 8+)
    default void displayPaymentInfo() {
        System.out.println("Payment Method: " + getPaymentMethod());
    }
    
    // Static method (Java 8+)
    static void showPaymentGuidelines() {
        System.out.println("=== Payment Guidelines ===");
        System.out.println("1. Verify amount");
        System.out.println("2. Process securely");
        System.out.println("3. Generate receipt");
        System.out.println("==========================");
    }
}


// Interface 2: Refund processing
interface Refund {
    void processRefund(double amount);
    boolean isRefundable();
}


// Interface 3: Transaction history
interface TransactionHistory {
    void recordTransaction(String details);
    void displayHistory();
}


// Class implementing single interface
class CreditCardPayment implements Payment {
    private String cardNumber;
    private double balance;
    
    
    public CreditCardPayment(String cardNumber, double balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        System.out.println("Credit Card Payment initialized");
    }
    
    
    @Override
    public void processPayment(double amount) {
        if (validatePayment(amount)) {
            balance -= amount;
            System.out.println("Credit Card Payment processed: $" + amount);
            System.out.println("Remaining balance: $" + balance);
        } else {
            System.out.println("Payment failed: Insufficient balance");
        }
    }
    
    
    @Override
    public boolean validatePayment(double amount) {
        return amount > 0 && amount <= balance;
    }
    
    
    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }
}


// Class implementing multiple interfaces
class PayPalPayment implements Payment, Refund, TransactionHistory {
    private String email;
    private double accountBalance;
    private StringBuilder transactionLog;
    
    
    public PayPalPayment(String email, double accountBalance) {
        this.email = email;
        this.accountBalance = accountBalance;
        this.transactionLog = new StringBuilder();
        System.out.println("PayPal Payment initialized for: " + email);
    }
    
    
    // Implementing Payment interface
    @Override
    public void processPayment(double amount) {
        if (validatePayment(amount)) {
            accountBalance -= amount;
            System.out.println("PayPal Payment processed: $" + amount);
            recordTransaction("Payment: $" + amount);
        } else {
            System.out.println("Payment failed");
        }
    }
    
    
    @Override
    public boolean validatePayment(double amount) {
        return amount > 0 && amount <= accountBalance;
    }
    
    
    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }
    
    
    // Implementing Refund interface
    @Override
    public void processRefund(double amount) {
        if (isRefundable()) {
            accountBalance += amount;
            System.out.println("Refund processed: $" + amount);
            recordTransaction("Refund: $" + amount);
        }
    }
    
    
    @Override
    public boolean isRefundable() {
        return true;  // PayPal supports refunds
    }
    
    
    // Implementing TransactionHistory interface
    @Override
    public void recordTransaction(String details) {
        transactionLog.append(details).append("\n");
    }
    
    
    @Override
    public void displayHistory() {
        System.out.println("\n=== Transaction History ===");
        System.out.println(transactionLog.toString());
        System.out.println("Current Balance: $" + accountBalance);
        System.out.println("===========================\n");
    }
}


// ============================================
// EXAMPLE 2: Abstract Classes with Partial Implementation
// ============================================

// Abstract class: Vehicle (partial implementation)
abstract class Vehicle {
    // Instance variables (allowed in abstract class)
    protected String brand;
    protected String model;
    protected int year;
    protected double price;
    
    
    // Constructor (allowed in abstract class)
    public Vehicle(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        System.out.println("Vehicle created: " + brand + " " + model);
    }
    
    
    // Abstract methods (must be implemented by subclasses)
    public abstract void startEngine();
    public abstract void stopEngine();
    public abstract double calculateMaintenanceCost();
    
    
    // Concrete method (with implementation)
    public void displayInfo() {
        System.out.println("\n=== Vehicle Information ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: $" + price);
    }
    
    
    // Concrete method
    public double calculateDepreciation(int years) {
        double depreciationRate = 0.15;  // 15% per year
        return price * Math.pow((1 - depreciationRate), years);
    }
}


// Concrete class extending abstract class
class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;
    
    
    public Car(String brand, String model, int year, double price, int numberOfDoors, String fuelType) {
        super(brand, model, year, price);  // Calling abstract class constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
    }
    
    
    // Implementing abstract methods
    @Override
    public void startEngine() {
        System.out.println("Car engine starting: Turning ignition key...");
        System.out.println("Engine started successfully!");
    }
    
    
    @Override
    public void stopEngine() {
        System.out.println("Car engine stopping...");
        System.out.println("Engine stopped.");
    }
    
    
    @Override
    public double calculateMaintenanceCost() {
        // Car maintenance cost calculation
        return price * 0.05;  // 5% of price
    }
    
    
    // Additional method specific to Car
    @Override
    public void displayInfo() {
        super.displayInfo();  // Calling parent method
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Annual Maintenance: $" + calculateMaintenanceCost());
        System.out.println("===========================\n");
    }
}


// Another concrete class extending abstract class
class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    private int engineCC;
    
    
    public Motorcycle(String brand, String model, int year, double price, boolean hasSidecar, int engineCC) {
        super(brand, model, year, price);
        this.hasSidecar = hasSidecar;
        this.engineCC = engineCC;
    }
    
    
    @Override
    public void startEngine() {
        System.out.println("Motorcycle engine starting: Kick-starting...");
        System.out.println("Vroom! Engine roaring!");
    }
    
    
    @Override
    public void stopEngine() {
        System.out.println("Motorcycle engine stopping...");
        System.out.println("Engine off.");
    }
    
    
    @Override
    public double calculateMaintenanceCost() {
        // Motorcycle maintenance cost calculation
        return price * 0.03;  // 3% of price
    }
    
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Engine CC: " + engineCC);
        System.out.println("Sidecar: " + (hasSidecar ? "Yes" : "No"));
        System.out.println("Annual Maintenance: $" + calculateMaintenanceCost());
        System.out.println("===========================\n");
    }
}


// ============================================
// EXAMPLE 3: Complex Scenario - Interface + Abstract Class
// ============================================

// Interface: Drawable
interface Drawable {
    void draw();
    void resize(double factor);
}


// Interface: Colorable
interface Colorable {
    void setColor(String color);
    String getColor();
}


// Abstract class implementing interface
abstract class Shape implements Drawable, Colorable {
    protected String name;
    protected String color;
    protected double area;
    
    
    public Shape(String name) {
        this.name = name;
        this.color = "Black";  // Default color
    }
    
    
    // Abstract method
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    
    
    // Implementing Colorable interface
    @Override
    public void setColor(String color) {
        this.color = color;
        System.out.println(name + " color changed to: " + color);
    }
    
    
    @Override
    public String getColor() {
        return color;
    }
    
    
    // Partial implementation of Drawable interface
    @Override
    public void draw() {
        System.out.println("Drawing " + name + " with color " + color);
    }
    
    
    // Concrete method
    public void displayDetails() {
        System.out.println("\n=== Shape Details ===");
        System.out.println("Shape: " + name);
        System.out.println("Color: " + color);
        System.out.println("Area: " + calculateArea());
        System.out.println("Perimeter: " + calculatePerimeter());
        System.out.println("=====================\n");
    }
}


// Concrete class
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
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    
    
    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.println("Circle resized by factor: " + factor);
        System.out.println("New radius: " + radius);
    }
}


// Another concrete class
class Rectangle extends Shape {
    private double length;
    private double width;
    
    
    public Rectangle(double length, double width) {
        super("Rectangle");
        this.length = length;
        this.width = width;
    }
    
    
    @Override
    public double calculateArea() {
        return length * width;
    }
    
    
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
    
    
    @Override
    public void resize(double factor) {
        length *= factor;
        width *= factor;
        System.out.println("Rectangle resized by factor: " + factor);
        System.out.println("New dimensions: " + length + " x " + width);
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit3_InterfacesAbstractClasses {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 3: INTERFACES AND ABSTRACT CLASSES");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: INTERFACES ==========
        System.out.println("EXAMPLE 1: Interfaces with Multiple Implementations");
        System.out.println("-".repeat(70));
        
        // Static method from interface
        System.out.println("\nCalling static method from interface:");
        Payment.showPaymentGuidelines();
        System.out.println();
        
        // Single interface implementation
        System.out.println("1. Credit Card Payment (Single Interface):");
        CreditCardPayment creditCard = new CreditCardPayment("1234-5678-9012-3456", 5000);
        creditCard.displayPaymentInfo();  // Default method from interface
        creditCard.processPayment(1500);
        creditCard.processPayment(4000);  // Should fail
        System.out.println();
        
        // Multiple interface implementation
        System.out.println("\n2. PayPal Payment (Multiple Interfaces):");
        PayPalPayment paypal = new PayPalPayment("user@email.com", 3000);
        paypal.displayPaymentInfo();
        paypal.processPayment(500);
        paypal.processPayment(800);
        paypal.processRefund(200);
        paypal.displayHistory();
        
        
        // ========== EXAMPLE 2: ABSTRACT CLASSES ==========
        System.out.println("\nEXAMPLE 2: Abstract Classes with Partial Implementation");
        System.out.println("-".repeat(70));
        
        System.out.println("\n1. Creating Car (extending abstract Vehicle):");
        Car car = new Car("Toyota", "Camry", 2023, 30000, 4, "Gasoline");
        car.displayInfo();
        car.startEngine();
        car.stopEngine();
        
        System.out.println("\nDepreciation after 3 years: $" + car.calculateDepreciation(3));
        System.out.println();
        
        System.out.println("\n2. Creating Motorcycle (extending abstract Vehicle):");
        Motorcycle bike = new Motorcycle("Harley-Davidson", "Street 750", 2022, 8000, false, 750);
        bike.displayInfo();
        bike.startEngine();
        bike.stopEngine();
        
        System.out.println("\nDepreciation after 2 years: $" + bike.calculateDepreciation(2));
        System.out.println();
        
        
        // ========== EXAMPLE 3: INTERFACE + ABSTRACT CLASS ==========
        System.out.println("\nEXAMPLE 3: Complex Scenario (Interface + Abstract Class)");
        System.out.println("-".repeat(70));
        
        System.out.println("\n1. Circle (extends abstract Shape, implements interfaces):");
        Circle circle = new Circle(5.0);
        circle.draw();
        circle.displayDetails();
        circle.setColor("Red");
        circle.resize(1.5);
        circle.displayDetails();
        
        System.out.println("\n2. Rectangle (extends abstract Shape, implements interfaces):");
        Rectangle rectangle = new Rectangle(10.0, 5.0);
        rectangle.draw();
        rectangle.displayDetails();
        rectangle.setColor("Blue");
        rectangle.resize(0.8);
        rectangle.displayDetails();
        
        
        // ========== POLYMORPHISM WITH INTERFACES AND ABSTRACT CLASSES ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("POLYMORPHISM DEMONSTRATION:");
        System.out.println("=".repeat(70));
        
        System.out.println("\n1. Interface reference with different implementations:");
        Payment payment1 = new CreditCardPayment("9999-8888-7777-6666", 2000);
        Payment payment2 = new PayPalPayment("another@email.com", 1500);
        
        payment1.processPayment(500);
        payment2.processPayment(300);
        System.out.println();
        
        System.out.println("\n2. Abstract class reference with different implementations:");
        Vehicle vehicle1 = new Car("Honda", "Civic", 2024, 25000, 4, "Hybrid");
        Vehicle vehicle2 = new Motorcycle("Yamaha", "R15", 2023, 3000, false, 155);
        
        vehicle1.displayInfo();
        vehicle1.startEngine();
        System.out.println("Maintenance: $" + vehicle1.calculateMaintenanceCost());
        System.out.println();
        
        vehicle2.displayInfo();
        vehicle2.startEngine();
        System.out.println("Maintenance: $" + vehicle2.calculateMaintenanceCost());
        System.out.println();
        
        
        // ========== SUMMARY ==========
        System.out.println("=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("\nINTERFACE:");
        System.out.println("  - Pure abstraction (contract)");
        System.out.println("  - All methods public and abstract (before Java 8)");
        System.out.println("  - Can have default methods (Java 8+)");
        System.out.println("  - Can have static methods (Java 8+)");
        System.out.println("  - Multiple inheritance supported");
        System.out.println("  - Variables are public, static, final");
        System.out.println("\nABSTRACT CLASS:");
        System.out.println("  - Partial abstraction");
        System.out.println("  - Can have abstract and concrete methods");
        System.out.println("  - Can have constructors and instance variables");
        System.out.println("  - Single inheritance only");
        System.out.println("  - Cannot be instantiated");
        System.out.println("\nKEY DIFFERENCES:");
        System.out.println("  - Interface: WHAT to do (contract)");
        System.out.println("  - Abstract Class: HOW to do (partial implementation)");
        System.out.println("  - Use Interface for multiple inheritance");
        System.out.println("  - Use Abstract Class for code reuse with partial implementation");
        System.out.println("=".repeat(70));
    }
}

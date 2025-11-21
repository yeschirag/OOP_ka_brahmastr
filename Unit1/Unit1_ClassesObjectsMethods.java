/*
 * UNIT 1: DEFINING CLASSES, OBJECTS, AND METHODS
 * 
 * THEORY:
 * - CLASS: Blueprint or template for creating objects
 * - OBJECT: Instance of a class (actual entity)
 * - METHOD: Function defined inside a class (behavior)
 * 
 * Class Structure:
 * - Data members (fields/attributes)
 * - Methods (functions/behaviors)
 * - Constructors (special methods to initialize objects)
 * 
 * Method Types:
 * - Instance methods (require object)
 * - Static methods (belong to class)
 * - Return type methods
 * - Void methods
 */


// ============================================
// EXAMPLE 1: Basic Class and Object Creation
// ============================================

class Book {
    // Data members (attributes/fields)
    String title;
    String author;
    double price;
    int pages;
    boolean isAvailable;
    
    
    // Default constructor
    Book() {
        title = "Unknown";
        author = "Unknown";
        price = 0.0;
        pages = 0;
        isAvailable = true;
        System.out.println("Book object created with default constructor");
    }
    
    
    // Parameterized constructor
    Book(String title, String author, double price, int pages) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.pages = pages;
        this.isAvailable = true;
        System.out.println("Book object created: " + title);
    }
    
    
    // Instance method - requires object to call
    void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("Pages: " + pages);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
    }
    
    
    // Method with return type
    double calculateDiscountedPrice(double discountPercent) {
        double discount = price * (discountPercent / 100);
        return price - discount;
    }
    
    
    // Method with boolean return
    boolean borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Book borrowed successfully!");
            return true;
        } else {
            System.out.println("Book not available!");
            return false;
        }
    }
    
    
    // Method to return book
    void returnBook() {
        isAvailable = true;
        System.out.println("Book returned successfully!");
    }
}


// ============================================
// EXAMPLE 2: Methods with Different Parameters and Return Types
// ============================================

class Calculator {
    // Static variable - belongs to class, not object
    static int calculationCount = 0;
    
    
    // Instance method - addition (two parameters)
    int add(int a, int b) {
        calculationCount++;
        int result = a + b;
        System.out.println(a + " + " + b + " = " + result);
        return result;
    }
    
    
    // Method overloading - same name, different parameters
    double add(double a, double b) {
        calculationCount++;
        double result = a + b;
        System.out.println(a + " + " + b + " = " + result);
        return result;
    }
    
    
    // Method with multiple parameters
    int add(int a, int b, int c) {
        calculationCount++;
        int result = a + b + c;
        System.out.println(a + " + " + b + " + " + c + " = " + result);
        return result;
    }
    
    
    // Method returning boolean
    boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    
    // Method returning String
    String getNumberType(int number) {
        if (number > 0) {
            return "Positive";
        } else if (number < 0) {
            return "Negative";
        } else {
            return "Zero";
        }
    }
    
    
    // Static method - can be called without object
    static void displayCalculationCount() {
        System.out.println("Total calculations performed: " + calculationCount);
    }
    
    
    // Method with array parameter
    int findMax(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }
        
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
    
    // Method with varargs (variable arguments)
    int sum(int... numbers) {
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
// EXAMPLE 3: Complex Class with Multiple Object Interactions
// ============================================

class Employee {
    // Private data members (encapsulation)
    private int employeeId;
    private String name;
    private String department;
    private double salary;
    private int yearsOfService;
    
    
    // Static variable - shared by all objects
    private static int totalEmployees = 0;
    private static double totalSalaryExpense = 0;
    
    
    // Constructor
    Employee(int employeeId, String name, String department, double salary, int yearsOfService) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.yearsOfService = yearsOfService;
        
        totalEmployees++;
        totalSalaryExpense += salary;
        
        System.out.println("Employee " + name + " hired!");
    }
    
    
    // Getter methods
    public int getEmployeeId() {
        return employeeId;
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public String getDepartment() {
        return department;
    }
    
    
    public double getSalary() {
        return salary;
    }
    
    
    // Method to calculate bonus based on years of service
    public double calculateBonus() {
        double bonus = 0;
        
        if (yearsOfService >= 10) {
            bonus = salary * 0.15;  // 15% bonus
        } else if (yearsOfService >= 5) {
            bonus = salary * 0.10;  // 10% bonus
        } else {
            bonus = salary * 0.05;  // 5% bonus
        }
        
        return bonus;
    }
    
    
    // Method to give raise
    public void giveRaise(double percentage) {
        double oldSalary = salary;
        double raise = salary * (percentage / 100);
        
        totalSalaryExpense -= oldSalary;
        salary += raise;
        totalSalaryExpense += salary;
        
        System.out.println("Raise given to " + name);
        System.out.println("Old Salary: $" + oldSalary);
        System.out.println("Raise: $" + raise + " (" + percentage + "%)");
        System.out.println("New Salary: $" + salary);
    }
    
    
    // Method to display employee details
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: $" + salary);
        System.out.println("Years of Service: " + yearsOfService);
        System.out.println("Bonus: $" + calculateBonus());
    }
    
    
    // Static method to display company statistics
    public static void displayCompanyStats() {
        System.out.println("Total Employees: " + totalEmployees);
        System.out.println("Total Salary Expense: $" + totalSalaryExpense);
        if (totalEmployees > 0) {
            System.out.println("Average Salary: $" + (totalSalaryExpense / totalEmployees));
        }
    }
    
    
    // Method demonstrating object interaction
    public boolean isSameDepartment(Employee other) {
        return this.department.equals(other.department);
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit1_ClassesObjectsMethods {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 1: CLASSES, OBJECTS, AND METHODS - COMPREHENSIVE EXAMPLES");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: BASIC CLASS AND OBJECTS ==========
        System.out.println("EXAMPLE 1: BASIC CLASS AND OBJECTS");
        System.out.println("-".repeat(70));
        
        // Creating objects using different constructors
        System.out.println("\nCreating Book objects:");
        Book book1 = new Book();  // Default constructor
        System.out.println();
        
        Book book2 = new Book("Java Programming", "John Doe", 49.99, 500);
        System.out.println();
        
        Book book3 = new Book("Data Structures", "Jane Smith", 59.99, 650);
        System.out.println();
        
        
        // Calling methods on objects
        System.out.println("\nBook 1 Details:");
        book1.displayInfo();
        System.out.println();
        
        System.out.println("Book 2 Details:");
        book2.displayInfo();
        System.out.println();
        
        
        // Using methods with return values
        System.out.println("Book 2 with 20% discount:");
        double discountedPrice = book2.calculateDiscountedPrice(20);
        System.out.println("Discounted Price: $" + discountedPrice);
        System.out.println();
        
        
        // Testing borrowing functionality
        System.out.println("Attempting to borrow book2:");
        book2.borrowBook();
        System.out.println();
        
        System.out.println("Attempting to borrow book2 again:");
        book2.borrowBook();
        System.out.println();
        
        System.out.println("Returning book2:");
        book2.returnBook();
        System.out.println();
        
        
        // ========== EXAMPLE 2: METHODS WITH DIFFERENT PARAMETERS ==========
        System.out.println("\nEXAMPLE 2: METHODS WITH DIFFERENT PARAMETERS AND RETURN TYPES");
        System.out.println("-".repeat(70));
        
        Calculator calc1 = new Calculator();
        Calculator calc2 = new Calculator();  // Another object
        
        
        System.out.println("\nMethod Overloading (same name, different parameters):");
        calc1.add(10, 20);
        calc1.add(15.5, 24.5);
        calc1.add(5, 10, 15);
        System.out.println();
        
        
        System.out.println("Methods with different return types:");
        boolean isEvenNumber = calc1.isEven(42);
        System.out.println("Is 42 even? " + isEvenNumber);
        
        String numberType = calc1.getNumberType(-15);
        System.out.println("Type of -15: " + numberType);
        System.out.println();
        
        
        System.out.println("Method with array parameter:");
        int[] numbers = {23, 67, 12, 89, 45, 91, 34};
        int maximum = calc2.findMax(numbers);
        System.out.println("Maximum in array: " + maximum);
        System.out.println();
        
        
        System.out.println("Method with varargs (variable arguments):");
        calc2.sum(1, 2, 3);
        calc2.sum(10, 20, 30, 40, 50);
        System.out.println();
        
        
        // Static method - called without object
        System.out.println("Calling static method:");
        Calculator.displayCalculationCount();
        System.out.println();
        
        
        // ========== EXAMPLE 3: COMPLEX CLASS WITH OBJECT INTERACTIONS ==========
        System.out.println("\nEXAMPLE 3: COMPLEX CLASS WITH MULTIPLE OBJECT INTERACTIONS");
        System.out.println("-".repeat(70));
        
        // Creating multiple employee objects
        System.out.println("\nHiring employees:");
        Employee emp1 = new Employee(101, "Alice Johnson", "Engineering", 75000, 6);
        System.out.println();
        
        Employee emp2 = new Employee(102, "Bob Smith", "Engineering", 65000, 3);
        System.out.println();
        
        Employee emp3 = new Employee(103, "Carol Davis", "Marketing", 70000, 12);
        System.out.println();
        
        
        // Displaying employee details
        System.out.println("\nEmployee 1 Details:");
        emp1.displayDetails();
        System.out.println();
        
        System.out.println("Employee 2 Details:");
        emp2.displayDetails();
        System.out.println();
        
        System.out.println("Employee 3 Details:");
        emp3.displayDetails();
        System.out.println();
        
        
        // Giving raise to an employee
        System.out.println("\nGiving 10% raise to Alice:");
        emp1.giveRaise(10);
        System.out.println();
        
        
        // Object interaction - comparing employees
        System.out.println("Checking if employees are in same department:");
        boolean sameDept1 = emp1.isSameDepartment(emp2);
        System.out.println(emp1.getName() + " and " + emp2.getName() + 
                          " in same department? " + sameDept1);
        
        boolean sameDept2 = emp1.isSameDepartment(emp3);
        System.out.println(emp1.getName() + " and " + emp3.getName() + 
                          " in same department? " + sameDept2);
        System.out.println();
        
        
        // Calling static method to display company stats
        System.out.println("Company Statistics:");
        Employee.displayCompanyStats();
        System.out.println();
        
        
        // ========== SUMMARY ==========
        System.out.println("=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("1. Class definition with data members and methods");
        System.out.println("2. Object creation using constructors");
        System.out.println("3. Instance methods (require object)");
        System.out.println("4. Static methods (belong to class)");
        System.out.println("5. Methods with different return types (void, int, double, boolean)");
        System.out.println("6. Method overloading (same name, different parameters)");
        System.out.println("7. Methods with array parameters");
        System.out.println("8. Methods with varargs (variable arguments)");
        System.out.println("9. Getter methods for encapsulation");
        System.out.println("10. Object interaction (objects calling methods on other objects)");
        System.out.println("11. Static variables (shared across all objects)");
        System.out.println("=".repeat(70));
    }
}

/*
 * UNIT 4: EXCEPTION HANDLING
 * 
 * Hey Chirag! Welcome to Exception Handling - where we learn to gracefully handle failure.
 * Kind of like how you handle interview rejections, but with more try-catch blocks! 😂
 * 
 * Pro Tip: 'try' is what you do with your code. 'catch' is what you do with errors. 
 * 'finally' is where you question your life choices. 🎯
 * 
 * THEORY:
 * 
 * EXCEPTION: Abnormal condition/event that disrupts normal program flow
 * 
 * Exception Hierarchy:
 * Throwable
 *  ├── Error (System errors - not handled by programs)
 *  └── Exception
 *       ├── Checked Exceptions (IOException, SQLException - must handle)
 *       └── Unchecked Exceptions (RuntimeException - optional handling)
 *            ├── ArithmeticException
 *            ├── NullPointerException
 *            ├── ArrayIndexOutOfBoundsException
 *            └── NumberFormatException
 * 
 * Exception Handling Keywords:
 * 1. try - Block of code that might throw exception
 * 2. catch - Handles specific exception
 * 3. finally - Always executes (cleanup code)
 * 4. throw - Manually throw an exception
 * 5. throws - Declare exceptions in method signature
 * 
 * Types:
 * - Checked Exception: Checked at compile-time (must handle)
 * - Unchecked Exception: Checked at runtime (optional handling)
 * - Custom Exception: User-defined exceptions
 */


// ============================================
// EXAMPLE 1: Built-in Exceptions (try-catch-finally)
// ============================================

class BuiltInExceptions {
    
    // Method demonstrating ArithmeticException
    public void demonstrateArithmeticException() {
        System.out.println("\n=== ArithmeticException Demo ===");
        
        try {
            int a = 100;
            int b = 0;
            System.out.println("Attempting to divide: " + a + " / " + b);
            int result = a / b;  // This will throw ArithmeticException
            System.out.println("Result: " + result);
            
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("Error: Cannot divide by zero!");
            
        } finally {
            System.out.println("Finally block: This always executes");
        }
        
        System.out.println("Program continues after exception handling\n");
    }
    
    
    // Method demonstrating NullPointerException
    public void demonstrateNullPointerException() {
        System.out.println("\n=== NullPointerException Demo ===");
        
        try {
            String str = null;
            System.out.println("Attempting to call method on null object");
            int length = str.length();  // This will throw NullPointerException
            System.out.println("Length: " + length);
            
        } catch (NullPointerException e) {
            System.out.println("Exception caught: " + e.getClass().getName());
            System.out.println("Error: Cannot call methods on null reference!");
            System.out.println("Stack trace (first 3 lines):");
            StackTraceElement[] trace = e.getStackTrace();
            for (int i = 0; i < Math.min(3, trace.length); i++) {
                System.out.println("  at " + trace[i]);
            }
            
        } finally {
            System.out.println("Finally: Cleanup operations");
        }
        
        System.out.println("Execution continues\n");
    }
    
    
    // Method demonstrating ArrayIndexOutOfBoundsException
    public void demonstrateArrayIndexException() {
        System.out.println("\n=== ArrayIndexOutOfBoundsException Demo ===");
        
        try {
            int[] numbers = {10, 20, 30, 40, 50};
            System.out.println("Array length: " + numbers.length);
            System.out.println("Attempting to access index 10");
            int value = numbers[10];  // This will throw exception
            System.out.println("Value: " + value);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Error: Array index out of bounds!");
            
        } finally {
            System.out.println("Finally: Array bounds checking is important");
        }
        
        System.out.println();
    }
    
    
    // Method demonstrating NumberFormatException
    public void demonstrateNumberFormatException() {
        System.out.println("\n=== NumberFormatException Demo ===");
        
        try {
            String invalidNumber = "123ABC";
            System.out.println("Attempting to parse: \"" + invalidNumber + "\"");
            int number = Integer.parseInt(invalidNumber);
            System.out.println("Parsed number: " + number);
            
        } catch (NumberFormatException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Error: Invalid number format!");
            
        } finally {
            System.out.println("Finally: Always validate input before parsing");
        }
        
        System.out.println();
    }
    
    
    // Method demonstrating multiple catch blocks
    public void demonstrateMultipleCatch(String operation) {
        System.out.println("\n=== Multiple Catch Blocks Demo ===");
        System.out.println("Operation: " + operation);
        
        try {
            if (operation.equals("divide")) {
                int result = 100 / 0;
                System.out.println("Result: " + result);
                
            } else if (operation.equals("array")) {
                int[] arr = {1, 2, 3};
                System.out.println("Value: " + arr[5]);
                
            } else if (operation.equals("null")) {
                String str = null;
                System.out.println("Length: " + str.length());
                
            } else if (operation.equals("parse")) {
                int num = Integer.parseInt("abc");
                System.out.println("Number: " + num);
            }
            
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception: " + e.getMessage());
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array Index Exception: " + e.getMessage());
            
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception: " + e.getMessage());
            
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception: " + e.getMessage());
            
        } catch (Exception e) {
            // Generic catch block for any other exceptions
            System.out.println("General Exception: " + e.getMessage());
            
        } finally {
            System.out.println("Finally: Exception handled successfully");
        }
        
        System.out.println();
    }
}


// ============================================
// EXAMPLE 2: Custom Exceptions
// ============================================

// Custom checked exception
class InsufficientBalanceException extends Exception {
    private double balance;
    private double withdrawAmount;
    
    
    public InsufficientBalanceException(double balance, double withdrawAmount) {
        super("Insufficient balance! Available: $" + balance + ", Requested: $" + withdrawAmount);
        this.balance = balance;
        this.withdrawAmount = withdrawAmount;
    }
    
    
    public double getBalance() {
        return balance;
    }
    
    
    public double getWithdrawAmount() {
        return withdrawAmount;
    }
    
    
    public double getShortfall() {
        return withdrawAmount - balance;
    }
}


// Custom unchecked exception
class InvalidAccountException extends RuntimeException {
    private String accountNumber;
    
    
    public InvalidAccountException(String accountNumber) {
        super("Invalid account number: " + accountNumber);
        this.accountNumber = accountNumber;
    }
    
    
    public String getAccountNumber() {
        return accountNumber;
    }
}


// Custom exception for age validation
class InvalidAgeException extends Exception {
    private int age;
    
    
    public InvalidAgeException(int age, String message) {
        super(message + " Age provided: " + age);
        this.age = age;
    }
    
    
    public int getAge() {
        return age;
    }
}


// Class using custom exceptions
class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;
    
    
    public BankAccount(String accountNumber, String holderName, double initialBalance) 
           throws InvalidAccountException {
        
        // Validating account number
        if (accountNumber == null || accountNumber.length() < 5) {
            throw new InvalidAccountException(accountNumber);
        }
        
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        
        System.out.println("Account created for: " + holderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Initial Balance: $" + balance);
    }
    
    
    // Method that throws custom checked exception
    public void withdraw(double amount) throws InsufficientBalanceException {
        System.out.println("\nProcessing withdrawal: $" + amount);
        
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }
        
        balance -= amount;
        System.out.println("Withdrawal successful!");
        System.out.println("New balance: $" + balance);
    }
    
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive!");
        }
        
        balance += amount;
        System.out.println("Deposited: $" + amount);
        System.out.println("New balance: $" + balance);
    }
    
    
    public double getBalance() {
        return balance;
    }
}


// Class for age validation
class Person {
    private String name;
    private int age;
    
    
    public Person(String name, int age) throws InvalidAgeException {
        this.name = name;
        
        // Age validation with custom exception
        if (age < 0) {
            throw new InvalidAgeException(age, "Age cannot be negative!");
        } else if (age > 150) {
            throw new InvalidAgeException(age, "Age seems unrealistic!");
        }
        
        this.age = age;
        System.out.println("Person created: " + name + ", Age: " + age);
    }
    
    
    public void checkVotingEligibility() throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException(age, "Not eligible to vote!");
        }
        System.out.println(name + " is eligible to vote!");
    }
}


// ============================================
// EXAMPLE 3: Exception Propagation and throw/throws
// ============================================

class ExceptionPropagation {
    
    // Method 3: throws exception
    public void level3Method() throws Exception {
        System.out.println("  Level 3: Throwing exception");
        throw new Exception("Exception from level 3");
    }
    
    
    // Method 2: propagates exception
    public void level2Method() throws Exception {
        System.out.println(" Level 2: Calling level 3");
        level3Method();  // Exception propagates to caller
        System.out.println(" Level 2: This won't execute");
    }
    
    
    // Method 1: propagates exception
    public void level1Method() throws Exception {
        System.out.println("Level 1: Calling level 2");
        level2Method();  // Exception propagates to caller
        System.out.println("Level 1: This won't execute");
    }
    
    
    // Method demonstrating throw keyword
    public void validateAge(int age) {
        System.out.println("\nValidating age: " + age);
        
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative: " + age);
        } else if (age < 18) {
            throw new IllegalArgumentException("Must be 18 or older: " + age);
        } else if (age > 120) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
        
        System.out.println("Age validation successful!");
    }
    
    
    // Method with multiple throws declarations
    public void processTransaction(String accountId, double amount) 
           throws InvalidAccountException, InsufficientBalanceException {
        
        System.out.println("\nProcessing transaction for: " + accountId);
        
        // Validate account
        if (accountId == null || accountId.isEmpty()) {
            throw new InvalidAccountException(accountId);
        }
        
        // Check balance (simulated)
        double currentBalance = 5000.0;
        if (amount > currentBalance) {
            throw new InsufficientBalanceException(currentBalance, amount);
        }
        
        System.out.println("Transaction successful: $" + amount);
    }
}


// ============================================
// Nested try-catch blocks
// ============================================

class NestedExceptionHandling {
    
    public void demonstrateNestedTryCatch() {
        System.out.println("\n=== Nested Try-Catch Demo ===");
        
        try {
            System.out.println("Outer try block");
            
            try {
                System.out.println("Inner try block");
                int[] arr = {1, 2, 3};
                System.out.println("Accessing index 5: " + arr[5]);
                
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: Array exception handled");
            }
            
            System.out.println("Back in outer try block");
            int result = 10 / 0;  // This will be caught by outer catch
            
        } catch (ArithmeticException e) {
            System.out.println("Outer catch: Arithmetic exception handled");
            
        } finally {
            System.out.println("Outer finally: Cleanup complete");
        }
        
        System.out.println();
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit4_ExceptionHandling {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 4: EXCEPTION HANDLING");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: BUILT-IN EXCEPTIONS ==========
        System.out.println("EXAMPLE 1: Built-in Exceptions (try-catch-finally)");
        System.out.println("-".repeat(70));
        
        BuiltInExceptions demo1 = new BuiltInExceptions();
        
        demo1.demonstrateArithmeticException();
        demo1.demonstrateNullPointerException();
        demo1.demonstrateArrayIndexException();
        demo1.demonstrateNumberFormatException();
        
        System.out.println("\nTesting multiple catch blocks:");
        demo1.demonstrateMultipleCatch("divide");
        demo1.demonstrateMultipleCatch("array");
        demo1.demonstrateMultipleCatch("null");
        demo1.demonstrateMultipleCatch("parse");
        
        
        // ========== EXAMPLE 2: CUSTOM EXCEPTIONS ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 2: Custom Exceptions");
        System.out.println("-".repeat(70));
        
        // Test 1: Valid bank account
        try {
            System.out.println("\n1. Creating valid bank account:");
            BankAccount account = new BankAccount("ACC12345", "John Doe", 10000);
            
            System.out.println("\n2. Valid withdrawal:");
            account.withdraw(3000);
            
            System.out.println("\n3. Invalid withdrawal (insufficient balance):");
            account.withdraw(9000);
            
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid account: " + e.getAccountNumber());
            
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Shortfall: $" + e.getShortfall());
        }
        
        
        // Test 2: Invalid bank account
        try {
            System.out.println("\n4. Creating invalid bank account:");
            BankAccount badAccount = new BankAccount("123", "Jane", 5000);
            
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
        // Test 3: Age validation
        try {
            System.out.println("\n5. Testing age validation:");
            Person person1 = new Person("Alice", 25);
            person1.checkVotingEligibility();
            
            System.out.println("\n6. Creating person with invalid age:");
            Person person2 = new Person("Bob", -5);
            
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid age provided: " + e.getAge());
        }
        
        
        try {
            System.out.println("\n7. Checking voting eligibility (underage):");
            Person youngPerson = new Person("Charlie", 15);
            youngPerson.checkVotingEligibility();
            
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
        // ========== EXAMPLE 3: EXCEPTION PROPAGATION ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 3: Exception Propagation and throw/throws");
        System.out.println("-".repeat(70));
        
        ExceptionPropagation propagation = new ExceptionPropagation();
        
        // Test propagation
        try {
            System.out.println("\n1. Testing exception propagation:");
            propagation.level1Method();
            
        } catch (Exception e) {
            System.out.println("Exception caught in main: " + e.getMessage());
            System.out.println("Exception propagated through multiple levels");
        }
        
        
        // Test throw keyword
        try {
            System.out.println("\n2. Testing 'throw' keyword:");
            propagation.validateAge(25);
            propagation.validateAge(-5);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
        
        
        // Test multiple throws
        try {
            System.out.println("\n3. Testing method with multiple 'throws':");
            propagation.processTransaction("ACC789", 3000);
            propagation.processTransaction("", 1000);
            
        } catch (InvalidAccountException e) {
            System.out.println("Account Error: " + e.getMessage());
            
        } catch (InsufficientBalanceException e) {
            System.out.println("Balance Error: " + e.getMessage());
        }
        
        
        // Test nested try-catch
        NestedExceptionHandling nested = new NestedExceptionHandling();
        nested.demonstrateNestedTryCatch();
        
        
        // ========== SUMMARY ==========
        System.out.println("=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("\n1. BUILT-IN EXCEPTIONS:");
        System.out.println("   - ArithmeticException");
        System.out.println("   - NullPointerException");
        System.out.println("   - ArrayIndexOutOfBoundsException");
        System.out.println("   - NumberFormatException");
        System.out.println("\n2. EXCEPTION HANDLING:");
        System.out.println("   - try-catch-finally blocks");
        System.out.println("   - Multiple catch blocks");
        System.out.println("   - Nested try-catch");
        System.out.println("\n3. CUSTOM EXCEPTIONS:");
        System.out.println("   - Checked exceptions (extends Exception)");
        System.out.println("   - Unchecked exceptions (extends RuntimeException)");
        System.out.println("   - Exception with additional data");
        System.out.println("\n4. KEYWORDS:");
        System.out.println("   - throw: Manually throw exception");
        System.out.println("   - throws: Declare exceptions in method signature");
        System.out.println("   - finally: Always executes (cleanup code)");
        System.out.println("\n5. EXCEPTION PROPAGATION:");
        System.out.println("   - Exceptions propagate through call stack");
        System.out.println("   - Can be caught at any level");
        System.out.println("=".repeat(70));
    }
}

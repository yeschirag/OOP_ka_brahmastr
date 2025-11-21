/*
 * UNIT 1: CONTROL FLOW AND BRANCHING IN JAVA
 * 
 * THEORY: Control flow statements allow you to control the order of execution
 * 
 * Types of Control Flow:
 * 1. SEQUENTIAL - Statements execute one after another (default)
 * 2. SELECTION - if, if-else, switch (branching)
 * 3. ITERATION - for, while, do-while (loops)
 * 4. JUMP - break, continue, return
 */


// ============================================
// EXAMPLE 1: Selection Statements (if-else, nested if)
// ============================================

class GradeCalculator {
    private String studentName;
    private double marks;
    
    
    public GradeCalculator(String studentName, double marks) {
        this.studentName = studentName;
        this.marks = marks;
    }
    
    
    // Method demonstrating if-else and nested if
    public void calculateGrade() {
        System.out.println("Student: " + studentName);
        System.out.println("Marks: " + marks);
        
        String grade;
        String performance;
        
        
        // Nested if-else for grade calculation
        if (marks >= 90) {
            grade = "A+";
            performance = "Outstanding";
            
        } else if (marks >= 80) {
            grade = "A";
            performance = "Excellent";
            
        } else if (marks >= 70) {
            grade = "B+";
            performance = "Very Good";
            
        } else if (marks >= 60) {
            grade = "B";
            performance = "Good";
            
        } else if (marks >= 50) {
            grade = "C";
            performance = "Average";
            
        } else if (marks >= 40) {
            grade = "D";
            performance = "Below Average";
            
        } else {
            grade = "F";
            performance = "Fail";
        }
        
        
        System.out.println("Grade: " + grade);
        System.out.println("Performance: " + performance);
        
        
        // Additional branching for honors
        if (marks >= 75) {
            System.out.println("Eligible for Honors!");
        }
        
        
        // Nested if for scholarship
        if (marks >= 85) {
            if (marks >= 95) {
                System.out.println("Scholarship: Full scholarship (100%)");
            } else {
                System.out.println("Scholarship: Partial scholarship (50%)");
            }
        }
        
        System.out.println();
    }
}


// ============================================
// EXAMPLE 2: Switch Statement and Loops
// ============================================

class MenuSystem {
    
    // Method demonstrating switch statement
    public void processMenuChoice(int choice) {
        System.out.println("Menu Choice: " + choice);
        
        
        // Switch statement for menu selection
        switch (choice) {
            case 1:
                System.out.println("Action: View Profile");
                System.out.println("Displaying user profile...");
                break;
                
            case 2:
                System.out.println("Action: Edit Profile");
                System.out.println("Opening profile editor...");
                break;
                
            case 3:
                System.out.println("Action: Settings");
                System.out.println("Opening settings panel...");
                break;
                
            case 4:
                System.out.println("Action: Logout");
                System.out.println("Logging out...");
                break;
                
            default:
                System.out.println("Invalid choice! Please select 1-4");
        }
        
        System.out.println();
    }
    
    
    // Method demonstrating different loop types
    public void demonstrateLoops() {
        System.out.println("LOOP DEMONSTRATIONS:");
        System.out.println();
        
        
        // FOR LOOP - when you know number of iterations
        System.out.println("1. FOR LOOP (printing 1 to 5):");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        
        
        // WHILE LOOP - when condition is checked before execution
        System.out.println("2. WHILE LOOP (countdown from 5):");
        int countdown = 5;
        while (countdown > 0) {
            System.out.print(countdown + " ");
            countdown--;
        }
        System.out.println("\n");
        
        
        // DO-WHILE LOOP - executes at least once
        System.out.println("3. DO-WHILE LOOP (executes at least once):");
        int number = 10;
        do {
            System.out.println("Number is: " + number);
            number--;
        } while (number > 10);  // Condition false, but executes once
        System.out.println();
        
        
        // ENHANCED FOR LOOP (for-each)
        System.out.println("4. ENHANCED FOR LOOP (iterating array):");
        String[] fruits = {"Apple", "Banana", "Orange", "Mango"};
        for (String fruit : fruits) {
            System.out.println("Fruit: " + fruit);
        }
        System.out.println();
    }
}


// ============================================
// EXAMPLE 3: Jump Statements (break, continue, return)
// ============================================

class JumpStatements {
    
    // Method demonstrating BREAK statement
    public void demonstrateBreak() {
        System.out.println("BREAK STATEMENT:");
        System.out.println("Finding first number divisible by 7:");
        
        for (int i = 1; i <= 50; i++) {
            if (i % 7 == 0) {
                System.out.println("Found: " + i);
                break;  // Exit loop immediately
            }
        }
        System.out.println();
    }
    
    
    // Method demonstrating CONTINUE statement
    public void demonstrateContinue() {
        System.out.println("CONTINUE STATEMENT:");
        System.out.println("Printing only even numbers from 1 to 10:");
        
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                continue;  // Skip odd numbers
            }
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }
    
    
    // Method demonstrating RETURN statement
    public int findMaximum(int a, int b, int c) {
        System.out.println("RETURN STATEMENT:");
        System.out.println("Finding maximum of: " + a + ", " + b + ", " + c);
        
        int max = a;
        
        if (b > max) {
            max = b;
        }
        
        if (c > max) {
            max = c;
        }
        
        return max;  // Return and exit method
    }
    
    
    // Demonstrating nested loops with labeled break
    public void demonstrateLabeledBreak() {
        System.out.println("LABELED BREAK (breaking outer loop):");
        
        outerLoop:  // Label for outer loop
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.println("i = " + i + ", j = " + j);
                
                if (i == 2 && j == 2) {
                    System.out.println("Breaking outer loop!");
                    break outerLoop;  // Break outer loop
                }
            }
        }
        System.out.println();
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit1_ControlFlow {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 1: CONTROL FLOW AND BRANCHING - COMPREHENSIVE EXAMPLES");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: SELECTION STATEMENTS ==========
        System.out.println("EXAMPLE 1: SELECTION STATEMENTS (if-else, nested if)");
        System.out.println("-".repeat(70));
        
        GradeCalculator student1 = new GradeCalculator("Alice", 92);
        student1.calculateGrade();
        
        GradeCalculator student2 = new GradeCalculator("Bob", 67);
        student2.calculateGrade();
        
        GradeCalculator student3 = new GradeCalculator("Charlie", 38);
        student3.calculateGrade();
        
        System.out.println();
        
        
        // ========== EXAMPLE 2: SWITCH AND LOOPS ==========
        System.out.println("EXAMPLE 2: SWITCH STATEMENT AND LOOPS");
        System.out.println("-".repeat(70));
        
        MenuSystem menu = new MenuSystem();
        
        System.out.println("Testing Switch Statement:");
        menu.processMenuChoice(1);
        menu.processMenuChoice(3);
        menu.processMenuChoice(5);  // Invalid choice
        
        System.out.println();
        menu.demonstrateLoops();
        
        System.out.println();
        
        
        // ========== EXAMPLE 3: JUMP STATEMENTS ==========
        System.out.println("EXAMPLE 3: JUMP STATEMENTS (break, continue, return)");
        System.out.println("-".repeat(70));
        
        JumpStatements jumps = new JumpStatements();
        
        jumps.demonstrateBreak();
        jumps.demonstrateContinue();
        
        int maximum = jumps.findMaximum(45, 78, 56);
        System.out.println("Maximum value: " + maximum);
        System.out.println();
        
        jumps.demonstrateLabeledBreak();
        
        
        // ========== EDGE CASES AND SPECIAL SCENARIOS ==========
        System.out.println("=".repeat(70));
        System.out.println("EDGE CASES AND SPECIAL SCENARIOS:");
        System.out.println("=".repeat(70));
        
        // Infinite loop prevention
        System.out.println("1. Loop with break condition:");
        int counter = 0;
        while (true) {
            counter++;
            System.out.println("Iteration: " + counter);
            if (counter >= 3) {
                System.out.println("Breaking infinite loop!");
                break;
            }
        }
        System.out.println();
        
        
        // Nested ternary operator (compact branching)
        System.out.println("2. Ternary Operator (compact if-else):");
        int score = 85;
        String result = (score >= 90) ? "Excellent" : 
                       (score >= 75) ? "Good" : 
                       (score >= 50) ? "Average" : "Poor";
        System.out.println("Score: " + score + " -> Result: " + result);
        System.out.println();
        
        
        System.out.println("=".repeat(70));
        System.out.println("CONTROL FLOW CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("1. Sequential execution (default flow)");
        System.out.println("2. Selection (if-else, switch)");
        System.out.println("3. Iteration (for, while, do-while)");
        System.out.println("4. Jump statements (break, continue, return)");
        System.out.println("5. Labeled breaks for nested loops");
        System.out.println("6. Enhanced for-each loops");
        System.out.println("=".repeat(70));
    }
}

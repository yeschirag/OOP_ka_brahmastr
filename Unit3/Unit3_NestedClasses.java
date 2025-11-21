/*
 * UNIT 3: NESTED CLASSES AND INNER CLASSES
 * 
 * THEORY:
 * Nested Classes: Classes defined inside another class
 * 
 * Types of Nested Classes:
 * 1. STATIC NESTED CLASS - Defined with 'static' keyword
 *    - Can access only static members of outer class
 *    - Can be instantiated without outer class instance
 * 
 * 2. INNER CLASS (Non-static nested class)
 *    - Can access all members (static and non-static) of outer class
 *    - Requires outer class instance to be created
 * 
 * 3. LOCAL INNER CLASS - Defined inside a method
 *    - Scope limited to the method
 *    - Can access final/effectively final local variables
 * 
 * 4. ANONYMOUS INNER CLASS - Class without a name
 *    - Defined and instantiated in single expression
 *    - Used for interfaces/abstract classes implementation
 */


// ============================================
// EXAMPLE 1: Static Nested Class and Regular Inner Class
// ============================================

class University {
    // Static members
    private static String universityName = "Tech University";
    private static int totalStudents = 0;
    
    // Instance members
    private String location;
    private int foundedYear;
    
    
    public University(String location, int foundedYear) {
        this.location = location;
        this.foundedYear = foundedYear;
        System.out.println("University created: " + universityName + " at " + location);
    }
    
    
    // ===== STATIC NESTED CLASS =====
    // Can access only static members of outer class
    static class Department {
        private String name;
        private int facultyCount;
        
        
        public Department(String name, int facultyCount) {
            this.name = name;
            this.facultyCount = facultyCount;
            System.out.println("Department created: " + name);
        }
        
        
        public void displayInfo() {
            System.out.println("\n=== Department Info ===");
            System.out.println("Department: " + name);
            System.out.println("Faculty Count: " + facultyCount);
            
            // Can access static members of outer class
            System.out.println("University: " + universityName);
            System.out.println("Total Students: " + totalStudents);
            
            // Cannot access instance members
            // System.out.println("Location: " + location);  // ERROR!
            
            System.out.println("======================\n");
        }
        
        
        // Static method in static nested class
        public static void displayUniversityName() {
            System.out.println("University: " + universityName);
        }
    }
    
    
    // ===== NON-STATIC INNER CLASS =====
    // Can access both static and non-static members of outer class
    class Student {
        private String name;
        private int rollNumber;
        private String major;
        
        
        public Student(String name, int rollNumber, String major) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.major = major;
            totalStudents++;  // Accessing static member
            System.out.println("Student enrolled: " + name);
        }
        
        
        public void displayInfo() {
            System.out.println("\n=== Student Info ===");
            System.out.println("Name: " + name);
            System.out.println("Roll Number: " + rollNumber);
            System.out.println("Major: " + major);
            
            // Can access both static and non-static members of outer class
            System.out.println("University: " + universityName);      // Static
            System.out.println("Location: " + location);              // Non-static
            System.out.println("Founded: " + foundedYear);            // Non-static
            System.out.println("Total Students: " + totalStudents);   // Static
            
            System.out.println("====================\n");
        }
        
        
        // Inner class method accessing outer class method
        public void showUniversityInfo() {
            System.out.println("Accessing outer class from inner class:");
            University.this.displayBasicInfo();  // Explicit outer class reference
        }
    }
    
    
    public void displayBasicInfo() {
        System.out.println("University: " + universityName);
        System.out.println("Location: " + location);
        System.out.println("Founded: " + foundedYear);
    }
}


// ============================================
// EXAMPLE 2: Local Inner Class and Anonymous Inner Class
// ============================================

// Interface for anonymous class demonstration
interface Greeting {
    void greet(String name);
    void farewell(String name);
}


// Abstract class for anonymous class demonstration
abstract class Animal {
    abstract void makeSound();
    abstract void move();
    
    void breathe() {
        System.out.println("Breathing...");
    }
}


class LocalInnerClassDemo {
    private String className = "LocalInnerClassDemo";
    
    
    // Method demonstrating LOCAL INNER CLASS
    public void demonstrateLocalInnerClass(final String message) {
        final int localVariable = 100;  // Must be final or effectively final
        
        System.out.println("\n=== LOCAL INNER CLASS DEMO ===");
        
        // LOCAL INNER CLASS defined inside method
        class LocalProcessor {
            private String processorName;
            
            
            LocalProcessor(String name) {
                this.processorName = name;
            }
            
            
            void process() {
                System.out.println("Processor: " + processorName);
                
                // Can access final/effectively final local variables
                System.out.println("Message: " + message);
                System.out.println("Local Variable: " + localVariable);
                
                // Can access outer class members
                System.out.println("Outer Class: " + className);
            }
        }
        
        // Creating instance of local inner class
        LocalProcessor processor = new LocalProcessor("DataProcessor");
        processor.process();
        
        System.out.println("==============================\n");
    }
    
    
    // Method demonstrating ANONYMOUS INNER CLASS with Interface
    public void demonstrateAnonymousClass() {
        System.out.println("\n=== ANONYMOUS INNER CLASS DEMO ===");
        
        // Anonymous class implementing Greeting interface
        Greeting englishGreeting = new Greeting() {
            @Override
            public void greet(String name) {
                System.out.println("Hello, " + name + "!");
            }
            
            @Override
            public void farewell(String name) {
                System.out.println("Goodbye, " + name + "!");
            }
        };
        
        // Using anonymous class
        System.out.println("\nEnglish Greeting:");
        englishGreeting.greet("Alice");
        englishGreeting.farewell("Alice");
        
        
        // Another anonymous class with different implementation
        Greeting spanishGreeting = new Greeting() {
            @Override
            public void greet(String name) {
                System.out.println("¡Hola, " + name + "!");
            }
            
            @Override
            public void farewell(String name) {
                System.out.println("¡Adiós, " + name + "!");
            }
        };
        
        System.out.println("\nSpanish Greeting:");
        spanishGreeting.greet("Bob");
        spanishGreeting.farewell("Bob");
        
        System.out.println("==================================\n");
    }
    
    
    // Method demonstrating ANONYMOUS INNER CLASS with Abstract Class
    public void demonstrateAnonymousAbstractClass() {
        System.out.println("\n=== ANONYMOUS CLASS FROM ABSTRACT CLASS ===");
        
        // Anonymous class extending Animal abstract class
        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Woof! Woof!");
            }
            
            @Override
            void move() {
                System.out.println("Running on four legs");
            }
        };
        
        System.out.println("\nDog:");
        dog.makeSound();
        dog.move();
        dog.breathe();  // Inherited method
        
        
        // Another anonymous class
        Animal bird = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Tweet! Tweet!");
            }
            
            @Override
            void move() {
                System.out.println("Flying in the sky");
            }
        };
        
        System.out.println("\nBird:");
        bird.makeSound();
        bird.move();
        bird.breathe();
        
        System.out.println("===========================================\n");
    }
}


// ============================================
// EXAMPLE 3: Complex Nested Classes (Multiple Levels)
// ============================================

class Company {
    private static String companyName = "TechCorp";
    private String ceo;
    
    
    public Company(String ceo) {
        this.ceo = ceo;
        System.out.println("Company created: " + companyName);
        System.out.println("CEO: " + ceo);
    }
    
    
    // First level: Inner class
    class Department {
        private String deptName;
        
        
        public Department(String deptName) {
            this.deptName = deptName;
            System.out.println("Department created: " + deptName);
        }
        
        
        // Second level: Inner class within inner class
        class Team {
            private String teamName;
            private int memberCount;
            
            
            public Team(String teamName, int memberCount) {
                this.teamName = teamName;
                this.memberCount = memberCount;
                System.out.println("Team created: " + teamName);
            }
            
            
            public void displayTeamInfo() {
                System.out.println("\n=== Team Information ===");
                System.out.println("Company: " + companyName);        // Static outer class
                System.out.println("CEO: " + ceo);                    // Outer class instance
                System.out.println("Department: " + deptName);        // Immediate outer class
                System.out.println("Team: " + teamName);              // Current class
                System.out.println("Members: " + memberCount);
                System.out.println("========================\n");
            }
            
            
            // Third level: Local inner class within nested inner class
            public void processTeamData() {
                final String processType = "Data Analysis";
                
                class DataProcessor {
                    void process() {
                        System.out.println("Processing " + processType);
                        System.out.println("For team: " + teamName);
                        System.out.println("In department: " + deptName);
                        System.out.println("Of company: " + companyName);
                    }
                }
                
                DataProcessor processor = new DataProcessor();
                processor.process();
            }
        }
        
        
        public void displayDeptInfo() {
            System.out.println("\nDepartment: " + deptName);
            System.out.println("Company: " + companyName);
            System.out.println("CEO: " + ceo);
        }
    }
    
    
    // Static nested class with inner class
    static class HRPolicy {
        private String policyName;
        
        
        public HRPolicy(String policyName) {
            this.policyName = policyName;
        }
        
        
        // Inner class within static nested class
        class PolicyRule {
            private String ruleName;
            
            
            public PolicyRule(String ruleName) {
                this.ruleName = ruleName;
            }
            
            
            public void display() {
                System.out.println("\nPolicy: " + policyName);
                System.out.println("Rule: " + ruleName);
                System.out.println("Company: " + companyName);  // Can access static member
            }
        }
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit3_NestedClasses {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 3: NESTED CLASSES AND INNER CLASSES - COMPREHENSIVE EXAMPLES");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: STATIC NESTED AND REGULAR INNER CLASS ==========
        System.out.println("EXAMPLE 1: Static Nested Class vs Regular Inner Class");
        System.out.println("-".repeat(70));
        
        // Creating STATIC NESTED CLASS - doesn't need outer class instance
        System.out.println("\n1. Creating Static Nested Class (Department):");
        University.Department dept1 = new University.Department("Computer Science", 25);
        University.Department dept2 = new University.Department("Mathematics", 18);
        
        dept1.displayInfo();
        dept2.displayInfo();
        
        // Static method in static nested class
        System.out.println("Calling static method in static nested class:");
        University.Department.displayUniversityName();
        System.out.println();
        
        
        // Creating outer class instance for inner class
        System.out.println("\n2. Creating Regular Inner Class (Student):");
        System.out.println("First, create outer class instance:");
        University university = new University("California", 1995);
        System.out.println();
        
        // Creating INNER CLASS - needs outer class instance
        System.out.println("Now creating inner class instances:");
        University.Student student1 = university.new Student("Alice Johnson", 101, "CS");
        University.Student student2 = university.new Student("Bob Smith", 102, "Math");
        
        student1.displayInfo();
        student2.displayInfo();
        
        System.out.println("Inner class accessing outer class method:");
        student1.showUniversityInfo();
        System.out.println();
        
        
        // ========== EXAMPLE 2: LOCAL AND ANONYMOUS INNER CLASSES ==========
        System.out.println("\nEXAMPLE 2: Local Inner Class and Anonymous Inner Class");
        System.out.println("-".repeat(70));
        
        LocalInnerClassDemo demo = new LocalInnerClassDemo();
        
        // Local inner class
        demo.demonstrateLocalInnerClass("Processing data from method");
        
        // Anonymous inner class with interface
        demo.demonstrateAnonymousClass();
        
        // Anonymous inner class with abstract class
        demo.demonstrateAnonymousAbstractClass();
        
        
        // ========== EXAMPLE 3: COMPLEX NESTED CLASSES ==========
        System.out.println("\nEXAMPLE 3: Complex Multi-Level Nested Classes");
        System.out.println("-".repeat(70));
        
        System.out.println("\nCreating nested structure:");
        Company company = new Company("John Doe");
        System.out.println();
        
        // Creating first level inner class
        Company.Department engineering = company.new Department("Engineering");
        System.out.println();
        
        // Creating second level inner class
        Company.Department.Team devTeam = engineering.new Team("Development Team", 12);
        System.out.println();
        
        // Displaying information from deeply nested class
        devTeam.displayTeamInfo();
        
        // Local inner class within nested structure
        System.out.println("Demonstrating local inner class in nested structure:");
        devTeam.processTeamData();
        System.out.println();
        
        
        // Creating static nested class with inner class
        System.out.println("\nStatic nested class with inner class:");
        Company.HRPolicy policy = new Company.HRPolicy("Leave Policy");
        Company.HRPolicy.PolicyRule rule = policy.new PolicyRule("Maximum 20 days/year");
        rule.display();
        System.out.println();
        
        
        // ========== EDGE CASES ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EDGE CASES AND SPECIAL SCENARIOS:");
        System.out.println("=".repeat(70));
        
        // Edge case 1: Multiple instances of inner classes
        System.out.println("\n1. Multiple inner class instances from different outer instances:");
        University uni1 = new University("New York", 2000);
        University uni2 = new University("Texas", 2005);
        
        University.Student s1 = uni1.new Student("Charlie", 201, "Physics");
        University.Student s2 = uni2.new Student("Diana", 202, "Chemistry");
        
        System.out.println("Both students can access their respective outer class data:");
        s1.displayInfo();
        s2.displayInfo();
        
        
        // Edge case 2: Anonymous class with additional methods
        System.out.println("\n2. Anonymous class with additional methods:");
        Greeting customGreeting = new Greeting() {
            // Additional field in anonymous class
            private int greetingCount = 0;
            
            @Override
            public void greet(String name) {
                greetingCount++;
                System.out.println("Greetings #" + greetingCount + ": Hello, " + name + "!");
            }
            
            @Override
            public void farewell(String name) {
                System.out.println("Farewell, " + name + "! Total greetings: " + greetingCount);
            }
        };
        
        customGreeting.greet("Eve");
        customGreeting.greet("Frank");
        customGreeting.farewell("All");
        System.out.println();
        
        
        // ========== SUMMARY ==========
        System.out.println("=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("\n1. STATIC NESTED CLASS:");
        System.out.println("   - Created without outer class instance");
        System.out.println("   - Can access only static members of outer class");
        System.out.println("   - Syntax: OuterClass.StaticNestedClass obj = new OuterClass.StaticNestedClass()");
        System.out.println("\n2. INNER CLASS (Non-static nested class):");
        System.out.println("   - Requires outer class instance");
        System.out.println("   - Can access all members (static and non-static) of outer class");
        System.out.println("   - Syntax: OuterClass.InnerClass obj = outerObj.new InnerClass()");
        System.out.println("\n3. LOCAL INNER CLASS:");
        System.out.println("   - Defined inside a method");
        System.out.println("   - Scope limited to the method");
        System.out.println("   - Can access final/effectively final local variables");
        System.out.println("\n4. ANONYMOUS INNER CLASS:");
        System.out.println("   - No name, defined and instantiated together");
        System.out.println("   - Used for one-time implementations of interfaces/abstract classes");
        System.out.println("   - Can have additional members and methods");
        System.out.println("\n5. MULTI-LEVEL NESTING:");
        System.out.println("   - Inner classes can contain other inner classes");
        System.out.println("   - Each level can access outer levels' members");
        System.out.println("=".repeat(70));
    }
}

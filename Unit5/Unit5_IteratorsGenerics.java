/*
 * UNIT 5: ITERATORS AND GENERICS
 * 
 * Dear Chirag and fellow vibe coders,
 * Welcome to Generics - the place where AI is learning to replace us one <T> at a time.
 * But hey, at least we still write the bugs that AI has to fix! 🤖
 * 
 * Pro tip: If you can understand wildcards, you're already smarter than 90% of developers.
 * If you can't... well, AI thanks you for your future unemployment contribution.
 * 
 * THEORY:
 * 
 * ITERATOR:
 * - Interface to traverse collections
 * - Methods: hasNext(), next(), remove()
 * - Fail-fast: Throws ConcurrentModificationException if collection modified during iteration
 * 
 * LISTITERATOR:
 * - Bidirectional iterator for Lists
 * - Additional methods: hasPrevious(), previous(), add(), set()
 * 
 * GENERICS:
 * - Type parameters for type-safe collections
 * - Compile-time type checking
 * - Eliminates need for type casting
 * - Syntax: <T>, <E>, <K, V>
 * 
 * BOUNDED TYPE PARAMETERS:
 * - Upper bound: <T extends Number>
 * - Multiple bounds: <T extends Class1 & Interface1 & Interface2>
 * 
 * WILDCARDS:
 * - Unbounded: <?>
 * - Upper bounded: <? extends Type>
 * - Lower bounded: <? super Type>
 */


import java.util.*;


// ============================================
// EXAMPLE 1: ITERATORS - Iterator, ListIterator, Enhanced For Loop
// ============================================

class IteratorExample {
    
    // Method 1: Basic Iterator
    public void basicIterator() {
        System.out.println("\n=== Basic Iterator ===");
        System.out.println("Methods: hasNext(), next(), remove()");
        System.out.println();
        
        List<String> languages = new ArrayList<>(Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby"));
        
        System.out.println("Original list: " + languages);
        
        // Using Iterator
        System.out.println("\nIterating with Iterator:");
        Iterator<String> iterator = languages.iterator();
        
        while (iterator.hasNext()) {
            String lang = iterator.next();
            System.out.println("  - " + lang);
            
            // Remove elements containing 'a'
            if (lang.contains("a")) {
                iterator.remove();  // Safe removal during iteration
                System.out.println("    (Removed: contains 'a')");
            }
        }
        
        System.out.println("\nAfter iterator removal: " + languages);
        System.out.println();
    }
    
    
    // Method 2: ListIterator (bidirectional)
    public void listIterator() {
        System.out.println("\n=== ListIterator (Bidirectional) ===");
        System.out.println("Additional methods: hasPrevious(), previous(), add(), set(), nextIndex(), previousIndex()");
        System.out.println();
        
        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        
        System.out.println("Original list: " + numbers);
        
        // Forward iteration
        System.out.println("\n1. Forward iteration:");
        ListIterator<Integer> listIter = numbers.listIterator();
        
        while (listIter.hasNext()) {
            int index = listIter.nextIndex();
            int value = listIter.next();
            System.out.println("  Index " + index + ": " + value);
            
            // Modify elements (multiply by 2)
            if (value == 30) {
                listIter.set(value * 2);
                System.out.println("    (Modified 30 to 60)");
            }
        }
        
        System.out.println("After modification: " + numbers);
        
        // Backward iteration
        System.out.println("\n2. Backward iteration:");
        while (listIter.hasPrevious()) {
            int index = listIter.previousIndex();
            int value = listIter.previous();
            System.out.println("  Index " + index + ": " + value);
        }
        
        // Adding elements during iteration
        System.out.println("\n3. Adding elements:");
        listIter = numbers.listIterator();
        while (listIter.hasNext()) {
            int value = listIter.next();
            if (value == 20) {
                listIter.add(25);  // Add after current element
                System.out.println("  Added 25 after 20");
            }
        }
        
        System.out.println("After adding: " + numbers);
        System.out.println();
    }
    
    
    // Method 3: Different iteration methods
    public void iterationMethods() {
        System.out.println("\n=== Different Iteration Methods ===");
        
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Date");
        
        // Method 1: Traditional for loop
        System.out.println("\n1. Traditional for loop:");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println("  [" + i + "] " + fruits.get(i));
        }
        
        // Method 2: Enhanced for loop (for-each)
        System.out.println("\n2. Enhanced for loop:");
        for (String fruit : fruits) {
            System.out.println("  - " + fruit);
        }
        
        // Method 3: Iterator
        System.out.println("\n3. Iterator:");
        Iterator<String> iter = fruits.iterator();
        while (iter.hasNext()) {
            System.out.println("  - " + iter.next());
        }
        
        // Method 4: forEach with lambda (Java 8+)
        System.out.println("\n4. forEach with lambda:");
        fruits.forEach(fruit -> System.out.println("  - " + fruit));
        
        // Method 5: Stream API (Java 8+)
        System.out.println("\n5. Stream API:");
        fruits.stream()
              .map(String::toUpperCase)
              .forEach(fruit -> System.out.println("  - " + fruit));
        System.out.println();
    }
    
    
    // Method 4: Fail-fast behavior
    public void failFastBehavior() {
        System.out.println("\n=== Fail-Fast Iterator ===");
        System.out.println("ConcurrentModificationException if collection modified during iteration");
        System.out.println();
        
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        
        System.out.println("Original list: " + numbers);
        
        // This will throw ConcurrentModificationException
        System.out.println("\nAttempting to modify collection during iteration:");
        try {
            for (Integer num : numbers) {
                System.out.println("  Processing: " + num);
                if (num == 3) {
                    numbers.remove(num);  // Modifying collection
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("  ERROR: " + e.getClass().getSimpleName());
            System.out.println("  Message: Collection modified during iteration");
        }
        
        // Correct way: Use Iterator.remove()
        System.out.println("\nCorrect way (using Iterator.remove()):");
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            System.out.println("  Processing: " + num);
            if (num == 3) {
                iterator.remove();  // Safe removal
                System.out.println("  Removed: " + num);
            }
        }
        
        System.out.println("Final list: " + numbers);
        System.out.println();
    }
}


// ============================================
// EXAMPLE 2: GENERICS - Classes, Methods, Interfaces
// ============================================

// Generic class with single type parameter
class Box<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
    
    public void display() {
        System.out.println("Box contains: " + content + " (Type: " + content.getClass().getSimpleName() + ")");
    }
}


// Generic class with multiple type parameters
class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
    
    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}


// Generic interface (renamed to avoid conflict with java.lang.Comparable)
interface Rankable<T> {
    int compareTo(T other);
}


// Implementing generic interface
class Student implements Rankable<Student> {
    private String name;
    private int marks;
    
    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
    
    @Override
    public int compareTo(Student other) {
        return this.marks - other.marks;
    }
    
    @Override
    public String toString() {
        return name + "(" + marks + ")";
    }
}


class GenericsExample {
    
    // Method 1: Generic classes
    public void genericClasses() {
        System.out.println("\n=== Generic Classes ===");
        System.out.println("Type-safe containers without casting");
        System.out.println();
        
        // Box with Integer
        Box<Integer> intBox = new Box<>();
        intBox.set(123);
        intBox.display();
        Integer intValue = intBox.get();  // No casting needed
        System.out.println("Retrieved: " + intValue);
        
        // Box with String
        System.out.println();
        Box<String> strBox = new Box<>();
        strBox.set("Hello Generics");
        strBox.display();
        String strValue = strBox.get();
        System.out.println("Retrieved: " + strValue);
        
        // Pair with multiple type parameters
        System.out.println();
        Pair<String, Integer> pair1 = new Pair<>("Age", 25);
        Pair<Integer, String> pair2 = new Pair<>(101, "Alice");
        
        System.out.println("Pair 1: " + pair1);
        System.out.println("Pair 2: " + pair2);
        System.out.println();
    }
    
    
    // Method 2: Generic methods
    public void genericMethods() {
        System.out.println("\n=== Generic Methods ===");
        System.out.println("Methods with type parameters");
        System.out.println();
        
        // Print array of any type
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] strArray = {"Java", "Python", "C++"};
        Double[] doubleArray = {1.1, 2.2, 3.3};
        
        System.out.println("Integer array:");
        printArray(intArray);
        
        System.out.println("\nString array:");
        printArray(strArray);
        
        System.out.println("\nDouble array:");
        printArray(doubleArray);
        
        // Find maximum
        System.out.println("\nFinding maximum:");
        System.out.println("Max of 10, 20, 30: " + findMax(Integer.valueOf(10), Integer.valueOf(20), Integer.valueOf(30)));
        System.out.println("Max of 'apple', 'banana', 'cherry': " + findMax("apple", "banana", "cherry"));
        System.out.println();
    }
    
    
    // Generic method to print array
    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    
    // Generic method with bounded type parameter
    public <T extends java.lang.Comparable<T>> T findMax(T a, T b, T c) {
        T max = a;
        if (b.compareTo(max) > 0) max = b;
        if (c.compareTo(max) > 0) max = c;
        return max;
    }
    
    
    // Method 3: Bounded type parameters
    public void boundedTypeParameters() {
        System.out.println("\n=== Bounded Type Parameters ===");
        System.out.println("Restricting type parameters to specific types");
        System.out.println();
        
        // Upper bound: <T extends Number>
        System.out.println("Summing numbers (Integer):");
        System.out.println("Sum: " + sumNumbers(10, 20, 30));
        
        System.out.println("\nSumming numbers (Double):");
        System.out.println("Sum: " + sumNumbers(1.5, 2.5, 3.5));
        
        // Multiple bounds
        System.out.println("\nSorting students:");
        List<Student> students = new ArrayList<>(Arrays.asList(
            new Student("Alice", 85),
            new Student("Bob", 92),
            new Student("Carol", 78)
        ));
        
        System.out.println("Before sorting: " + students);
        Collections.sort(students, (s1, s2) -> s1.compareTo(s2));
        System.out.println("After sorting: " + students);
        System.out.println();
    }
    
    
    // Method with upper bounded type parameter
    public <T extends Number> double sumNumbers(T a, T b, T c) {
        return a.doubleValue() + b.doubleValue() + c.doubleValue();
    }
    
    
    // Method with multiple bounds
    public <T extends java.lang.Comparable<T>> void sortList(List<T> list) {
        Collections.sort(list);
    }
}


// ============================================
// EXAMPLE 3: WILDCARDS - Unbounded, Upper Bounded, Lower Bounded
// ============================================

class WildcardsExample {
    
    // Method 1: Unbounded wildcard <?>
    public void unboundedWildcard() {
        System.out.println("\n=== Unbounded Wildcard <?> ===");
        System.out.println("Accepts any type");
        System.out.println();
        
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("A", "B", "C");
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("Integer list:");
        printList(intList);
        
        System.out.println("\nString list:");
        printList(strList);
        
        System.out.println("\nDouble list:");
        printList(doubleList);
        System.out.println();
    }
    
    
    // Method accepting any type of list
    public void printList(List<?> list) {
        for (Object element : list) {
            System.out.println("  - " + element);
        }
    }
    
    
    // Method 2: Upper bounded wildcard <? extends Type>
    public void upperBoundedWildcard() {
        System.out.println("\n=== Upper Bounded Wildcard <? extends Type> ===");
        System.out.println("Accepts Type or its subtypes (read-only)");
        System.out.println();
        
        List<Integer> intList = Arrays.asList(10, 20, 30, 40);
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);
        
        System.out.println("Sum of integers: " + sumNumbers(intList));
        System.out.println("Sum of doubles: " + sumNumbers(doubleList));
        
        // Edge case: Cannot add to upper bounded wildcard list
        System.out.println("\nEdge case:");
        System.out.println("Cannot add to <? extends Number> list (read-only)");
        System.out.println("Compile error: list.add(10); // Not allowed");
        System.out.println();
    }
    
    
    // Method accepting Number or its subtypes
    public double sumNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
    
    
    // Method 3: Lower bounded wildcard <? super Type>
    public void lowerBoundedWildcard() {
        System.out.println("\n=== Lower Bounded Wildcard <? super Type> ===");
        System.out.println("Accepts Type or its supertypes (write-only)");
        System.out.println();
        
        List<Number> numberList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        
        System.out.println("Adding integers to Number list:");
        addIntegers(numberList);
        System.out.println("Number list: " + numberList);
        
        System.out.println("\nAdding integers to Object list:");
        addIntegers(objectList);
        System.out.println("Object list: " + objectList);
        
        // Edge case: Can add but cannot read with specific type
        System.out.println("\nEdge case:");
        System.out.println("Can add Integer to <? super Integer> list");
        System.out.println("But can only read as Object (not Integer)");
        System.out.println();
    }
    
    
    // Method accepting Integer or its supertypes
    public void addIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
    }
    
    
    // Method 4: PECS principle (Producer Extends, Consumer Super)
    public void pecsPrinciple() {
        System.out.println("\n=== PECS Principle ===");
        System.out.println("Producer Extends, Consumer Super");
        System.out.println();
        
        // Producer (extends): Reading from source
        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5);
        
        // Consumer (super): Writing to destination
        List<Number> destination = new ArrayList<>();
        
        System.out.println("Source (producer): " + source);
        copyList(source, destination);
        System.out.println("Destination (consumer): " + destination);
        
        System.out.println("\nExplanation:");
        System.out.println("- Source uses <? extends T>: Produces T (read-only)");
        System.out.println("- Destination uses <? super T>: Consumes T (write-only)");
        System.out.println();
    }
    
    
    // PECS example: Copy from producer to consumer
    public <T> void copyList(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }
    
    
    // Method 5: Wildcard comparison
    public void wildcardComparison() {
        System.out.println("\n=== Wildcard Comparison ===");
        System.out.println();
        
        System.out.println("WILDCARD TYPE          | READ  | WRITE | USE CASE");
        System.out.println("-".repeat(60));
        System.out.println("<?>                    | Yes*  | No    | Unknown type");
        System.out.println("<? extends T>          | Yes   | No    | Producer (covariance)");
        System.out.println("<? super T>            | No**  | Yes   | Consumer (contravariance)");
        System.out.println("<T>                    | Yes   | Yes   | Known type");
        System.out.println();
        System.out.println("* Read as Object only");
        System.out.println("** Read as Object only");
        System.out.println();
    }
}


// ============================================
// MAIN CLASS
// ============================================

public class Unit5_IteratorsGenerics {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 5: ITERATORS AND GENERICS");
        System.out.println("=".repeat(70));
        
        
        // ========== EXAMPLE 1: ITERATORS ==========
        System.out.println("\nEXAMPLE 1: Iterators");
        System.out.println("-".repeat(70));
        
        IteratorExample iterExample = new IteratorExample();
        iterExample.basicIterator();
        iterExample.listIterator();
        iterExample.iterationMethods();
        iterExample.failFastBehavior();
        
        
        // ========== EXAMPLE 2: GENERICS ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 2: Generics");
        System.out.println("-".repeat(70));
        
        GenericsExample genExample = new GenericsExample();
        genExample.genericClasses();
        genExample.genericMethods();
        genExample.boundedTypeParameters();
        
        
        // ========== EXAMPLE 3: WILDCARDS ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 3: Wildcards");
        System.out.println("-".repeat(70));
        
        WildcardsExample wildExample = new WildcardsExample();
        wildExample.unboundedWildcard();
        wildExample.upperBoundedWildcard();
        wildExample.lowerBoundedWildcard();
        wildExample.pecsPrinciple();
        wildExample.wildcardComparison();
        
        
        // ========== SUMMARY ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("\nITERATORS:");
        System.out.println("  Iterator        : Forward traversal, remove()");
        System.out.println("  ListIterator    : Bidirectional, add(), set()");
        System.out.println("  Enhanced for    : Simple syntax, no remove");
        System.out.println("  forEach lambda  : Functional style (Java 8+)");
        System.out.println("  Fail-fast       : ConcurrentModificationException");
        System.out.println("\nGENERICS:");
        System.out.println("  Type parameters : <T>, <E>, <K,V>");
        System.out.println("  Generic class   : class Box<T>");
        System.out.println("  Generic method  : <T> void method(T param)");
        System.out.println("  Generic interface: interface List<E>");
        System.out.println("  Benefits        : Type safety, no casting, compile-time checks");
        System.out.println("\nBOUNDED TYPES:");
        System.out.println("  Upper bound     : <T extends Number>");
        System.out.println("  Multiple bounds : <T extends Class & Interface>");
        System.out.println("\nWILDCARDS:");
        System.out.println("  Unbounded       : <?> - Any type");
        System.out.println("  Upper bounded   : <? extends T> - T or subtypes (read)");
        System.out.println("  Lower bounded   : <? super T> - T or supertypes (write)");
        System.out.println("  PECS            : Producer Extends, Consumer Super");
        System.out.println("=".repeat(70));
    }
}

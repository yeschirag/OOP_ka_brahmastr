/*
 * UNIT 5: COLLECTIONS FRAMEWORK
 * 
 * What's up Chirag! Collections are like your browser tabs - lots of different types, 
 * all doing something, and you're not entirely sure which one you need right now. 🗂️
 * 
 * ArrayList vs LinkedList debate is the programmer equivalent of 'tabs vs spaces'.
 * Choose wisely, or don't. AI will judge you either way. 🤖
 * 
 * THEORY:
 * 
 * COLLECTION FRAMEWORK: A unified architecture for representing and manipulating collections
 * 
 * Hierarchy:
 * Collection (interface)
 *   ├── List (interface) - Ordered, allows duplicates
 *   │     ├── ArrayList - Resizable array
 *   │     ├── LinkedList - Doubly-linked list
 *   │     └── Vector - Synchronized ArrayList
 *   │
 *   ├── Set (interface) - No duplicates
 *   │     ├── HashSet - Unordered, fast
 *   │     ├── LinkedHashSet - Insertion order
 *   │     └── TreeSet - Sorted order
 *   │
 *   └── Queue (interface) - FIFO operations
 *         ├── PriorityQueue - Priority-based
 *         └── Deque (interface) - Double-ended
 *               └── ArrayDeque - Resizable array deque
 * 
 * Map (interface) - Key-Value pairs (NOT a Collection)
 *   ├── HashMap - Unordered, fast
 *   ├── LinkedHashMap - Insertion order
 *   └── TreeMap - Sorted by keys
 */


import java.util.*;


// ============================================
// EXAMPLE 1: LIST INTERFACE - ArrayList, LinkedList, Vector
// ============================================

class ListExample {
    
    // Method 1: ArrayList operations
    public void arrayListOperations() {
        System.out.println("\n=== ArrayList Operations ===");
        System.out.println("Implementation: Resizable array");
        System.out.println("Best for: Random access, frequent reads");
        System.out.println();
        
        // Creating ArrayList
        ArrayList<String> fruits = new ArrayList<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Apple");  // Duplicates allowed
        System.out.println("After adding: " + fruits);
        System.out.println("Size: " + fruits.size());
        
        // Adding at specific index
        fruits.add(1, "Mango");
        System.out.println("After inserting Mango at index 1: " + fruits);
        
        // Accessing elements
        System.out.println("\nAccessing elements:");
        System.out.println("Element at index 0: " + fruits.get(0));
        System.out.println("First element: " + fruits.get(0));
        System.out.println("Last element: " + fruits.get(fruits.size() - 1));
        
        // Searching
        System.out.println("\nSearching:");
        System.out.println("Contains 'Banana': " + fruits.contains("Banana"));
        System.out.println("Index of 'Apple': " + fruits.indexOf("Apple"));
        System.out.println("Last index of 'Apple': " + fruits.lastIndexOf("Apple"));
        
        // Modifying
        fruits.set(2, "Blueberry");
        System.out.println("\nAfter setting index 2 to Blueberry: " + fruits);
        
        // Removing
        fruits.remove("Apple");  // Removes first occurrence
        System.out.println("After removing first 'Apple': " + fruits);
        
        fruits.remove(0);  // Removes by index
        System.out.println("After removing index 0: " + fruits);
        
        // Iterating
        System.out.println("\nIterating through ArrayList:");
        for (String fruit : fruits) {
            System.out.println("  - " + fruit);
        }
        
        // Sublist
        List<String> subList = fruits.subList(0, 2);
        System.out.println("\nSublist (0 to 2): " + subList);
        
        // Clear
        fruits.clear();
        System.out.println("After clear: " + fruits + " (isEmpty: " + fruits.isEmpty() + ")");
        System.out.println();
    }
    
    
    // Method 2: LinkedList operations
    public void linkedListOperations() {
        System.out.println("\n=== LinkedList Operations ===");
        System.out.println("Implementation: Doubly-linked list");
        System.out.println("Best for: Frequent insertions/deletions, queue/deque operations");
        System.out.println();
        
        LinkedList<Integer> numbers = new LinkedList<>();
        
        // Adding elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        System.out.println("After adding: " + numbers);
        
        // Adding at beginning/end
        numbers.addFirst(5);
        numbers.addLast(40);
        System.out.println("After addFirst(5) and addLast(40): " + numbers);
        
        // Accessing first/last
        System.out.println("\nAccessing:");
        System.out.println("First element: " + numbers.getFirst());
        System.out.println("Last element: " + numbers.getLast());
        System.out.println("Element at index 2: " + numbers.get(2));
        
        // Peek operations (doesn't remove)
        System.out.println("\nPeek operations:");
        System.out.println("Peek first: " + numbers.peekFirst());
        System.out.println("Peek last: " + numbers.peekLast());
        System.out.println("List unchanged: " + numbers);
        
        // Poll operations (removes and returns)
        System.out.println("\nPoll operations:");
        System.out.println("Poll first: " + numbers.pollFirst());
        System.out.println("Poll last: " + numbers.pollLast());
        System.out.println("After polling: " + numbers);
        
        // Using as Stack (LIFO)
        System.out.println("\nUsing as Stack:");
        numbers.push(100);  // Adds to beginning
        numbers.push(200);
        System.out.println("After push(100, 200): " + numbers);
        System.out.println("Pop: " + numbers.pop());  // Removes from beginning
        System.out.println("After pop: " + numbers);
        
        // Using as Queue (FIFO)
        System.out.println("\nUsing as Queue:");
        numbers.offer(50);  // Adds to end
        System.out.println("After offer(50): " + numbers);
        System.out.println("Poll: " + numbers.poll());  // Removes from beginning
        System.out.println("After poll: " + numbers);
        System.out.println();
    }
    
    
    // Method 3: Comparing ArrayList vs LinkedList
    public void compareListPerformance() {
        System.out.println("\n=== ArrayList vs LinkedList Performance ===");
        
        int size = 100000;
        
        // ArrayList performance
        ArrayList<Integer> arrayList = new ArrayList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long arrayListAddTime = System.nanoTime() - startTime;
        
        // LinkedList performance
        LinkedList<Integer> linkedList = new LinkedList<>();
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        long linkedListAddTime = System.nanoTime() - startTime;
        
        System.out.println("\nAdding " + size + " elements at end:");
        System.out.println("ArrayList: " + arrayListAddTime / 1000000.0 + " ms");
        System.out.println("LinkedList: " + linkedListAddTime / 1000000.0 + " ms");
        
        // Random access
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.get(size / 2);
        }
        long arrayListAccessTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get(size / 2);
        }
        long linkedListAccessTime = System.nanoTime() - startTime;
        
        System.out.println("\nRandom access (1000 times):");
        System.out.println("ArrayList: " + arrayListAccessTime / 1000000.0 + " ms (FAST)");
        System.out.println("LinkedList: " + linkedListAccessTime / 1000000.0 + " ms (SLOW)");
        
        System.out.println("\nConclusion:");
        System.out.println("- ArrayList: Fast random access, slower insertions in middle");
        System.out.println("- LinkedList: Slow random access, faster insertions/deletions");
        System.out.println();
    }
}


// ============================================
// EXAMPLE 2: SET INTERFACE - HashSet, LinkedHashSet, TreeSet
// ============================================

class SetExample {
    
    // Method 1: HashSet operations
    public void hashSetOperations() {
        System.out.println("\n=== HashSet Operations ===");
        System.out.println("Implementation: Hash table");
        System.out.println("Order: Unordered");
        System.out.println("Null: Allows one null");
        System.out.println("Performance: O(1) for add/remove/contains");
        System.out.println();
        
        HashSet<String> set = new HashSet<>();
        
        // Adding elements
        set.add("Java");
        set.add("Python");
        set.add("C++");
        set.add("Java");  // Duplicate - won't be added
        set.add("JavaScript");
        
        System.out.println("HashSet: " + set);
        System.out.println("Size: " + set.size());
        System.out.println("Note: Unordered and no duplicates");
        
        // Checking existence
        System.out.println("\nContains 'Java': " + set.contains("Java"));
        System.out.println("Contains 'Ruby': " + set.contains("Ruby"));
        
        // Removing
        set.remove("C++");
        System.out.println("\nAfter removing C++: " + set);
        
        // Iterating
        System.out.println("\nIterating:");
        for (String lang : set) {
            System.out.println("  - " + lang);
        }
        
        // Set operations
        HashSet<String> set2 = new HashSet<>(Arrays.asList("Python", "Ruby", "Go"));
        System.out.println("\nSet2: " + set2);
        
        // Union
        HashSet<String> union = new HashSet<>(set);
        union.addAll(set2);
        System.out.println("Union: " + union);
        
        // Intersection
        HashSet<String> intersection = new HashSet<>(set);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        HashSet<String> difference = new HashSet<>(set);
        difference.removeAll(set2);
        System.out.println("Difference (set - set2): " + difference);
        System.out.println();
    }
    
    
    // Method 2: LinkedHashSet operations
    public void linkedHashSetOperations() {
        System.out.println("\n=== LinkedHashSet Operations ===");
        System.out.println("Implementation: Hash table + Linked list");
        System.out.println("Order: Insertion order maintained");
        System.out.println("Performance: Slightly slower than HashSet");
        System.out.println();
        
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        
        // Adding elements
        set.add(50);
        set.add(20);
        set.add(40);
        set.add(10);
        set.add(30);
        set.add(20);  // Duplicate
        
        System.out.println("LinkedHashSet: " + set);
        System.out.println("Note: Maintains insertion order");
        
        // Compare with HashSet
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList(50, 20, 40, 10, 30));
        System.out.println("\nHashSet (same elements): " + hashSet);
        System.out.println("Note: Random order");
        System.out.println();
    }
    
    
    // Method 3: TreeSet operations
    public void treeSetOperations() {
        System.out.println("\n=== TreeSet Operations ===");
        System.out.println("Implementation: Red-Black tree (balanced BST)");
        System.out.println("Order: Sorted (natural ordering or comparator)");
        System.out.println("Null: Does NOT allow null");
        System.out.println("Performance: O(log n) for add/remove/contains");
        System.out.println();
        
        TreeSet<Integer> numbers = new TreeSet<>();
        
        // Adding elements
        numbers.add(50);
        numbers.add(20);
        numbers.add(40);
        numbers.add(10);
        numbers.add(30);
        numbers.add(60);
        
        System.out.println("TreeSet: " + numbers);
        System.out.println("Note: Automatically sorted");
        
        // Navigation methods
        System.out.println("\nNavigation methods:");
        System.out.println("First: " + numbers.first());
        System.out.println("Last: " + numbers.last());
        System.out.println("Lower than 30: " + numbers.lower(30));
        System.out.println("Higher than 30: " + numbers.higher(30));
        System.out.println("Floor of 35: " + numbers.floor(35));
        System.out.println("Ceiling of 35: " + numbers.ceiling(35));
        
        // Subset operations
        System.out.println("\nSubset operations:");
        System.out.println("HeadSet (< 40): " + numbers.headSet(40));
        System.out.println("TailSet (>= 40): " + numbers.tailSet(40));
        System.out.println("SubSet [20, 50): " + numbers.subSet(20, 50));
        
        // Poll operations (removes and returns)
        System.out.println("\nPoll operations:");
        System.out.println("Poll first: " + numbers.pollFirst());
        System.out.println("Poll last: " + numbers.pollLast());
        System.out.println("After polling: " + numbers);
        
        // Custom comparator (descending order)
        System.out.println("\n--- Custom Comparator (Descending) ---");
        TreeSet<Integer> descSet = new TreeSet<>(Collections.reverseOrder());
        descSet.addAll(Arrays.asList(50, 20, 40, 10, 30));
        System.out.println("Descending TreeSet: " + descSet);
        System.out.println();
    }
}


// ============================================
// EXAMPLE 3: MAP INTERFACE - HashMap, LinkedHashMap, TreeMap
// ============================================

class MapExample {
    
    // Method 1: HashMap operations
    public void hashMapOperations() {
        System.out.println("\n=== HashMap Operations ===");
        System.out.println("Implementation: Hash table");
        System.out.println("Order: Unordered");
        System.out.println("Null: Allows one null key, multiple null values");
        System.out.println("Performance: O(1) for get/put");
        System.out.println();
        
        HashMap<String, Integer> scores = new HashMap<>();
        
        // Adding key-value pairs
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Carol", 92);
        scores.put("David", 88);
        scores.put("Alice", 98);  // Overwrites previous value
        
        System.out.println("HashMap: " + scores);
        System.out.println("Size: " + scores.size());
        
        // Accessing values
        System.out.println("\nAccessing:");
        System.out.println("Alice's score: " + scores.get("Alice"));
        System.out.println("Eve's score: " + scores.get("Eve"));  // null
        System.out.println("Bob's score (with default): " + scores.getOrDefault("Bob", 0));
        System.out.println("Eve's score (with default): " + scores.getOrDefault("Eve", 0));
        
        // Checking existence
        System.out.println("\nChecking:");
        System.out.println("Contains key 'Carol': " + scores.containsKey("Carol"));
        System.out.println("Contains value 92: " + scores.containsValue(92));
        
        // Removing
        scores.remove("David");
        System.out.println("\nAfter removing David: " + scores);
        
        // putIfAbsent
        scores.putIfAbsent("Eve", 90);
        scores.putIfAbsent("Alice", 100);  // Won't update (key exists)
        System.out.println("After putIfAbsent: " + scores);
        
        // Iterating
        System.out.println("\n1. Iterating over keys:");
        for (String name : scores.keySet()) {
            System.out.println("  " + name + ": " + scores.get(name));
        }
        
        System.out.println("\n2. Iterating over values:");
        for (Integer score : scores.values()) {
            System.out.println("  Score: " + score);
        }
        
        System.out.println("\n3. Iterating over entries:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // Compute operations (Java 8+)
        System.out.println("\n4. Compute operations:");
        scores.compute("Alice", (key, value) -> value + 2);  // Add 2 to Alice's score
        System.out.println("After computing Alice: " + scores);
        
        scores.merge("Bob", 5, Integer::sum);  // Add 5 to Bob's score
        System.out.println("After merging Bob: " + scores);
        System.out.println();
    }
    
    
    // Method 2: LinkedHashMap operations
    public void linkedHashMapOperations() {
        System.out.println("\n=== LinkedHashMap Operations ===");
        System.out.println("Implementation: Hash table + Linked list");
        System.out.println("Order: Insertion order (or access order)");
        System.out.println();
        
        // Insertion order
        LinkedHashMap<String, String> capitals = new LinkedHashMap<>();
        capitals.put("India", "New Delhi");
        capitals.put("USA", "Washington DC");
        capitals.put("Japan", "Tokyo");
        capitals.put("France", "Paris");
        
        System.out.println("LinkedHashMap (insertion order):");
        for (Map.Entry<String, String> entry : capitals.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // Access order (LRU cache behavior)
        System.out.println("\n--- Access Order LinkedHashMap ---");
        LinkedHashMap<String, Integer> lruCache = new LinkedHashMap<>(16, 0.75f, true);
        lruCache.put("A", 1);
        lruCache.put("B", 2);
        lruCache.put("C", 3);
        
        System.out.println("Initial: " + lruCache);
        
        lruCache.get("A");  // Access A
        System.out.println("After accessing A: " + lruCache);
        System.out.println("Note: A moved to end (most recently used)");
        System.out.println();
    }
    
    
    // Method 3: TreeMap operations
    public void treeMapOperations() {
        System.out.println("\n=== TreeMap Operations ===");
        System.out.println("Implementation: Red-Black tree");
        System.out.println("Order: Sorted by keys");
        System.out.println("Null: Does NOT allow null keys");
        System.out.println("Performance: O(log n) for get/put");
        System.out.println();
        
        TreeMap<Integer, String> students = new TreeMap<>();
        
        // Adding entries
        students.put(103, "Carol");
        students.put(101, "Alice");
        students.put(105, "Eve");
        students.put(102, "Bob");
        students.put(104, "David");
        
        System.out.println("TreeMap (sorted by keys):");
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println("  Roll " + entry.getKey() + ": " + entry.getValue());
        }
        
        // Navigation methods
        System.out.println("\nNavigation:");
        System.out.println("First entry: " + students.firstEntry());
        System.out.println("Last entry: " + students.lastEntry());
        System.out.println("Lower key than 103: " + students.lowerKey(103));
        System.out.println("Higher key than 103: " + students.higherKey(103));
        System.out.println("Floor key of 103: " + students.floorKey(103));
        System.out.println("Ceiling key of 103: " + students.ceilingKey(103));
        
        // Submap operations
        System.out.println("\nSubmaps:");
        System.out.println("HeadMap (< 103): " + students.headMap(103));
        System.out.println("TailMap (>= 103): " + students.tailMap(103));
        System.out.println("SubMap [102, 105): " + students.subMap(102, 105));
        
        // Descending order
        System.out.println("\nDescending:");
        NavigableMap<Integer, String> descStudents = students.descendingMap();
        System.out.println("Descending map: " + descStudents);
        
        // Custom comparator (reverse order)
        System.out.println("\n--- Custom Comparator (Reverse) ---");
        TreeMap<String, Integer> reverseMap = new TreeMap<>(Collections.reverseOrder());
        reverseMap.put("Charlie", 30);
        reverseMap.put("Alice", 25);
        reverseMap.put("Eve", 28);
        reverseMap.put("Bob", 22);
        System.out.println("Reverse order TreeMap: " + reverseMap);
        System.out.println();
    }
}


// ============================================
// MAIN CLASS
// ============================================

public class Unit5_Collections {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 5: COLLECTIONS FRAMEWORK");
        System.out.println("=".repeat(70));
        
        
        // ========== EXAMPLE 1: LIST INTERFACE ==========
        System.out.println("\nEXAMPLE 1: List Interface");
        System.out.println("-".repeat(70));
        
        ListExample listExample = new ListExample();
        listExample.arrayListOperations();
        listExample.linkedListOperations();
        listExample.compareListPerformance();
        
        
        // ========== EXAMPLE 2: SET INTERFACE ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 2: Set Interface");
        System.out.println("-".repeat(70));
        
        SetExample setExample = new SetExample();
        setExample.hashSetOperations();
        setExample.linkedHashSetOperations();
        setExample.treeSetOperations();
        
        
        // ========== EXAMPLE 3: MAP INTERFACE ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 3: Map Interface");
        System.out.println("-".repeat(70));
        
        MapExample mapExample = new MapExample();
        mapExample.hashMapOperations();
        mapExample.linkedHashMapOperations();
        mapExample.treeMapOperations();
        
        
        // ========== SUMMARY ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("COLLECTIONS SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("\nLIST (Ordered, Duplicates allowed):");
        System.out.println("  ArrayList    : Fast random access, slow insertions");
        System.out.println("  LinkedList   : Slow random access, fast insertions");
        System.out.println("  Vector       : Synchronized ArrayList (thread-safe)");
        System.out.println("\nSET (No duplicates):");
        System.out.println("  HashSet      : Unordered, O(1) operations");
        System.out.println("  LinkedHashSet: Insertion order, O(1) operations");
        System.out.println("  TreeSet      : Sorted, O(log n) operations");
        System.out.println("\nMAP (Key-Value pairs):");
        System.out.println("  HashMap      : Unordered, O(1) operations");
        System.out.println("  LinkedHashMap: Insertion/access order, O(1) operations");
        System.out.println("  TreeMap      : Sorted by keys, O(log n) operations");
        System.out.println("\nQUEUE:");
        System.out.println("  PriorityQueue: Priority-based ordering");
        System.out.println("  ArrayDeque   : Double-ended queue, fast operations");
        System.out.println("=".repeat(70));
    }
}

/*
 * UNIT 6: MULTITHREADING
 * 
 * Chirag, my dude! Multithreading is when your code does multiple things at once.
 * Kind of like how your brain tries to code, check Instagram, and question existence simultaneously. 🧵
 * 
 * Warning: Deadlocks ahead! They're like your thoughts at 3 AM - circular and going nowhere.
 * 
 * Remember: With great threading comes great responsibility (and debugging nightmares). 🕷️
 * 
 * THEORY:
 * 
 * THREAD: Lightweight subprocess, smallest unit of processing
 * 
 * MULTITHREADING: Concurrent execution of multiple threads
 * Benefits: Better CPU utilization, responsiveness, parallel execution
 * 
 * Thread Creation:
 * 1. Extend Thread class - Override run()
 * 2. Implement Runnable interface - Implement run()
 * 3. Implement Callable interface - Returns result, throws exception
 * 
 * Thread Lifecycle:
 * New -> Runnable -> Running -> Blocked/Waiting/Timed Waiting -> Terminated
 * 
 * Thread Methods:
 * - start() : Starts thread execution
 * - run() : Entry point for thread
 * - sleep(ms) : Pauses thread
 * - join() : Waits for thread to finish
 * - yield() : Hints to scheduler to give chance to other threads
 * - setPriority() : Sets thread priority (1-10)
 * - isAlive() : Checks if thread is alive
 * 
 * SYNCHRONIZATION: Controlling access to shared resources
 * - synchronized method : Only one thread can execute at a time
 * - synchronized block : Locks specific object
 * - static synchronized : Locks class, not instance
 * 
 * INTER-THREAD COMMUNICATION:
 * - wait() : Releases lock and waits
 * - notify() : Wakes up one waiting thread
 * - notifyAll() : Wakes up all waiting threads
 * 
 * DEADLOCK: Two or more threads waiting for each other indefinitely
 */


import java.util.*;
import java.util.concurrent.*;


// ============================================
// EXAMPLE 1: THREAD CREATION AND LIFECYCLE
// ============================================

// Method 1: Extending Thread class
class MyThread extends Thread {
    private String threadName;
    private int iterations;
    
    public MyThread(String name, int iterations) {
        this.threadName = name;
        this.iterations = iterations;
    }
    
    @Override
    public void run() {
        System.out.println(threadName + " started (ID: " + Thread.currentThread().getId() + ")");
        
        for (int i = 1; i <= iterations; i++) {
            System.out.println(threadName + " - Iteration " + i);
            
            try {
                Thread.sleep(500);  // Sleep for 500ms
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted");
            }
        }
        
        System.out.println(threadName + " finished");
    }
}


// Method 2: Implementing Runnable interface (preferred)
class MyRunnable implements Runnable {
    private String taskName;
    private int iterations;
    
    public MyRunnable(String name, int iterations) {
        this.taskName = name;
        this.iterations = iterations;
    }
    
    @Override
    public void run() {
        System.out.println(taskName + " started (Thread: " + Thread.currentThread().getName() + ")");
        
        for (int i = 1; i <= iterations; i++) {
            System.out.println(taskName + " - Step " + i);
            
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted");
            }
        }
        
        System.out.println(taskName + " completed");
    }
}


// Method 3: Implementing Callable interface (returns result)
class MyCallable implements Callable<Integer> {
    private String taskName;
    private int limit;
    
    public MyCallable(String name, int limit) {
        this.taskName = name;
        this.limit = limit;
    }
    
    @Override
    public Integer call() throws Exception {
        System.out.println(taskName + " started");
        
        int sum = 0;
        for (int i = 1; i <= limit; i++) {
            sum += i;
            Thread.sleep(100);
        }
        
        System.out.println(taskName + " computed sum: " + sum);
        return sum;
    }
}


class ThreadCreationExample {
    
    // Method 1: Creating threads by extending Thread
    public void extendingThread() {
        System.out.println("\n=== Creating Threads (Extending Thread) ===");
        System.out.println("Main thread: " + Thread.currentThread().getName());
        System.out.println();
        
        MyThread thread1 = new MyThread("Thread-A", 3);
        MyThread thread2 = new MyThread("Thread-B", 3);
        
        // Start threads
        thread1.start();  // Calls run() in new thread
        thread2.start();
        
        System.out.println("\nMain thread continues...");
        System.out.println();
    }
    
    
    // Method 2: Creating threads with Runnable
    public void implementingRunnable() {
        System.out.println("\n=== Creating Threads (Implementing Runnable) ===");
        System.out.println("Better approach: Allows extending other classes");
        System.out.println();
        
        // Create Runnable objects
        MyRunnable task1 = new MyRunnable("Task-1", 3);
        MyRunnable task2 = new MyRunnable("Task-2", 3);
        
        // Create Thread objects with Runnable
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        
        // Set thread names
        thread1.setName("Worker-1");
        thread2.setName("Worker-2");
        
        // Start threads
        thread1.start();
        thread2.start();
        
        System.out.println("Main thread continues...");
        System.out.println();
    }
    
    
    // Method 3: Thread lifecycle and methods
    public void threadLifecycle() {
        System.out.println("\n=== Thread Lifecycle and Methods ===");
        System.out.println();
        
        Thread thread = new Thread(() -> {
            System.out.println("Thread running...");
            
            for (int i = 1; i <= 5; i++) {
                System.out.println("Count: " + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }
            
            System.out.println("Thread finished");
        });
        
        // NEW state
        System.out.println("State after creation: " + thread.getState());
        
        // Start thread (NEW -> RUNNABLE)
        thread.start();
        System.out.println("State after start(): " + thread.getState());
        
        // Check if alive
        System.out.println("Is alive: " + thread.isAlive());
        
        // Wait for thread to finish
        try {
            thread.join();  // Main thread waits
            System.out.println("\nThread joined to main thread");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // TERMINATED state
        System.out.println("State after completion: " + thread.getState());
        System.out.println("Is alive: " + thread.isAlive());
        System.out.println();
    }
    
    
    // Method 4: Thread priority
    public void threadPriority() {
        System.out.println("\n=== Thread Priority ===");
        System.out.println("MIN_PRIORITY = 1, NORM_PRIORITY = 5, MAX_PRIORITY = 10");
        System.out.println();
        
        Thread highPriority = new Thread(() -> {
            System.out.println("High priority task executing...");
        });
        
        Thread lowPriority = new Thread(() -> {
            System.out.println("Low priority task executing...");
        });
        
        // Set priorities
        highPriority.setPriority(Thread.MAX_PRIORITY);  // 10
        lowPriority.setPriority(Thread.MIN_PRIORITY);   // 1
        
        System.out.println("High priority: " + highPriority.getPriority());
        System.out.println("Low priority: " + lowPriority.getPriority());
        
        lowPriority.start();
        highPriority.start();
        
        System.out.println("Note: Priority is a hint to scheduler, not a guarantee");
        System.out.println();
    }
    
    
    // Method 5: Callable with ExecutorService
    public void callableExample() {
        System.out.println("\n=== Callable and Future ===");
        System.out.println("Returns result and can throw checked exceptions");
        System.out.println();
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit Callable tasks
        Future<Integer> future1 = executor.submit(new MyCallable("Task-1", 10));
        Future<Integer> future2 = executor.submit(new MyCallable("Task-2", 20));
        
        try {
            // Get results (blocks until complete)
            System.out.println("\nWaiting for results...");
            Integer result1 = future1.get();
            Integer result2 = future2.get();
            
            System.out.println("\nTask-1 result: " + result1);
            System.out.println("Task-2 result: " + result2);
            System.out.println("Total: " + (result1 + result2));
            
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        System.out.println();
    }
}


// ============================================
// EXAMPLE 2: SYNCHRONIZATION AND THREAD SAFETY
// ============================================

// Shared resource WITHOUT synchronization (thread-unsafe)
class UnsafeCounter {
    private int count = 0;
    
    public void increment() {
        count++;  // Not atomic: read -> increment -> write
    }
    
    public int getCount() {
        return count;
    }
}


// Shared resource WITH synchronization (thread-safe)
class SafeCounter {
    private int count = 0;
    
    // Synchronized method
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}


// Bank account with synchronized methods
class BankAccount {
    private double balance;
    private String accountHolder;
    
    public BankAccount(String holder, double initialBalance) {
        this.accountHolder = holder;
        this.balance = initialBalance;
    }
    
    // Synchronized deposit
    public synchronized void deposit(double amount) {
        System.out.println(Thread.currentThread().getName() + " depositing " + amount);
        double newBalance = balance + amount;
        
        // Simulate processing delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        balance = newBalance;
        System.out.println(Thread.currentThread().getName() + " deposit complete. Balance: " + balance);
    }
    
    // Synchronized withdrawal
    public synchronized void withdraw(double amount) {
        System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
        
        if (balance >= amount) {
            double newBalance = balance - amount;
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            balance = newBalance;
            System.out.println(Thread.currentThread().getName() + " withdrawal complete. Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " withdrawal failed. Insufficient balance.");
        }
    }
    
    public synchronized double getBalance() {
        return balance;
    }
}


class SynchronizationExample {
    
    // Method 1: Race condition (without synchronization)
    public void raceCondition() {
        System.out.println("\n=== Race Condition (Without Synchronization) ===");
        System.out.println("Multiple threads accessing shared resource");
        System.out.println();
        
        UnsafeCounter counter = new UnsafeCounter();
        
        // Create 10 threads, each incrementing 1000 times
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }
        
        // Wait for all threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Expected count: 10000");
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Note: Result is incorrect due to race condition");
        System.out.println();
    }
    
    
    // Method 2: Thread safety with synchronization
    public void threadSafety() {
        System.out.println("\n=== Thread Safety (With Synchronization) ===");
        System.out.println();
        
        SafeCounter counter = new SafeCounter();
        
        // Create 10 threads, each incrementing 1000 times
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }
        
        // Wait for all threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Expected count: 10000");
        System.out.println("Actual count: " + counter.getCount());
        System.out.println("Note: Result is correct due to synchronization");
        System.out.println();
    }
    
    
    // Method 3: Synchronized block
    public void synchronizedBlock() {
        System.out.println("\n=== Synchronized Block ===");
        System.out.println("Locks specific object, more granular control");
        System.out.println();
        
        BankAccount account = new BankAccount("Alice", 1000);
        
        System.out.println("Initial balance: " + account.getBalance());
        System.out.println();
        
        // Multiple threads accessing account
        Thread t1 = new Thread(() -> account.deposit(500), "Thread-1");
        Thread t2 = new Thread(() -> account.withdraw(300), "Thread-2");
        Thread t3 = new Thread(() -> account.deposit(200), "Thread-3");
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nFinal balance: " + account.getBalance());
        System.out.println("Expected: 1000 + 500 - 300 + 200 = 1400");
        System.out.println();
    }
}


// ============================================
// EXAMPLE 3: INTER-THREAD COMMUNICATION AND DEADLOCK
// ============================================

// Producer-Consumer problem
class SharedBuffer {
    private Queue<Integer> buffer = new LinkedList<>();
    private int capacity;
    
    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }
    
    // Producer adds to buffer
    public synchronized void produce(int item) throws InterruptedException {
        while (buffer.size() == capacity) {
            System.out.println("Buffer full. Producer waiting...");
            wait();  // Release lock and wait
        }
        
        buffer.add(item);
        System.out.println("Produced: " + item + " (Buffer size: " + buffer.size() + ")");
        
        notify();  // Wake up consumer
    }
    
    // Consumer removes from buffer
    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer empty. Consumer waiting...");
            wait();  // Release lock and wait
        }
        
        int item = buffer.poll();
        System.out.println("Consumed: " + item + " (Buffer size: " + buffer.size() + ")");
        
        notify();  // Wake up producer
        
        return item;
    }
}


// Deadlock example
class Resource {
    private String name;
    
    public Resource(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}


class InterThreadCommunicationExample {
    
    // Method 1: Producer-Consumer problem
    public void producerConsumer() {
        System.out.println("\n=== Producer-Consumer Problem ===");
        System.out.println("Inter-thread communication using wait() and notify()");
        System.out.println();
        
        SharedBuffer buffer = new SharedBuffer(5);
        
        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.consume();
                    Thread.sleep(200);  // Slower than producer
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        producer.start();
        consumer.start();
        
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nProducer-Consumer completed");
        System.out.println();
    }
    
    
    // Method 2: Deadlock scenario
    public void deadlockExample() {
        System.out.println("\n=== Deadlock Example ===");
        System.out.println("Two threads waiting for each other's resources");
        System.out.println();
        
        final Resource resource1 = new Resource("Resource-1");
        final Resource resource2 = new Resource("Resource-2");
        
        // Thread 1: Locks resource1, then tries to lock resource2
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread-1: Locked " + resource1.getName());
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread-1: Waiting for " + resource2.getName());
                synchronized (resource2) {
                    System.out.println("Thread-1: Locked " + resource2.getName());
                }
            }
        });
        
        // Thread 2: Locks resource2, then tries to lock resource1
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread-2: Locked " + resource2.getName());
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread-2: Waiting for " + resource1.getName());
                synchronized (resource1) {
                    System.out.println("Thread-2: Locked " + resource1.getName());
                }
            }
        });
        
        thread1.start();
        thread2.start();
        
        // Wait for a short time to observe deadlock
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nDEADLOCK DETECTED!");
        System.out.println("Thread-1 waiting for Resource-2 (held by Thread-2)");
        System.out.println("Thread-2 waiting for Resource-1 (held by Thread-1)");
        System.out.println("Both threads blocked indefinitely");
        
        // Force stop (not recommended in production)
        thread1.interrupt();
        thread2.interrupt();
        System.out.println();
    }
    
    
    // Method 3: Deadlock prevention (ordered locking)
    public void deadlockPrevention() {
        System.out.println("\n=== Deadlock Prevention ===");
        System.out.println("Solution: Always acquire locks in same order");
        System.out.println();
        
        final Resource resource1 = new Resource("Resource-1");
        final Resource resource2 = new Resource("Resource-2");
        
        // Both threads lock resources in same order
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread-1: Locked " + resource1.getName());
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                synchronized (resource2) {
                    System.out.println("Thread-1: Locked " + resource2.getName());
                }
            }
            System.out.println("Thread-1: Completed");
        });
        
        Thread thread2 = new Thread(() -> {
            synchronized (resource1) {  // Same order as thread1
                System.out.println("Thread-2: Locked " + resource1.getName());
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                synchronized (resource2) {
                    System.out.println("Thread-2: Locked " + resource2.getName());
                }
            }
            System.out.println("Thread-2: Completed");
        });
        
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nNo deadlock! Both threads completed successfully");
        System.out.println();
    }
    
    
    // Method 4: Thread pool (ExecutorService)
    public void threadPool() {
        System.out.println("\n=== Thread Pool (ExecutorService) ===");
        System.out.println("Reuses threads for better performance");
        System.out.println();
        
        // Create thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        System.out.println("Submitting 10 tasks to pool of 3 threads");
        System.out.println();
        
        // Submit 10 tasks
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executing on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " completed");
            });
        }
        
        // Shutdown executor
        executor.shutdown();
        
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nAll tasks completed");
        System.out.println("Note: Only 3 threads were used for 10 tasks");
        System.out.println();
    }
}


// ============================================
// MAIN CLASS
// ============================================

public class Unit6_Multithreading {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 6: MULTITHREADING");
        System.out.println("=".repeat(70));
        
        
        // ========== EXAMPLE 1: THREAD CREATION ==========
        System.out.println("\nEXAMPLE 1: Thread Creation and Lifecycle");
        System.out.println("-".repeat(70));
        
        ThreadCreationExample creationExample = new ThreadCreationExample();
        creationExample.extendingThread();
        
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        
        creationExample.implementingRunnable();
        
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        
        creationExample.threadLifecycle();
        creationExample.threadPriority();
        creationExample.callableExample();
        
        
        // ========== EXAMPLE 2: SYNCHRONIZATION ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 2: Synchronization and Thread Safety");
        System.out.println("-".repeat(70));
        
        SynchronizationExample syncExample = new SynchronizationExample();
        syncExample.raceCondition();
        syncExample.threadSafety();
        syncExample.synchronizedBlock();
        
        
        // ========== EXAMPLE 3: INTER-THREAD COMMUNICATION ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 3: Inter-Thread Communication and Deadlock");
        System.out.println("-".repeat(70));
        
        InterThreadCommunicationExample commExample = new InterThreadCommunicationExample();
        commExample.producerConsumer();
        commExample.deadlockExample();
        commExample.deadlockPrevention();
        commExample.threadPool();
        
        
        // ========== SUMMARY ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("MULTITHREADING SUMMARY");
        System.out.println("=".repeat(70));
        System.out.println("\nTHREAD CREATION:");
        System.out.println("  1. Extend Thread class (less flexible)");
        System.out.println("  2. Implement Runnable interface (preferred)");
        System.out.println("  3. Implement Callable interface (returns result)");
        System.out.println("\nTHREAD LIFECYCLE:");
        System.out.println("  New -> Runnable -> Running -> Blocked/Waiting -> Terminated");
        System.out.println("\nKEY METHODS:");
        System.out.println("  start()     : Start thread execution");
        System.out.println("  run()       : Thread's code");
        System.out.println("  sleep(ms)   : Pause thread");
        System.out.println("  join()      : Wait for thread to finish");
        System.out.println("  yield()     : Give chance to other threads");
        System.out.println("  interrupt() : Interrupt thread");
        System.out.println("\nSYNCHRONIZATION:");
        System.out.println("  synchronized method : Entire method locked");
        System.out.println("  synchronized block  : Specific code locked");
        System.out.println("  Purpose             : Prevent race conditions");
        System.out.println("\nINTER-THREAD COMMUNICATION:");
        System.out.println("  wait()      : Release lock and wait");
        System.out.println("  notify()    : Wake up one waiting thread");
        System.out.println("  notifyAll() : Wake up all waiting threads");
        System.out.println("\nDEADLOCK:");
        System.out.println("  Cause       : Circular wait for resources");
        System.out.println("  Prevention  : Ordered locking, timeouts, deadlock detection");
        System.out.println("\nTHREAD POOL:");
        System.out.println("  ExecutorService : Manages pool of threads");
        System.out.println("  Benefits        : Reuses threads, better performance");
        System.out.println("=".repeat(70));
    }
}

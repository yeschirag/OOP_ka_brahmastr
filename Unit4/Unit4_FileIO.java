/*
 * UNIT 4: FILE I/O AND STREAMS
 * 
 * Alright Chirag! File I/O is how Java reads and writes files.
 * It's like texting, but with more exceptions and less ghosting. 📁
 * 
 * Streams are not Netflix - they're data flows. Although debugging them feels like binge-watching... 
 * endless suffering with occasional satisfaction! 🌊
 * 
 * THEORY:
 * 
 * STREAM: Flow of data from source to destination
 * 
 * Types of Streams:
 * 1. BYTE STREAMS - Process data byte by byte (8 bits)
 *    - InputStream, OutputStream (abstract base classes)
 *    - FileInputStream, FileOutputStream
 *    - BufferedInputStream, BufferedOutputStream
 * 
 * 2. CHARACTER STREAMS - Process data character by character (16 bits)
 *    - Reader, Writer (abstract base classes)
 *    - FileReader, FileWriter
 *    - BufferedReader, BufferedWriter
 * 
 * 3. OBJECT STREAMS - For serialization
 *    - ObjectInputStream, ObjectOutputStream
 * 
 * File Operations:
 * - Create, Read, Write, Append, Delete
 * - Check existence, size, permissions
 * - Directory operations
 * 
 * Serialization: Converting object to byte stream
 * Deserialization: Converting byte stream to object
 */


import java.io.*;
import java.nio.file.*;
import java.util.*;


// ============================================
// EXAMPLE 1: Character Streams (FileReader, FileWriter, BufferedReader, BufferedWriter)
// ============================================

class CharacterStreamExample {
    
    // Method 1: Writing to file using FileWriter
    public void writeToFile(String filename, String content) {
        System.out.println("\n=== Writing to File (FileWriter) ===");
        System.out.println("File: " + filename);
        
        FileWriter writer = null;
        
        try {
            writer = new FileWriter(filename);
            writer.write(content);
            System.out.println("Content written successfully!");
            System.out.println("Characters written: " + content.length());
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    System.out.println("FileWriter closed");
                }
            } catch (IOException e) {
                System.out.println("Error closing writer: " + e.getMessage());
            }
        }
        System.out.println();
    }
    
    
    // Method 2: Reading from file using FileReader
    public String readFromFile(String filename) {
        System.out.println("\n=== Reading from File (FileReader) ===");
        System.out.println("File: " + filename);
        
        StringBuilder content = new StringBuilder();
        FileReader reader = null;
        
        try {
            reader = new FileReader(filename);
            int character;
            
            // Read character by character
            while ((character = reader.read()) != -1) {
                content.append((char) character);
            }
            
            System.out.println("Content read successfully!");
            System.out.println("Characters read: " + content.length());
            System.out.println("\nFile Content:");
            System.out.println("-".repeat(50));
            System.out.println(content.toString());
            System.out.println("-".repeat(50));
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    System.out.println("FileReader closed");
                }
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
        System.out.println();
        
        return content.toString();
    }
    
    
    // Method 3: Buffered operations (more efficient)
    public void writeWithBufferedWriter(String filename, List<String> lines) {
        System.out.println("\n=== Writing with BufferedWriter ===");
        System.out.println("File: " + filename);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i));
                writer.newLine();  // Platform-independent line separator
            }
            
            System.out.println("Lines written: " + lines.size());
            System.out.println("Content written with BufferedWriter");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
    
    
    // Method 4: Reading with BufferedReader (line by line)
    public List<String> readWithBufferedReader(String filename) {
        System.out.println("\n=== Reading with BufferedReader ===");
        System.out.println("File: " + filename);
        
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            
            String line;
            int lineNumber = 1;
            
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }
            
            System.out.println("\nTotal lines read: " + lines.size());
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        return lines;
    }
    
    
    // Method 5: Appending to file
    public void appendToFile(String filename, String content) {
        System.out.println("\n=== Appending to File ===");
        System.out.println("File: " + filename);
        
        try (FileWriter writer = new FileWriter(filename, true)) {  // true = append mode
            
            writer.write("\n" + content);
            System.out.println("Content appended successfully!");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
}


// ============================================
// EXAMPLE 2: Byte Streams and File Operations
// ============================================

class ByteStreamExample {
    
    // Method 1: Copy file using byte streams
    public void copyFile(String sourceFile, String destFile) {
        System.out.println("\n=== Copying File (Byte Streams) ===");
        System.out.println("Source: " + sourceFile);
        System.out.println("Destination: " + destFile);
        
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destFile);
            
            byte[] buffer = new byte[1024];  // 1KB buffer
            int bytesRead;
            int totalBytes = 0;
            
            // Read and write in chunks
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;
            }
            
            System.out.println("File copied successfully!");
            System.out.println("Total bytes copied: " + totalBytes);
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
                System.out.println("Streams closed");
                
            } catch (IOException e) {
                System.out.println("Error closing streams: " + e.getMessage());
            }
        }
        System.out.println();
    }
    
    
    // Method 2: Buffered byte streams (more efficient)
    public void copyFileBuffered(String sourceFile, String destFile) {
        System.out.println("\n=== Copying File (Buffered Byte Streams) ===");
        
        try (
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile))
        ) {
            byte[] buffer = new byte[8192];  // 8KB buffer
            int bytesRead;
            int totalBytes = 0;
            
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;
            }
            
            System.out.println("Buffered copy completed!");
            System.out.println("Bytes copied: " + totalBytes);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
    
    
    // Method 3: File information and operations
    public void fileOperations(String filename) {
        System.out.println("\n=== File Operations ===");
        
        File file = new File(filename);
        
        System.out.println("File: " + filename);
        System.out.println("Exists: " + file.exists());
        
        if (file.exists()) {
            System.out.println("Is File: " + file.isFile());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Can Read: " + file.canRead());
            System.out.println("Can Write: " + file.canWrite());
            System.out.println("Size: " + file.length() + " bytes");
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Parent: " + file.getParent());
            System.out.println("Last Modified: " + new Date(file.lastModified()));
        }
        System.out.println();
    }
    
    
    // Method 4: Directory operations
    public void directoryOperations(String dirPath) {
        System.out.println("\n=== Directory Operations ===");
        
        File directory = new File(dirPath);
        
        // Create directory
        if (!directory.exists()) {
            boolean created = directory.mkdirs();  // Creates parent directories too
            System.out.println("Directory created: " + created);
        }
        
        // List files
        if (directory.isDirectory()) {
            System.out.println("\nFiles in directory:");
            File[] files = directory.listFiles();
            
            if (files != null) {
                for (File file : files) {
                    String type = file.isDirectory() ? "[DIR]" : "[FILE]";
                    System.out.println(type + " " + file.getName());
                }
            }
        }
        System.out.println();
    }
}


// ============================================
// EXAMPLE 3: Serialization and Deserialization
// ============================================

// Serializable class 1: Simple student data
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int rollNumber;
    private double marks;
    private transient String password;  // transient = not serialized
    
    
    public Student(String name, int rollNumber, double marks, String password) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        this.password = password;
    }
    
    
    @Override
    public String toString() {
        return "Student{" +
               "name='" + name + '\'' +
               ", rollNumber=" + rollNumber +
               ", marks=" + marks +
               ", password='" + password + '\'' +
               '}';
    }
}


// Serializable class 2: Complex object with nested objects
class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String courseName;
    private String courseCode;
    private int credits;
    private List<String> topics;
    
    
    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.topics = new ArrayList<>();
    }
    
    
    public void addTopic(String topic) {
        topics.add(topic);
    }
    
    
    @Override
    public String toString() {
        return "Course{" +
               "courseName='" + courseName + '\'' +
               ", courseCode='" + courseCode + '\'' +
               ", credits=" + credits +
               ", topics=" + topics +
               '}';
    }
}


class SerializationExample {
    
    // Method 1: Serialize object to file
    public void serializeObject(Object obj, String filename) {
        System.out.println("\n=== Serializing Object ===");
        System.out.println("File: " + filename);
        System.out.println("Object: " + obj);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            
            oos.writeObject(obj);
            System.out.println("Object serialized successfully!");
            
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }
        System.out.println();
    }
    
    
    // Method 2: Deserialize object from file
    public Object deserializeObject(String filename) {
        System.out.println("\n=== Deserializing Object ===");
        System.out.println("File: " + filename);
        
        Object obj = null;
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            
            obj = ois.readObject();
            System.out.println("Object deserialized successfully!");
            System.out.println("Object: " + obj);
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
            
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        System.out.println();
        
        return obj;
    }
    
    
    // Method 3: Serialize multiple objects
    public void serializeMultipleObjects(String filename, List<Object> objects) {
        System.out.println("\n=== Serializing Multiple Objects ===");
        System.out.println("File: " + filename);
        System.out.println("Objects count: " + objects.size());
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            
            for (Object obj : objects) {
                oos.writeObject(obj);
                System.out.println("Serialized: " + obj.getClass().getSimpleName());
            }
            
            System.out.println("All objects serialized!");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
    
    
    // Method 4: Deserialize multiple objects
    public List<Object> deserializeMultipleObjects(String filename, int count) {
        System.out.println("\n=== Deserializing Multiple Objects ===");
        System.out.println("File: " + filename);
        
        List<Object> objects = new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            
            for (int i = 0; i < count; i++) {
                Object obj = ois.readObject();
                objects.add(obj);
                System.out.println("Deserialized: " + obj.getClass().getSimpleName());
            }
            
            System.out.println("All objects deserialized!");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        return objects;
    }
}


// ============================================
// MAIN CLASS - Testing all examples
// ============================================

public class Unit4_FileIO {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("UNIT 4: FILE I/O AND STREAMS");
        System.out.println("=".repeat(70));
        System.out.println();
        
        
        // ========== EXAMPLE 1: CHARACTER STREAMS ==========
        System.out.println("EXAMPLE 1: Character Streams (Reader/Writer)");
        System.out.println("-".repeat(70));
        
        CharacterStreamExample charExample = new CharacterStreamExample();
        
        // Writing to file
        String content = "Hello, File I/O!\nThis is line 2.\nThis is line 3.\nJava Streams are powerful!";
        charExample.writeToFile("test_file.txt", content);
        
        // Reading from file
        charExample.readFromFile("test_file.txt");
        
        // BufferedWriter
        List<String> lines = Arrays.asList(
            "Line 1: Java Programming",
            "Line 2: Object-Oriented Programming",
            "Line 3: File Input/Output",
            "Line 4: Streams and Buffers",
            "Line 5: Exception Handling"
        );
        charExample.writeWithBufferedWriter("buffered_file.txt", lines);
        
        // BufferedReader
        charExample.readWithBufferedReader("buffered_file.txt");
        
        // Appending to file
        charExample.appendToFile("buffered_file.txt", "Line 6: Appended content!");
        charExample.readWithBufferedReader("buffered_file.txt");
        
        
        // ========== EXAMPLE 2: BYTE STREAMS AND FILE OPERATIONS ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 2: Byte Streams and File Operations");
        System.out.println("-".repeat(70));
        
        ByteStreamExample byteExample = new ByteStreamExample();
        
        // Copy file
        byteExample.copyFile("test_file.txt", "copy_of_test.txt");
        
        // Buffered copy
        byteExample.copyFileBuffered("buffered_file.txt", "buffered_copy.txt");
        
        // File operations
        byteExample.fileOperations("test_file.txt");
        byteExample.fileOperations("nonexistent_file.txt");
        
        // Directory operations
        byteExample.directoryOperations("test_directory");
        
        
        // ========== EXAMPLE 3: SERIALIZATION ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXAMPLE 3: Serialization and Deserialization");
        System.out.println("-".repeat(70));
        
        SerializationExample serExample = new SerializationExample();
        
        // Serialize single object
        System.out.println("\n1. Single Object Serialization:");
        Student student = new Student("Alice Johnson", 101, 92.5, "secret123");
        System.out.println("Before serialization: " + student);
        serExample.serializeObject(student, "student.ser");
        
        // Deserialize single object
        Student deserializedStudent = (Student) serExample.deserializeObject("student.ser");
        System.out.println("After deserialization: " + deserializedStudent);
        System.out.println("Note: password (transient) is null after deserialization");
        
        
        // Serialize complex object
        System.out.println("\n2. Complex Object Serialization:");
        Course course = new Course("Object-Oriented Programming", "CS201", 4);
        course.addTopic("Inheritance");
        course.addTopic("Polymorphism");
        course.addTopic("Exception Handling");
        course.addTopic("File I/O");
        
        System.out.println("Before serialization: " + course);
        serExample.serializeObject(course, "course.ser");
        
        Course deserializedCourse = (Course) serExample.deserializeObject("course.ser");
        System.out.println("After deserialization: " + deserializedCourse);
        
        
        // Multiple objects
        System.out.println("\n3. Multiple Objects Serialization:");
        List<Object> objectsToSerialize = Arrays.asList(
            new Student("Bob", 102, 88.0, "pass456"),
            new Student("Carol", 103, 95.5, "pwd789"),
            new Course("Data Structures", "CS202", 4)
        );
        
        serExample.serializeMultipleObjects("multiple_objects.ser", objectsToSerialize);
        List<Object> deserializedObjects = serExample.deserializeMultipleObjects("multiple_objects.ser", 3);
        
        System.out.println("\nDeserialized objects:");
        for (Object obj : deserializedObjects) {
            System.out.println(obj);
        }
        
        
        // ========== SUMMARY ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("CONCEPTS DEMONSTRATED:");
        System.out.println("=".repeat(70));
        System.out.println("\n1. CHARACTER STREAMS:");
        System.out.println("   - FileReader / FileWriter (character-based)");
        System.out.println("   - BufferedReader / BufferedWriter (efficient line-based)");
        System.out.println("   - Reading and writing text files");
        System.out.println("   - Appending to existing files");
        System.out.println("\n2. BYTE STREAMS:");
        System.out.println("   - FileInputStream / FileOutputStream (byte-based)");
        System.out.println("   - BufferedInputStream / BufferedOutputStream (efficient)");
        System.out.println("   - Copying files using byte streams");
        System.out.println("   - Better for binary files");
        System.out.println("\n3. FILE OPERATIONS:");
        System.out.println("   - File class for file/directory manipulation");
        System.out.println("   - Check existence, size, permissions");
        System.out.println("   - Create, delete, rename operations");
        System.out.println("   - Directory listing and creation");
        System.out.println("\n4. SERIALIZATION:");
        System.out.println("   - ObjectOutputStream / ObjectInputStream");
        System.out.println("   - Convert objects to byte streams (serialization)");
        System.out.println("   - Restore objects from byte streams (deserialization)");
        System.out.println("   - transient keyword (exclude fields from serialization)");
        System.out.println("   - serialVersionUID for version control");
        System.out.println("\n5. BEST PRACTICES:");
        System.out.println("   - Always close streams (use try-with-resources)");
        System.out.println("   - Use buffered streams for better performance");
        System.out.println("   - Handle exceptions properly");
        System.out.println("   - Use character streams for text, byte streams for binary");
        System.out.println("=".repeat(70));
    }
}


public class Sutdy {
    public static void main(String[] args) {
        // using interface
        Animal dog = new Dog();
        dog.makeSound(); // Output: Woof
        Animal cat = new Cat();
        cat.makeSound(); // Output: Meow
        // using abstract class
        Vehicle car = new Car();
        car.start(); // Output: Car started
        Vehicle bike = new Bike();
        bike.start(); // Output: Bike started

        System.out.println(
                "the difference between abstract class and interface is that an abstract class can have both abstract and concrete methods, while an interface can only have abstract methods (until Java 8, which introduced default methods). Additionally, a class can implement multiple interfaces but can only extend one abstract class.");
        // using multiple interfaces
        AmphibiousVehicle amphibiousVehicle = new AmphibiousVehicle();
        amphibiousVehicle.fly(); // Output: Flying up to 10000 feet.
        amphibiousVehicle.swim(); // Output: Swimming up to 500 feet.

        // using generics
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello, Generics!");
        System.out.println(stringBox.getItem()); // Output: Hello, Generics!

        Box<Integer> integerBox = new Box<>();
        integerBox.setItem(123);
        System.out.println(integerBox.getItem()); // Output: 123


        // using nested class
        OuterClass outer = new OuterClass();
        outer.createInnerInstance(); // Output: Accessing: Outer Field

        OuterClass.InnerClass inner = outer.new InnerClass();

        inner.display(); // Output: Accessing: Outer Field



    }

    // make all static...

    // implementing interface
    interface Animal {
        void makeSound();
    }

    static class Dog implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Woof");
        }
    }

    static class Cat implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Meow");
        }
    }

    // difference between abstract class and interface
    abstract static class Vehicle {
        abstract void start();
    }

    static class Car extends Vehicle {
        @Override
        void start() {
            System.out.println("Car started");
        }
    }

    static class Bike extends Vehicle {
        @Override
        void start() {
            System.out.println("Bike started");
        }
    }

    // implementing mulitple interfaces
    interface Flyable {
        void fly();

        int MAX_ALTITUDE = 10000; // in feet
    }

    interface Swimmable {
        void swim();

        int MAX_DEPTH = 500; // in feet
    }

    static class AmphibiousVehicle implements Flyable, Swimmable {
        public void fly() {
            System.out.println("Flying up to " + MAX_ALTITUDE + " feet.");
        }

        public void swim() {
            System.out.println("Swimming up to " + MAX_DEPTH + " feet.");
        }
    }

    // using generics
    static class Box<T> {
        private T item;

        public void setItem(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }
    // implementing nested class
    public static class OuterClass {
        private String outerField = "Outer Field";

        OuterClass() {
            System.out.println("OuterClass instance created");
        }

        public class InnerClass {
            public void display() {
                System.out.println("Accessing: " + outerField);
            }

            public InnerClass() {
                System.out.println("InnerClass instance created");
            }
        }

        public void createInnerInstance() {
            InnerClass inner = new InnerClass();
            inner.display();
        }
    }
}
package structural.composite.familytree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parent grandFather = new Parent("Murugan");
        Parent father = new Parent("Deepan");
        Parent aunt = new Parent("Thenu");

        Person child1 = new Person("Akilan");
        Person child2 = new Person("Dharsini");
        Person cousin = new Person("Laksitha");

        father.addChild(child1);
        father.addChild(child2);
        aunt.addChild(cousin);
        grandFather.addChild(father);
        grandFather.addChild(aunt);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("=== Family Tree Interactive Demo ===");

        while (!exit) {
            System.out.println("\nOptions:");
            System.out.println("1. Show Family Tree");
            System.out.println("2. Search Member");
            System.out.println("3. Count Descendants");
            System.out.println("4. Add Child");
            System.out.println("5. Remove Child");
            System.out.println("6. Add Parent");
            System.out.println("7. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> grandFather.showFamily(0);
                case 2 -> {
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    FamilyMember found = grandFather.search(name);
                    System.out.println(found != null ? "Found: " + found.getName() : "Member not found");
                }
                case 3 -> {
                    System.out.print("Enter parent name: ");
                    String name = scanner.nextLine();
                    FamilyMember member = grandFather.search(name);
                    if (member != null) {
                        int count = member.countDescendants();
                        System.out.println(member.getName() + " has " + count + " descendant(s)");
                    } else System.out.println("Member not found");
                }
                case 4 -> {
                    System.out.print("Enter parent name: ");
                    String parentName = scanner.nextLine();
                    FamilyMember parent = grandFather.search(parentName);
                    if (parent instanceof Parent p) {
                        System.out.print("Enter child name: ");
                        String childName = scanner.nextLine();
                        System.out.print("Is child a parent? (y/n): ");
                        String isParent = scanner.nextLine();
                        FamilyMember child = isParent.equalsIgnoreCase("y")
                                ? new Parent(childName)
                                : new Person(childName);
                        p.addChild(child);
                        System.out.println("Child added.");
                    } else System.out.println("Parent not found or is a leaf");
                }
                case 5 -> {
                    System.out.print("Enter parent name: ");
                    String parentName = scanner.nextLine();
                    FamilyMember parent = grandFather.search(parentName);
                    if (parent instanceof Parent p) {
                        System.out.print("Enter child name to remove: ");
                        String childName = scanner.nextLine();
                        FamilyMember child = p.search(childName);
                        if (child != null && child != p) {
                            p.removeChild(child);
                            System.out.println("Child removed.");
                        } else System.out.println("Child not found or invalid");
                    } else System.out.println("Parent not found or is a leaf");
                }
                case 6 -> {
                    System.out.print("Enter new parent name: ");
                    String parentName = scanner.nextLine();
                    Parent newParent = new Parent(parentName);

                    System.out.print("Attach this parent under existing parent? (y/n): ");
                    String attach = scanner.nextLine();

                    if (attach.equalsIgnoreCase("y")) {
                        System.out.print("Enter existing parent name: ");
                        String existingName = scanner.nextLine();
                        FamilyMember existing = grandFather.search(existingName);
                        if (existing instanceof Parent p) {
                            p.addChild(newParent);
                            System.out.println("Parent " + parentName + " added under " + existingName);
                        } else {
                            System.out.println("Existing parent not found or is not eligible.");
                        }
                    } else {
                        grandFather.addChild(newParent);
                        System.out.println("Parent " + parentName + " added directly under root.");
                    }
                }
                case 7 -> exit = true;
                default -> System.out.println("Invalid option");
            }
        }

        scanner.close();
        System.out.println("=== Demo Finished ===");
        FamilyTreeLogger.log("Family Tree Interactive Demo finished");
    }
}

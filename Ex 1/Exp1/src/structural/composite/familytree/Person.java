package structural.composite.familytree;

public class Person implements FamilyMember {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showFamily(int level) {
        String indent = " ".repeat(level * 2);
        System.out.println(indent + "- " + name);
        FamilyTreeLogger.log("Displayed person: " + name);
    }

    @Override
    public FamilyMember search(String name) {
        if (this.name.equalsIgnoreCase(name)) {
            FamilyTreeLogger.log("Found person: " + name);
            return this;
        }
        return null;
    }

    @Override
    public int countDescendants() {
        return 0; // Leaf has no children
    }
}

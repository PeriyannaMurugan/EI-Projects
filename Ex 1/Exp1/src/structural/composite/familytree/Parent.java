package structural.composite.familytree;

import java.util.ArrayList;
import java.util.List;

public class Parent implements FamilyMember {
    private final String name;
    private final List<FamilyMember> children = new ArrayList<>();

    public Parent(String name) {
        this.name = name;
    }

    public void addChild(FamilyMember child) {
        children.add(child);
        FamilyTreeLogger.log("Added child " + child.getName() + " to " + name);
    }

    public void removeChild(FamilyMember child) {
        if (children.remove(child)) {
            FamilyTreeLogger.log("Removed child " + child.getName() + " from " + name);
        } else {
            FamilyTreeLogger.log("Attempted to remove non-existing child " + child.getName() + " from " + name);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showFamily(int level) {
        String indent = " ".repeat(level * 2);
        System.out.println(indent + "+ " + name);
        FamilyTreeLogger.log("Displayed parent: " + name);
        for (FamilyMember child : children) {
            child.showFamily(level + 1);
        }
    }

    @Override
    public FamilyMember search(String searchName) {
        if (name.equalsIgnoreCase(searchName)) {
            FamilyTreeLogger.log("Found parent: " + name);
            return this;
        }
        for (FamilyMember child : children) {
            FamilyMember found = child.search(searchName);
            if (found != null) return found;
        }
        return null;
    }

    @Override
    public int countDescendants() {
        int total = children.size();
        for (FamilyMember child : children) {
            total += child.countDescendants();
        }
        FamilyTreeLogger.log(name + " has " + total + " descendant(s)");
        return total;
    }
}

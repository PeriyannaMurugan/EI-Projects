package structural.composite.familytree;

public interface FamilyMember {
    String getName();
    void showFamily(int level);
    FamilyMember search(String name);
    int countDescendants();
}

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Category> subcategories;

    public Category(String name) {
        this.name = name;
        this.subcategories = new ArrayList<>();
    }

    public void addSubcategory(Category sub) {
        subcategories.add(sub);
    }

    public void printHierarchy(String prefix) {
        System.out.println(prefix + "- " + name);
        for (Category sub : subcategories) {
            sub.printHierarchy(prefix + "  ");
        }
    }

    public int countAllCategories() {
        int count = 1; // сам себе
        for (Category sub : subcategories) {
            count += sub.countAllCategories(); // рекурсія
        }
        return count;
    }

    public String getName() {
        return name;
    }
}
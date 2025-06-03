import java.util.Set;

public interface DependencyResolver {

    void loadFromFile(String filePath);

    Set<String> getDependencies(String input);
}

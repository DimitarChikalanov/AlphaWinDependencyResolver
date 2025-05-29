import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DependencyResolver {

    private static final String DEPENDENCY_SEPARATOR = "->";
    private final Map<String, List<String>> graph = new HashMap<>();

    public void loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DEPENDENCY_SEPARATOR);
                for (int i = 0; i < parts.length - 1; i++) {
                    String from = parts[i].trim();
                    String to = parts[i + 1].trim();
                    graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }

    public Set<String> getDependencies(String input) {
        Set<String> visited = new TreeSet<>();
        Queue<String> que = new LinkedList<>();
        que.add(input);

        while (!que.isEmpty()) {
            String current = que.poll();
            for (String dependency : graph.getOrDefault(current, Collections.emptyList())) {
                if (visited.add(dependency)) {
                    que.add(dependency);
                }
            }
        }
        return visited;
    }
}

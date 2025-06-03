import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        DependencyResolver dependencyResolver = new DependencyResolverImp();

        System.out.println("Start application");
        dependencyResolver.loadFromFile("input.txt");

        Scanner scanner = new Scanner(System.in);

        processInput(dependencyResolver, scanner);
    }

    private static void processInput(DependencyResolver dependencyResolver, Scanner scanner) {
        System.out.println("Add input to dependency resolver");
        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("exit")) {
            Set<String> result = dependencyResolver.getDependencies(input);

            String outputContent = result.isEmpty() ? "Empty" : String.join(", ", result);
            System.out.println("Output: " + outputContent);

            input = scanner.nextLine();
        }
        System.out.println("Exiting application.");
    }

}
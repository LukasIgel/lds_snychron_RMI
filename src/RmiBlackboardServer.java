import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiBlackboardServer implements Remote {
    public static void main(String[] args) {
        try {
            System.out.println("Erstelle BlackboardEngine...");
            IBlackboard localBlackboard = new BlackboardEngine();
            System.out.println("...fertig.\n");
            System.out.println("Erstelle Registry auf Port ("+ Registry.REGISTRY_PORT+")...");
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            System.out.println("...fertig.\n");

            System.out.println("Binde Server...");
            Naming.rebind("BlackboardServer", localBlackboard);
            System.out.println("...fertig.\n");

            System.out.println("Drücke 'b' zum beenden...");
            Scanner scanner = new Scanner(System.in);
            String eingabe = scanner.next();
            if (eingabe.toLowerCase().equals("b")) {
                Naming.unbind("BlackboardServer");
            }
            System.out.println("beende Server...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

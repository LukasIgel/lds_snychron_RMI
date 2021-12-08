import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;
import java.util.Scanner;

public class RmiCalcServer  implements Remote {
    public static void main(String[] args) {
        try {
            System.out.println("Erstelle CalcEngine...");
            ICalc localCalc = new CalcEngine();
            System.out.println("...fertig.\n");
            System.out.println("Erstelle Registry auf Port ("+ Registry.REGISTRY_PORT+")...");
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            System.out.println("...fertig.\n");

            System.out.println("Binde Server...");
            Naming.rebind("CalcServer", localCalc);
            System.out.println("...fertig.\n");

            System.out.println("Drücke 'b' zum beenden...");
            Scanner scanner = new Scanner(System.in);
            String eingabe = scanner.next();
            if (eingabe.toLowerCase().equals("b")) {
                Naming.unbind("CalcServer");
            }
            System.out.println("beende Server...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

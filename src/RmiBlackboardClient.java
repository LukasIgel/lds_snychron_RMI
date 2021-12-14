import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiBlackboardClient {
    static String remoteAddress = "127.0.0.1";
    static String remoteObject = "BlackboardServer";

    public static void main(String[] args) {

        char userChoice;
        do {
            System.out.println("\nEinfacher RMI-Client für Blackboard\n");
            System.out.println("k)onfiguriere Aufruf");
            System.out.println("r)ufe Nachricht ab mit der Remote Methode");
            System.out.println("f)üge Nachricht hinzu mit der Remote Methode");
            System.out.println("b)eende Programm\n");

            userChoice = new Scanner(System.in).next().charAt(0);
            switch (userChoice) {
                case 'f':
                    pushToBlackboard();
                    break;
                case 'r':
                    popFromBlackboard();
                    break;
                case 'k':
                    configureBlackboardAddress();
                    break;
            }
        }while (!(userChoice=='b'));


        //executeCalc();
        System.out.println("\nBeende Programm.");

    }

    private static void configureBlackboardAddress() {
        System.out.println("Gebe Remote Adresse ein: ");

        remoteAddress = new Scanner(System.in).next();
    }
    private static void popFromBlackboard() {
        System.out.println("Gebe key ein:");
        String key = new Scanner(System.in).next();
        System.out.println("lookup Remote Methode...");
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(remoteAddress);
            IBlackboard remoteBlackboard = (IBlackboard) registry.lookup(remoteObject);
            System.out.println("...fertig.");
            System.out.println("Rufe Remote Methode auf...");
            String value = remoteBlackboard.pop(key);
            System.out.println("...fertig.");
            System.out.println("Ergebnis: "+value);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private static void pushToBlackboard() {
        System.out.println("Gebe Argumente ein: key, value");
        System.out.println("key:");
        String key = new Scanner(System.in).next();
        System.out.println("Gebe Argumente ein: key, value");
        System.out.println("value:");
        String value = new Scanner(System.in).next();
        System.out.println("lookup Remote Methode...");
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(remoteAddress);
            IBlackboard remoteBlackboard = (IBlackboard) registry.lookup(remoteObject);
            System.out.println("...fertig.");
            System.out.println("Rufe Remote Methode auf...");
            remoteBlackboard.push(key, value);
            System.out.println("...fertig.");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}

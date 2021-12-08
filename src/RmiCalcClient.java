import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiCalcClient {
    static String remoteAddress = "127.0.0.1";
    static String remoteObject = "CalcServer";

    public static void main(String[] args) {

        char userChoice;
        do {
            System.out.println("\nEinfacher RMI-Client zum Subtrahieren\n");
            System.out.println("k)onfiguriere Aufruf");
            System.out.println("a)ddieren auf der Remote Methode");
            System.out.println("s)ubtrahieren auf der Remote Methode");
            System.out.println("b)eende Programm\n");

            userChoice = new Scanner(System.in).next().charAt(0);
            switch (userChoice) {
                case 'a', 's':
                    executeCalc(userChoice);
                    break;
                case 'k':
                    configureCalc();
                    break;
            }
        }while (!(userChoice=='b'));


        //executeCalc();
        System.out.println("\nBeende Programm.");
        
    }

    private static void configureCalc() {
        System.out.println("Gebe Remote Adresse ein: ");

        remoteAddress = new Scanner(System.in).next();
    }

    private static void executeCalc(char typeOfCalc) {

        try {

            System.out.println("Gebe Argumente ein: x1 +/- x2");
            System.out.println("x1:");
            long x1 = new Scanner(System.in).nextLong();
            System.out.println("Gebe Argumente ein: x1 +/- x2");
            System.out.println("x2:");
            long x2 = new Scanner(System.in).nextLong();
            System.out.println("lookup Remote Methode...");
            //System.out.println(remoteAddress + " "+remoteObject);
            Registry registry = LocateRegistry.getRegistry(remoteAddress);
            ICalc remoteCalc = (ICalc) registry.lookup(remoteObject);
            System.out.println("...fertig.");
            System.out.println("Rufe Remote Methode auf...");
            long result;
            if (typeOfCalc == 's') {
                result = remoteCalc.subtract(x1, x2);
            }
            else {
                result = remoteCalc.add(x1, x2);
            }
            System.out.println("...fertig.");
            System.out.println("Ergebnis: "+result);

        } catch (Exception e) {
            System.err.println("Fehler!");
            e.printStackTrace();
        }
    }

}

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcEngine extends UnicastRemoteObject implements ICalc {
    private static final long serialVersionUID = 1L;
    protected CalcEngine() throws RemoteException {
        super();
    }
    @Override
    public long subtract(long paramOne, long paramTwo) throws RemoteException {
        System.out.println("RMI Aufruf: subtract("+paramOne+", "+paramTwo+")");
        return paramOne-paramTwo;
    }
    public long add(long paramOne, long paramTwo) throws RemoteException {
        System.out.println("RMI Aufruf: subtract("+paramOne+", "+paramTwo+")");
        return paramOne+paramTwo;
    }
}

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalc extends Remote {
    public long subtract(long paramOne, long paramTwo) throws RemoteException;
    public long add(long paramOne, long paramTwo) throws RemoteException;

}
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBlackboard extends Remote {
    public void push(String key, String value) throws RemoteException;
    public String pop(String key) throws RemoteException;
}

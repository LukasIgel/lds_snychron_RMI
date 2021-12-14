import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BlackboardEngine extends UnicastRemoteObject implements IBlackboard{
    private static final long serialVersionUID = 1L;
    private static Map<String, String> blackboardMap = new HashMap<String, String>();
    protected BlackboardEngine() throws RemoteException {
        super();
    }

    @Override
    public void push(String key, String value) throws RemoteException {
        blackboardMap.put(key, value);
    }

    @Override
    public String pop(String key) throws RemoteException {
        return blackboardMap.get(key);
    }
}

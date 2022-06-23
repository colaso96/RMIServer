
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AgeMap2 extends Remote {

    String put(String name, Integer age) throws RemoteException;

    String get(String name) throws RemoteException;

    String delete(String name) throws RemoteException;


}

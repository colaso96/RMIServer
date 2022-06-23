import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

// Server class
class RMIServer implements AgeMap2 {

    private ConcurrentHashMap<String, Integer> ageMap = new ConcurrentHashMap<>();

    public RMIServer() {
        ageMap.put("test1", 25);
        ageMap.put("test2", 23);
        ageMap.put("test3", 23);
        ageMap.put("test4", 13);
        ageMap.put("test5", 6);
    }

    @Override
    public String put(String name, Integer age) throws RemoteException {
        ageMap.put(name, age);
        return "Added " + name + ": " + age + " to map!";
    }

    @Override
    public String get(String name) throws RemoteException {
        if (ageMap.get(name) == null) {
            return name + " is not in map!";
        }

        Integer age = ageMap.get(name);
        return name + " age is: " + age;
    }

    @Override
    public String delete(String name) throws RemoteException {

        if (ageMap.remove(name) == null) {
            return name + " is not in map!";
        }
        ageMap.remove(name);
        return "Removed: " + name + " from map!";
    }

    public static void main(String[] args)
    {

        try {

            int port = Integer.parseInt(args[0]);
            RMIServer obj = new RMIServer();
            AgeMap2 stub = (AgeMap2) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(port);
            //Registry registry = LocateRegistry.getRegistry();  OBSOLETE
            registry.rebind("AgeMap2", stub);

            System.out.println("Server ready");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException err) {
            System.out.println("Port needs to match server and be an integer");
        }
        catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }

    }

}


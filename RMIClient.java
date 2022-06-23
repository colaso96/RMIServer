import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

// Client class
class RMIClient {

    //private static HashMap<String, Integer> ageMap = new HashMap<>();
    private RMIClient() {

    }

    public static void main(String[] args)
    {

        try {

            String host = (args.length < 1) ? null : args[0];
            int port = Integer.parseInt(args[1]);

            Registry registry = LocateRegistry.getRegistry(host, port);
            AgeMap2 stub = (AgeMap2) registry.lookup("AgeMap2");

            //stub.put("test", 44);

            System.out.println("Key Value store is a name & age hashmap pre-populated with:" +
                    "Cole, Sophie, Rae, Koda, Max as the keys.  Please type a command (put, get, delete)" +
                    " in the form String Key, Integer Value");

            Scanner scan = new Scanner(System.in);

            while (scan != null) {
                String[] split = scan.nextLine().split(" ");

                String arg = split[0].toLowerCase();

                String key = split[1].toLowerCase();

                //FOR DEBUGGING: System.out.println(arg + " " + key);

                // write a response based on the
                // answer from the client
                switch (arg) {

                    case "put":
                        Integer val = Integer.parseInt(split[2]);

                        String response = stub.put(key, val);
                        System.out.println("response: " + response);
                        break;

                    case "get":

                        String responseGet = stub.get(key);
                        System.out.println("response: " + responseGet);
                        break;

                    case "delete":

                        String responseDel = stub.delete(key);
                        System.out.println("response: " + responseDel);
                        break;

                    default:
                        System.out.println("response: Invalid Inputs");
                        break;
                }
            }


            // closing the scanner object
            scan.close();


        }
        catch (ArrayIndexOutOfBoundsException er) {
            System.out.println("Invalid arguments");
        }
        catch (NumberFormatException err) {
            System.out.println("Port needs to match server and be an integer");
        }
        catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }

    }
}

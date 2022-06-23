# RMIServer
Create a multithreaded client server communication protocol using java RMI

## How To Run:
The server.jar takes a port number as the first argument and prints server ready when it has created the registry.  The server must be started before the client.  To start the client program the client.jar program takes a hostname as the first arg and a port as the second with some error catching if the port is not an integer.   After connection is setup, the client can get the prepopulated keys that are printed to the console as a loading message.  My design takes in Hashmap<String, Integer> to represent a person and their age. 

Please read the printed message from the client to understand what is in the hashmap.

## Description of examples:
The videos are pretty self explanatory but they run through the server implementation as well as some error handling if the client passses incorrect arguments from the hashmap.  Multiple clients can interact with the server and do concurrent operations on the concurrent hashmap that stores the key value pairs.  Shown in the video are cummulative 5 PUTS, 5 GETS & 5 DELETES.

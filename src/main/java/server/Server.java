package server;

import java.io.*;
import java.net.*;
import handler.*;

import com.sun.net.httpserver.*;

public class Server {

    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    private void run(String portNumber) throws IOException {

        System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        server.setExecutor(null);

        System.out.println("Creating contexts");
        server.createContext("/", new DefaultHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill/", new FillHandler());
        server.createContext("/load", new LoadHandler());

        System.out.println("Starting server");
        server.start();
        System.out.println("Server started");
    }

    public static void main(String[] args) throws IOException {
        String portNumber = args[0];
        new Server().run(portNumber);
    }

}


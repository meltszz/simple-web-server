package org.meltszz;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class Server {
    private final int SERVER_PORT = 8080;
    private final int REQUEST_TYPE_COL = 0;
    private final int REQUEST_TYPE_ROW = 0;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private BufferedReader req;
    private PrintWriter resp;

    public void start() {
        try {
            System.out.println(":::: Starting server ::::");
            init();

            while (true) {
                listen();
                configStreams();
                handleRequest();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            stop();
        }
    }

    private void init() throws IOException {
        System.out.println("> Initializing server...");
        serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("# Server initialized!\n");
    }

    private void stop() {
        System.out.println(":::: Stoping server ::::");
        try {
            if(serverSocket == null || serverSocket.isClosed()) {
                return;
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("# Bye, bye!");
    }

    private void listen() throws IOException {
        System.out.println("> Listening for connection...");
        clientSocket = serverSocket.accept();
        System.out.println("# Client with IP " + clientSocket.getLocalAddress().getHostAddress() + " has connected!");
    }

    private void configStreams() throws IOException {
        System.out.println("> Configuring streams...");
        req = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        resp = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        System.out.println("# Streams configured!\n");
    }

    private void handleRequest() throws IOException {
        System.out.println("> Reading request...");
        String request = "";
        String line = req.readLine();
        while (line != null && !line.isEmpty()) {
            request += line + "\n";
            line = req.readLine();
            System.out.println(line + "\n");
        }
        System.out.println("# Request read");
    }
}

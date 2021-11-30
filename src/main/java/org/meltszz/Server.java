package org.meltszz;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int SERVER_PORT = 8080;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private BufferedReader req;
    private PrintWriter resp;

    public void start() {
        try {
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
        serverSocket = new ServerSocket(SERVER_PORT);
    }

    private void stop() {
        try {
            if(serverSocket == null || serverSocket.isClosed()) {
                return;
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() throws IOException {
        clientSocket = serverSocket.accept();
    }

    private void configStreams() throws IOException {
        req = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        resp = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    private void handleRequest() throws IOException {
        String request = "";
        String line = "";
        while ((line = req.readLine()) != null) {
            request += line + "\n";
        }

        System.out.println(request);
    }
}

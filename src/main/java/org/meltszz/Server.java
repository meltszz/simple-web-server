package org.meltszz;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int SERVER_PORT = 8080;

    private final int REQUEST_TYPE_COL = 0;
    private final int REQUEST_ROUTE_COL = 1;
    private final int REQUEST_ROW = 0;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    private Router router;
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
        router = new Router();
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
        StringBuilder request = new StringBuilder();
        String line = req.readLine();
        while (line != null && !line.isEmpty()) {
            request.append(line).append("\n");
            line = req.readLine();
        }
        System.out.println(request);
        System.out.println("# Request read\n");

        System.out.println("> Analyzing request...");
        String[] splitedRequest = request.toString().split("\n");
        String requestType = splitedRequest[REQUEST_ROW].split(" ")[REQUEST_TYPE_COL];
        if (!requestType.equals("GET")) {
            System.out.println("# Invalid request type!\n");
            resp.write(ResponseHeaders.getInstance().NOT_IMPLEMENTED);
            resp.flush();
            resp.close();
            return;
        }
        String requestRoute = splitedRequest[REQUEST_ROW].split(" ")[REQUEST_ROUTE_COL];
        if (!router.hasRoute(requestRoute)) {
            System.out.println("# Invalid route!\n");
            resp.write(ResponseHeaders.getInstance().NOT_FOUND);
            resp.flush();
            resp.close();
            return;
        }
        System.out.println("# Request OK!\n");
    }
}

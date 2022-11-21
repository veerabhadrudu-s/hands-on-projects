package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UdpServer {

    public static void main(String[] args) throws IOException {
        int httpHealthPort = Integer.parseInt(args[0]);
        int responseAfterDelay = Integer.parseInt(args[1]);
        boolean printHealthCheckRequestLog = Boolean.parseBoolean(args[2]);
        int retryCount = Integer.parseInt(args[3]);
        HttpServer server = HttpServer.create(new InetSocketAddress(httpHealthPort), 0);
        server.createContext("/", new MyHandler(printHealthCheckRequestLog));
        server.setExecutor(null); // creates a default executor
        server.start();

        // Step 1 : Create a socket to listen at port 1234
        DatagramSocket ds = new DatagramSocket(5684);
        byte[] receive = null;
        System.out.println("Starting Server !!!");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);
        while (true) {
            receive = new byte[65535];
            // Step 2 : create a DatgramPacket to receive the data.
            DatagramPacket clientDataGramPacket = new DatagramPacket(receive, receive.length);
            // Step 3 : revieve the data in byte buffer.
            ds.receive(clientDataGramPacket);
            String clientData = data(receive).toString();
            System.out.println(String.format("Received Client data -> \"%s\" from client with ip %s and port %s", clientData,
                    clientDataGramPacket.getAddress().toString(), clientDataGramPacket.getPort()));
            String ackMsg = "Ack for " + clientData;
            DatagramPacket ackResponse = new DatagramPacket(ackMsg.getBytes(), ackMsg.getBytes().length,
                    clientDataGramPacket.getAddress(), clientDataGramPacket.getPort());
            ds.send(ackResponse);
            System.out.println(String.format("Ack sent to client -> \"%s\" with client with ip %s and port %s", ackMsg,
                    ackResponse.getAddress().toString(), ackResponse.getPort()));
            executorService.schedule(() -> {
                try {
                    for (int i = 1; i <= retryCount; i++) {
                        String responseData = String.format("Resending your message back after %s sec : %s with retry counter : %s",
                                responseAfterDelay, clientData, i);
                        byte[] responseDataBytes = responseData.getBytes();
                        System.out.println("Retry Count : " + i);
                        DatagramPacket clientResponse = new DatagramPacket(responseDataBytes, responseDataBytes.length,
                                clientDataGramPacket.getAddress(), clientDataGramPacket.getPort());
                        System.out.println(String.format("Sending response back -> \"%s\" to client with ip %s , port %s with retry counter %s",
                                responseData, clientResponse.getAddress().toString(), clientResponse.getPort(), i));
                        ds.send(clientResponse);
                        Thread.sleep(2000 * i);
                    }
                } catch (IOException e) {
                    System.err.println(e);
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    System.err.println(e);
                    e.printStackTrace();
                }
            }, responseAfterDelay, TimeUnit.SECONDS);
            // Exit the server if the client sends "bye"
            if (data(receive).toString().contains("bye")) {
                System.out.println("Client sent bye.....EXITING");
                System.exit(0);
            }
        }
    }

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    static class MyHandler implements HttpHandler {

        private final boolean printHealthCheckRequestLog;

        MyHandler(boolean printHealthCheckRequestLog) {
            this.printHealthCheckRequestLog = printHealthCheckRequestLog;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            if (this.printHealthCheckRequestLog)
                System.out.println("Received HTTP Health check request !!!");
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
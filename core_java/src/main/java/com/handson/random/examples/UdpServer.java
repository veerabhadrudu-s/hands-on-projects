package com.handson.random.examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UdpServer {
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<String>();
    private Random random;

    public UdpServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
        random = new Random();
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: UdpServer <file> <port>");
            return;
        }
        String quoteFile = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            UdpServer server = new UdpServer(port);
            System.out.println("Server address -> " + server.socket.getLocalAddress().getHostAddress());
            server.loadQuotesFromFile(quoteFile);
            server.service();
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    private void service() throws IOException {
        System.out.println("Listening for client request's");
        while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            socket.receive(request);
            String quote = getRandomQuote();
            byte[] buffer = quote.getBytes();
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
            System.out.println("Client Host Address -> " + clientAddress.getHostAddress());
            System.out.println("Client Host port -> " + clientPort);
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            socket.send(response);
        }
    }

    private void loadQuotesFromFile(String quoteFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(quoteFile));
        String aQuote;
        while ((aQuote = reader.readLine()) != null) {
            listQuotes.add(aQuote);
        }
        reader.close();
    }

    private String getRandomQuote() {
        int randomIndex = random.nextInt(listQuotes.size());
        String randomQuote = listQuotes.get(randomIndex);
        return randomQuote;
    }
}

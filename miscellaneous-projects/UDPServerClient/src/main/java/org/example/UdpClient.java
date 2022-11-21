package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class UdpClient {

    public static void main(String args[]) throws IOException {

        Scanner sc = new Scanner(System.in);
        Random randomPort = new Random();
        int nextRandomPort = randomPort.nextInt(50000);
        System.out.println(String.format("Client is using port number %s to connect to the Server", nextRandomPort));
        DatagramSocket ds = new DatagramSocket(nextRandomPort);
//        InetAddress ip = InetAddress.getByName("localhost");
//        InetAddress ip = InetAddress.getByName("ec2-3-110-190-89.ap-south-1.compute.amazonaws.com");
        InetAddress ip = InetAddress.getByName(args[0]);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // loop while user not enters "bye"
        System.out.println("Starting Client !!!");
        AtomicBoolean isClientStopped = new AtomicBoolean(false);
        System.out.print("Enter the client Messages : ");
        executorService.submit(() -> {
            while (true) {
                String inp = sc.nextLine();
                if (inp == null || inp.isEmpty())
                    continue;
                inp = String.format("Timestamped Msg: %s -> %s", new Timestamp(new java.util.Date().getTime()), inp);
                byte[] buf = inp.getBytes();
                DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 5684);
                try {
                    ds.send(DpSend);
                    if (inp.contains("bye")) {
                        System.out.println("Exiting the client");
                        isClientStopped.set(true);
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.submit(() -> {
            DatagramPacket serverResponse = null;
            byte[] receive = null;
            System.out.println("Started receiver thread");
            while (!isClientStopped.get()) {
                receive = new byte[65535];
                serverResponse = new DatagramPacket(receive, receive.length);
                try {
                    ds.receive(serverResponse);
                    System.out.println(String.format("Received Server Response -> \"%s\" from with ip %s and port %s",
                            data(receive).toString(), serverResponse.getAddress().toString(), serverResponse.getPort()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // A utility method to convert the byte array data into a string representation.
    public static StringBuilder data(byte[] a) {
        if (a == null) return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
package com.handson.random.examples;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * Run program using below java commands
 * java -cp core_java-0.0.1-SNAPSHOT.jar com.handson.random.examples.NetworkLoadBalancerUdpClientV3 <NLB_DNS> 2000 30
 * THis program can be used against both NetworkLoadBalancerUdpServerV2 and NetworkLoadBalancerUdpServerV3 Server programs
 */
public class NetworkLoadBalancerUdpClientV3 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: UdpClient <hostname> <port>");
            return;
        }
        Random random = new Random();
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        long subsequentUdpUplinkPacketAfterIntervalInSeconds = Long.parseLong(args[2]);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
            System.out.println("So Timeout value " + socket.getSoTimeout());
            String helloServer = "Hello Server";
            DatagramPacket request = new DatagramPacket(helloServer.getBytes(), helloServer.length(), address, port);
            socket.send(request);
            while (true) {
                System.out.println("Polling for response !!!");
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                String quote = new String(buffer, 0, response.getLength());
                System.out.println("Response From Server -> " + quote);
                scheduledExecutorService.schedule(() -> {
                    try {
                        String subsequentUplinkPayload = "Sending subsequent Uplink with ID ->" + random.nextInt();
                        byte[] subsequentUplinkPacketBuffer = subsequentUplinkPayload.getBytes();
                        DatagramPacket subsequentUplinkPacket = new DatagramPacket(subsequentUplinkPacketBuffer, subsequentUplinkPacketBuffer.length, address, port);
                        socket.send(subsequentUplinkPacket);
                        System.out.println("Subsequent uplink sent after " + subsequentUdpUplinkPacketAfterIntervalInSeconds + " seconds !!!");
                        System.out.println();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }, subsequentUdpUplinkPacketAfterIntervalInSeconds, TimeUnit.SECONDS);
                Thread.sleep(5000);
            }

        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
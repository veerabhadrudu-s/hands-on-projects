package com.handson.random.examples;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/*
 * Run program using below java commands
 * java -cp core_java-0.0.1-SNAPSHOT.jar com.handson.random.examples.NetworkLoadBalancerUdpClientV2 <NLB_DNS> 2000
 *
 */
public class NetworkLoadBalancerUdpClientV2 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: UdpClient <hostname> <port>");
            return;
        }
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
            System.out.println("So Timeout value " + socket.getSoTimeout());
            DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
            socket.send(request);
            while (true) {
                System.out.println("Polling for response !!!");
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                String quote = new String(buffer, 0, response.getLength());
                System.out.println(quote);
                System.out.println();
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
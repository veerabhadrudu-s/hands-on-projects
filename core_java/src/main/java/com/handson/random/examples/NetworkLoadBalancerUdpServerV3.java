package com.handson.random.examples;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.concurrent.*;

/*
 * Run program using below java commands
 * java -cp core_java-0.0.1-SNAPSHOT.jar com.handson.random.examples.NetworkLoadBalancerUdpServerV2 2000 5 60
 *
 */
public class NetworkLoadBalancerUdpServerV3 {
    private DatagramSocket socket;

    public NetworkLoadBalancerUdpServerV3(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        if (args.length < 3) {
            System.out.println("Syntax: UdpServer <port> <delayInSecondsForAck> <delayInSecondsForDownlink>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        long delayInSecondsForAck = Long.parseLong(args[1]);
        long delayInSecondsForDownlink = Long.parseLong(args[2]);
        try {
            NetworkLoadBalancerUdpServerV3 server = new NetworkLoadBalancerUdpServerV3(port);
            System.out.println("Server address -> " + server.socket.getLocalAddress().getHostAddress());
            server.service(delayInSecondsForAck, delayInSecondsForDownlink);
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    private void service(long delayInSecondsForAck, long delayInSecondsForDownlink) throws IOException, ExecutionException, InterruptedException {

        System.out.println("Listening for client request's");
        while (true) {
            byte[] readBuffer = new byte[512];
            DatagramPacket request = new DatagramPacket(readBuffer, readBuffer.length);
            socket.receive(request);
            String requestPayload = new String(readBuffer, 0, request.getLength());
            String ackResponse = "Sending ack for client request : " + requestPayload;
            byte[] buffer = ackResponse.getBytes();
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
            System.out.println("Client Host Address -> " + clientAddress.getHostAddress() + ":" + clientPort);
            System.out.println("Received input request from client -> " + requestPayload);
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.schedule(() -> {
                try {
                    DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                    socket.send(response);
                    System.out.println("Acknowledgement sent after " + delayInSecondsForAck + " seconds !!!");
                    System.out.println();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, delayInSecondsForAck, TimeUnit.SECONDS);

            ScheduledFuture<?> downlinkRequestScheduledFuture = scheduledExecutorService.schedule(() -> {
                try {
                    Date date = new Date();
                    String downlinkPayload = "Downlink payload sent to client " +
                            clientAddress.getHostAddress() + ":" + clientPort + " @ " + date;
                    byte[] downlinkPayloadBuffer = downlinkPayload.getBytes();
                    DatagramPacket response = new DatagramPacket(downlinkPayloadBuffer, downlinkPayloadBuffer.length, clientAddress, clientPort);
                    socket.send(response);
                    System.out.println("Downlink sent !!!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, delayInSecondsForDownlink, TimeUnit.SECONDS);
        }
    }

}

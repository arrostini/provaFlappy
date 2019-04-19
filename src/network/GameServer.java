package network;

import stati.MainGame;

import java.io.IOException;
import java.net.*;

public class GameServer extends Thread{

    private DatagramSocket socket;
    private MainGame game;

    public GameServer(MainGame game){
        this.game = game;
        try {
            this.socket = new DatagramSocket(4444);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data,data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(packet.getData());
            if(message.trim().equalsIgnoreCase("jump")){
                System.out.println("client on "+new String(packet.getData()));
                sendData("salto".getBytes(), packet.getAddress(), packet.getPort());
            }

        }
    }

    public void sendData(byte[] data, InetAddress ipAdress, int port){
        DatagramPacket packet = new DatagramPacket(data,data.length,ipAdress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

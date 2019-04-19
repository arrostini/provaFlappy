package network;

import org.newdawn.slick.GameContainer;
import stati.MainGame;
import sun.applet.Main;

import java.io.IOException;
import java.net.*;

public class GameClient extends Thread {
    private InetAddress ipAdress;
    private DatagramSocket socket;
    private MainGame game;

    public GameClient(MainGame game, String ipAdress){
        this.game = game;
        try {
            this.socket = new DatagramSocket();
            this.ipAdress = InetAddress.getByName(ipAdress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
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
            System.out.println("server on "+new String(packet.getData()));
        }
    }

    public void sendData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data,data.length,ipAdress, 4444);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

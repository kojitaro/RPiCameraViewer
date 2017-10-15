package ca.frozen.rpicameraviewer.classes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import ca.frozen.library.classes.Log;
import ca.frozen.rpicameraviewer.message.Message.VerticalJoystickPosition;

/**
 * Created by ohura on 2017/10/14.
 */

public class Message {

    public final static String CAMERA = "camera";

    public static void sendMove(String address, int index, float value){
        try{
//            VerticalJoystickPosition position = VerticalJoystickPosition.newBuilder()
//                    .setIndex(index)
//                    .setValue(value)
//                    .build();
//            byte[] b = position.toByteArray();


            byte[] b = (index + "," + String.format("%1$.6f", value)).getBytes();

            InetAddress addr = InetAddress.getByName(address);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(b, b.length, addr, 9999);
            socket.send(packet);

        }catch(IOException e){
            Log.error("sendMove error: " + e.getLocalizedMessage());
        }

    }
}

package chating;

import org.apache.log4j.net.SocketServer;

import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.*;

public class Client extends Frame {
    String nickname = "";
    String serverIP = "127.0.0.1";
    int serverPort = 7777;
    DataInputStream in;
    DataOutputStream out;
    Panel p = new Panel(new BorderLayout());
    TextArea ta = new TextArea();
    TextField tf = new TextField();

    Client(String nickname) {
        this.nickname = nickname;
        p.add(ta, "Center");
        p.add(tf, "South");
        ta.setEditable(false);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = tf.getText();
                if ("".equals(msg)) return;
                if (out != null) {
                    try {
                        out.writeUTF(nickname + " > " + msg);
                    } catch (IOException e1) { // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    ta.append("\n"+nickname+" > " + msg); tf.setText("");
                }
            }
        };
        tf.addActionListener(actionListener);
        this.add(p);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("close!");
                System.exit(0);
            }
        });
        this.setBounds(600, 200, 300, 300);
        this.setVisible(true);
        tf.requestFocus();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("Client");
            client.startClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startClient() throws UnknownHostException, IOException {
        Socket socket = new Socket(serverIP, serverPort);

        ta.setText("서버에 연결되었습니다.");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        while (in != null) {
            String msg = in.readUTF();
            System.out.println("받은 메세지 : " + msg);
            ta.append("\n" + msg);
        }
    }
}
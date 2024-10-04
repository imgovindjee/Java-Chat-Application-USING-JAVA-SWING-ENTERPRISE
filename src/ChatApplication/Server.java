package ChatApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Server implements ActionListener {

    static JFrame jFrame = new JFrame();
    static Box verticalBox = Box.createVerticalBox();
    static DataOutputStream dataOutputStream;
    JTextField jTextField;
    JPanel body_jpanel;

    public Server(){
//        custom design activation
        jFrame.setLayout(null);

//        adding the panel-1
        JPanel headerbackground_jPanel = new JPanel();
        headerbackground_jPanel.setBackground(new Color(7, 94, 84));
        headerbackground_jPanel.setBounds(0, 0, 450, 70);
        headerbackground_jPanel.setLayout(null);
        jFrame.add(headerbackground_jPanel);

//        adding image's over the panel
//        1. back
        ImageIcon unscale_back_imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/3.png"));
        Image scale_back_image = unscale_back_imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon back_imageIcon_ = new ImageIcon(scale_back_image);

        JLabel back_jlabel = new JLabel(back_imageIcon_);
        back_jlabel.setBounds(5, 20, 25, 25);
        headerbackground_jPanel.add(back_jlabel);
//        EventListener on click
        back_jlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                jFrame.setVisible(false);
//                or
//                System.exit(0);
            }
        });

//        2. profile Image
        ImageIcon unscale_profile_imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/1p.png"));
        Image scale_profile_image = unscale_profile_imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon profile_imageIcon_ = new ImageIcon(scale_profile_image);

        JLabel profile_jlabel = new JLabel(profile_imageIcon_);
        profile_jlabel.setBounds(40, 10, 50, 50);
        headerbackground_jPanel.add(profile_jlabel);

//        3. Video Image / Button
        ImageIcon unscale_video_imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/video.png"));
        Image scale_video_image = unscale_video_imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon video_imageIcon_ = new ImageIcon(scale_video_image);

        JLabel video_jlabel = new JLabel(video_imageIcon_);
        video_jlabel.setBounds(300, 20, 30, 30);
        headerbackground_jPanel.add(video_jlabel);

//        4. Call Image / Button
        ImageIcon unscale_call_imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/phone.png"));
        Image scale_call_image = unscale_call_imageIcon.getImage().getScaledInstance(35, 50, Image.SCALE_DEFAULT);
        ImageIcon call_imageIcon_ = new ImageIcon(scale_call_image);

        JLabel call_jlabel = new JLabel(call_imageIcon_);
        call_jlabel.setBounds(360, 12, 30, 50);
        headerbackground_jPanel.add(call_jlabel);

//        5. 3-Dot Option Button / Image
        ImageIcon unscale_3dot_imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/3icon.png"));
        Image scale_3dot_image = unscale_3dot_imageIcon.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon moreOption_imageIcon_ = new ImageIcon(scale_3dot_image);

        JLabel moreOption_jlabel = new JLabel(moreOption_imageIcon_);
        moreOption_jlabel.setBounds(410, 20, 10, 25);
        headerbackground_jPanel.add(moreOption_jlabel);

//        6. USER-NAME
        JLabel name_jlabel = new JLabel("Priyanka G.");
        name_jlabel.setBounds(110, 15, 105, 18);
        name_jlabel.setForeground(Color.WHITE);
        name_jlabel.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        headerbackground_jPanel.add(name_jlabel);

//        6. Online or Not
        JLabel status_jlabel = new JLabel("Active Now");
        status_jlabel.setBounds(110, 35, 100, 18);
        status_jlabel.setForeground(Color.WHITE);
        status_jlabel.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        headerbackground_jPanel.add(status_jlabel);



//        panel - 2
        body_jpanel = new JPanel();
        body_jpanel.setBounds(5, 75, 440, 570);
        jFrame.add(body_jpanel);

//        text field for sending the message
        jTextField = new JTextField();
        jTextField.setBounds(5, 655, 310, 40);
        jTextField.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        jFrame.add(jTextField);

//        send Button
        JButton send_jbutton = new JButton("Send");
        send_jbutton.setBounds(320, 655, 123, 40);
        send_jbutton.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send_jbutton.setBackground(new Color(7, 94, 84));
        send_jbutton.setForeground(Color.WHITE);
        send_jbutton.addActionListener(this);
        jFrame.add(send_jbutton);


//        making the JFrame
        jFrame.setSize(450, 700);
        jFrame.setLocation(200, 50);
        jFrame.setUndecorated(true);
        jFrame.getContentPane().setBackground(Color.WHITE);
        jFrame.setVisible(true);
    }

    public static JPanel FormatLabel(String text){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JLabel text_jlabel = new JLabel(
                "<html><p style=\"width:150px\">"+text+"</p></html>"
        );
        text_jlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        text_jlabel.setBackground(new Color(37, 211, 102));
        text_jlabel.setOpaque(true);
        text_jlabel.setBorder(new EmptyBorder(15, 15, 15, 50));
        jPanel.add(text_jlabel);


//        Current Date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
//        making the date addable to the Panel
        JLabel time_jlabel = new JLabel();
        time_jlabel.setText(simpleDateFormat.format(calendar.getTime()));
        jPanel.add(time_jlabel);

        return jPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
//            accessing the message from the text field
            String text = jTextField.getText();
//            making the string addable component
            JPanel text_jPanel = FormatLabel(text);

//            setting the panel layout for text
            body_jpanel.setLayout(new BorderLayout());

//            setting the text right side of the panel
            JPanel right_jpanel = new JPanel(new BorderLayout());
            right_jpanel.add(text_jPanel, BorderLayout.LINE_END);
//            aligning the messages vertically one after another
            verticalBox.add(right_jpanel);
//            gaping between the two consecutive messages
            verticalBox.add(Box.createVerticalStrut(15));
//            adding all the message over the panel
            body_jpanel.add(verticalBox, BorderLayout.PAGE_START);



//            Sending the Message to the person sitting next in the Chat
            dataOutputStream.writeUTF(text);


//            after sending the message set the Text Field as Empty
            jTextField.setText("");


//            reloading the frame
            jFrame.repaint();
            jFrame.invalidate();
            jFrame.validate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



//    Drive Main
    public static void main(String[] args){
        new Server();

//        Socket Connection
        try {
            ServerSocket serverSocket = new ServerSocket(3501);
//            accepting all the messages
            while(true) {
                Socket socket = serverSocket.accept();

//                I/O operation on the Socket
//                1. Send the message-Out
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
//                2. Receiving the message-In
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                while(true) {
//                    when we receive a message form the other side (2nd Person)
                    String messageReceived = dataInputStream.readUTF();

//                    adding the received-message to the chat
                    JPanel messageReceived_jpanel = FormatLabel(messageReceived); // messageFormat
                    JPanel left_jpanel = new JPanel(new BorderLayout());
                    left_jpanel.add(messageReceived_jpanel, BorderLayout.LINE_START);
                    verticalBox.add(left_jpanel);
//                    reloading the frame
                    jFrame.validate();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

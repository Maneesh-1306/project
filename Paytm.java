package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame {

    // Default constructor
    public Paytm() {
        // Simple message in case no booking details are passed
        JLabel messageLabel = new JLabel("No booking details available");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageLabel.setBounds(200, 200, 400, 50);
        add(messageLabel);
        
        setSize(800, 600);
        setLocation(600, 220);
        setVisible(true);
    }

    // Constructor with arguments to show the booking details
    public Paytm(String username, String hotel, int persons, int days, String ac, String food, String totalAmount) {
        // Web page panel to display Paytm page
        JEditorPane j = new JEditorPane();
        j.setEditable(false);   

        // Try to load the Paytm page for electricity bill payment
        try {
            String paymentUrl = "https://paytm.com/electricity-bill-payment";
            j.setPage(paymentUrl); // You can change this to a custom URL if needed
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load the Paytm page.</html>");
        } 

        // Scroll pane for the web page content
        JScrollPane scrollPane = new JScrollPane(j);     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(scrollPane);
        setPreferredSize(new Dimension(800, 600));

        // Back button to return to the previous screen
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        back.setBounds(610, 20, 80, 40);
        j.add(back);

        // Displaying booking details like hotel, persons, and price
        JTextArea details = new JTextArea();
        details.setEditable(false);
        details.setText("Booking Details:\n\n" +
                "Username: " + username + "\n" +
                "Hotel: " + hotel + "\n" +
                "Total Persons: " + persons + "\n" +
                "Number of Days: " + days + "\n" +
                "AC/Non-AC: " + ac + "\n" +
                "Food Included: " + food + "\n" +
                "Total Price: " + totalAmount + "\n\n" +
                "Proceed to Paytm for Payment");
        details.setBounds(30, 50, 700, 200);
        details.setFont(new Font("Arial", Font.PLAIN, 16));
        details.setBackground(Color.LIGHT_GRAY);
        details.setForeground(Color.BLACK);
        getContentPane().add(details);
        
        // Set the size of the frame
        setSize(800, 600);
        setLocation(600, 220);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Default constructor for testing
        new Paytm().setVisible(true);
    }
}

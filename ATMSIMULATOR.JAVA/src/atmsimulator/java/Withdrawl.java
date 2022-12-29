
package atmsimulator.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import java.util.*;


public class Withdrawl extends JFrame implements ActionListener{
    JButton withdraw,back;
    JTextField amount;
    String pinno;
    
    Withdrawl( String pinno){
        this.pinno=pinno;
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 =i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        JLabel text =new JLabel("ENTER THE AMOUNT YOU WANT TO WITHDRAW");
        text.setForeground(Color.WHITE);
        text.setFont(new Font ("Raleway",Font.BOLD,14));
        text.setBounds(170,300,400,20);
        image.add(text);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(170,350,320,25);
        image.add(amount);
        
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 485, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);
        
        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        
       if(ae.getSource() == back)
        {
            setVisible(false);
            new Transaction(pinno).setVisible(true);
        }else {
            String number =  amount.getText();
            conn c = new conn();
            try{
                ResultSet rs = c.s.executeQuery("Select * from bank where pin = '"+pinno+"'");
                int balance= 0;
                while(rs.next()){
                    if(rs.getString("type").equals("Deposite")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                    
                }
                if(ae.getSource() != back && balance < Integer.parseInt(number)){
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                
                Date date = new Date();
                String query="insert into bank values('"+pinno+"','"+date+"','Withdrawl','"+number+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Debite Sucessfully");
                
                setVisible(false);
                new Transaction(pinno).setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    public static void main(String args[]) {
        new Withdrawl("");
        
    }}

   

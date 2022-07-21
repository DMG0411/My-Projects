import com.sun.tools.javac.Main;

import javax.naming.spi.DirStateFactory;
import javax.swing.*;
import javax.swing.text.AsyncBoxView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class NewAd extends JFrame implements ActionListener{
    Choice brand_choose,gastype1,transm1;
    JLabel brand,model,yearoffab,km,transm,gastype,capacity,color;
    JTextField model1,yearoffab1,km1,capacity1,color1;
    JButton publish,cancel;
    JCheckBox check;
    private enum Actions{
        PUBLISH,
        CANCEL
    }
    NewAd(){
        setLayout(null);
        getContentPane().setBackground(Color.pink);

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("images/sellcar.jpg"));
        JLabel largephoto = new JLabel(image1);
        largephoto.setBounds(650,100,300,300);
        add(largephoto);

        ImageIcon image2 = new ImageIcon(ClassLoader.getSystemResource("images/MarketAuto.jpg"));
        JLabel miniature = new JLabel(image2);
        miniature.setBounds(0,0,82,60);
        add(miniature);

        JLabel midtext = new JLabel("Create your own ad");
        midtext.setBounds(300,20,300,30);
        midtext.setFont(new Font("SansSerif",Font.BOLD,30));
        add(midtext);

        brand = new JLabel("Brand:");
        brand.setBounds(260,85,60,30);
        brand.setFont(new Font("serif",3,20));
        add(brand);

        model = new JLabel("Model:");
        model.setBounds(260,115,100,30);
        model.setFont(new Font("serif",3,20));
        add(model);

        yearoffab = new JLabel("Year of Fabrication:");
        yearoffab.setBounds(260,150,160,30);
        yearoffab.setFont(new Font("serif",3,17));
        add(yearoffab);

        km = new JLabel("Mileage(in km):");
        km.setBounds(260,180,140,30);
        km.setFont(new Font("serif",3,18));
        add(km);

        transm = new JLabel("Transmission:");
        transm.setBounds(260,210,110,30);
        transm.setFont(new Font("serif",3,18));
        add(transm);

        gastype = new JLabel("GasType:");
        gastype.setBounds(260,240,75,30);
        gastype.setFont(new Font("serif",3,18));
        add(gastype);

        capacity = new JLabel("Engine capacity(in cm³):");
        capacity.setBounds(260,270,180,30);
        capacity.setFont(new Font("serfi",3,16));
        add(capacity);

        color = new JLabel("Color:");
        color.setBounds(260,300,140,30);
        color.setFont(new Font("serif",3,18));
        add(color);

        JLabel checktxt = new JLabel("Agree to Terms and Conditions");
        checktxt.setBounds(260,340,200,30);
        checktxt.setFont(new Font("serif",3,15));
        add(checktxt);

        //// Input textboxes

        brand_choose = new Choice();
        brand_choose.setBounds(330,91,120,25);
        brand_choose.add("Audi");brand_choose.add("BMW");brand_choose.add("Chevrolet");brand_choose.add("Citroen");
        brand_choose.add("Dacia");brand_choose.add("Ford");brand_choose.add("Honda");brand_choose.add("Hyundai");
        brand_choose.add("Kia");brand_choose.add("Mazda");brand_choose.add("Mercedes-Benz");brand_choose.add("Mitsubishi");
        brand_choose.add("Nissan");brand_choose.add("Opel");brand_choose.add("Renault");brand_choose.add("Seat");
        brand_choose.add("Skoda");brand_choose.add("Toyota");brand_choose.add("Volkswagen");brand_choose.add("Volvo");
        add(brand_choose);

        model1 = new JTextField();
        model1.setBounds(330,120,130,25);
        add(model1);

        yearoffab1 = new JTextField();
        yearoffab1.setBounds(410,154,130,25);
        add(yearoffab1);

        km1 = new JTextField();
        km1.setBounds(385,186,130,25);
        add(km1);

        transm1 = new Choice();
        transm1.setBounds(370,216,140,25);
        transm1.add("Manual");transm1.add("Automatic");
        add(transm1);

        gastype1 = new Choice();
        gastype1.setBounds(335,245,130,25);
        gastype1.add("Diesel");gastype1.add("Gasoline");gastype1.add("Hybrid");gastype1.add("Electric");
        add(gastype1);

        capacity1 = new JTextField();
        capacity1.setBounds(445,275,130,25);
        add(capacity1);

        color1 = new JTextField();
        color1.setBounds(315,305,130,25);
        add(color1);

        check = new JCheckBox();
        check.setBounds(460,349,20,14);
        check.setBackground(Color.pink);
        add(check);

        publish = new JButton("Publish");
        publish.setBounds(300,400,100,30);
        publish.addActionListener(this);
        publish.setActionCommand(Actions.PUBLISH.name());
        publish.setForeground(Color.white);
        publish.setBackground(Color.black);
        add(publish);

        cancel = new JButton("Cancel");
        cancel.setBounds(450,400,100,30);
        cancel.addActionListener(this);
        cancel.setActionCommand(Actions.CANCEL.name());
        cancel.setForeground(Color.white);
        cancel.setBackground(Color.black);
        add(cancel);

        setBounds(150,30,1015,700);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals(Actions.CANCEL.name())){
            setVisible(false);
            new MainPage();
        }
        else if(ae.getActionCommand().equals(Actions.PUBLISH.name()))
        {
            if(model1.getText().length() == 0 || yearoffab1.getText().length() == 0 || km1.getText().length() == 0
                    || capacity1.getText().length() == 0 || color1.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"All fields must be completed.");
            }
            else if(!check.isSelected()){
                JOptionPane.showMessageDialog(null,"Please accept Terms and Conditions.");
            }
            else{
            String pin = JOptionPane.showInputDialog(null,"To confirm this action please enter your PIN(1/2)");
            String pass = JOptionPane.showInputDialog(null,"To confirm this action please enter your password(2/2)");
            if(pin == null || pass == null){
                JOptionPane.showMessageDialog(null,"Don't leave empty spaces.");
            }
            else if(pin.length() == 4 && pass.length() >= 8){
            try{
                SQLConnection conn = new SQLConnection();
                String query = "select * from login where pin = '"+pin+"' and password = '"+pass+"'";
                ResultSet rs= conn.st.executeQuery(query);
                if(rs.next()){
                    String query1 = "insert into carads(pin, password, brand, model, yearoffab, km, transmission, gastype, capacity, color)" +
                            " values('"+pin+"', '"+pass+"', '"+(brand_choose.getSelectedItem())+"', '"+model1.getText()+
                            "', '"+yearoffab1.getText()+"', '"+km1.getText()+"', '" +transm1.getSelectedItem()+"', '"+
                            (gastype1.getSelectedItem())+"', '"+capacity1.getText()+"', '"+color1.getText()+"');";
                    int x = conn.st.executeUpdate(query1);
                    if(x == 1){
                    JOptionPane.showMessageDialog(null,"Your ad has been created.");
                    setVisible(false);
                    new MainPage();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"PIN doesn't match with password");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        }
        }
    }
    public static void main(String[] args) {
        new NewAd();
    }
}



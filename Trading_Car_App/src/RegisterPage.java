import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class RegisterPage extends JFrame implements ActionListener {
    private enum Actions {
        backLOGIN,
        EXIT,
        REGISTER,
    }
    JTextField phonenum, username, e_mail, realname, pinn,pinn1;
    JPasswordField password,password1;
    JCheckBox check;
    RegisterPage(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        ImageIcon image2 = new ImageIcon(ClassLoader.getSystemResource("images/MarketAuto.jpg"));
        JLabel miniature = new JLabel(image2);
        miniature.setBounds(0,0,82,60);
        add(miniature);

        //// Middle text
        JLabel welc_msg = new JLabel("Welcome to Register Page!");
        welc_msg.setBounds(130,20,400,50);
        welc_msg.setFont(new Font("SansSerif",Font.BOLD,30));
        add(welc_msg);
        //// Labels
        JLabel user = new JLabel("Username:");
        user.setBounds(65,80,100,30);
        user.setFont(new Font("serif",3,20));
        add(user);

        JLabel pass = new JLabel("Password:");
        pass.setBounds(65,110,100,30);
        pass.setFont(new Font("serif",3,20));
        add(pass);

        JLabel minpass = new JLabel("*At least 8 chars");
        minpass.setBounds(50,135,180,20);
        minpass.setFont(new Font("serif",3,13));
        add(minpass);

        JLabel pass1 = new JLabel("Re-type password:");
        pass1.setBounds(20,150,140,30);
        pass1.setFont(new Font("serif",3,18));
        add(pass1);

        JLabel name = new JLabel("Real name:");
        name.setBounds(60,180,100,30);
        name.setFont(new Font("serif",3,20));
        add(name);

        JLabel mail = new JLabel("E-mail address:");
        mail.setBounds(40,210,140,30);
        mail.setFont(new Font("serif",3,18));
        add(mail);

        JLabel phone = new JLabel("Phone number:");
        phone.setBounds(45,240,140,30);
        phone.setFont(new Font("serif",3,18));
        add(phone);

        JLabel pin = new JLabel("PIN:");
        pin.setBounds(310, 83,70,25);
        pin.setFont(new Font("serif",3,15));
        add(pin);

        JLabel cpin = new JLabel("Confirm PIN:");
        cpin.setBounds(310, 115,100,25);
        cpin.setFont(new Font("serif",3,15));
        add(cpin);

        JLabel checktxt = new JLabel("Agree to Terms and Conditions");
        checktxt.setBounds(60,270,200,30);
        checktxt.setFont(new Font("serif",3,15));
        add(checktxt);

        //// Input textboxes

        username = new JTextField();
        username.setBounds(167,85,130,25);
        add(username);

        password = new JPasswordField();
        password.setBounds(167,115,130,25);
        add(password);

        password1 = new JPasswordField();
        password1.setBounds(167,154,130,25);
        add(password1);

        realname = new JTextField();
        realname.setBounds(167,186,130,25);
        add(realname);

        e_mail = new JTextField();
        e_mail.setBounds(167,216,130,25);
        add(e_mail);

        phonenum = new JTextField();
        phonenum.setBounds(167,245,130,25);
        add(phonenum);

        pinn = new JTextField();
        pinn.setBounds(345,87,40,20);
        add(pinn);

        pinn1 = new JTextField();
        pinn1.setBounds(400,118,40,20);
        add(pinn1);

        JLabel passtext = new JLabel("*Characters can be");
        passtext.setBounds(310,135,120,20);
        passtext.setFont(new Font("serif",3,13));
        add(passtext);

        JLabel passtext1 = new JLabel("only alphanumerical");
        passtext1.setBounds(310,150,120,20);
        passtext1.setFont(new Font("serif",3,13));
        add(passtext1);
        /////CheckBox

        check = new JCheckBox();
        check.setBounds(260,275,20,20);
        add(check);
        /////Buttons
        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(300, 275,120,30);
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        backButton.setActionCommand(Actions.backLOGIN.name());
        backButton.setFont(new Font("SansSerif",Font.BOLD,12));
        add(backButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(430, 275,120,30);
        registerButton.setBackground(Color.black);
        registerButton.setForeground(Color.white);
        registerButton.addActionListener(this);
        registerButton.setActionCommand(Actions.REGISTER.name());
        registerButton.setFont(new Font("SansSerif",Font.BOLD,12));
        add(registerButton);

        JButton cancelButton = new JButton("Exit");
        cancelButton.setBounds(560, 275,120,30);
        cancelButton.setBackground(Color.black);
        cancelButton.setForeground(Color.white);
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand(Actions.EXIT.name());
        cancelButton.setFont(new Font("SansSerif",Font.BOLD,12));
        add(cancelButton);

        ImageIcon avatar = new ImageIcon(ClassLoader.getSystemResource("images/car_avatar.jpg"));
        ImageIcon avatarSized = new ImageIcon(avatar.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
        JLabel avatarlabel = new JLabel(avatarSized);
        avatarlabel.setBounds(450,70,200,200);
        add(avatarlabel);


        setBounds(400,150,700,350);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals(Actions.EXIT.name()) ){
            setVisible(false);
        }
        else if(ae.getActionCommand().equals(Actions.backLOGIN.name())){
            setVisible(false);
            new LoginPage();
        }
        else if(ae.getActionCommand().equals(Actions.REGISTER.name())){
            String user = username.getText();
            String pass = password.getText();
            String cpass = password1.getText();
            String real = realname.getText();
            String mail = e_mail.getText();
            int n = mail.length()-1;
            String phone = phonenum.getText();
            String pin = pinn.getText();
            String cpin = pinn1.getText();
            try{
                SQLConnection c = new SQLConnection();
                String query = "select * from login where username = '" + user +"'";
                ResultSet rs = c.st.executeQuery(query);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"The username is already taken!Try a different one.");
                }
                else{
                    String query1 = "select * from login where PIN = '" + pin +"'";
                    ResultSet rs1 = c.st.executeQuery(query1);
                    if(rs1.next()){
                        JOptionPane.showMessageDialog(null,"PIN already taken!Try a different one.");
                    }
                }
                if(user.length() == 0 || pass.length() == 0 || cpass.length() == 0 || real.length() == 0 || mail.length() == 0 ||
                        phone.length() == 0 || pin.length() == 0 || cpin.length() == 0){
                    JOptionPane.showMessageDialog(null, "All fields must be completed!");
                }
                else if(pass.length() < 8){
                    JOptionPane.showMessageDialog(null,"The password has less then 8 characters.");
                }
                else if(!pass.equals(cpass)){
                    JOptionPane.showMessageDialog(null,"Passwords don't match each other.");
                }
                else if(!pin.equals(cpin)){
                    JOptionPane.showMessageDialog(null,"PINs don't match each other.");
                }
                else if(pin.length() != 4){
                    JOptionPane.showMessageDialog(null,"PIN must have exactly 4 digits.");
                }
                else if(mail.charAt(n) != 'm' || mail.charAt(n-1) != 'o' || mail.charAt(n-2) != 'c' || mail.indexOf('@') == -1){
                    JOptionPane.showMessageDialog(null,"Please enter a valid e-mail address.");
                }
                else if(phone.length() < 4 || phone.length() > 19){
                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
                }
                else if(!check.isSelected()){
                    JOptionPane.showMessageDialog(null,"Please accept to our Terms and Conditions.");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Register was succesfully, going back to login page.");
                    String query1 = "insert into login(username, password, realname, e_mail, phone, pin) values('"+user+"', '"+pass+"', '"+real+"', '"+mail+"', '"+phone + "', '"+pin +"')";
                    c.st.executeUpdate(query1);
                    setVisible(false);
                    new LoginPage();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new RegisterPage();
    }
}



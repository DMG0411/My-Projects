import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener{
    private enum Actions{
        LOGIN,
        EXIT,
        REGISTER
    }
    JPasswordField pass;
    JTextField user_input;
    JLabel user_name,password,not_acc,avatarlabel,sign_msg;
    JButton Login,Cancel,Register;
    public LoginPage() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        ImageIcon image2 = new ImageIcon(ClassLoader.getSystemResource("images/MarketAuto.jpg"));
        JLabel miniature = new JLabel(image2);
        miniature.setBounds(0, 0, 82, 60);
        add(miniature);

        //////// Middle text
        sign_msg = new JLabel("Welcome to Login page!");
        sign_msg.setBounds(180, 5, 500, 100);
        sign_msg.setFont(new Font("SansSerif", Font.BOLD, 30));
        add(sign_msg);

        //////// User label
        user_name = new JLabel("Username:");
        user_name.setBounds(65, 80, 100, 30);
        user_name.setFont(new Font("serif", 3, 20));
        add(user_name);

        user_input = new JTextField();
        user_input.setBounds(160, 85, 150, 25);
        add(user_input);
        //////// Password label
        password = new JLabel("Password:");
        password.setBounds(70, 120, 100, 30);
        password.setFont(new Font("serif", 3, 20));
        add(password);

        pass = new JPasswordField();
        pass.setBounds(160, 125, 150, 25);
        pass.setFont(new Font("serif", 3, 20));
        add(pass);
        //////// Register label
        not_acc = new JLabel("Don't have an account?");
        not_acc.setBounds(30, 250, 180, 20);
        not_acc.setFont(new Font("serif", 3, 16));
        add(not_acc);
        ///////// Buttons
        Login = new JButton("Log in");
        Login.setBounds(65, 200, 100, 30);
        Login.addActionListener(this);
        Login.setActionCommand(Actions.LOGIN.name());
        Login.setBackground(Color.black);
        Login.setForeground(Color.white);
        Login.setFont(new Font("SansSerif", 3, 18));
        add(Login);

        Cancel = new JButton("Exit");
        Cancel.setBounds(200, 200, 100, 30);
        Cancel.setBackground(Color.black);
        Cancel.setForeground(Color.white);
        Cancel.addActionListener(this);
        Cancel.setActionCommand(Actions.EXIT.name());
        Cancel.setFont(new Font("SansSerif", 3, 18));
        add(Cancel);

        Register = new JButton("Register now!");
        Register.setBounds(200, 244, 150, 30);
        Register.setBackground(Color.black);
        Register.setForeground(Color.white);
        Register.addActionListener(this);
        Register.setActionCommand(Actions.REGISTER.name());
        Register.setFont(new Font("SansSerif", 3, 17));
        add(Register);
        //////// Image
        ImageIcon avatar = new ImageIcon(ClassLoader.getSystemResource("images/avatar.jpg"));
        ImageIcon avatarSized = new ImageIcon(avatar.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        avatarlabel = new JLabel(avatarSized);
        avatarlabel.setBounds(400, 100, 200, 200);
        add(avatarlabel);

        setBounds(400, 150, 700, 350);
        setVisible(true);
    }
        public void actionPerformed (ActionEvent ae){
            String user = user_input.getText();
            String passw = pass.getText();
            if (ae.getActionCommand().equals(Actions.EXIT.name())) {
                setVisible(false);
            } else if (ae.getActionCommand().equals(Actions.REGISTER.name())) {
                setVisible(false);
                new RegisterPage();
            } else if (ae.getActionCommand().equals(Actions.LOGIN.name())) {
                try {

                    SQLConnection c = new SQLConnection();
                    String query = "select * from login where username = '" + user + "' and password = '" + passw + "'";
                    ResultSet rs = c.st.executeQuery(query);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Welcome back!");
                        setVisible(false);
                        new MainPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password! Or non-existing account!");
                        JOptionPane.showMessageDialog(null, "Try again or create a new account!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    public static void main(String[] args)  {
            new LoginPage();
    }
}

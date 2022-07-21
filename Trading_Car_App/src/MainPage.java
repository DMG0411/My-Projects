import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MainPage extends JFrame implements ActionListener {
    public enum Actions{
        ADD,
        SEE,
        DISC,
        DELETE,
        SUPPORT,
        SEARCH
    }
    ResultSet rs1,rsz,rs3;
    JTable table1,table2;
    JTextField SearchBar;
    JButton Search;
    MainPage(){
        //SearchBar.addKeyListener(this);
        setLayout(null);
        getContentPane().setBackground(Color.pink);

        ImageIcon image2 = new ImageIcon(ClassLoader.getSystemResource("images/MarketAuto.jpg"));
        JLabel miniature = new JLabel(image2);
        miniature.setBounds(0,0,82,60);
        add(miniature);
    ///// Label

        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("images/MainPage.jpg"));
        ImageIcon backgroundSized = new ImageIcon(background.getImage().getScaledInstance(1000,700,Image.SCALE_DEFAULT));
        JLabel backgroundlabel = new JLabel(backgroundSized);
        backgroundlabel.setBounds(0,0,1000,700);
        add(backgroundlabel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(880,0,120,30);
        backgroundlabel.add(menuBar);

        JMenu Account = new JMenu("Account");
        Account.setPreferredSize(new Dimension(60,30));
        menuBar.add(Account);

        JMenu Supp = new JMenu("       ?");
        Supp.setPreferredSize(new Dimension(60,30));
        menuBar.add(Supp);

        JMenuItem Support = new JMenuItem("Support");
        Support.addActionListener(this);
        Support.setActionCommand(Actions.SUPPORT.name());
        Support.setPreferredSize(new Dimension(57,30));
        Supp.add(Support);

        JMenuItem Add = new JMenuItem("Add a new ad");
        Add.addActionListener(this);
        Add.setActionCommand(Actions.ADD.name());
        Account.add(Add);
        JMenuItem See = new JMenuItem("See your ads");
        See.addActionListener(this);
        See.setActionCommand(Actions.SEE.name());
        Account.add(See);

        JMenuItem Delete = new JMenuItem(("Delete Account"));
        Delete.addActionListener(this);
        Delete.setActionCommand(Actions.DELETE.name());
        Account.add(Delete);

        JMenuItem Disc = new JMenuItem("Disconnect");
        Disc.addActionListener(this);
        Disc.setActionCommand(Actions.DISC.name());
        Account.add(Disc);
/////////////
        JLabel brand = new JLabel("Brand");
        brand.setBounds(160,170,80,30);
        brand.setFont(new Font("serif",3,18));
        backgroundlabel.add(brand);

        JLabel model = new JLabel("Model");
        model.setBounds(250,170,80,30);
        model.setFont(new Font("serif",3,18));
        backgroundlabel.add(model);

        JLabel year = new JLabel("Year");
        year.setBounds(340,155,100,30);
        year.setFont(new Font("serif",3,15));
        backgroundlabel.add(year);

        JLabel offab = new JLabel("of fabrication");
        offab.setBounds(325,170,100,30);
        offab.setFont(new Font("serif",3,13));
        backgroundlabel.add(offab);

        JLabel km = new JLabel("Km");
        km.setBounds(430,170,100,30);
        km.setFont(new Font("serif",3,18));
        backgroundlabel.add(km);

        JLabel transm = new JLabel("Transmission");
        transm.setBounds(500,170,100,30);
        transm.setFont(new Font("serif",3,13));
        backgroundlabel.add(transm);

        JLabel gas = new JLabel("Gas");
        gas.setBounds(600,170,100,30);
        gas.setFont(new Font("serif",3,18));
        backgroundlabel.add(gas);

        JLabel engine = new JLabel("Engine");
        engine.setBounds(690,155,100,30);
        engine.setFont(new Font("serif",3,16));
        backgroundlabel.add(engine);

        JLabel capacity = new JLabel("capacity");
        capacity.setBounds(685,170,100,30);
        capacity.setFont(new Font("serif",3,14));
        backgroundlabel.add(capacity);

        JLabel color = new JLabel("Color");
        color.setBounds(775,170,100,30);
        color.setFont(new Font("serif",3,18));
        backgroundlabel.add(color);

        table1 = new JTable();
        table1.setBounds(150,200,700,400);
        backgroundlabel.add(table1);

        JLabel SearchText = new JLabel("Search for cars");
        SearchText.setBounds(150,90,200,30);
        SearchText.setFont(new Font("Sanserif",Font.BOLD,22));
        backgroundlabel.add(SearchText);

        SearchBar = new JTextField();
        SearchBar.setBounds(350,90,400,25);
        backgroundlabel.add(SearchBar);

        Search = new JButton("Search");
        Search.setBounds(750,90,80,25);
        Search.addActionListener(this);
        Search.setActionCommand(Actions.SEARCH.name());
        backgroundlabel.add(Search);

        try{
            SQLConnection conn = new SQLConnection();
            String query1 = ("select brand,model,yearoffab,km,transmission,gastype,capacity,color from carads");
            rs1 = conn.st.executeQuery(query1);
            table1.setModel(DbUtils.resultSetToTableModel(rs1));
        }catch(Exception e){
            e.printStackTrace();
        }

        setBounds(150,30,1015,700);
        setVisible(true);
    }
        public void actionPerformed (ActionEvent ae){
            String pin = "";
            if(ae.getActionCommand().equals(Actions.SEARCH.name())) {
                String s = SearchBar.getText();
                if (s.length() == 0) {
                    try {
                        SQLConnection conn = new SQLConnection();

                            String querry = ("select brand,model,yearoffab,km,transmission,gastype,capacity,color from carads");
                            ResultSet resultquerry = conn.st.executeQuery(querry);
                            table1.setModel(DbUtils.resultSetToTableModel(resultquerry));
                        }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        SQLConnection conn = new SQLConnection();
                        String query = ("select brand,model,yearoffab,km,transmission,gastype,capacity,color from carads where brand ='" + s + "' or" +
                                " model ='" + s + "' or yearoffab = '" + s + "' or km = '" + s + "' or transmission ='" + s + "' or gastype = '" + s + "' or capacity ='" +
                                s + "' or color = '" + s + "'");
                        rsz = conn.st.executeQuery(query);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    table1.setModel(DbUtils.resultSetToTableModel(rsz));
                }
            }else if (ae.getActionCommand().equals(Actions.ADD.name())) {
                setVisible(false);
                new NewAd();
            } else if (ae.getActionCommand().equals(Actions.SEE.name())) {
                setVisible(false);
                new SeeYourAds();
            } else if (ae.getActionCommand().equals(Actions.DISC.name())) {
                JOptionPane.showMessageDialog(null, "Disconnected, going back to the login page!");
                setVisible(false);
                new LoginPage();
            } else if (ae.getActionCommand().equals(Actions.DELETE.name())) {
                String insertPin = JOptionPane.showInputDialog(null, "To confirm this action please enter your PIN. (1/2)", "WARNING", JOptionPane.WARNING_MESSAGE);
                String insertPass = JOptionPane.showInputDialog(null, "To confirm this action please enter your password. (2/2)", "WARNING", JOptionPane.WARNING_MESSAGE);
                if(insertPin.length() == 0 || insertPass.length() == 0){
                    JOptionPane.showMessageDialog(null,"All fields must be completed.");
                }
                else if (insertPin.length() == 4 && insertPass.length() >= 8) {
                    try {
                        SQLConnection c = new SQLConnection();
                        String query = "delete from login where pin = '" + insertPin + "' and password = '" + insertPass + "'";
                        int x = c.st.executeUpdate(query);
                        if (x == 0) {
                            JOptionPane.showMessageDialog(null, "PIN doesn't match with password.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Account deleted.");
                            setVisible(false);
                            new LoginPage();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try{
                        SQLConnection c1 = new SQLConnection();
                        String querry = "delete from carads where pin ='"+insertPin+"' and password ='"+insertPass+"'";
                        c1.st.executeUpdate(querry);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong PIN or password.Try again.");
                }

            } else if (ae.getActionCommand().equals(Actions.SUPPORT.name())) {
                JOptionPane.showMessageDialog(null, "For support send an email to: m2bobko@gmail.com");
            }
        }

    public static void main(String[] args) {
        new MainPage();
    }

}


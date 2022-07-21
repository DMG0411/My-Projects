import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class SeeYourAds extends JFrame implements ActionListener {

    private enum Actions{
        BACK
    }

    SeeYourAds(){
        setLayout(null);
        getContentPane().setBackground(Color.pink);

        String pin = JOptionPane.showInputDialog(null, "To perform this action please enter your PIN(1/2)");
        String pass = JOptionPane.showInputDialog(null, "To confirm this action please enter your password(2/2)");
        if(pin == null || pass == null || pin.length() == 0 || pass.length() == 0){
            JOptionPane.showMessageDialog(null,"Don't leave empty spaces. Returning to Main Page.");
            setVisible(false);
            new MainPage();
        }
        else{
            try {
                SQLConnection conn = new SQLConnection();
                String query = "select * from login where pin = '" + pin + "' and password = '" + pass + "'";
                ResultSet rs = conn.st.executeQuery(query);
                if (rs.next()) {
                    setBounds(150,30,1015,700);
                    setVisible(true);

                    JLabel midtext = new JLabel("Your posted ads");
                    midtext.setBounds(300,20,300,30);
                    midtext.setFont(new Font("SansSerif",Font.BOLD,30));
                    add(midtext);

                    ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("images/youradhere.jpg"));
                    JLabel largephoto = new JLabel(image1);
                    largephoto.setBounds(650,100,300,300);
                    add(largephoto);

                    ImageIcon image2 = new ImageIcon(ClassLoader.getSystemResource("images/MarketAuto.jpg"));
                    JLabel miniature = new JLabel(image2);
                    miniature.setBounds(0,0,82,60);
                    add(miniature);

                    JLabel brand = new JLabel("Brand");
                    brand.setBounds(100,75,80,30);
                    brand.setFont(new Font("serif",3,18));
                    add(brand);

                    JLabel model = new JLabel("Model");
                    model.setBounds(165,75,80,30);
                    model.setFont(new Font("serif",3,18));
                    add(model);

                    JLabel year = new JLabel(" Year");
                    year.setBounds(235,65,100,30);
                    year.setFont(new Font("serif",3,15));
                    add(year);

                    JLabel offab = new JLabel("of fabrication");
                    offab.setBounds(225,75,100,30);
                    offab.setFont(new Font("serif",3,12));
                    add(offab);

                    JLabel km = new JLabel("Km");
                    km.setBounds(300,75,100,30);
                    km.setFont(new Font("serif",3,18));
                    add(km);

                    JLabel transm = new JLabel("Transmission");
                    transm.setBounds(340,75,100,30);
                    transm.setFont(new Font("serif",3,13));
                    add(transm);

                    JLabel gas = new JLabel("Gas");
                    gas.setBounds(430,75,100,30);
                    gas.setFont(new Font("serif",3,18));
                    add(gas);

                    JLabel engine = new JLabel("Engine");
                    engine.setBounds(485,65,100,30);
                    engine.setFont(new Font("serif",3,16));
                    add(engine);

                    JLabel capacity = new JLabel("capacity");
                    capacity.setBounds(480,75,100,30);
                    capacity.setFont(new Font("serif",3,14));
                    add(capacity);

                    JLabel color = new JLabel("Color");
                    color.setBounds(540,75,100,30);
                    color.setFont(new Font("serif",3,18));
                    add(color);

                    JTable table = new JTable();
                    table.setBounds(100,100,500,400);
                    add(table);

                    JLabel tip = new JLabel("Tip:Delete your ad by left-clicking on it.");
                    tip.setBounds(200,500,300,30);
                    tip.setFont(new Font("serif",3,18));
                    add(tip);

                    JButton back = new JButton("Back to MainPage");
                    back.setBounds(750,600,200,30);
                    back.addActionListener(this);
                    back.setActionCommand(Actions.BACK.name());
                    back.setForeground(Color.white);
                    back.setBackground(Color.black);
                    add(back);

                    try{
                        String query1 = ("select brand,model,yearoffab,km,transmission,gastype,capacity,color from carads where pin = '"+pin+"'");
                        ResultSet rs1 = conn.st.executeQuery(query1);
                        table.setModel(DbUtils.resultSetToTableModel(rs1));
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int choose = JOptionPane.showConfirmDialog(null,"Are you sure that you want to delete this ad?",
                                    "WARNING",JOptionPane.YES_NO_OPTION);
                            if(choose == JOptionPane.YES_OPTION){
                                String km = JOptionPane.showInputDialog(null,"Write the kilometers for confimation.");
                                try{
                                String query2 = ("delete from carads where pin = '"+pin+"' and km = '"+km+"'");
                                int result = conn.st.executeUpdate(query2);
                                if(result == 1){
                                    JOptionPane.showMessageDialog(null,"Your ad has been delete succesfully.");
                                    JOptionPane.showMessageDialog(null,"The page will refresh.");
                                    setVisible(false);
                                    new SeeYourAds();
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"An error has occured.");
                                }
                                }catch (Exception e1){
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }else{
                    JOptionPane.showMessageDialog(null,"PIN doesn't match with password. Returning to Main Page...");
                    setVisible(false);
                    new MainPage();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals(Actions.BACK.name())){
            setVisible(false);
            new MainPage();
        }
    }
    public static void main(String[] args) {
        new SeeYourAds();
    }
}

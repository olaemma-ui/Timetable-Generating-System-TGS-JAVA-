import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database extends Model{

    Statement query = null;
    Connection con = null;
    Timer timer = null;
    int[] x = {250, 310, 370, 430, 490};
    int[] y = {120, 120, 120, 120, 120};
    int i = 0;
    JLabel label = new JLabel("CONNECTING !!!", SwingConstants.CENTER);
    JPanel load = new JPanel();
    void dbConnect () throws InterruptedException {
        label.setFont(new Font("MONOSPACE", Font.BOLD, 35));
        try{
            for (int j = 0; j < 5; j++) {
                x[j] -= 190;
            }
            load = new JPanel(){
                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    g.setColor(color.get("blue"));
                    g.fillOval(x[0], y[0], 40,40);
                    g.fillOval(x[1], y[1], 40,40);
                    g.fillOval(x[2], y[2], 40,40);
                    g.fillOval(x[3], y[3], 40,40);
                    g.fillOval(x[4], y[4], 40,40);
                }
            };
            timer = new Timer(50, e -> {
                if (y[i] == 150) {
                    y[i] = 120;
                }
                else if(y[i] >= 120){
                    y[i]+=10;
                }
                load.repaint();
                if (i >= 4)
                    i = 0;
                else i++;

            });
            timer.start();
            load.add(label);
            load.setBackground(color.get("white"));
            index.add(load);
            index.setSize(400, 200);
            index.setLocationRelativeTo(null);
            index.setUndecorated(true);
            index.setVisible(true);

		//Host: sql8.freesqldatabase.com
		//Database name: sql8507357
		//Database user: sql8507357
		//Database password: Y5kLC7Ge8r
		//Port number: 3306
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s:%d?user=%s&password=%s",  "localhost", 3306, "root", "");
            con = DriverManager.getConnection(url);
            query = con.createStatement();
            query.executeUpdate("use timetable");
            index.dispose();
            index.setVisible(false);
            timer.stop();
            if (!con.isClosed())
            new Controller();
        }catch (Exception ex){
            for (int j = 0; j < 5; j++) {
                y[j] = 120;
            }
            ex.printStackTrace();
            timer.stop();
            load.repaint();
            label.setText("Fail to Connect");
            label.setForeground(color.get("red"));
            load.add(label);
            Thread.sleep(2000);
            index.dispose();
        }
    }
    void connect (){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s:%d?user=%s&password=%s",  "localhost", 3306, "root", "");
            con = DriverManager.getConnection(url);
            query = con.createStatement();
            query.executeUpdate("use timetable");
        }catch (Exception ex){ex.printStackTrace();}
    }

    boolean dbAction(String act){
        boolean bool = false;
        try{
            bool = query.executeUpdate(act) == 1;
        }catch (Exception ex){ex.printStackTrace();}

        return  bool;
    }
    public Database() throws SQLException {}
}

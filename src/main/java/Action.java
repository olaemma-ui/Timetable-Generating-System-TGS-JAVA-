import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.*;

public class Action extends Model{

    HashMap<Integer, String> empty_err = new HashMap<>();
    HashMap<Integer, String> title_err = new HashMap<>();
    HashMap<Integer, String> num_err = new HashMap<>();
    HashMap<Integer, String> id_err = new HashMap<>();
    HashMap<Integer, String> mobile_err = new HashMap<>();

    String pattern = null;
    String value = null;
    Matcher match = null;
    Pattern regex = null;
    Timer timer = null;

    boolean validate(JTextField[] input){
        int i = 0;
        for (JTextField txt: input) {
            if (!txt.getText().equalsIgnoreCase("")) {
                if (txt.getName().equalsIgnoreCase("title")) {
                    value = txt.getText();
                    pattern = "[a-zA-Z0-9()\\s-_]*";
                    regex = Pattern.compile(pattern);
                    match = regex.matcher(value);
                    if (match.matches()) {
                        title_err.remove(i);
                        txt.setBorder(BorderFactory.createLineBorder(color.get("green"), 2));
                    } else {
                        title_err.put(i, "No Special characters");
                        txt.setBorder(BorderFactory.createLineBorder(color.get("red"), 2));
                    }
                }

                if (txt.getName().equalsIgnoreCase("number")) {
                    value = txt.getText();
                    pattern = "\\d*";
                    regex = Pattern.compile(pattern);
                    match = regex.matcher(value);
                    if (match.matches()) {
                        num_err.remove(i);
                        txt.setBorder(BorderFactory.createLineBorder(color.get("green"), 2));
                    } else {
                        num_err.put(i, "No Special characters");
                        txt.setBorder(BorderFactory.createLineBorder(color.get("red"), 2));
                    }
                }

                if (txt.getName().equalsIgnoreCase("id")) {
                    value = txt.getText();
                    pattern = "[a-zA-Z0-9\\s{1}_-]*";
                    regex = Pattern.compile(pattern);
                    match = regex.matcher(value);
                    if (match.matches()) {
                        id_err.remove(i);
                        txt.setBorder(BorderFactory.createLineBorder(color.get("green"), 2));
                    } else {
                        id_err.put(i,"No Special characters");
                        txt.setBorder(BorderFactory.createLineBorder(color.get("red"), 2));
                    }
                }

                if (txt.getName().equalsIgnoreCase("mobile")) {
                    value = txt.getText();
                    pattern = "[+0-9{4}][0-9{3}][0-9{4}]*";
                    regex = Pattern.compile(pattern);
                    match = regex.matcher(value);
                    if (match.matches()) {
                        mobile_err.remove(i);
                        txt.setBorder(BorderFactory.createLineBorder(color.get("green"), 2));
                    } else {
                        mobile_err.put(i, "No Special characters");
                        txt.setBorder(BorderFactory.createLineBorder(color.get("red"), 2));
                    }
                }
                empty_err.remove(i);
            }
            else {
                empty_err.put(i,"All fields are required !!");
                txt.setBorder(BorderFactory.createLineBorder(color.get("red"), 2));
            }
            i++;
        }
        boolean bool;
        if (title_err.size() == 0 && num_err.size() == 0 && id_err.size() == 0 && mobile_err.size() == 0 && empty_err.size() == 0) {
            bool = true;
        }else bool = false;
        return bool;
    }
    void clearTable(Object[][] data, JTable table){
        for (int k = 0; k < data.length; k++) {
            for (int l = 0; l < data[k].length; l++) {
                data[k][l] = null;
            }
        }
        table.repaint();
    }
    String id(){
        Random rand = new Random();
        int n1 = 10+rand.nextInt(98-10);
        int n2 = 100 + rand.nextInt(999-100);
        int low, up;
        StringBuilder lower = new StringBuilder();
        StringBuilder upper = new StringBuilder();

        for (int i = 0, j = 0; i < 2 || j < 3; i++, j++) {
            if (i < 2){
                low = 97+rand.nextInt(121-97);
                lower.append((char) low);
            }
            up = 66+rand.nextInt(90-66);
            upper.append((char) up);
        }

        return n1+ upper.toString() +n2+lower;
    }
    String uniqueID(String path){
        String id = "";
        String check = "";
        try{
            String dir = "C:/TGS/.timetable/.data/.files";
            File createFile = new File(dir);
            createFile.mkdirs();

            createFile = new File(dir+"/"+path);
            createFile.createNewFile();
            createFile.setWritable(true);

            FileInputStream file = new FileInputStream(dir+"/"+path);
            FileWriter write = new FileWriter(dir+"/"+path, true);

            int r = file.read();
            boolean bool = true;
            while(bool){
                bool = false;
                id = "TGS-"+id();
                while (r!=-1){
                    check+=((char)r);
                    if (r == 32){
                        if (check.equals(id)){
                            bool = true;
                        }
                        check = "";
                    }
                    r = file.read();
                }
            }
            write.append(id).append("\n");
            write.close();
            file.close();
        }catch(Exception ex){
            ex.printStackTrace();}
        return id;
    }
    void alert(JPanel page, Color color, String message, int[] pos){

        JPanel box = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                g.setColor(color);
                g.fillRect(0, 0, pos[2], pos[3]);
                g.setFont(new Font("monospace", Font.PLAIN, 20));
                g.setColor(Color.WHITE);
                g.drawString(message, 10, (pos[3]/2)+5);
            }
        };
        box.setBounds(pos[0], pos[1], pos[2], pos[3]);
        box.repaint();
        page.repaint();
        page.add(box);
        timer = new Timer(2000, e->{
            page.remove(box);
            page.repaint();
            timer.stop();
        });
        timer.start();

    }
    Action() throws SQLException {}
}

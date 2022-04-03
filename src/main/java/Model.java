import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

abstract class Model {
    CardLayout pages = new CardLayout();
    String[] courseLabel = {"Course Title", "Course Code", "Semester", "Special Hall", "Lecturer"};
    String[] lectLabel = {"Name : ", "Mobile : "};
    String[] roomLabel = {"Hall Name", "Total Seats", "Type"};
    String[] semSelect = {"1st Semester", "2nd Semester"};
    String[] levSelect = {"ND 1", "ND 2", "HND 1", "HND 2",};
    String[] type = {"CLASS","LAB"};
    String[] courseType = {};

    String[] courseCol = {"S/N", "Course Title ", "Course Code", "Level", "P/T", "Lecturer"};
    String[][] courseData = new String[50][6];

    String[] roomCol = {"S/N", "Hall Name", "Total Seat", "Type"};
    String[][] roomData = new String[50][5];

    String[] lectCol = {"S/N", "Name", "Mobile"};
    String[][] lectData = new String[50][3];

    String[] timeCol = {"Day/Time", "8:00 - 10:00", "10:00 - 12:00", "12:00 - 2:00", "2:00 - 4:00"};
    String[][] nd1Data = new String[5][5];
    String[][] nd2Data = new String[5][5];
    String[][] hnd1Data = new String[5][5];
    String[][] hnd2Data = new String[5][5];

    String[][] nd1DataRoom = new String[5][5];
    String[][] nd2DataRoom = new String[5][5];
    String[][] hnd1DataRoom = new String[5][5];
    String[][] hnd2DataRoom = new String[5][5];
    String[][][] Datas = {nd1Data, nd2Data, hnd1Data, hnd2Data};
    String[][][] RoomDatas = {nd1DataRoom, nd2DataRoom, hnd1DataRoom, hnd2DataRoom};

    JPanel courseTablePanel =  new JPanel(new CardLayout());
    JPanel[] timeTablePanel =  {
            new JPanel(new CardLayout()),
            new JPanel(new CardLayout()),
            new JPanel(new CardLayout()),
            new JPanel(new CardLayout())
    };


    JTable[] timeTable = {
        new JTable(nd1Data, timeCol),
        new JTable(nd2Data, timeCol),
        new JTable(hnd1Data, timeCol),
        new JTable(hnd2Data, timeCol)
    };
    JScrollPane[] timeScroll = {
        new JScrollPane(timeTable[0]),
        new JScrollPane(timeTable[1]),
        new JScrollPane(timeTable[2]),
        new JScrollPane(timeTable[3])
    };


    /**
     * Lecturers Component
     * */
    JTable lectTable = new JTable(lectData, lectCol);
    JPanel lectTablePanel =  new JPanel(new CardLayout());
    JScrollPane lectScroll = new JScrollPane(lectTable);
    JButton addLect= new JButton("Add");
    JTextField [] lectInput = {
        new JTextField(),
        new JTextField(),
    };
    JButton[] lectActionBtn = {
        new JButton("EDIT"),
        new JButton("UPDATE"),
        new JButton("DELETE"),
    };


    /**
     * ClassComponent
     * */
    JTable roomTable = new JTable(roomData, roomCol);
    JPanel roomTablePanel =  new JPanel(new CardLayout());
    JScrollPane roomScroll = new JScrollPane(roomTable);
    JButton addClass= new JButton("Add");
    JComboBox roomType = new JComboBox(type);
    JTextField [] roomInput = {
        new JTextField(),
        new JTextField(),
    };
    JButton[] roomActionBtn = {
        new JButton("EDIT"),
        new JButton("UPDATE"),
        new JButton("DELETE"),
    };


    /**
     * Courses Component
     * */
    JTable courseTable = new JTable(courseData, courseCol);
    JScrollPane courseScroll = new JScrollPane(courseTable);
    JTextField [] courseInput = {
        new JTextField(),
        new JTextField(),
    };
    JButton[] actionBtn = {
        new JButton("EDIT"),
        new JButton("UPDATE"),
        new JButton("DELETE"),
    };
    JButton addCourse = new JButton("ADD");
    JButton[] levelBtn = {
        new JButton("ND 1"),
        new JButton("ND 2"),
        new JButton("HND 1"),
        new JButton("HND 2"),
    };
    JButton generate = new JButton("Generate");

    JComboBox semester = new JComboBox(semSelect);
    JComboBox[] select = {
        new JComboBox(semSelect),
        new JComboBox(courseType),
        new JComboBox(),
    };

    JButton save = new JButton("Save");

    JComboBox[] timeTableSelect = {
        new JComboBox(semSelect),
        new JComboBox(levSelect),
    };

    JFrame index = new JFrame();
    JPanel content = new JPanel(null);
    JPanel sidenav = new JPanel(null);
    Container body = new Container();

    /**
     * Navbar
     * */
    JPanel navbar = new JPanel(null){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setFont(new Font("monospace", Font.BOLD, 25));
            g.setColor(color.get("white"));
            g.drawString("TGS", 10, 30);
        }
    };

    /**
     * Pages
     * */
    JPanel timetable = new JPanel(null){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < 2; i++) {
                g.setColor(color.get("blue"));
                g.fillRect(20, 60+(i*300), 1150,40);
            }
            String[] txt = {"ND 1", "ND 2", "HND 1", "HND 2"};
            g.setFont(new Font("monospace", Font.BOLD, 28));
            for (int i = 0; i < txt.length; i++) {
                if (i < 2) {
                    g.setColor(color.get("white"));
                    g.drawString(txt[i], 30 + (i * 580), 90);
                }
                else g.drawString(txt[i], 30+((i-2)*580), 390);

            }
        }
    };
    JPanel rooms = new JPanel(null){
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(color.get("blue"));
            g.fillRect(40, 70, 700, 50);

            g.setColor(color.get("white"));
            g.setFont(new Font("monospace", Font.BOLD, 28));
            g.drawString("Hall", 50, 105);

            g.setColor(color.get("blue"));
            g.fillRect(770, 70, 350, 50);
            g.drawRect(770, 110, 349,360);

            g.setColor(color.get("white"));
            g.setFont(new Font("monospace", Font.BOLD, 28));
            g.drawString("Add Hall", 800, 105);

            for (int i = 0; i < roomLabel.length; i++) {
                g.setColor(color.get("black"));
                g.setFont(new Font("monospace", Font.BOLD, 18));
                g.drawString(roomLabel[i], 780, 150+(i*100));
            }
        }
    };
    JPanel courses = new JPanel(null){
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(color.get("blue"));
            g.fillRect(40, 50, 700, 50);

            g.setColor(color.get("white"));
            g.setFont(new Font("monospace", Font.BOLD, 28));
            g.drawString("Courses", 70, 85);

            g.setColor(color.get("blue"));
            g.fillRect(770, 50, 350, 50);
            g.drawRect(770, 90, 349,480);

            g.setColor(color.get("white"));
            g.setFont(new Font("monospace", Font.BOLD, 28));
            g.drawString("Add Course", 800, 85);

            for (int i = 0; i < courseLabel.length; i++) {
                g.setColor(color.get("black"));
                g.setFont(new Font("monospace", Font.BOLD, 18));
                g.drawString(courseLabel[i], 780, 130+(i*80));
            }
        }
    };
    JPanel lecturers = new JPanel(null){
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(color.get("blue"));
            g.fillRect(40, 50, 700, 50);

            g.setColor(color.get("white"));
            g.setFont(new Font("monospace", Font.BOLD, 28));
            g.drawString("Lecturers", 50, 85);

            g.setColor(color.get("blue"));
            g.fillRect(770, 50, 350, 50);
            g.drawRect(770, 90, 349,260);

            g.setColor(color.get("white"));
            g.setFont(new Font("monospace", Font.BOLD, 28));
            g.drawString("Add Lecturers", 800, 85);
//
            for (int i = 0; i < lectLabel.length; i++) {
                g.setColor(color.get("black"));
                g.setFont(new Font("monospace", Font.BOLD, 18));
                g.drawString(lectLabel[i], 780, 130+(i*100));
            }
        }
    };


    /**
     * Side Button Arr
     * */
    JButton[] sidebtn = {
        new JButton("lecturers"),
        new JButton("Course"),
        new JButton("Hall"),
        new JButton("Timetable"),
    };


    /**
     * Colors Property
    * */
    HashMap<String, Color> color = new HashMap<>();
    Color[] colorArr = {
        new Color(87, 145, 245, 255),
        new Color(13, 156, 49, 255),
        new Color(246, 80, 5, 200)
    };

    Color[] colorTrans = {
        new Color(87, 145, 245, 100),
        new Color(1, 215, 47, 100),
        new Color(246, 80, 5, 100)
    };

    Model (){
        color.put("blue", new Color(58, 130, 246, 255));
        color.put("blue-1", new Color(87, 145, 245, 255));
        color.put("green", new Color(58, 246, 130, 200));
        color.put("red", new Color(246, 80, 5, 200));
        color.put("white", new Color(255, 255, 255, 255));
        color.put("black", new Color(0, 0, 0));
    }

    void active(JButton btn, JButton[] btnArr){
        for (JButton b : btnArr) {
            if (b == btn){
                pages.show(body, btn.getName());
                btn.setBackground(color.get("white"));
                btn.setForeground(color.get("blue"));
            }
            else{
                b.setBackground(color.get("blue"));
                b.setForeground(color.get("white"));
            }
        }
    }
}

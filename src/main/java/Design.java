import javax.swing.*;
import java.awt.*;

public class Design extends  Model {
    Design(){
        body.setLayout(pages);
        timetable.setBackground(color.get("white"));
        courses.setBackground(color.get("white"));
        lecturers.setBackground(color.get("white"));
        rooms.setBackground(color.get("white"));

        sidebtn[3].setName("Timetable");
        sidebtn[2].setName("Rooms");
        sidebtn[1].setName("Course");
        sidebtn[0].setName("lecturers");


        for (int i = 0; i < sidebtn.length; i++) {
            sidebtn[i].setBounds(5, 50+(i*80), 190, 60);
            sidebtn[i].setFont(new Font("monospace", Font.BOLD, 18));
            sidebtn[i].setForeground(color.get("white"));
            sidebtn[i].setBackground(color.get("blue"));
            sidebtn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            sidebtn[i].setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
            sidebtn[i].setFocusPainted(false);
            sidenav.add(sidebtn[i]);
        }
        for (int i = 0; i < courseInput.length; i++) {
            courseInput[i].setBounds(780, 140+(i*80), 320, 40);
            courseInput[i].setFont(new Font("monospace", Font.PLAIN, 17));
            courseInput[0].setName("title");
            courseInput[1].setName("id");

            roomInput[i].setBounds(780, 160+(i*100), 320, 40);
            roomInput[i].setFont(new Font("monospace", Font.PLAIN, 17));
            roomInput[0].setName("title");
            roomInput[1].setName("number");

            lectInput[i].setBounds(780, 140+(i*100), 320, 40);
            lectInput[i].setFont(new Font("monospace", Font.PLAIN, 17));
            lectInput[0].setName("title");
            lectInput[1].setName("number");

            addLect.setForeground(color.get("white"));
            addLect.setFont(new Font("monospace", Font.PLAIN, 15));
            addLect.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addLect.setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
            addLect.setFocusPainted(false);
            addLect.setBackground(colorArr[1]);
            addLect.setBounds(1000, 300, 100, 40);

            addCourse.setForeground(color.get("white"));
            addCourse.setFont(new Font("monospace", Font.PLAIN, 15));
            addCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addCourse.setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
            addCourse.setFocusPainted(false);
            addCourse.setBackground(colorArr[1]);
            addCourse.setBounds(1000, 520, 100, 40);

            lecturers.add(addLect);
            lecturers.add(lectInput[i]);
            courses.add(addCourse);
            courses.add(courseInput[i]);
            rooms.add(roomType);
            rooms.add(roomInput[i]);
        }
        for (int i = 0; i < actionBtn.length; i++) {
            actionBtn[i].setForeground(color.get("white"));
            actionBtn[i].setFont(new Font("monospace", Font.PLAIN, 15));
            actionBtn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            actionBtn[i].setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
            actionBtn[i].setFocusPainted(false);
            actionBtn[i].setBackground(colorArr[i]);
            actionBtn[i].setBounds(780+(i*120), 620, 100, 40);

            roomActionBtn[i].setForeground(color.get("white"));
            roomActionBtn[i].setFont(new Font("monospace", Font.PLAIN, 15));
            roomActionBtn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            roomActionBtn[i].setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
            roomActionBtn[i].setFocusPainted(false);
            roomActionBtn[i].setBackground(colorArr[i]);
            roomActionBtn[i].setBounds(780+(i*120), 520, 100, 40);

            lectActionBtn[i].setForeground(color.get("white"));
            lectActionBtn[i].setFont(new Font("monospace", Font.PLAIN, 15));
            lectActionBtn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            lectActionBtn[i].setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
            lectActionBtn[i].setFocusPainted(false);
            lectActionBtn[i].setBackground(colorArr[i]);
            lectActionBtn[i].setBounds(780+(i*120), 360, 100, 40);


            lecturers.add(lectActionBtn[i]);
            rooms.add(roomActionBtn[i]);
            courses.add(actionBtn[i]);
        }
        for (int i = 0; i < levelBtn.length; i++) {
            levelBtn[i].setBackground(color.get("blue"));
            levelBtn[i].setForeground(color.get("white"));
            levelBtn[i].setFont(new Font("monospace", Font.PLAIN, 15));
            levelBtn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            levelBtn[i].setBorder(BorderFactory.createLineBorder(color.get("blue-1"), 1));
            levelBtn[i].setFocusPainted(false);
            levelBtn[i].setBounds(40+(i*120), 110, 100, 40);
            courses.add(levelBtn[i]);
        }
        for (int i = 0; i < select.length; i++) {
            select[i].setBounds(780, 140+((i+2)*80), 320, 40);
            select[i].setBackground(color.get("white"));

            if (i<1) {
                timeTableSelect[i].setBounds(600+(220), 10, 200, 40);
                timeTableSelect[i].setBackground(color.get("white"));
                timetable.add(timeTableSelect[i]);
            }
            courses.add(select[i]);
        }
        courseTable.setRowSelectionAllowed(true);
        nd1Data[0][0] = "Monday";
        nd1Data[1][0] = "Tuesday";
        nd1Data[2][0] = "Wednesday";
        nd1Data[3][0] = "Thursday";
        nd1Data[4][0] = "Friday";

        nd2Data[0][0] = "Monday";
        nd2Data[1][0] = "Tuesday";
        nd2Data[2][0] = "Wednesday";
        nd2Data[3][0] = "Thursday";
        nd2Data[4][0] = "Friday";

        hnd2Data[0][0] = "Monday";
        hnd2Data[1][0] = "Tuesday";
        hnd2Data[2][0] = "Wednesday";
        hnd2Data[3][0] = "Thursday";
        hnd2Data[4][0] = "Friday";

        hnd1Data[0][0] = "Monday";
        hnd1Data[1][0] = "Tuesday";
        hnd1Data[2][0] = "Wednesday";
        hnd1Data[3][0] = "Thursday";
        hnd1Data[4][0] = "Friday";
        for (int i = 0; i < timeTablePanel.length; i++){
            if (i < 2)
                timeTablePanel[i].setBounds(20+(i*580), 100, 570, 240);
            else
                timeTablePanel[i].setBounds(20+((i-2)*580), 400, 570, 240);
            timeTablePanel[i].add(timeScroll[i]);
            timetable.add(timeTablePanel[i]);
        timeTable[i].setRowHeight(95);
        timeTable[i].setFont(new Font("monospace", Font.BOLD, 10));
        timeTable[i].getColumnModel().getColumn(0).setPreferredWidth(150);
        timeTable[i].getColumnModel().getColumn(1).setPreferredWidth(200);
        timeTable[i].getColumnModel().getColumn(2).setPreferredWidth(200);
        timeTable[i].getColumnModel().getColumn(3).setPreferredWidth(200);
        timeTable[i].getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        courseTable.setRowHeight(30);
        courseTable.setFont(new Font("monospace", Font.PLAIN, 14));
        courseTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        courseTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        courseTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        courseTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        courseTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        courseTable.getColumnModel().getColumn(5).setPreferredWidth(300);

        roomTablePanel.setBounds(40, 120, 700, 500);
        roomTablePanel.add(roomScroll);
        roomTable.setRowHeight(30);
        roomTable.setFont(new Font("monospace", Font.PLAIN, 14));
        roomTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        roomTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        roomTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        roomTable.getColumnModel().getColumn(3).setPreferredWidth(50);

        lectTablePanel.setBounds(40, 100, 700, 500);
        lectTablePanel.add(lectScroll);
        lectTable.setRowHeight(30);
        lectTable.setFont(new Font("monospace", Font.PLAIN, 14));
        lectTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        lectTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        lectTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        lecturers.add(lectTablePanel);

        courseTablePanel.setBounds(40, 155, 700, 500);
        courseTablePanel.add(courseScroll);

        roomType.setBorder(BorderFactory.createLineBorder(color.get("white"), 0));
        roomType.setBackground(color.get("white"));
        roomType.setBounds(780, 360, 320, 40);

        semester.setBorder(BorderFactory.createLineBorder(color.get("white"), 0));
        semester.setBackground(color.get("white"));
        semester.setBounds(530, 110, 100, 40);

        generate.setForeground(color.get("white"));
        generate.setFont(new Font("monospace", Font.PLAIN, 17));
        generate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        generate.setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
        generate.setFocusPainted(false);
        generate.setBackground(colorArr[1]);
        generate.setBounds(1040, 10, 100, 40);

        save.setForeground(color.get("white"));
        save.setFont(new Font("monospace", Font.PLAIN, 17));
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
        save.setFocusPainted(false);
        save.setBackground(colorArr[1]);
        save.setBounds(20, 10, 100, 40);
        timetable.add(save);


        addClass.setForeground(color.get("white"));
        addClass.setFont(new Font("monospace", Font.PLAIN, 17));
        addClass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addClass.setBorder(BorderFactory.createLineBorder(color.get("white"), 1));
        addClass.setFocusPainted(false);
        addClass.setBackground(colorArr[1]);
        addClass.setBounds(1000, 420, 100, 40);


        rooms.add(addClass);
        rooms.add(roomTablePanel);
        timetable.add(generate);
        courses.add(courseTablePanel);
        courses.add(semester);
        sidenav.setBackground(color.get("blue"));
        navbar.setBackground(color.get("blue"));

        navbar.setBounds(0,0, 2000, 44);
        sidenav.setBounds(0,45,180, 1000);

        body.add(lecturers, "lecturers");
        body.add(courses, "Course");
        body.add(rooms, "Rooms");
        body.add(timetable, "Timetable");
        body.setBounds(180, 45, 1400, 900);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

//        width = (int)size.getWidth();
//        height = (int)size.getHeight();
        index.setIconImage(new ImageIcon("src/Icon.png").getImage());
        content.add(body);
        content.add(navbar);
        content.add(sidenav);
        index.add(content);
        index.setSize(1380, 780);
        index.setResizable(false);
        index.setLocationRelativeTo(null);
        index.setVisible(true);
        index.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

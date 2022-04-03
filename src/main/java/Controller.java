import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Controller extends Design implements Methods {
    Action action = new Action();
    Database db = new Database();
    DocsGenerate docsGenerate = new DocsGenerate();
    LinkedList<String> course_id = new LinkedList<>();
    LinkedList<String> room_id = new LinkedList<>();
    LinkedList<String> lect_id = new LinkedList<>();
    LinkedList<String> nd1List = new LinkedList<>();
    LinkedList<String> nd2List = new LinkedList<>();
    LinkedList<String> hnd1List = new LinkedList<>();
    LinkedList<String> hnd2List = new LinkedList<>();
    LinkedList<String> roomList = new LinkedList<>();

    int[] _days = null;
    int[] _hours = null;
    Random rand = new Random();
    int k = 0;
    String cid = "";

    @Override
    public void listener(){
        generate.addActionListener(this);
        save.addActionListener(this);
        addLect.addActionListener(this);
        for (JButton jButton : sidebtn) {
            jButton.addActionListener(this);
        }
        for (JButton jButton : levelBtn) {
            jButton.addActionListener(this);
        }
        for (int i = 0; i < roomActionBtn.length; i++) {
            roomActionBtn[i].addActionListener(this);
            actionBtn[i].addActionListener(this);
            lectActionBtn[i].addActionListener(this);
        }
        addCourse.addActionListener(this);
        addClass.addActionListener(this);
        semester.addActionListener(this);
    }

    @Override
    public void select(String lev){
        course_id.clear();lect_id.clear();room_id.clear();nd1List.clear();nd2List.clear();hnd2List.clear();nd2List.clear();
        String select = "SELECT * FROM course WHERE level = '"+lev+"' AND  semester = '"+ Objects.requireNonNull(semester.getSelectedItem()) +"'";
        try{
            ResultSet result = db.query.executeQuery(select);
            int i = 0;
            while (result.next()){
                courseData[i][0] = ""+(++i)+"";
                --i;
                courseData[i][1] = result.getString("course_title");
                courseData[i][2] = result.getString("course_code");
                courseData[i][3] = result.getString("level");
                courseData[i][4] = result.getString("course_type");
                courseData[i][5] = result.getString("lecturer");
                course_id.add(result.getString("courseId"));
                i++;
            }
            i = 0;
            result.close();
            select = "SELECT * FROM room";
            result = db.query.executeQuery(select);
            super.select[1].removeAllItems();
            super.select[1].addItem(".....");
            while (result.next()){
                roomData[i][0] = ""+(++i)+"";
                --i;
                roomData[i][1] = result.getString("room");
                roomData[i][2] = result.getString("total_seat");
                roomData[i][3] = result.getString("room_type");
                room_id.add(result.getString("room_id"));
                roomList.add(result.getString("room"));
                if (result.getString("room_type").equalsIgnoreCase("LAB"))
                    super.select[1].addItem(result.getString("room"));
                i++;
            }


            i=0;
            result.close();
//            action.clearTable(lectData, lectTable);
            select = "SELECT * FROM lecturers";
            result = db.query.executeQuery(select);
            super.select[2].removeAllItems();
            while (result.next()){
                lectData[i][0] = ""+(i+1)+"";
                lectData[i][1] = result.getString("name");
                lectData[i][2] = result.getString("mobile");
                super.select[2].addItem(result.getString("name"));
                lect_id.add(result.getString("lectID"));
                i++;
            }

            select = "SELECT * FROM ((generate INNER JOIN lecturers on lecturers.lectID = generate.lectID) INNER JOIN course ON generate.courseID = course.courseID) WHERE generate.semester = '"+timeTableSelect[0].getSelectedItem().toString()+"'";
            result = db.query.executeQuery(select);
            while (result.next()) {
                String  li = result.getString("course_code")+"\n"+result.getString("name");
//                if (!result.getString("room").equalsIgnoreCase("....."))
//                    li+="\n RM: "+ result.getString("room");
                if (result.getString("level").equalsIgnoreCase("ND 1")) {
                    nd1List.add(li);
                    System.out.println(li);
                }
                if (result.getString("level").equalsIgnoreCase("ND 2")) {
                    nd2List.add(li);
                }
                if (result.getString("level").equalsIgnoreCase("HND 1")) {
                    hnd1List.add(li);
                }
                if (result.getString("level").equalsIgnoreCase("HND 2")) {
                    hnd2List.add(li);
                }
            }

        }catch (Exception ex){/***/
        ex.printStackTrace();
        }
        for (JComboBox s: super.select) {
            s.repaint();
        }
        roomTable.repaint();
        courseTable.repaint();
        lectTable.repaint();
    }

    @Override
    public void edit (String id, String table){
        try{
            String sel = "SELECT * FROM course WHERE course_id = '"+id+"'";
            ResultSet result = db.query.executeQuery(sel);
            if (table.equalsIgnoreCase("course")){
                while (result.next()){
                    courseInput[0].setText(result.getString("course_title"));
                    courseInput[1].setText(result.getString("course_code"));
                    select[0].setSelectedItem(result.getString("semester"));
                    select[1].setSelectedItem(result.getString("level"));
                    cid = result.getString("course_Id");
                }
            }

            if (table.equalsIgnoreCase("room")) {
                sel = "SELECT * FROM room WHERE room_id = '" + id + "'";
                result = db.query.executeQuery(sel);
                while (result.next()) {
                    roomInput[0].setText(result.getString("room"));
                    roomInput[1].setText(result.getString("total_seat"));
                    roomType.setSelectedItem(result.getString("room_type"));
                    cid = result.getString("room_Id");
                }
            }

            if (table.equalsIgnoreCase("lecturers")) {
                sel = "SELECT * FROM lecturers WHERE lectID = '" + id + "'";
                result = db.query.executeQuery(sel);
                while (result.next()) {
                    lectInput[0].setText(result.getString("name"));
                    lectInput[1].setText(result.getString("mobile"));
                    cid = result.getString("lectId");
                }
            }
        }catch (Exception ex){/***/}
    }

    @Override
    public boolean update(String table){
        boolean bool = false;
        select(levelBtn[k].getText());
        if (table.equalsIgnoreCase("course") && courseTable.getSelectedRow() >= 0) {
            cid = course_id.get(courseTable.getSelectedRow());
            if (!this.cid.equals("") && !courseInput[0].getText().equalsIgnoreCase("") && !courseInput[1].getText().equalsIgnoreCase("")) {
                String update =
                        "UPDATE course SET course_title = '" + courseInput[0].getText() + "', course_code = '" + courseInput[1].getText() + "', " +
                                "level = '" + levelBtn[k].getText() + "', semester = '" + select[0].getSelectedItem() + "' WHERE " +
                                "course_id = '" + cid + "'";

                if (db.dbAction(update)) {
                    bool = true;
                }
            }
        }

        if (table.equalsIgnoreCase("room") && roomTable.getSelectedRow() >= 0){
            cid = room_id.get(roomTable.getSelectedRow());
            if (!this.cid.equals("") && !roomInput[0].getText().equalsIgnoreCase("") && !roomInput[1].getText().equalsIgnoreCase("")) {
                String update =
                        "UPDATE room SET room = '" + roomInput[0].getText() + "', total_seat = '" + roomInput[1].getText() + "', " +
                                "room_type = '" + roomType.getSelectedItem() + "' WHERE " +
                                "room_id = '" + cid + "'";

                if (db.dbAction(update)) {
                    bool = true;
                }
            }
        }

        if (table.equalsIgnoreCase("lecturers") && lectTable.getSelectedRow() >= 0){
            cid = lect_id.get(lectTable.getSelectedRow());
            if (!this.cid.equals("") && !lectInput[0].getText().equalsIgnoreCase("") && !lectInput[1].getText().equalsIgnoreCase("")) {
                String update =
                        "UPDATE lecturers SET name = '" + lectInput[0].getText() + "', mobile = '" + lectInput[1].getText() +"' WHERE lectID = '" + cid + "'";

                if (db.dbAction(update)) {
                    bool = true;
                }
            }
        }
        return bool;
    }

    @Override
    public boolean delete(String table){
        boolean bool = false;
        select(levelBtn[k].getText());
        int response =
            JOptionPane.showOptionDialog(index,
            "Are you sure you want to Delete ?", "Delete ?",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null,
            new Object[] {"Yes", "No"},
            JOptionPane.YES_OPTION)
        ;

        if (response == JOptionPane.YES_OPTION) {
            if (table.equalsIgnoreCase("course")) {
                cid = course_id.get(courseTable.getSelectedRow());
                if (!this.cid.equals("")) {
                    String del = "DELETE FROM course WHERE course_id = '" + cid + "'";
                    if (db.dbAction(del)) {
                        bool = true;
                    }
                }
            }
            if (table.equalsIgnoreCase("room")){
                cid = room_id.get(roomTable.getSelectedRow());
                if (!this.cid.equals("")) {
                    String del = "DELETE FROM room WHERE room_id = '" + cid + "'";
                    if (db.dbAction(del)) {
                        bool = true;
                    }

                }
            }
        }

        if (table.equalsIgnoreCase("lecturers")){
            cid = lect_id.get(lectTable.getSelectedRow());
            if (!this.cid.equals("")) {
                String del = "DELETE FROM lecturers WHERE lectID = '" + cid + "'";
                if (db.dbAction(del)) {
                    bool = true;
                }

            }
        }
        return bool;
    }

    @Override
    public void generate() {
        for (int i = 0; i < timeTable.length; i++) {
            action.clearTable(Datas[i], timeTable[i]);
            timeTable[i].repaint( );
        }
        select(levelBtn[this.k].getText());
        LinkedList[] levels = new LinkedList[]{nd1List, nd2List, hnd1List, hnd2List};
        nd1Data[0][0] = "Monday";nd1Data[1][0] = "Tuesday";nd1Data[2][0] = "Wednesday";nd1Data[3][0] = "Thursday";nd1Data[4][0] = "Friday";
        nd2Data[0][0] = "Monday";nd2Data[1][0] = "Tuesday";nd2Data[2][0] = "Wednesday";nd2Data[3][0] = "Thursday";nd2Data[4][0] = "Friday";
        hnd2Data[0][0] = "Monday";hnd2Data[1][0] = "Tuesday";hnd2Data[2][0] = "Wednesday";hnd2Data[3][0] = "Thursday";hnd2Data[4][0] = "Friday";
        hnd1Data[0][0] = "Monday";hnd1Data[1][0] = "Tuesday";hnd1Data[2][0] = "Wednesday";hnd1Data[3][0] = "Thursday";        hnd1Data[4][0] = "Friday";
        timeTable[0].repaint();
        _days = new int[5];
        _hours = new int[4];
        int r = 0, d = 0, h = 0;
        int n = 0,  index = 0;

        for (LinkedList list: levels) {
            String lect = "";
            int loop = 0;
            while(!(n == list.size())) {
                loop:
                for (int i = 0; i < list.size(); i++) {
                    boolean gen = true;
                    while (gen) {
                        gen = false;
                        r = 1 + rand.nextInt(4);
                        for (int j = 0; j < k; j++) {
                            if (_hours[j] == r)
                                gen = true;
                        }
                    }
                    _hours[k] = r;
                    h = r;
                    if (i < 5) {
                        gen = true;
                        while (gen) {
                            gen = false;
                            r = rand.nextInt(5);
                            for (int j = 0; j < i; j++) {
                                if (_days[j] == r)
                                    gen = true;
                            }
                        }
                        _days[i] = r;
                        d = r;
                    }
                    if (n < list.size()) {
                        lect = (String) list.get(n);
                        if (!lect.equalsIgnoreCase(nd2Data[d][h]) && !lect.equalsIgnoreCase(hnd1Data[d][h]) && !lect.equalsIgnoreCase(hnd2Data[d][h]) && !lect.equalsIgnoreCase(nd1Data[d][h]) && (Datas[index][d][h] == null || Datas[index][d][h].length() == 0)) {
                            Datas[index][d][h] = lect;
                            boolean roomGen = true;
                            while (roomGen){
                                roomGen = false;
                                int roomRand = new Random().nextInt(roomList.size());
                                String room = roomList.get(roomRand);
                                if (!room.equalsIgnoreCase(nd1DataRoom[d][h]) && !room.equalsIgnoreCase(nd2DataRoom[d][h]) && !room.equalsIgnoreCase(hnd1DataRoom[d][h]) && !room.equalsIgnoreCase(hnd2DataRoom[d][h])){
                                    RoomDatas[index][d][h] = room;
                                    Datas[index][d][h]+="\n"+room;
                                    roomGen = false;
                                }
                            }
                            n++;
                            break loop;
                        }
                    }
                }
            }
            n = 0;
            index++;
        }
        for (JTable t: timeTable) {
            t.repaint();
        }
    }

    void save(String[][][] arr){
        String file = docsGenerate.chooseFile(index);
        System.out.println(file);
        if (!file.equalsIgnoreCase("empty")){
            docsGenerate.generateDocument(file, arr, index);
        }
    }

    Controller() throws SQLException {
        db.connect();
        listener();
        active(sidebtn[0], sidebtn);
        active(levelBtn[0], levelBtn);
        select(levelBtn[0].getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int j = 0;
        for (JButton btn: sidebtn) {
            if(btn == e.getSource())
                active(btn, sidebtn);
        }
        for (JButton btn: levelBtn) {
            if(btn == e.getSource()) {
                action.clearTable(courseData, courseTable);
                active(btn, levelBtn);
                select(levelBtn[j].getText());
                k = j;
            }
            j++;
        }
        if (e.getSource() == semester){
            action.clearTable(courseData, courseTable);
            select(levelBtn[k].getText());
        }

        /**
         * Course Actions
         * */
        if (e.getSource() == addCourse){
            if (action.validate(courseInput) && !super.select[2].getSelectedItem().toString().equalsIgnoreCase(".....")){
                String sem = select[0].getSelectedItem().toString();
                String typ = select[1].getSelectedItem().toString();
                String lev = levelBtn[k].getText();
                String id = action.uniqueID("courseID.tgs");
                String cs = "Theory";
                if (!typ.equalsIgnoreCase(".....")){
                    cs = "practical";
                }
                if (
                        db.dbAction("INSERT INTO course VALUES('0', '"+courseInput[0].getText().toUpperCase()+"', '"+courseInput[1].getText().toUpperCase()+"', '"+id+"', '"+lev+"', '0', '"+sem+"', '"+cs+"', '"+super.select[2].getSelectedItem().toString()+"')")
                        &&
                        db.dbAction("INSERT INTO generate VALUES('0', '"+lect_id.get(super.select[2].getSelectedIndex())+"', '"+id+"', '"+lev+"', '"+sem+"', '"+typ+"')")
                ) {
                        action.alert(courses, color.get("green"),"Course Added", new int[]{770, 5, 350, 60});
                        courseInput[0].setText("");
                        courseInput[1].setText("");
                }else action.alert(courses, color.get("red"),"Course Not Added", new int[]{770, 5, 350, 60});

            }else action.alert(courses, color.get("red"),"Incomplete/Invalid details", new int[]{770, 5, 350, 60});
            action.clearTable(courseData, courseTable);
            select(levelBtn[k].getText());
        }
        if (e.getSource() == actionBtn[0] && !course_id.isEmpty()){
            edit(course_id.get(courseTable.getSelectedRow()), "course");
        }
        if (e.getSource() == actionBtn[1] && !course_id.isEmpty()){
            if (update("course")){
                action.alert(courses, color.get("green"),"Course Updated", new int[]{770, 5, 350, 60});
            }else{
                action.alert(courses, color.get("red"),"Course not Updated !!!", new int[]{770, 5, 350, 60});
            }
            action.clearTable(courseData, courseTable);
            select(levelBtn[k].getText());
        }
        if (e.getSource() == actionBtn[2] && !course_id.isEmpty()){
            if (delete("course")){
                action.alert(courses, color.get("green"),"Course Deleted", new int[]{770, 5, 350, 60});
            }else{
                action.alert(courses, color.get("red"),"Course not Deleted !!!", new int[]{770, 5, 350, 60});
            }
            action.clearTable(courseData, courseTable);
            select(levelBtn[k].getText());
        }

        /**
         * Room Action
         * */
        if (e.getSource() == addClass ){
            if (action.validate(roomInput)){
                String typ = roomType.getSelectedItem().toString();
                if (db.dbAction("INSERT INTO room VALUES('0', '"+roomInput[0].getText().toUpperCase()+"', '"+action.uniqueID("roomID.tgs")+"', '"+typ+"',  '"+roomInput[1].getText().toUpperCase()+"')")) {
                    action.alert(rooms, color.get("green"),"Room Added", new int[]{770, 5, 350, 60});
                    roomInput[0].setText("");
                    roomInput[1].setText("");
                }else action.alert(rooms, color.get("red"),"Room Not Added", new int[]{770, 5, 350, 60});

            }else action.alert(rooms, color.get("red"),"Incomplete/Invalid details", new int[]{770, 5, 350, 60});
            action.clearTable(roomData, roomTable);
            select(levelBtn[k].getText());
        }
        if (e.getSource() == roomActionBtn[0] && !room_id.isEmpty()){
            edit(room_id.get(roomTable.getSelectedRow()), "room");
        }
        if (e.getSource() == roomActionBtn[1] && !room_id.isEmpty()){
            if (update("room")){
                action.alert(rooms, color.get("green"),"Room Updated", new int[]{770, 5, 350, 60});
            }else{
                action.alert(rooms, color.get("red"),"Room not Updated !!!", new int[]{770, 5, 350, 60});
            }
            action.clearTable(roomData, roomTable);
            select(levelBtn[k].getText());
        }
        if (e.getSource() == roomActionBtn[2] && !room_id.isEmpty()){
            if (delete("room")){
                action.alert(rooms, color.get("green"),"Room Deleted", new int[]{770, 5, 350, 60});
            }else{
                action.alert(rooms, color.get("red"),"Room not Deleted !!!", new int[]{770, 5, 350, 60});
            }
            action.clearTable(roomData, roomTable);
            select(levelBtn[k].getText());
        }

        /**
         * Lecturers Action
         **/
        if (e.getSource() == addLect) {
            if (action.validate(lectInput) && lectInput[1].getText().length() == 11){
                if (db.dbAction("INSERT INTO lecturers VALUES('0', '"+lectInput[0].getText().toUpperCase()+"', '"+lectInput[1].getText()+"' ,'"+action.uniqueID("lectID.tgs")+"')")) {
                    action.alert(lecturers, color.get("green"),"Room Added", new int[]{770, 5, 350, 60});
                    lectInput[0].setText("");
                    lectInput[1].setText("");
                }else action.alert(lecturers, color.get("red"),"Room Not Added", new int[]{770, 5, 350, 60});

            }
            else action.alert(lecturers, color.get("red"),"Incomplete/Invalid details", new int[]{770, 5, 350, 60});
            action.clearTable(lectData, lectTable);
            select(levelBtn[k].getText());
        }
        if (e.getSource() == lectActionBtn[0] && !lect_id.isEmpty()){
            edit(lect_id.get(lectTable.getSelectedRow()), "lecturers");
        }
        if (e.getSource() == lectActionBtn[1] && !lect_id.isEmpty()){
            if (update("lecturers")){
                action.alert(lecturers, color.get("green"),"Lecturers Updated", new int[]{770, 5, 350, 60});
            }else{
                action.alert(lecturers, color.get("red"),"Lecturers not Updated !!!", new int[]{770, 5, 350, 60});
            }
            action.clearTable(lectData, lectTable);
            select(levelBtn[k].getText());
        }
        if (e.getSource() == lectActionBtn[2] && !lect_id.isEmpty()){
            if (delete("lecturers")){
                action.alert(lecturers, color.get("green"),"Lecturers Deleted", new int[]{770, 5, 350, 60});
            }else{
                action.alert(lecturers, color.get("red"),"Lecturers not Deleted !!!", new int[]{770, 5, 350, 60});
            }
            action.clearTable(lectData, lectTable);
//            select(levelBtn[k].getText());
        }

        /**
         *  Generate Timetable
         * */
        if (e.getSource() == generate){
            generate();
        }
        if (e.getSource() == save){
            save(Datas);
        }
        select(levelBtn[k].getText());
    }
}

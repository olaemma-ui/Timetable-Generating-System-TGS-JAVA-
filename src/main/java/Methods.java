import java.awt.event.ActionListener;

public interface Methods extends ActionListener {
    /**
     * Add Listeners to Component METHOD
     * */
    void listener();

    /**
     * Select COURSE for each Level & Semester METHOD
     * */
    void select(String lev);

    /**
     * Edit COURSE base on selected ROW on the table METHOD
     * */
    void edit (String id, String table);

    /**
     * Update COURSE after selecting a ROW on the table METHOD
     * */
    boolean update (String table);

    /**
     * Delete a ROW from the COURSE table METHOD
     * */
    boolean delete (String table);

    /**
     * Generate Timetable
     * */
    void generate();
}

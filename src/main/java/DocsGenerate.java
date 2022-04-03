import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class DocsGenerate {

    String chooseFile(JFrame f){
        JFileChooser chose = new JFileChooser();
        int opt = chose.showSaveDialog(f);
        File file = null;
        if (opt == JFileChooser.APPROVE_OPTION){
            file = chose.getSelectedFile();
        }

        String sf = null;
        if (!file.equals(null)){
            sf = file.getAbsolutePath();
        }
        return sf;
    }

    boolean generateDocument(String file, String[][][] data, JFrame f){
        boolean gerate = false;
        String[] txt = {"Day / Time", "8:00 - 10:00", "10:00 - 12:00", "12:00 - 2:00", "2:00 - 4:00"};
        String[][] day = new String[6][6];

        try{
            XWPFDocument doc = new XWPFDocument();
            // -- OR --
            // open an existing empty document with styles already defined
            //XWPFDocument doc = new XWPFDocument(new FileInputStream("base_document.docx"));
            // Create a new table with 6 rows and 3 columns
            XWPFParagraph p = doc.createParagraph();
            XWPFRun run = p.createRun();
            p.setAlignment(ParagraphAlignment.CENTER);
            run.setText("Time Table for ND 1 Conputer Science");
            run.setBold(true);
            run.setFontSize(20);

            for (int k = 0; k < data.length; k++) {
//                for (int i = 0; i < data[k].length; i++) {
//                    for (int j = 0; j < data[k][i].length; j++) {
                        int nRows = 6;
                        int nCols = 5;
                        XWPFTable table = doc.createTable(nRows, nCols);

                        CTTblPr tblPr = table.getCTTbl().getTblPr();
                        CTString styleStr = tblPr.addNewTblStyle();
                        styleStr.setVal("StyledTable");
                        // Get a list of the rows in the table
                        List<XWPFTableRow> rows = table.getRows();
                        int i = 0;
                        for (int r = 0; r < data[k].length+1; r++) {
                            //.out.println("file = "+data[k].length);
                            // get table row properties (trPr)
                            CTTrPr trPr = rows.get(r).getCtRow().addNewTrPr();
                            CTHeight ht = trPr.addNewTrHeight();
                            ht.setVal(BigInteger.valueOf(360));
                            // get the cells in this row
                            List<XWPFTableCell> cells = rows.get(r).getTableCells();
                            // add content to each cell
                            for (int c = 0; c < data[k][i].length; c++) {
//                                day[r+1][c] = data[k][r][c];
                                // get a table cell properties element (tcPr)
                                CTTcPr tcpr = cells.get(c).getCTTc().addNewTcPr();
                                // set vertical alignment to "center"
                                CTVerticalJc va = tcpr.addNewVAlign();
                                va.setVal(STVerticalJc.CENTER);
                                // create cell color element
                                CTShd ctshd = tcpr.addNewShd();
                                ctshd.setColor("auto");
                                // header row
                                ctshd.setVal(STShd.CLEAR);
                                if (r == 0) {
                                    ctshd.setFill("A7BFDE");
                                }
                                XWPFParagraph para = cells.get(c).getParagraphs().get(0);
                                XWPFRun rh = para.createRun();

                                if (r == 0) {
                                    rh.addBreak();
                                    rh.setText(txt[c]);
                                    rh.addBreak();
                                    rh.setBold(true);
                                    para.setAlignment(ParagraphAlignment.CENTER);
                                } if (r > 0) {
                                    rh.addBreak();
                                    rh.setText(data[k][r-1][c]);
                                    rh.addBreak();
                                    para.setAlignment(ParagraphAlignment.CENTER);
                                }
                            }
                        }
                        i++;
                doc.createParagraph().createRun().addBreak();
                doc.createParagraph().createRun().addBreak();
                doc.createParagraph().createRun().addBreak();
                doc.createParagraph().createRun().addBreak();
                FileOutputStream out = new FileOutputStream( file);
                doc.write(out);
                out.close();
                //.out.println("Process Completed Successfully");
            }
            JOptionPane.showMessageDialog(f,"File Saved");
        }catch (Exception ex){
            /**/
            ex.printStackTrace();
        }

        return gerate;
    }
}

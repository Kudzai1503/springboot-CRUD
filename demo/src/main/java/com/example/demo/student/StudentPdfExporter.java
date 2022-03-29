package com.example.demo.student;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StudentPdfExporter {

    private List<Student> studentList;

    public StudentPdfExporter(List<Student> studentList) {
        this.studentList = studentList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);


        cell.setPhrase(new Phrase("User ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("D.O.B", font));
        table.addCell(cell);

    }

    private void writerTableHeading(PdfPTable table) {

    }

    private void writeTableData(PdfPTable table) {
        for (Student student : studentList) {
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getName());
            table.addCell(student.getEmail());
            table.addCell(student.getDob().toString());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        Image image = Image.getInstance("demo/src/main/resources/static/images/th.jpeg");
        image.scaleAbsolute(100, 100);
        image.setAlignment(Image.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Student", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{2, 2, 2, 2});
        table.setSpacingBefore(10);
        document.open();
        document.add(image);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);

        document.close();


    }
}

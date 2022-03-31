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

    private PdfPCell cell = new PdfPCell();


    public StudentPdfExporter(List<Student> studentList) {
        this.studentList = studentList;
    }

    private void writeTableHeader(PdfPTable table) {
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("User ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Age", font));
        table.addCell(cell);

    }

    private void writerTableHeading(PdfPTable table) {

    }

    private void writeTableData(PdfPTable table) {
        cell.setBackgroundColor(Color.white);
        cell.setPadding(7f);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        for (Student student : studentList) {
            cell.setPhrase(new Phrase(String.valueOf(student.getId())));
            table.addCell(cell);
            cell.setPhrase(new Phrase(student.getName()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(student.getEmail()));
            table.addCell(cell);
            cell.setPhrase(new Phrase(student.getAge().toString()));
            table.addCell(cell);
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        Student student = new Student();
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
        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(30);
        table1.addCell(new Phrase("TOTAL = " + student.getName()));
        table1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        document.open();
        document.add(image);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.add(table1);
        document.close();


    }
}

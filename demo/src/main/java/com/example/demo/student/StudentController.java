package com.example.demo.student;


//import io.swagger.annotations.ApiOperation;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
//@ApiOperation("Student Endpoint")
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

    @GetMapping("/student/export/pdf")
    public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType(MediaType.APPLICATION_PDF.toString());
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=student_" + currentDateTime + ".pdf";
        String headerKey = "Content-Disposition";

        response.setHeader(headerKey, headerValue);
        response.encodeURL("google.com");
        List<Student> studentList = studentService.getStudents();

        StudentPdfExporter studentPdfExporter = new StudentPdfExporter(studentList);

        studentPdfExporter.export(response);
    }

    @GetMapping("/student/pdf")
    public String exportLink(HttpServletResponse response) throws IOException {

        String path = "https://onedrive.live.com/?id=13F0266706321240%21105&cid=13F0266706321240\\invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);


        pdfDocument.setDefaultPageSize(PageSize.A4);

        float col = 280f;

        float[] columnWidth = {col, col};
        Table table = new Table(columnWidth);
        table.addCell(new Cell().add(new Paragraph("INVOICE")));
        table.addCell(new Cell().add(new Paragraph("Dambimbo")));

        document.add(table);
        document.close();
        System.out.println("Document printed");
        return path;
    }
}

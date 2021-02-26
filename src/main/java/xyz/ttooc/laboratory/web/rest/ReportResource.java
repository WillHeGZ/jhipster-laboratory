package xyz.ttooc.laboratory.web.rest;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.ttooc.laboratory.service.JasperReportService;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/api")
@Slf4j
public class ReportResource {


    private static final String REPORT_NAME = "reportName";
    private static final String FILE_FORMAT = "format";
    private static final String DATASOURCE = "datasource";

    private static final String queryStr = "你的sql语句";

    private final JasperReportService jasperReportService;
    private final DataSource dataSource;

    public ReportResource(JasperReportService jasperReportService, DataSource dataSource) {
        this.jasperReportService = jasperReportService;
        this.dataSource = dataSource;
    }

    @GetMapping("/report")
    public ResponseEntity report(HttpServletResponse response) throws JRException, SQLException, IOException {
        jasperReportService.compileReport(response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-report")
    public void report2(HttpServletResponse response) throws JRException, SQLException, IOException {
        InputStream employeeReportStream = getClass().getResourceAsStream("/jrxml/Blank_A4.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);

        JRSaver.saveObject(jasperReport, "Blank_A4.jasper");

        Connection connection = dataSource.getConnection();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @GetMapping("/get-report-3")
    public void report3(HttpServletResponse response) throws JRException, SQLException, IOException {
        response.setContentType("text/html");
        InputStream employeeReportStream = getClass().getResourceAsStream("/jrxml/Blank_A4.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(employeeReportStream);
        JRSaver.saveObject(jasperReport, "Blank_A4.jasper");

        Connection connection = dataSource.getConnection();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }


    public void test(){
        ReentrantLock lock = new ReentrantLock();

        lock.lock();

        lock.unlock();

    }

}

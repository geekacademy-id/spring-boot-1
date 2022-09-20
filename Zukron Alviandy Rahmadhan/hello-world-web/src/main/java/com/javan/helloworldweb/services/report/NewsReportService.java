package com.javan.helloworldweb.services.report;

import com.javan.helloworldweb.exceptions.GlobalException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class NewsReportService {
    @Autowired
    private DataSource dataSource;

    public File getReport(String filename, boolean withComment) throws GlobalException {
        try {
            File file = withComment
                    ? new ClassPathResource("reports/news_report_with_comment.jasper").getFile()
                    : new ClassPathResource("reports/news_report.jasper").getFile();

            JasperReport report = (JasperReport) JRLoader.loadObject(file);
            Map<String, Object> map = new HashMap<>();

            if (withComment) {
                File comment = new ClassPathResource("reports/comment.jasper").getFile();
                map.put("COMMENT_REPORT", comment.getPath());
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, dataSource.getConnection());

            // Excel Export
            String uploadDir = StringUtils.cleanPath("./generated-reports/" + filename);
            Path uploadPath = Paths.get(uploadDir);
            File output = new File(uploadPath.toString());

            JRXlsxExporter exporter = new JRXlsxExporter();
            SimpleXlsxReportConfiguration reportConfiguration = new SimpleXlsxReportConfiguration();
            reportConfiguration.setSheetNames(new String[]{"News"});

            exporter.setConfiguration(reportConfiguration);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
            exporter.exportReport();

            return new File(uploadPath.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());

            if (e instanceof IOException) {
                throw new GlobalException("File not found");
            }

            if (e instanceof JRException) {
                throw new GlobalException("Failed to generate report");
            }
        }

        return null;
    }
}

package com.javan.helloworldweb.services.report;

import com.javan.helloworldweb.exceptions.GlobalException;
import com.javan.helloworldweb.models.NewsReport;
import com.javan.helloworldweb.repositories.NewsRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsReportService {
    @Autowired
    private NewsRepository newsRepository;

    public File getReport(String filename) throws GlobalException {
        try {
            List<NewsReport> news = newsRepository
                    .findAll()
                    .stream()
                    .map(NewsReport::new)
                    .collect(Collectors.toList());

            File file = new ClassPathResource("reports/news_report.jrxml").getFile();
            JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(news);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);

            // PDF Export
            String uploadDir = StringUtils.cleanPath("./generated-reports/" + filename);
            Path uploadPath = Paths.get(uploadDir);

            JasperExportManager.exportReportToPdfFile(jasperPrint, uploadPath.toString());

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

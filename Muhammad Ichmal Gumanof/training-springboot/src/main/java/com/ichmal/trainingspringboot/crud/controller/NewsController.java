package com.ichmal.trainingspringboot.crud.controller;

import com.ichmal.trainingspringboot.crud.GlobalException;
import com.ichmal.trainingspringboot.crud.NotFoundException;
import com.ichmal.trainingspringboot.crud.models.News;
import com.ichmal.trainingspringboot.crud.models.Response;
import com.ichmal.trainingspringboot.crud.models.dto.NewsDto;
import com.ichmal.trainingspringboot.crud.newsreport.NewsReportService;
import com.ichmal.trainingspringboot.crud.service.NewsService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsReportService newsReportService;

    @GetMapping
    public Response all(@RequestParam(value = "search", required = false) String keyword) {
        if (keyword != null){
            return new Response(HttpStatus.OK, "Success list search news", newsService.search(keyword));
        } else {
            return new Response(HttpStatus.OK, "Success list all news", newsService.list());
        }
    }

    @GetMapping(value = "/report")
    public ResponseEntity<InputStreamResource> report() throws GlobalException, FileNotFoundException {
        String filename = "News Report ("+ new Date() +").pdf";
        File report = newsReportService.getReport(filename);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(report));

        return ResponseEntity.ok().headers(headers).contentLength(report.length()).contentType(MediaType.APPLICATION_PDF).body(resource);
    }

    @PostMapping
    public Response create(@Valid @RequestBody NewsDto newsDto) throws NotFoundException {
        News news = newsService.create(newsDto);

        return new Response(HttpStatus.CREATED, "Success create news", news);
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody NewsDto newsDto) throws NotFoundException {
        News news = newsService.update(id, newsDto);

        return new Response(HttpStatus.OK, "Success update news", news);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable("id") Long id) throws NotFoundException{
        newsService.delete(id);

        return new Response(HttpStatus.OK, "Success delete news", null);
    }
}

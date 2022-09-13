package com.mob.portal.Api;

import com.mob.portal.Entity.News;
import com.mob.portal.EntityDTO.NewsDTO;
import com.mob.portal.Helper.ResponseBodyFormatter;
import com.mob.portal.Repository.NewsRepository;
import com.mob.portal.Service.EntityService.EntityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/news/")
public class NewsController {
    @Autowired
    private EntityService<NewsRepository, News, Long> newsService;

    @Autowired
    private ResponseBodyFormatter responseBodyFormatter;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ResponseBodyFormatter> findAll(@RequestParam Map<String, Object> allParams){
        System.out.println(allParams.entrySet());
        List<News> data = (List<News>) newsService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success get all data", data)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseBodyFormatter> save(@Valid @RequestBody NewsDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        News data = newsService.save(modelMapper.map(object, News.class));
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success add one data", data)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> findById(@PathVariable(value = "id") long id){
        Optional<News> target = newsService.findById(id);

        if(target.isPresent()){
            News data = target.get();
            return ResponseEntity.status(HttpStatus.OK).body(
                    responseBodyFormatter.success("Success get data by id: " + id, data)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    responseBodyFormatter.error("Data by id: " + id + " not found")
            );
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> updateById(@PathVariable(value = "id") long id, @Valid @RequestBody NewsDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Optional<News> target = newsService.findById(id);

        if(target.isPresent()){
            News newObject = target.get();
            modelMapper.map(object, newObject);
            News data = newsService.save(newObject);
            return ResponseEntity.status(HttpStatus.OK).body(
                    responseBodyFormatter.success("Success update data by id: " + id, data)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    responseBodyFormatter.error("Data by id: " + id + " not found")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> deleteById(@PathVariable(value = "id") long id){
        Optional<News> target = newsService.findById(id);

        if(target.isPresent()){
            newsService.deleteById(id);
            List<News> data = newsService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(
                    responseBodyFormatter.success("Success delete data by id: " + id, data)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    responseBodyFormatter.error("Data by id: " + id + " not found")
            );
        }
    }
}

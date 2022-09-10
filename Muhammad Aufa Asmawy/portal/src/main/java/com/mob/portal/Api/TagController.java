package com.mob.portal.Api;

import com.mob.portal.Entity.Tag;
import com.mob.portal.EntityDTO.TagDTO;
import com.mob.portal.Helper.ResponseBodyFormatter;
import com.mob.portal.Repository.TagRepository;
import com.mob.portal.Service.EntityService.EntityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/tag/")
public class TagController {
    @Autowired
    private EntityService<TagRepository, Tag, Long> tagService;

    @Autowired
    ResponseBodyFormatter responseBodyFormatter;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ResponseBodyFormatter> findAll(){
        List<Tag> data = (List<Tag>) tagService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success get all data", data)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseBodyFormatter> save(@Valid @RequestBody TagDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Tag data = tagService.save(modelMapper.map(object, Tag.class));
        return ResponseEntity.status(HttpStatus.OK).body(
                responseBodyFormatter.success("Success add one data", data)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyFormatter> findById(@PathVariable(value = "id") long id){
        Optional<Tag> target = tagService.findById(id);

        if(target.isPresent()){
            Tag data = target.get();
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
    public ResponseEntity<ResponseBodyFormatter> updateById(@PathVariable(value = "id") long id, @Valid @RequestBody TagDTO object, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseBodyFormatter.error(errors)
            );
        }

        Optional<Tag> target = tagService.findById(id);

        if(target.isPresent()){
            Tag newObject = target.get();
            modelMapper.map(object, newObject);
            Tag data = tagService.save(newObject);
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
        Optional<Tag> target = tagService.findById(id);

        if(target.isPresent()){
            tagService.deleteById(id);
            List<Tag> data = tagService.findAll();
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

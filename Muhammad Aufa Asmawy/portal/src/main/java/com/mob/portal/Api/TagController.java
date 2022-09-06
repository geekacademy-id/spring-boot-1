package com.mob.portal.Api;

import com.mob.portal.Entity.Category;
import com.mob.portal.Entity.Tag;
import com.mob.portal.Helper.ResponseFormatter;
import com.mob.portal.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/tag/")
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    ResponseFormatter responseFormatter;

    @GetMapping
    public ResponseFormatter findAllAuthor(){
        List<Tag> data = (List<Tag>) tagRepository.findAll();
        return responseFormatter.generate(200, "Success get all data", data);
    }

    @PostMapping
    public ResponseFormatter saveAuthor(@Validated @RequestBody Tag object){
        Tag data = tagRepository.save(object);
        return responseFormatter.generate(200, "Success add one data", data);
    }

    @GetMapping("/{id}")
    public ResponseFormatter findAuthorById(@PathVariable(value = "id") long id){
        Optional<Tag> target = tagRepository.findById(id);

        if(target.isPresent()){
            Tag data = target.get();
            return responseFormatter.generate(200, "Success delete data by id: " + id, data);
        }else {
            return responseFormatter.generate(400, "Failed to get data by id: " + id);
        }
    }

    @PostMapping("/{id}")
    public ResponseFormatter updateAuthorById(@PathVariable(value = "id") long id, @RequestBody Tag object){
        Optional<Tag> target = tagRepository.findById(id);

        if(target.isPresent()){
            Tag tag = target.get();
            tag.setName(object.getName());
            Tag data = tagRepository.save(tag);
            return responseFormatter.generate(200, "Success update data by id: " + id, data);
        }else{
            return responseFormatter.generate(400, "Failed to update data by id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseFormatter deleteUserById(@PathVariable(value = "id") long id){
        Optional<Tag> target = tagRepository.findById(id);

        if(target.isPresent()){
            tagRepository.deleteById(id);
            List<Tag> data = tagRepository.findAll();
            return responseFormatter.generate(200, "Success delete data by id: " + id, data);
        }else {
            return responseFormatter.generate(400, "Failed to delete data by id: " + id);
        }
    }
}

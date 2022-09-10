package com.mob.portal.Service;

import com.mob.portal.Entity.Tag;
import com.mob.portal.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TagService extends EntityService <TagRepository, Tag, Long>{
//    @Autowired
//    private TagRepository tagRepository;
//
//    public List<Tag> findAll(){
//        return (List<Tag>) tagRepository.findAll();
//    }
//
//    public Tag save(Tag object){
//        return tagRepository.save(object);
//    }
//
//    public Optional<Tag> findById(long id){
//        return tagRepository.findById(id);
//    }
//
//    public List<Tag> deleteById(long id){
//        tagRepository.deleteById(id);
//        return tagRepository.findAll();
//    }
}

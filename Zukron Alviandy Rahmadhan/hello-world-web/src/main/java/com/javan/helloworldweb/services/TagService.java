package com.javan.helloworldweb.services;

import com.javan.helloworldweb.models.Tag;
import com.javan.helloworldweb.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> list() {
        return tagRepository.findAll();
    }

    public void create(Tag tag) {
        tagRepository.save(tag);
    }

    public void update(Long id, Tag tag) {
        tagRepository.findById(id)
                .ifPresent(item -> {
                    item.setName(tag.getName());

                    tagRepository.save(item);
                });
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}

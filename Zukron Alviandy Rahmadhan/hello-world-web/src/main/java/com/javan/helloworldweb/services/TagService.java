package com.javan.helloworldweb.services;

import com.javan.helloworldweb.exceptions.NotFoundException;
import com.javan.helloworldweb.models.Tag;
import com.javan.helloworldweb.models.dto.TagDto;
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

    public Tag create(TagDto dto) {
        Tag tag = new Tag();
        tag.setName(dto.getName());

        return tagRepository.save(tag);
    }

    public Tag update(Long id, TagDto dto) throws NotFoundException {
        Tag tag = tagRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Tag not found"));

        tag.setName(dto.getName());

        return tagRepository.save(tag);
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}

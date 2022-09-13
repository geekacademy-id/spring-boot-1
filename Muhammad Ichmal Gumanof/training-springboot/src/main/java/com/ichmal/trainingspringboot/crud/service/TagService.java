package com.ichmal.trainingspringboot.crud.service;

import com.ichmal.trainingspringboot.crud.NotFoundException;
import com.ichmal.trainingspringboot.crud.models.Tag;
import com.ichmal.trainingspringboot.crud.models.dto.TagDto;
import com.ichmal.trainingspringboot.crud.repository.TagRepository;
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

    public void delete(Long id) throws NotFoundException {
        Tag tag = tagRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Tag not found"));

        tagRepository.delete(tag);
    }
}

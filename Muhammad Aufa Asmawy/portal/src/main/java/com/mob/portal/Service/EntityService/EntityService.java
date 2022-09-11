package com.mob.portal.Service.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class EntityService<R extends JpaRepository<E, ID>, E, ID> {
    @Autowired
    private R genericRepository;

    public List<E> findAll(Map<String, Object> allParams){
//        JpaRepository<E, ID> jpaRepository = (JpaRepository<E, ID>) genericRepository;
        return genericRepository.findAll();
    }

    public List<E> findAll(){
//        JpaRepository<E, ID> jpaRepository = (JpaRepository<E, ID>) genericRepository;
        return genericRepository.findAll();
    }

    public E save(E object){
//        JpaRepository<E, ID> jpaRepository = (JpaRepository<E, ID>) genericRepository;
        return genericRepository.save(object);
    }

    public Optional<E> findById(ID id){
//        JpaRepository<E, ID> jpaRepository = (JpaRepository<E, ID>) genericRepository;
        return genericRepository.findById(id);
    }

    public List<E> deleteById(ID id){
//        JpaRepository<E, ID> jpaRepository = (JpaRepository<E, ID>) genericRepository;
        genericRepository.deleteById(id);
        return genericRepository.findAll();
    }
}

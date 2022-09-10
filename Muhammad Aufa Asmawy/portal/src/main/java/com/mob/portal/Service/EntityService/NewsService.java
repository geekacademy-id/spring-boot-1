package com.mob.portal.Service;

import com.mob.portal.Entity.News;
import com.mob.portal.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NewsService extends EntityService <NewsRepository, News, Long>{
//    @Autowired
//    private NewsRepository newsRepository;
//
//    public List<News> findAll(){
//        return (List<News>) newsRepository.findAll();
//    }
//
//    public News save(News object){
//        return newsRepository.save(object);
//    }
//
//    public Optional<News> findById(long id){
//        return newsRepository.findById(id);
//    }
//
//    public List<News> deleteById(long id){
//        newsRepository.deleteById(id);
//        return newsRepository.findAll();
//    }
}

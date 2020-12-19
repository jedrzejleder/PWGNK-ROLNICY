package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.Article;
import net.javaguides.springboot.springsecurity.repository.ArticleRepository;
import net.javaguides.springboot.springsecurity.web.dto.ArticleRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article save(ArticleRegistrationDto registration) {
        Article article = new Article();
        article.setName(registration.getName());
        article.setPlaceOfTheObject(registration.getPlaceOfTheObject());
        article.setDescription(registration.getDescription());
        article.setPrice(registration.getPrice());
        article.setAvailable(registration.getAvailable());
        return articleRepository.save(article);
    }
}

package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.Article;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.ArticleRepository;
import net.javaguides.springboot.springsecurity.web.dto.ArticleRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserService userService;

    public Article save(ArticleRegistrationDto registration) {
        Article article = new Article();
        article.setName(registration.getName());
        article.setPlaceOfTheObject(registration.getPlaceOfTheObject());
        article.setDescription(registration.getDescription());
        article.setPrice(registration.getPrice());
        article.setAvailable(true);
        article.setUser_owner(userService.loadCurrentUser());
        article.setPhoto(registration.getPhoto());

        return articleRepository.save(article);
    }


}

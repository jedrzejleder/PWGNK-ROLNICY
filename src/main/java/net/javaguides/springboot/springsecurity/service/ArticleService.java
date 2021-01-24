package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.Article;
import net.javaguides.springboot.springsecurity.repository.ArticleRepository;
import net.javaguides.springboot.springsecurity.web.dto.ArticleRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Article updateArticle(Article article) {return articleRepository.save(article);}

    public List<Article> listAll(String keyword, String searchKeyword) {

//        System.out.printf(searchKeyword);

        if (keyword != null) {
            if(searchKeyword.equals("Nazwa") )
            {
                return articleRepository.searchName(keyword);
            }
            else if (searchKeyword.equals("Lokalizacja"))
            {
                return articleRepository.searchPlace(keyword);
            }
            else if( searchKeyword.equals("Cena"))
            {
                return articleRepository.searchPrice(keyword);
            }
        }

        return articleRepository.findAllAvailable();
    }
}

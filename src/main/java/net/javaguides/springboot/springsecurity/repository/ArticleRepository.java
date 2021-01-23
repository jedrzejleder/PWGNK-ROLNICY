package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.Article;
import net.javaguides.springboot.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a where a.available = 1")
    List<Article> findAllAvailable();

    @Query("SELECT p FROM Article p WHERE p.name LIKE %?1%"
            + " AND p.available = 1 "
            + " OR p.placeOfTheObject LIKE %?1%"
            + " AND p.available = 1 "
            + " OR CONCAT(p.price, '') LIKE %?1%"
            + " AND p.available = 1 ")
    public List<Article> search(String keyword);
}

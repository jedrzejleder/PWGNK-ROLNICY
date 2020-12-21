package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}

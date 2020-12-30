package net.javaguides.springboot.springsecurity.web;

import net.javaguides.springboot.springsecurity.model.Article;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.ArticleRepository;
import net.javaguides.springboot.springsecurity.service.ArticleService;
import net.javaguides.springboot.springsecurity.service.UserService;
import net.javaguides.springboot.springsecurity.web.dto.ArticleRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @ModelAttribute("article")
    public ArticleRegistrationDto articleRegistrationDto() {
        return new ArticleRegistrationDto();
    }

    @GetMapping("/add")
    public String showAddArticleForm(Model model, @AuthenticationPrincipal User user) {
        return "/addArticle";
    }

    @PostMapping("/add")
    public String registerArticle(@ModelAttribute("article") @Valid ArticleRegistrationDto articleDto,
                                  BindingResult result, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        articleDto.setPhoto(fileName);

        Article savedArticle = articleService.save(articleDto);

        String uploadDir = "article-photos/" + savedArticle.getArticleId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        if(result.hasErrors()){ return  "/addArticle"; }

        return "redirect:/article/list";
    }

    @GetMapping("/list")
    public String showUpdateForm(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "listArticle";
    }
}

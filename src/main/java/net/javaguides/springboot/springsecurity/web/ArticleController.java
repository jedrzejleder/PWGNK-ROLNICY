package net.javaguides.springboot.springsecurity.web;

import net.javaguides.springboot.springsecurity.model.Article;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.ArticleRepository;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.service.ArticleService;
import net.javaguides.springboot.springsecurity.service.UserService;
import net.javaguides.springboot.springsecurity.web.dto.ArticleRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    private UserRepository userRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }



    @ModelAttribute("article")
    public ArticleRegistrationDto articleRegistrationDto() {
        return new ArticleRegistrationDto();
    }

    @GetMapping("/add")
    public String showAddArticleForm(Model model) {
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

        if (result.hasErrors()) {
            return "/addArticle";
        }

        return "redirect:/article/list";
    }

    @RequestMapping("/list")
    public String showUpdateForm(Model model, @Param("keyword") String keyword) {
        List<Article> listProducts = articleService.listAll(keyword);
        model.addAttribute("articles", listProducts);
        model.addAttribute("keyword", keyword);

        return "listArticle";
    }


//    @GetMapping("/list")
//    public String showUpdateForm(Model model) {
//        model.addAttribute("articles", articleRepository.findAllAvailable());
//        return "listArticle";
//    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable("id") long id, Model model) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));

        User user = userRepository.findById(article.getUser_owner_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));;

        model.addAttribute(article);
        model.addAttribute(user);
        return "showArticle";
    }

    @GetMapping("/listMy")
    public String showMyArticles(Model model) {
        User currUser = userService.loadCurrentUser();
        model.addAttribute("myArticles", currUser.getArticles());
        return "listMyArticle";
    }

    @GetMapping("/listMy/{id}")
    public String changeAvailability(@PathVariable("id") long id, Model model) {
        User currUser = userService.loadCurrentUser();
        model.addAttribute("myArticles", currUser.getArticles());

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));

        article.setAvailable(!article.getAvailable());
        articleService.updateArticle(article);
        return "redirect:/article/listMy";
    }

}

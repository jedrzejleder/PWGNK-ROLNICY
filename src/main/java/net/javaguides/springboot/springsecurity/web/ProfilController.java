package net.javaguides.springboot.springsecurity.web;


import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.ArticleRepository;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.service.ArticleService;
import net.javaguides.springboot.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profil")
public class ProfilController {


    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public ProfilController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/display")
    public String showProfilePage(Model model)
    {
        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        User user = userService.findByEmail(currentUserName);
        model.addAttribute(user);
        return "/profil";
    }

    @GetMapping("/editProfile")
    public String editProfile(){

        return "/editProfile";
    }


/*    // Zapisywanie zmian w profilu uzytkownika
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(User user)
    {
        userRepository.save(user);
        return "redirect:/";
    }

    // Mozliwosc usuniecia konta uzytkownika
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long userId, Model model)
    {
        userRepository.delete(userId);
        return "redirect:/";
    }*/

}

package com.example.TfgSoftAlba.controller;

import com.example.TfgSoftAlba.models.entity.Article;
import com.example.TfgSoftAlba.models.entity.Rol;
import com.example.TfgSoftAlba.models.entity.User;
import com.example.TfgSoftAlba.models.repository.ArticleRepository;
import com.example.TfgSoftAlba.models.repository.TagRepository;
import com.example.TfgSoftAlba.models.service.ArticleService;
import com.example.TfgSoftAlba.models.service.TagService;
import com.example.TfgSoftAlba.models.service.UserArticleLikeService;
import com.example.TfgSoftAlba.models.service.UserService;
import com.example.TfgSoftAlba.util.CustomUserDetails;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

@Controller
public class WebController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    private ArticleRepository articleRepository;

    @Autowired
    private UserArticleLikeService userArticleLikeService;

    @GetMapping ({"/","/index"})
    public String index(Model model){

        return findPaginated(1, model);  
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 6;

        Page<Article> page = articleService.findPaginated(pageNo, pageSize);
        List<Article> listArticles = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("Articles", listArticles);

        model.addAttribute("fechasCreacion", listArticles.stream()
                                                       .map(Article::getCreationDate)
                                                       .filter(Objects::nonNull)
                                                       .map(date -> date.withDayOfMonth(1))
                                                       .map(date -> date.format(DateTimeFormatter.ofPattern("MM/yyyy")))
                                                       .distinct()
                                                       .collect(Collectors.toList()));

        model.addAttribute("FiltroLocalizacion", tagService.TypeLocalizacion());
        model.addAttribute("FiltroTipos", tagService.TypeTipo());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long idUser = null;
        if (principal instanceof CustomUserDetails)  idUser = ((CustomUserDetails)principal).getId();

        if(idUser != null){ 
            User usuario = userService.get(idUser);
            Collection<Rol> roles = usuario.getRoles();
            Rol rol =  roles.iterator().next();
            model.addAttribute("rol", rol.getName());
        }

        return "index";
    }

    @GetMapping("/favorites")
    public String showLikedNews(Model model, Authentication authentication){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long idUser = null;
        if (principal instanceof CustomUserDetails)  idUser = ((CustomUserDetails)principal).getId();
        
        ArrayList<Long> likedNewsIds = (ArrayList<Long>) userArticleLikeService.getLikedNewsIdsByUserId(idUser);
        ArrayList<Article> likedArticles = articleService.getArticlesByIds(likedNewsIds);
        model.addAttribute("favorites", likedArticles);
        return "favorites";
    }


    @GetMapping("/filterByDate/{selectedDate}")
        public String filterByDate(@RequestParam("selectedDate") String selectedDate, Model model) {
        // Lógica para filtrar artículos por fecha y obtener la lista filtrada
        List<Article> filteredArticles = articleService.findByCreationDate(selectedDate);
    
        model.addAttribute("Articles", filteredArticles);
        return "index"; // O el nombre de la vista donde deseas mostrar los artículos filtrados
    }


}
package com.example.TfgSoftAlba.controller;
import com.example.TfgSoftAlba.models.entity.*;
import com.example.TfgSoftAlba.models.repository.UserArticleLikeRepository;
import com.example.TfgSoftAlba.models.service.ArticleService;
import com.example.TfgSoftAlba.models.service.TagService;
import com.example.TfgSoftAlba.models.service.UserService;
import com.example.TfgSoftAlba.util.CustomUserDetails;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserArticleLikeRepository userArticleRepository;

    @ModelAttribute("article")
	public Article returnNewArticleDTO() {
		return new Article();
	}

    @GetMapping("/article/{id}")
    public String showArticle(@PathVariable("id") Long id, Model model) {

        //verifica si hay usuario con sesion iniciada
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long idUser = null;   

        if (principal instanceof CustomUserDetails)  idUser = ((CustomUserDetails)principal).getId(); 

        model.addAttribute("Like", "Unliked");
        if(idUser != null){ 
            User usuario = userService.get(idUser);
            Collection<Rol> roles = usuario.getRoles();
            Rol rol =  roles.iterator().next();
            model.addAttribute("rol", rol.getName());


            if( userArticleRepository.findByUserIdAndArticleId(idUser, id).isPresent()){
                UserArticleLike userArticle = userArticleRepository.findByUserIdAndArticleId(idUser, id).get();
                
                if(userArticle.getActive()){
                    model.addAttribute("Like", "Liked");
                }                
            }        
        }

        if (id != null && id != 0) {
            model.addAttribute("Article", articleService.get(id));
        } else {
            model.addAttribute("Article", new Article());
        }
        
        return "article";
    }


    /****** CRUD ******/

    @GetMapping("/article/list")
	public String listar(Model model) {
         //verifica si hay usuario con sesion iniciada
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
		model.addAttribute("Articles", articleService.listAllArticles());
		return "panel/article/index";
	}

    @GetMapping("/article/edit/{id}")
	public String edit(@PathVariable Long id,Model model) {
        //verifica si hay usuario con sesion iniciada
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
        
        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("tags", tags);

		model.addAttribute("Article", articleService.edit(id));
		return "panel/article/form";
	}

    @GetMapping("/article/new")
	public String nuevo(Model model) {
        //verifica si hay usuario con sesion iniciada
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

        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("tags", tags);

        model.addAttribute("Article", new Article());
        return "panel/article/form";
    }


    @PostMapping("/article/save")
	public String save(@ModelAttribute("article") Article article, @RequestParam("img") MultipartFile multipartFile,@RequestParam(value = "selectedTags", required = false) List<Long> selectedTags, Model model) throws IOException {
        int id=articleService.save(article, multipartFile,selectedTags);
		if(id==0) {
			return "panel/article/form";
		}
		return "redirect:/article/list";
	}

    @GetMapping("/article/delete/{id}")
	public String delete(@PathVariable Long id,Model model) {
        //verifica si hay usuario con sesion iniciada
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long idUser = null; 
        if (principal instanceof CustomUserDetails)  idUser = ((CustomUserDetails)principal).getId(); 
        if(idUser != null){ 
            articleService.delete(id);
        }
		return "redirect:/article/list";
	}

}
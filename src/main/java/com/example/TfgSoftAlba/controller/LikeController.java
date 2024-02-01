package com.example.TfgSoftAlba.controller;

import com.example.TfgSoftAlba.models.entity.*;
//import com.example.TfgSoftAlba.models.entity.Vector;
import com.example.TfgSoftAlba.models.repository.UserArticleLikeRepository;
//import com.example.TfgSoftAlba.models.repository.UserArticleSimRepository;
//import com.example.TfgSoftAlba.models.repository.VectorRepository;
import com.example.TfgSoftAlba.models.service.ArticleService;
import com.example.TfgSoftAlba.models.service.UserService;
import com.example.TfgSoftAlba.util.CustomUserDetails;
//import com.example.TfgSoftAlba.util.Stemm_es;

//import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
//import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.MediaType;


@Controller
public class LikeController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserArticleLikeRepository userArticleLikeRepository;

    //@Autowired
    //private UserArticleSimRepository userArticleSimRepository;

    //@Autowired
    //private VectorRepository vectorRepository;


    @GetMapping("/article/like/{id}")
	public String like(@PathVariable Long id  ,Model model) {

        //verifica si hay usuario con sesion iniciada
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long idUser = null; 
        if (principal instanceof CustomUserDetails)  idUser = ((CustomUserDetails)principal).getId(); 
    
        if(idUser != null){ 
            if( userArticleLikeRepository.findByUserIdAndArticleId(idUser, id).isPresent()) {

                UserArticleLike userArticle = userArticleLikeRepository.findByUserIdAndArticleId(idUser, id).get();
                userArticle.setActive(true);
                userArticleLikeRepository.save(userArticle); 
            }else{
                UserArticleLike userArticle =  new UserArticleLike(idUser, id, true);
                userArticleLikeRepository.save(userArticle);   
            }
            //generateUserVector(idUser);
            //saveRecomendedArticles(idUser);
        }else{
            return "redirect:/login";
        }
		
		return "redirect:/article/"+id;
	}

    @GetMapping("/article/unlike/{id}")
	public String unlike(@PathVariable Long id  ,Model model) {

        //verifica si hay usuario con sesion iniciada
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long idUser = null; 
        if (principal instanceof CustomUserDetails)  idUser = ((CustomUserDetails)principal).getId(); 
    
        if(idUser != null){           
            UserArticleLike userArticle = userArticleLikeRepository.findByUserIdAndArticleId(idUser, id).get();
            userArticle.setActive(false);
            userArticleLikeRepository.save(userArticle); 
            //generateUserVector(idUser);
            //saveRecomendedArticles(idUser);
        }else{
            return "redirect:/login";
        }
		return "redirect:/article/"+id;
	}
    
    
}

package com.example.TfgSoftAlba.controller;
import com.example.TfgSoftAlba.models.entity.*;
import com.example.TfgSoftAlba.models.repository.UserArticleLikeRepository;
import com.example.TfgSoftAlba.models.service.ArticleService;
import com.example.TfgSoftAlba.models.service.CategoriaTagService;
import com.example.TfgSoftAlba.models.service.SubcategoriaTagService;
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
    private UserArticleLikeRepository userArticleRepository;

    /*@Autowired
    private CategoriaTagService categoriaTagService;*/

    /*@Autowired
    private SubcategoriaTagService subcategoriaTagService;*/

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
        model.addAttribute("Article", new Article());
        return "panel/article/form";
    }
        /* 
        //Obtener las categorias de base de datos
        List<TagsType> categorias = categoriaTagService.ObtenerTodasCategorias();
        model.addAttribute("Categorias", categorias);
		model.addAttribute("Article", new Article());
        */

		
	

    /*SAVE CON CATEGORIAS 
    @PostMapping("/article/save")
	public String save(@ModelAttribute("article") Article article, @RequestParam("img") MultipartFile multipartFile, @RequestParam("subcategorias") List<Long> subcategoriaIds, Model model) throws IOException {
        article.setCreationDate(new Date());
        int id=articleService.save(article, multipartFile,subcategoriaIds);
		if(id==0) {
			return "panel/article/form";
		}
		return "redirect:/article/list";
	}
    */

    @PostMapping("/article/save")
	public String save(@ModelAttribute("article") Article article, @RequestParam("img") MultipartFile multipartFile, Model model) throws IOException {
        int id=articleService.save(article, multipartFile);
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

    /*
    @GetMapping("/favorites")
    public String showIndex(Model model) {
    model.addAttribute("recommendedNews", getRecommendedNews());
    return "favorites";
    }

    // Configuración de la conexión a la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_tfgalba";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    // Consulta para obtener las noticias recomendadas
    private static final String SQL_QUERY = "SELECT ua.article_id, a.s_title, a.image " +
            "FROM user_article_like ua " +
            "JOIN article a ON ua.article_id = a.id " +
            "WHERE ua.Active = 1 " +
            "GROUP BY ua.article_id ";

    public List<Article> getRecommendedNews() {
        List<Article> recommendedNews = new ArrayList<>();
        try {
            // Establecer la conexión a la base de datos
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Crear el objeto Statement para ejecutar la consulta
            Statement statement = conn.createStatement();
            // Ejecutar la consulta
            try (ResultSet resultSet = statement.executeQuery(SQL_QUERY)) {
                while (resultSet.next()) {
                    // Obtener los valores de las columnas del resultado
                    Long articleId = resultSet.getLong("articleid");
                    String title = resultSet.getString("title");
                    String image = resultSet.getString("image");

                    // Crear el objeto Article y añadirlo a la lista
                    Article news = new Article(articleId,title,image);
                    //news.setTotalLikes(totalLikes);
                    recommendedNews.add(news);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recommendedNews;
    }

    /*public static void main(String[] args) {
        ArticleController newsController = new ArticleController();
        List<Article> recommendedNews = newsController.getRecommendedNews();

        // Mostrar las noticias recomendadas
        for (Article news : recommendedNews) {
            System.out.println("title: " + news.getTitle());
            //System.out.println("likes: " + news.getTotalLikes());
            System.out.println("image: " + news.getImage());
            System.out.println();
        }
    }*/
}
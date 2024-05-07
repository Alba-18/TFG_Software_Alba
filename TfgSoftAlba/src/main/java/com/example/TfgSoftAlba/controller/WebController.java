package com.example.TfgSoftAlba.controller;

import com.example.TfgSoftAlba.models.entity.Article;
import com.example.TfgSoftAlba.models.entity.Rol;
import com.example.TfgSoftAlba.models.entity.User;
import com.example.TfgSoftAlba.models.repository.ArticleRepository;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;

@Controller
public class WebController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    //private ArticleRepository articleRepository;

    @Autowired
    private UserArticleLikeService userArticleLikeService;

    @GetMapping ({"/","/index"})
    public String index(Model model){
        
        findPaginated(1,null,null,null, model);

        return "index";
    }


    @GetMapping ({"/filter"})
    public String filter(Model model,
                         @ModelAttribute(value = "fecha") String selectedDate,
                         @ModelAttribute(value = "localizacion") String selectedLocation,
                         @ModelAttribute(value = "categoria") String selectedType) {

        // Verificar y convertir valores de filtro nulos o vacíos a null
        selectedDate = normalizeFilterValue(selectedDate);
        selectedLocation = normalizeFilterValue(selectedLocation);
        selectedType = normalizeFilterValue(selectedType);
 
        return findPaginated(1, selectedDate, selectedLocation, selectedType, model);
        //return "index";
    }

    // Método para normalizar los valores de filtro
    private String normalizeFilterValue(String value) {
        return (value != null && value.isEmpty()) ? null : value;
    }



    /*@GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam(value = "selectedDate", required = false) String selectedDate,
                                @RequestParam(value = "selectedLocation", required = false) String selectedLocation,
                                @RequestParam(value = "selectedType", required = false) String selectedType,
                                Model model) {
        
        System.out.println("Categoria: " + selectedType);
        System.out.println("Localizacion: " + selectedLocation);
        System.out.println("Fecha: " + selectedDate);
        int pageSize = 6;

        Page<Article> page = null;

        // Filtrar artículos si se proporcionan los parámetros de filtro
        List<Article> filteredArticles = null;
        if (selectedDate != null || selectedLocation != null || selectedType != null) {
            filteredArticles = articleService.findByCreationDateAndLocationAndType(selectedDate, selectedLocation, selectedType);
        }

        // Si no se proporcionan parámetros de filtro o si la lista filtrada está vacía, obtener artículos paginados
        if (filteredArticles != null && !filteredArticles.isEmpty()) {
            // Utiliza el número de página y el tamaño de página para obtener una página específica
            page = new PageImpl<>(filteredArticles.subList(pageNo * pageSize, Math.min((pageNo + 1) * pageSize, filteredArticles.size())), PageRequest.of(pageNo, pageSize), filteredArticles.size());
        } else {
            // Si no se proporcionan parámetros de filtro o si la lista filtrada está vacía, obtener artículos paginados
            page = articleService.findPaginated(pageNo, pageSize);

            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
        }    
        model.addAttribute("Articles", page.getContent());

        model.addAttribute("fechasCreacion", page.getContent().stream()
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

        return "index";}*/
    
    /*@GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam(value = "selectedDate", required = false) String selectedDate,
                                @RequestParam(value = "selectedLocation", required = false) String selectedLocation,
                                @RequestParam(value = "selectedType", required = false) String selectedType,
                                Model model) {
    
        System.out.println("Categoria: " + selectedType);
        System.out.println("Localizacion: " + selectedLocation);
        System.out.println("Fecha: " + selectedDate);
    
        // Obtener la lista filtrada
        List<Article> filteredArticles = articleService.findByCreationDateAndLocationAndType(selectedDate, selectedLocation, selectedType);
    
        // Crear la página paginada usando la lista filtrada
        Page<Article> page = findPaginated2(pageNo, 6, filteredArticles);
    
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
           
        model.addAttribute("Articles", page.getContent());

        model.addAttribute("fechasCreacion", page.getContent().stream()
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
    }*/

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                            @RequestParam(value = "fecha", required = false) String selectedDate,
                            @RequestParam(value = "localizacion", required = false) String selectedLocation,
                            @RequestParam(value = "categoria", required = false) String selectedType,
                            Model model) {

         // Configurar el número de artículos por página
        int pageSize = 6;

        // Obtener la lista filtrada de artículos
        List<Article> filteredArticles = articleService.findByCreationDateAndLocationAndType(selectedDate, selectedLocation, selectedType);

        int totalPages = (int) Math.ceil((double) filteredArticles.size() / pageSize);

        int start = (pageNo - 1) * pageSize;
        int end = Math.min(start + pageSize, filteredArticles.size());
        List<Article> pageArticles = filteredArticles.subList(start, end);

        // Setear los atributos del modelo
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("Articles", pageArticles);
        model.addAttribute("fechasCreacion", filteredArticles.stream()
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



    /*public Page<Article> findPaginated2(int pageNo, int pageSize, List<Article> filteredArticles) {
        // Asegúrate de que la lista filtrada no sea nula
        if (filteredArticles == null) {
            return new PageImpl<>(Collections.emptyList(), PageRequest.of(0, pageSize), 0);
        }
    
        int totalElements = filteredArticles.size();
        int fromIndex = (pageNo-1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalElements);
        List<Article> content = filteredArticles.subList(fromIndex, toIndex);
    
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        return new PageImpl<>(content, pageable, totalElements);
    }*/


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
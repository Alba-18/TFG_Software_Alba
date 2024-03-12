package com.example.TfgSoftAlba.models.service.Impl;

import com.example.TfgSoftAlba.models.entity.Article;
import com.example.TfgSoftAlba.models.entity.Tag;
import com.example.TfgSoftAlba.models.repository.ArticleRepository;
import com.example.TfgSoftAlba.models.repository.TagRepository;
import com.example.TfgSoftAlba.models.service.ArticleService;
import com.example.TfgSoftAlba.util.CustomUserDetails;
import com.example.TfgSoftAlba.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;


    @Override
    public List<Article> listAllArticles() {
        return articleRepository.findAll();
    }

    public ArrayList<Article> getArticlesByIds(ArrayList<Long> ArticleIds) {
        return articleRepository.findByIdIn(ArticleIds);
    }

    @Override
    public Article get(Long id) {
        return articleRepository.findById(id).get();
    }


    @Override
	public int save(Article article, MultipartFile multipartFile, List<Long>selectedTagIds) throws IOException {
		int res=0;

		Article newArticle = articleRepository.save(article);

        if (newArticle != null) {
            res = 1;

            // Asocia los tags seleccionados con el artículo
            updateArticleTags(newArticle.getId(), selectedTagIds);

             // Lógica para guardar la imagen del artículo
            String fileName = newArticle.getId().toString();
             if (!multipartFile.isEmpty()) {
                FileUploadUtil.saveImage(fileName, multipartFile);
                newArticle.setImage(fileName);
            } 
            // Actualiza el artículo para reflejar las asociaciones de subcategorías
            newArticle = articleRepository.save(newArticle);
        }
        articleRepository.save(newArticle);

        return res;
    }

    /* 
    public int save(Article article, MultipartFile multipartFile, List<Long> subcategorias) throws IOException {
        int res=0;
        Article newArticle = articleRrepository.save(article);
        if(!newArticle.equals(null)) {
            res=1; 
            String fileName = newArticle.getId().toString();

            if(!multipartFile.isEmpty()){
                FileUploadUtil.saveImage(fileName, multipartFile);
            }            
            newArticle.setImage(fileName);
            articleRrepository.save(newArticle); 
        }
        return res;
    }  */  

		//if(!newArticle.equals(null)) {
		//	res=1; 
        //    String fileName = newArticle.getId().toString();

        //    if(!multipartFile.isEmpty()){
        //        FileUploadUtil.saveImage(fileName, multipartFile);
        //    }            
        //    newArticle.setImage(fileName);
        //    articleRrepository.save(newArticle); 
		//}
	

    private void updateArticleTags(Long articleid, List<Long> selectedTagIds) {
        Article article = articleRepository.findById(articleid).orElse(null);
        if (article != null){
            Set<Tag> tags = new HashSet<>(tagRepository.findAllById(selectedTagIds));
            article.setTags(tags);
        }
    }

    @Override
    public Optional<Article> edit(Long id) {
        return articleRepository.findById(id);
    }

    
    @Override
	public void delete(Long id) {
		articleRepository.deleteById(id);		
	}


    @Override
    public Page<Article> findPaginated(int pageNo, int pageSize) {

        //verifica si hay usuario con sesion iniciada
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal =  auth.getPrincipal();
        Long idUser = null; 
        if (principal instanceof CustomUserDetails)  idUser = ((CustomUserDetails)principal).getId(); 
    
        List<Article> allContent = articleRepository.findAll();

        int totalElements = allContent.size();
        int fromIndex = (pageNo - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalElements);
        List<Article> content = allContent.subList(fromIndex, toIndex);

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Article> page = new PageImpl<>(content, pageable, totalElements);
        
        return page;
    }

    public List<Article> findByCreationDate(String selectedMonthYear) {
        // Convertir la cadena selectedMonthYear a LocalDate
        LocalDate startDate = LocalDate.parse(selectedMonthYear + "-01", DateTimeFormatter.ofPattern("MM/yyyy-dd"));
        LocalDate endDate = startDate.plusMonths(1).minusDays(1); // Obtener el último día del mes

        // Consultar los artículos en el rango de fechas correspondiente al mes y año proporcionados
        return articleRepository.findByCreationDateBetween(startDate, endDate);
    }

}

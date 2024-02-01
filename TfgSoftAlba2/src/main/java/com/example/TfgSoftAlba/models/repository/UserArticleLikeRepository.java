package com.example.TfgSoftAlba.models.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TfgSoftAlba.models.entity.UserArticleLike;

//@Repository
public interface UserArticleLikeRepository extends JpaRepository<UserArticleLike,Long>{

    Optional<UserArticleLike> findByUserIdAndArticleId(Long userId, Long articleId);

    @Query(value = "SELECT article_id FROM user_article_like WHERE user_id = :userId AND active = true", nativeQuery=true)
    List<Long> findNewsIdsByUserIdAndLiked(@Param("userId")Long userId);  
}

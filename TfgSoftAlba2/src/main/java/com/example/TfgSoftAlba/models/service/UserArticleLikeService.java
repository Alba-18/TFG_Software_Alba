package com.example.TfgSoftAlba.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TfgSoftAlba.models.repository.UserArticleLikeRepository;

@Service
public class UserArticleLikeService {
    
    private final UserArticleLikeRepository userArticleLikeRepository;

    public UserArticleLikeService(UserArticleLikeRepository userArticleLikeRepository) {
        this.userArticleLikeRepository = userArticleLikeRepository;
    }

    public List<Long> getLikedNewsIdsByUserId(Long userId) {
        List<Long> likedNewsIds= userArticleLikeRepository.findNewsIdsByUserIdAndLiked(userId);
        return likedNewsIds;
    }

}

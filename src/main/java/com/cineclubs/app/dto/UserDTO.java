package com.cineclubs.app.dto;

import com.cineclubs.app.models.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private String clerkId;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int postsCount;
    private int commentsCount;
    private int likedPostsCount;
    private int likedCommentsCount;
    private List<PostDTO> posts;

    public UserDTO(User user) {
        this(user, false);
    }

    public UserDTO(User user, boolean includePosts) {
        this.clerkId = user.getClerkId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.imageUrl = user.getImageUrl();
        this.username = user.getUsername();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.postsCount = user.getPosts() != null ? user.getPosts().size() : 0;
        this.commentsCount = user.getComments() != null ? user.getComments().size() : 0;
        this.likedPostsCount = user.getLikedPosts() != null ? user.getLikedPosts().size() : 0;
        this.likedCommentsCount = user.getLikedComments() != null ? user.getLikedComments().size() : 0;

        if (includePosts && user.getPosts() != null) {
            this.posts = user.getPosts().stream()
                    .map(post -> new PostDTO(post, user.getClerkId()))
                    .collect(Collectors.toList());
        }
    }

    public String getClerkId() {
        return clerkId;
    }

    public void setClerkId(String clerkId) {
        this.clerkId = clerkId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getLikedPostsCount() {
        return likedPostsCount;
    }

    public void setLikedPostsCount(int likedPostsCount) {
        this.likedPostsCount = likedPostsCount;
    }

    public int getLikedCommentsCount() {
        return likedCommentsCount;
    }

    public void setLikedCommentsCount(int likedCommentsCount) {
        this.likedCommentsCount = likedCommentsCount;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
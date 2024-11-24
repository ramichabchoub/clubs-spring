package com.cineclubs.app.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String clerkId;

    @Column(nullable = false)
    private String email;

    private String firstName;
    private String lastName;
    private String imageUrl;

    @Column(nullable = false)
    private String username;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "likes")
    private Set<Post> likedPosts;

    @ManyToMany(mappedBy = "likes")
    private Set<Comment> likedComments;

    public User() {
    }

    public User(String clerkId, String email, String firstName, String lastName,
            String imageUrl, String username, LocalDateTime createdAt,
            LocalDateTime updatedAt, Set<Post> posts, Set<Comment> comments,
            Set<Post> likedPosts, Set<Comment> likedComments) {
        this.clerkId = clerkId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.posts = posts;
        this.comments = comments;
        this.likedPosts = likedPosts;
        this.likedComments = likedComments;
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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public Set<Comment> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(Set<Comment> likedComments) {
        this.likedComments = likedComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(clerkId, user.clerkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clerkId);
    }

    @Override
    public String toString() {
        return "User{" +
                "clerkId='" + clerkId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", postsCount=" + (posts != null ? posts.size() : 0) +
                ", commentsCount=" + (comments != null ? comments.size() : 0) +
                ", likedPostsCount=" + (likedPosts != null ? likedPosts.size() : 0) +
                ", likedCommentsCount=" + (likedComments != null ? likedComments.size() : 0) +
                '}';
    }
}

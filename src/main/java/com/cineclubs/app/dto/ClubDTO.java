package com.cineclubs.app.dto;

import com.cineclubs.app.models.Club;
import java.util.List;
import java.util.stream.Collectors;

public class ClubDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer currentMembers;
    private String ownerId;
    private String ownerUsername;
    private String ownerImageUrl;
    private int membersCount;
    private int postsCount;
    private List<PostDTO> posts;
    private boolean isJoined;

    public ClubDTO(Club club, String currentUserId) {
        this(club, currentUserId, false);
    }

    public ClubDTO(Club club, String currentUserId, boolean includePosts) {
        this.id = club.getId();
        this.name = club.getName();
        this.description = club.getDescription();
        this.imageUrl = club.getImageUrl();
        this.currentMembers = club.getCurrentMembers();
        this.ownerId = club.getUser().getClerkId();
        this.ownerUsername = club.getUser().getUsername();
        this.ownerImageUrl = club.getUser().getImageUrl();
        this.membersCount = club.getMembers() != null ? club.getMembers().size() : 0;
        this.postsCount = club.getPosts() != null ? club.getPosts().size() : 0;
        this.isJoined = club.getMembers() != null &&
                club.getMembers().stream()
                        .anyMatch(member -> member.getClerkId().equals(currentUserId));

        if (includePosts && club.getPosts() != null) {
            this.posts = club.getPosts().stream()
                    .map(post -> new PostDTO(post, currentUserId))
                    .collect(Collectors.toList());
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCurrentMembers() {
        return currentMembers;
    }

    public void setCurrentMembers(Integer currentMembers) {
        this.currentMembers = currentMembers;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getOwnerImageUrl() {
        return ownerImageUrl;
    }

    public void setOwnerImageUrl(String ownerImageUrl) {
        this.ownerImageUrl = ownerImageUrl;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }
}
package com.cineclubs.app.services;

import com.cineclubs.app.dto.ClubDTO;
import com.cineclubs.app.models.Club;
import com.cineclubs.app.models.User;
import com.cineclubs.app.repository.ClubRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;

    public ClubService(ClubRepository clubRepository,
            UserService userService,
            SimpMessagingTemplate messagingTemplate) {
        this.clubRepository = clubRepository;
        this.userService = userService;
        this.messagingTemplate = messagingTemplate;
    }

    public List<ClubDTO> getAllClubs(String userId, boolean includePosts) {
        return clubRepository.findAll().stream()
                .map(club -> new ClubDTO(club, userId, includePosts))
                .collect(Collectors.toList());
    }

    public ClubDTO getClubDTOById(Long id, String userId, boolean includePosts) {
        Club club = getClubById(id);
        return new ClubDTO(club, userId, includePosts);
    }

    public ClubDTO createClub(Club club, String userId) {
        User user = userService.getUserByClerkId(userId);
        club.setUser(user);
        if (club.getMembers() == null) {
            club.setMembers(new HashSet<>());
        }
        club.getMembers().add(user);
        Club savedClub = clubRepository.save(club);

        ClubDTO clubDTO = new ClubDTO(savedClub, userId);
        messagingTemplate.convertAndSend("/topic/clubs", clubDTO);
        return clubDTO;
    }

    public ClubDTO updateClub(Long id, Club clubDetails, String userId) {
        Club club = getClubById(id);

        if (!club.getUser().getClerkId().equals(userId)) {
            throw new RuntimeException("Unauthorized to update this club");
        }

        club.setName(clubDetails.getName());
        club.setDescription(clubDetails.getDescription());
        club.setImageUrl(clubDetails.getImageUrl());
        club.setCurrentMembers(clubDetails.getCurrentMembers());

        Club updatedClub = clubRepository.save(club);
        ClubDTO clubDTO = new ClubDTO(updatedClub, userId);
        messagingTemplate.convertAndSend("/topic/clubs", clubDTO);
        return clubDTO;
    }

    public void deleteClub(Long id) {
        Club club = getClubById(id);
        clubRepository.delete(club);
        messagingTemplate.convertAndSend("/topic/clubs/delete", id);
    }

    public boolean isUserJoined(Club club, User user) {
        return club.getMembers().contains(user);
    }

    public void joinClub(String clerkId, Long clubId) {
        Club club = getClubById(clubId);
        User user = userService.getUserByClerkId(clerkId);
        boolean isJoined = isUserJoined(club, user);
        if (!isJoined) {
            club.getMembers().add(user);
            club.setCurrentMembers(club.getCurrentMembers() + 1);
            Club joinedClub = clubRepository.save(club);
            messagingTemplate.convertAndSend("/topic/clubs", joinedClub);
        }
    }

    public void leaveClub(String clerkId, Long clubId) {
        Club club = getClubById(clubId);
        User user = userService.getUserByClerkId(clerkId);
        boolean isJoined = isUserJoined(club, user);
        if (isJoined) {
            club.getMembers().remove(user);
            club.setCurrentMembers(club.getCurrentMembers() - 1);
            Club joinedClub = clubRepository.save(club);
            messagingTemplate.convertAndSend("/topic/clubs", joinedClub);
        }
    }

    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found with id: " + id));
    }
}

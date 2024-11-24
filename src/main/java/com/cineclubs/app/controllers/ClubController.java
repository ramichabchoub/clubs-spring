package com.cineclubs.app.controllers;

import com.cineclubs.app.dto.ClubDTO;
import com.cineclubs.app.models.Club;
import com.cineclubs.app.services.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<ClubDTO>> getAllClubs(
            @RequestParam(required = false) String userId,
            @RequestParam(defaultValue = "false") boolean includePosts) {
        return ResponseEntity.ok(clubService.getAllClubs(userId, includePosts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(
            @PathVariable Long id,
            @RequestParam(required = false) String userId,
            @RequestParam(defaultValue = "false") boolean includePosts) {
        return ResponseEntity.ok(clubService.getClubDTOById(id, userId, includePosts));
    }

    @PostMapping
    public ResponseEntity<ClubDTO> createClub(
            @RequestBody Club club,
            @RequestParam String userId) {
        return ResponseEntity.ok(clubService.createClub(club, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubDTO> updateClub(@PathVariable Long id, @RequestBody Club club,
            @RequestParam String clerkId) {
        return ResponseEntity.ok(clubService.updateClub(id, club, clerkId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/join")
    public ResponseEntity<Void> joinClub(@RequestParam String clerkId, @RequestParam Long clubId) {
        clubService.joinClub(clerkId, clubId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/leave")
    public ResponseEntity<Void> leaveClub(@RequestParam String clerkId, @RequestParam Long clubId) {
        clubService.leaveClub(clerkId, clubId);
        return ResponseEntity.noContent().build();
    }
}

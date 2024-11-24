package com.cineclubs.app.controllers;

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
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club, @RequestParam String clerkId) {
        return ResponseEntity.ok(clubService.createClub(club, clerkId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club club,
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

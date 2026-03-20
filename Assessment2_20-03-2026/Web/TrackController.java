package com.example.demo.Web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entities.Track;
import com.example.demo.repo.TrackRepository;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @PostMapping
    public ResponseEntity<String> addTrack(@RequestBody Track track) {
        trackRepository.save(track);
        return ResponseEntity.ok("Track added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Track>> getTracks() {
        List<Track> list = trackRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Track>> getTracksByTitle(@PathVariable String title) {
        List<Track> list = trackRepository.findByTitle(title);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTrack(@PathVariable Long id) {
        Optional<Track> track = trackRepository.findById(id);

        if (track.isPresent()) {
            return ResponseEntity.ok(track.get());
        } else {
            return ResponseEntity.status(404).body("Track not found");
            
        }
    }
}

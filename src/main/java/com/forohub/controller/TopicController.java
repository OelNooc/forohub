package com.forohub.controller;

import com.forohub.entity.Topic;
import com.forohub.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody @Valid Topic topic) {

        if (topicService.isDuplicateTopic(topic.getTitle(), topic.getMessage())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Topic with the same title and message already exists.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.createTopic(topic));
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        return topicService.getTopicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody @Valid Topic topic) {
        Optional<Topic> existingTopic = topicService.getTopicById(id);
        if (existingTopic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topic updatedTopic = existingTopic.get();
        updatedTopic.setTitle(topic.getTitle());
        updatedTopic.setMessage(topic.getMessage());
        updatedTopic.setAuthor(topic.getAuthor());
        updatedTopic.setCourse(topic.getCourse());

        Topic savedTopic = topicService.createTopic(updatedTopic);
        return ResponseEntity.ok(savedTopic);
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}

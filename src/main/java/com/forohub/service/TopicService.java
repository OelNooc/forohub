package com.forohub.service;

import com.forohub.entity.Topic;
import com.forohub.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic updateTopic(Long id, Topic topicDetails) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topic.setTitle(topicDetails.getTitle());
        topic.setMessage(topicDetails.getMessage());
        topic.setAuthor(topicDetails.getAuthor());
        topic.setCourse(topicDetails.getCourse());

        return topicRepository.save(topic);
    }

    public void deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topicRepository.delete(topic);
    }

    public boolean isDuplicateTopic(String title, String message) {
        return topicRepository.existsByTitleAndMessage(title, message);
    }
}

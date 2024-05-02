package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Topic;
@Service("topicService")
public interface TopicService {
	public int insertTopic(Topic topic);

	public int updateTopic(Topic topic);

	public int deleteTopic(String topicid);

	public int deleteTopicByIds(String[] ids);

	public List<Topic> getAllTopic();

	public List<Topic> getTopicByCond(Topic topic);

	public List<Topic> getTopicByLike(Topic topic);

	public Topic getTopicById(String topicid);

}


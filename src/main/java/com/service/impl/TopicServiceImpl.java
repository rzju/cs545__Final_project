package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.TopicDAO;
import com.entity.Topic;
import com.service.TopicService;

@Service("topicService") //
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TopicDAO topicDAO;
	@Override
	public int insertTopic(Topic topic) {
		return this.topicDAO.insertTopic(topic);
	}

	@Override
	public int updateTopic(Topic topic) {
		return this.topicDAO.updateTopic(topic);
	}

	@Override
	public int deleteTopic(String topicid) {
		return this.topicDAO.deleteTopic(topicid);
	}

	@Override
	public int deleteTopicByIds(String[] ids) {
		return this.topicDAO.deleteTopicByIds(ids);
	}

	@Override
	public List<Topic> getAllTopic() {
		return this.topicDAO.getAllTopic();
	}

	@Override
	public List<Topic> getTopicByCond(Topic topic) {
		return this.topicDAO.getTopicByCond(topic);
	}

	@Override
	public List<Topic> getTopicByLike(Topic topic) {
		return this.topicDAO.getTopicByLike(topic);
	}

	@Override
	public Topic getTopicById(String topicid) {
		return this.topicDAO.getTopicById(topicid);
	}

}


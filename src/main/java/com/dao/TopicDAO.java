package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Topic;

@Repository("topicDAO")
@Mapper
public interface TopicDAO {

	public int insertTopic(Topic topic);

	public int updateTopic(Topic topic);

	public int deleteTopic(String topicid);

	public int deleteTopicByIds(String[] ids);

	public List<Topic> getAllTopic();

	public List<Topic> getTopicByCond(Topic topic);

	public List<Topic> getTopicByLike(Topic topic);

	public Topic getTopicById(String topicid);

}



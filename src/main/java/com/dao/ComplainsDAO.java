package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Complains;

@Repository("complainsDAO")
@Mapper
public interface ComplainsDAO {

	public int insertComplains(Complains complains);

	public int updateComplains(Complains complains);

	public int deleteComplains(String complainsid);

	public int deleteComplainsByIds(String[] ids);

	public List<Complains> getAllComplains();

	public List<Complains> getComplainsByCond(Complains complains);

	public List<Complains> getComplainsByLike(Complains complains);

	public Complains getComplainsById(String complainsid);

}



package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Details;

@Repository("detailsDAO")
@Mapper
public interface DetailsDAO {

	public int insertDetails(Details details);

	public int updateDetails(Details details);

	public int deleteDetails(String detailsid);

	public int deleteDetailsByIds(String[] ids);

	public List<Details> getAllDetails();

	public List<Details> getDetailsByCond(Details details);

	public List<Details> getDetailsByLike(Details details);

	public Details getDetailsById(String detailsid);

}



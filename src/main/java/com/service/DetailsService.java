package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Details;
@Service("detailsService")
public interface DetailsService {
	public int insertDetails(Details details);

	public int updateDetails(Details details);

	public int deleteDetails(String detailsid);

	public int deleteDetailsByIds(String[] ids);

	public List<Details> getAllDetails();

	public List<Details> getDetailsByCond(Details details);

	public List<Details> getDetailsByLike(Details details);

	public Details getDetailsById(String detailsid);

}


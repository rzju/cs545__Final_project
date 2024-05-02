package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.DetailsDAO;
import com.entity.Details;
import com.service.DetailsService;

@Service("detailsService") //
public class DetailsServiceImpl implements DetailsService {
	@Autowired
	private DetailsDAO detailsDAO;
	@Override
	public int insertDetails(Details details) {
		return this.detailsDAO.insertDetails(details);
	}

	@Override
	public int updateDetails(Details details) {
		return this.detailsDAO.updateDetails(details);
	}

	@Override
	public int deleteDetails(String detailsid) {
		return this.detailsDAO.deleteDetails(detailsid);
	}

	@Override
	public int deleteDetailsByIds(String[] ids) {
		return this.detailsDAO.deleteDetailsByIds(ids);
	}

	@Override
	public List<Details> getAllDetails() {
		return this.detailsDAO.getAllDetails();
	}

	@Override
	public List<Details> getDetailsByCond(Details details) {
		return this.detailsDAO.getDetailsByCond(details);
	}

	@Override
	public List<Details> getDetailsByLike(Details details) {
		return this.detailsDAO.getDetailsByLike(details);
	}

	@Override
	public Details getDetailsById(String detailsid) {
		return this.detailsDAO.getDetailsById(detailsid);
	}

}


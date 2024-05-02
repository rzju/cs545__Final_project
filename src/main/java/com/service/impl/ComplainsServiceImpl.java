package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.ComplainsDAO;
import com.entity.Complains;
import com.service.ComplainsService;

@Service("complainsService") //
public class ComplainsServiceImpl implements ComplainsService {
	@Autowired
	private ComplainsDAO complainsDAO;
	@Override
	public int insertComplains(Complains complains) {
		return this.complainsDAO.insertComplains(complains);
	}

	@Override
	public int updateComplains(Complains complains) {
		return this.complainsDAO.updateComplains(complains);
	}

	@Override
	public int deleteComplains(String complainsid) {
		return this.complainsDAO.deleteComplains(complainsid);
	}

	@Override
	public int deleteComplainsByIds(String[] ids) {
		return this.complainsDAO.deleteComplainsByIds(ids);
	}

	@Override
	public List<Complains> getAllComplains() {
		return this.complainsDAO.getAllComplains();
	}

	@Override
	public List<Complains> getComplainsByCond(Complains complains) {
		return this.complainsDAO.getComplainsByCond(complains);
	}

	@Override
	public List<Complains> getComplainsByLike(Complains complains) {
		return this.complainsDAO.getComplainsByLike(complains);
	}

	@Override
	public Complains getComplainsById(String complainsid) {
		return this.complainsDAO.getComplainsById(complainsid);
	}

}


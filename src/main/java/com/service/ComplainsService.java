package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Complains;
@Service("complainsService")
public interface ComplainsService {
	public int insertComplains(Complains complains);

	public int updateComplains(Complains complains);

	public int deleteComplains(String complainsid);

	public int deleteComplainsByIds(String[] ids);

	public List<Complains> getAllComplains();

	public List<Complains> getComplainsByCond(Complains complains);

	public List<Complains> getComplainsByLike(Complains complains);

	public Complains getComplainsById(String complainsid);

}


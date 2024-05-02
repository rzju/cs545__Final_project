package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.FavDAO;
import com.entity.Fav;
import com.service.FavService;

@Service("favService") //
public class FavServiceImpl implements FavService {
	@Autowired
	private FavDAO favDAO;
	@Override
	public int insertFav(Fav fav) {
		return this.favDAO.insertFav(fav);
	}

	@Override
	public int updateFav(Fav fav) {
		return this.favDAO.updateFav(fav);
	}

	@Override
	public int deleteFav(String favid) {
		return this.favDAO.deleteFav(favid);
	}

	@Override
	public int deleteFavByIds(String[] ids) {
		return this.favDAO.deleteFavByIds(ids);
	}

	@Override
	public List<Fav> getAllFav() {
		return this.favDAO.getAllFav();
	}

	@Override
	public List<Fav> getFavByCond(Fav fav) {
		return this.favDAO.getFavByCond(fav);
	}

	@Override
	public List<Fav> getFavByLike(Fav fav) {
		return this.favDAO.getFavByLike(fav);
	}

	@Override
	public Fav getFavById(String favid) {
		return this.favDAO.getFavById(favid);
	}

}


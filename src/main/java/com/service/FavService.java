package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Fav;
@Service("favService")
public interface FavService {
	public int insertFav(Fav fav);

	public int updateFav(Fav fav);

	public int deleteFav(String favid);

	public int deleteFavByIds(String[] ids);

	public List<Fav> getAllFav();

	public List<Fav> getFavByCond(Fav fav);

	public List<Fav> getFavByLike(Fav fav);

	public Fav getFavById(String favid);

}


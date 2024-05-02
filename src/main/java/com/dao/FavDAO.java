package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Fav;

@Repository("favDAO")
@Mapper
public interface FavDAO {

	public int insertFav(Fav fav);

	public int updateFav(Fav fav);

	public int deleteFav(String favid);

	public int deleteFavByIds(String[] ids);

	public List<Fav> getAllFav();

	public List<Fav> getFavByCond(Fav fav);

	public List<Fav> getFavByLike(Fav fav);

	public Fav getFavById(String favid);

}



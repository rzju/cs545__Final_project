package com.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Foods;

@Service("foodsService")
public interface FoodsService {
	public int insertFoods(Foods foods);

	public int updateFoods(Foods foods);

	public int deleteFoods(String foodsid);

	public int deleteFoodsByIds(String[] ids);

	public List<Foods> getAllFoods();

	public List<Foods> getFoodsByNews();

	public List<Foods> getFoodsByHot();

	public List<Foods> getFoodsByCate(String cateid);

	public List<Foods> getFoodsByCond(Foods foods);

	public List<Foods> getFoodsByLike(Foods foods);

	public Foods getFoodsById(String foodsid);

}

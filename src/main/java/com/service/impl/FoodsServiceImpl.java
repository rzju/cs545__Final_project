package com.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.FoodsDAO;
import com.entity.Foods;
import com.service.FoodsService;

@Service("foodsService") //
public class FoodsServiceImpl implements FoodsService {
	@Autowired
	private FoodsDAO foodsDAO;

	@Override
	public int insertFoods(Foods foods) {
		return this.foodsDAO.insertFoods(foods);
	}

	@Override
	public int updateFoods(Foods foods) {
		return this.foodsDAO.updateFoods(foods);
	}

	@Override
	public int deleteFoods(String foodsid) {
		return this.foodsDAO.deleteFoods(foodsid);
	}

	@Override
	public int deleteFoodsByIds(String[] ids) {
		return this.foodsDAO.deleteFoodsByIds(ids);
	}

	@Override
	public List<Foods> getAllFoods() {
		return this.foodsDAO.getAllFoods();
	}

	@Override
	public List<Foods> getFoodsByNews() {
		return this.foodsDAO.getFoodsByNews();
	}

	@Override
	public List<Foods> getFoodsByHot() {
		return this.foodsDAO.getFoodsByHot();
	}

	@Override
	public List<Foods> getFoodsByCate(String cateid) {
		return this.foodsDAO.getFoodsByCate(cateid);
	}

	@Override
	public List<Foods> getFoodsByCond(Foods foods) {
		return this.foodsDAO.getFoodsByCond(foods);
	}

	@Override
	public List<Foods> getFoodsByLike(Foods foods) {
		return this.foodsDAO.getFoodsByLike(foods);
	}

	@Override
	public Foods getFoodsById(String foodsid) {
		return this.foodsDAO.getFoodsById(foodsid);
	}

}

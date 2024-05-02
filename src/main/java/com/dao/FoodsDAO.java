package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Foods;

@Repository("foodsDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到FoodsServiceImpl中
public interface FoodsDAO {

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

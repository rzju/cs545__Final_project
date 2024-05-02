package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Cate;

@Repository("cateDAO")
@Mapper
public interface CateDAO {

	public int insertCate(Cate cate);

	public int updateCate(Cate cate);

	public int deleteCate(String cateid);

	public int deleteCateByIds(String[] ids);

	public List<Cate> getAllCate();

	public List<Cate> getCateFront();

	public List<Cate> getCateByCond(Cate cate);

	public List<Cate> getCateByLike(Cate cate);

	public Cate getCateById(String cateid);

}



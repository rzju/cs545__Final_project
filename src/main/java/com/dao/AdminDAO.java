package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Admin;

@Repository("adminDAO")
@Mapper
public interface AdminDAO {

	public int insertAdmin(Admin admin);

	public int updateAdmin(Admin admin);

	public int deleteAdmin(String adminid);

	public int deleteAdminByIds(String[] ids);

	public List<Admin> getAllAdmin();

	public List<Admin> getAdminByCond(Admin admin);

	public List<Admin> getAdminByLike(Admin admin);

	public Admin getAdminById(String adminid);

}



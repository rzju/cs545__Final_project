package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Users;

@Repository("usersDAO")
@Mapper
public interface UsersDAO {

	public int insertUsers(Users users);

	public int updateUsers(Users users);

	public int deleteUsers(String usersid);

	public int deleteUsersByIds(String[] ids);

	public List<Users> getAllUsers();

	public List<Users> getUsersByCond(Users users);

	public List<Users> getUsersByLike(Users users);

	public Users getUsersById(String usersid);

}



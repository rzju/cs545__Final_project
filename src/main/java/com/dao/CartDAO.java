package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Cart;

@Repository("cartDAO")
@Mapper
public interface CartDAO {

	public int insertCart(Cart cart);

	public int updateCart(Cart cart);

	public int deleteCart(String cartid);

	public int deleteCartByIds(String[] ids);

	public List<Cart> getAllCart();

	public List<Cart> getCartByCond(Cart cart);

	public List<Cart> getCartByLike(Cart cart);

	public Cart getCartById(String cartid);

}



package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Cart;
@Service("cartService")
public interface CartService {
	public int insertCart(Cart cart);

	public int updateCart(Cart cart);

	public int deleteCart(String cartid);

	public int deleteCartByIds(String[] ids);

	public List<Cart> getAllCart();

	public List<Cart> getCartByCond(Cart cart);

	public List<Cart> getCartByLike(Cart cart);

	public Cart getCartById(String cartid);

}


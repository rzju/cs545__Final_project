package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.CartDAO;
import com.entity.Cart;
import com.service.CartService;

@Service("cartService") //
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;
	@Override
	public int insertCart(Cart cart) {
		return this.cartDAO.insertCart(cart);
	}

	@Override
	public int updateCart(Cart cart) {
		return this.cartDAO.updateCart(cart);
	}

	@Override
	public int deleteCart(String cartid) {
		return this.cartDAO.deleteCart(cartid);
	}

	@Override
	public int deleteCartByIds(String[] ids) {
		return this.cartDAO.deleteCartByIds(ids);
	}

	@Override
	public List<Cart> getAllCart() {
		return this.cartDAO.getAllCart();
	}

	@Override
	public List<Cart> getCartByCond(Cart cart) {
		return this.cartDAO.getCartByCond(cart);
	}

	@Override
	public List<Cart> getCartByLike(Cart cart) {
		return this.cartDAO.getCartByLike(cart);
	}

	@Override
	public Cart getCartById(String cartid) {
		return this.cartDAO.getCartById(cartid);
	}

}


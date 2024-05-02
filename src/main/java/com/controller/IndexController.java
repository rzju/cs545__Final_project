package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.entity.Article;
import com.entity.Cart;
import com.entity.Cate;
import com.entity.Complains;
import com.entity.Details;
import com.entity.Fav;
import com.entity.Foods;
import com.entity.Items;
import com.entity.Orders;
import com.entity.Topic;
import com.entity.Users;
import com.github.pagehelper.Page;
import com.service.ArticleService;
import com.service.CartService;
import com.service.CateService;
import com.service.ComplainsService;
import com.service.DetailsService;
import com.service.FavService;
import com.service.FoodsService;
import com.service.OrdersService;
import com.service.TopicService;
import com.service.UsersService;
import com.util.VeDate;

@RestController
@RequestMapping(value = "/index", produces = "application/json; charset=utf-8")
@CrossOrigin
public class IndexController extends BaseController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CateService cateService;
	@Autowired
	private FoodsService foodsService;
	@Autowired
	private FavService favService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private DetailsService detailsService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ComplainsService complainsService;

	// TODO Auto-generated method stub

	@GetMapping(value = "front.action")
	public Map<String, Object> front() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Cate> cateList = this.cateService.getAllCate();
		map.put("cateList", cateList);
		List<Foods> hotList = this.foodsService.getFoodsByHot();
		map.put("hotList", hotList);
		return map;
	}

	@GetMapping(value = "index.action")
	public Map<String, Object> index() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Cate> cateList = this.cateService.getCateFront();
		List<Cate> frontList = new ArrayList<Cate>();
		for (Cate cate : cateList) {
			List<Foods> foodsList = this.foodsService.getFoodsByCate(cate.getCateid());
			cate.setFoodsList(foodsList);
			frontList.add(cate);
		}
		map.put("frontList", frontList);
		return map;
	}

	@GetMapping(value = "article.action")
	public Map<String, Object> article(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "20") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Article> list = this.articleService.getAllArticle();
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@GetMapping(value = "read.action")
	public Map<String, Object> read(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Article article = this.articleService.getArticleById(id);
		article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
		this.articleService.updateArticle(article);
		map.put("article", article);
		return map;
	}

	@PostMapping(value = "login.action")
	public Map<String, Object> login(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String username = obj.getString("username");
		String password = obj.getString("password");
		Users usersEntity = new Users();
		usersEntity.setUsername(username);
		List<Users> userslist = this.usersService.getUsersByCond(usersEntity);
		if (userslist.size() == 0) {
			map.put("success", false);
			map.put("message", "username doesn't exist");
		} else {
			Users users = userslist.get(0);
			if (password.equals(users.getPassword())) {
				map.put("success", true);
				map.put("message", "login success");
				map.put("userid", users.getUsersid());
				map.put("username", users.getUsername());
				map.put("realname", users.getRealname());
			} else {
				map.put("success", false);
				map.put("message", "wrong password");
			}
		}
		return map;
	}

	@PostMapping(value = "register.action")
	public Map<String, Object> register(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Users users = new Users();
		users.setUsername(obj.getString("username"));
		users.setPassword(obj.getString("password"));
		users.setSex(obj.getString("sex"));
		users.setBirthday(obj.getString("birthday"));
		users.setContact(obj.getString("contact"));
		users.setRealname(obj.getString("realname"));
		users.setRegdate(VeDate.getStringDateShort());
		int num = this.usersService.insertUsers(users);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "register success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "register fail");
		}
		return map;
	}

	@PostMapping(value = "editpwd.action")
	public Map<String, Object> editpwd(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String userid = obj.getString("userid");
		String password = obj.getString("password");
		String repassword = obj.getString("repassword");
		int num = 0;
		Users users = this.usersService.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			num = this.usersService.updateUsers(users);
			if (num > 0) {
				map.put("success", true);
				map.put("code", num);
				map.put("message", "success");
			} else {
				map.put("success", false);
				map.put("code", num);
				map.put("message", "fail");
			}
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "wrong password");
		}
		return map;
	}

	@GetMapping(value = "userinfo.action")
	public Map<String, Object> userinfo(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users users = this.usersService.getUsersById(id);
		map.put("users", users);
		return map;
	}

	@PostMapping(value = "personal.action")
	public Map<String, Object> personal(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Users users = this.usersService.getUsersById(obj.getString("usersid"));
		users.setUsername(obj.getString("username"));
		users.setSex(obj.getString("sex"));
		users.setBirthday(obj.getString("birthday"));
		users.setContact(obj.getString("contact"));
		users.setRealname(obj.getString("realname"));
		int num = this.usersService.updateUsers(users);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@GetMapping(value = "cart.action")
	public Map<String, Object> cart(String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Cart cart = new Cart();
		cart.setUsersid(userid);
		List<Cart> cartList = this.cartService.getCartByCond(cart);
		map.put("cartList", cartList);
		map.put("num", cartList.size());
		return map;
	}

	@RequestMapping(value = "deletecart.action")
	public Map<String, Object> deletecart(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.cartService.deleteCart(id);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@RequestMapping(value = "showOrders.action")
	public Map<String, Object> showOrders(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders = new Orders();
		orders.setUsersid(userid);
		Page<Orders> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Orders> list = this.ordersService.getOrdersByCond(orders);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@RequestMapping(value = "orderdetail.action")
	public Map<String, Object> orderdetail(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Details details = new Details();
		details.setOrdercode(id);
		List<Details> detailsList = this.detailsService.getDetailsByCond(details);
		map.put("detailsList", detailsList);
		return map;
	}

	@PostMapping(value = "addcart.action")
	public Map<String, Object> addcart(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Cart cart = new Cart();
		cart.setUsersid(obj.getString("userid"));
		cart.setAddtime(VeDate.getStringDateShort());
		cart.setFoodsid(obj.getString("foodsid"));
		cart.setNum(obj.getString("num"));
		cart.setPrice(obj.getString("price"));
		int num = this.cartService.insertCart(cart);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@RequestMapping(value = "checkout.action", method = RequestMethod.POST)
	public Map<String, Object> checkout(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Cart cart1 = new Cart();
		cart1.setUsersid(obj.getString("userid"));
		List<Cart> cartList = this.cartService.getCartByCond(cart1);
		int num = -1;
		if (cartList.size() == 0) {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		} else {
			String ordercode = "PD" + VeDate.getStringDatex();
			double total = 0;
			for (Cart cart : cartList) {
				Details details = new Details();
				details.setDetailsid(VeDate.getStringDatex() + (Math.random() * 9 + 1) * 1000);
				details.setFoodsid(cart.getFoodsid());
				details.setNum(cart.getNum());
				details.setOrdercode(ordercode);
				details.setPrice(cart.getPrice());
				this.detailsService.insertDetails(details);
				Foods foods = this.foodsService.getFoodsById(cart.getFoodsid());
				foods.setSellnum("" + (Integer.parseInt(foods.getSellnum()) + Integer.parseInt(cart.getNum())));
				this.foodsService.updateFoods(foods);
				total += Double.parseDouble(cart.getPrice()) * Double.parseDouble(cart.getNum());
				this.cartService.deleteCart(cart.getCartid());
			}
			Orders orders = new Orders();
			orders.setAddress(obj.getString("address"));
			orders.setAddtime(VeDate.getStringDateShort());
			orders.setContact(obj.getString("contact"));
			orders.setOrdercode(ordercode);
			orders.setReceiver(obj.getString("receiver"));
			orders.setStatus("未付款");
			orders.setTotal("" + VeDate.getDouble(total));
			orders.setUsersid(obj.getString("userid"));
			num = this.ordersService.insertOrders(orders);
		}
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@RequestMapping(value = "over.action")
	public Map<String, Object> over(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders = this.ordersService.getOrdersById(id);
		orders.setStatus("delivered");
		int num = this.ordersService.updateOrders(orders);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "delivered");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail delivery");
		}
		return map;
	}

	@RequestMapping(value = "pay.action")
	public Map<String, Object> pay(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders = this.ordersService.getOrdersById(id);
		orders.setStatus("paid");
		int num = this.ordersService.updateOrders(orders);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "pay success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "pay fail");
		}
		return map;
	}

	@RequestMapping(value = "cancel.action")
	public Map<String, Object> cancel(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders = this.ordersService.getOrdersById(id);
		orders.setStatus("cancelled");
		int num = this.ordersService.updateOrders(orders);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "cancelled");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "cancel fail");
		}
		return map;
	}

	@RequestMapping(value = "prePay.action")
	public Map<String, Object> prePay(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders = this.ordersService.getOrdersById(id);
		map.put("orders", orders);
		return map;
	}

	@PostMapping(value = "addfav.action")
	public Map<String, Object> addFav(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		int num = 0;
		Fav fav = new Fav();
		fav.setUsersid(obj.getString("userid"));
		fav.setFoodsid(obj.getString("foodsid"));
		List<Fav> list = this.favService.getFavByCond(fav);
		if (list.size() != 0) {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "You've added the item in your favorite.");
			return map;
		}
		fav.setAddtime(VeDate.getStringDateShort());
		num = this.favService.insertFav(fav);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	// 我的收藏
	@GetMapping(value = "myfav.action")
	public Map<String, Object> myFav(String userid, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Fav> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		Fav fav = new Fav();
		fav.setUsersid(userid);
		List<Fav> list = this.favService.getFavByCond(fav);
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@RequestMapping(value = "deletefav.action")
	public Map<String, Object> deleteFav(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.favService.deleteFav(id);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@PostMapping(value = "addComplains.action")
	public Map<String, Object> addComplains(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Complains complains = new Complains();
		complains.setAddtime(VeDate.getStringDateShort());
		complains.setStatus("no response");
		complains.setReps(" ");
		complains.setTitle(obj.getString("title"));
		complains.setContents(obj.getString("contents"));
		complains.setUsersid(obj.getString("usersid"));
		int num = this.complainsService.insertComplains(complains);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "success");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@RequestMapping(value = "myComplains.action")
	public Map<String, Object> myComplains(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer limit, String userid) {
		System.out.println("userid ==> " + userid);
		Map<String, Object> map = new HashMap<String, Object>();
		Complains complains = new Complains();
		complains.setUsersid(userid);
		Page<Complains> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Complains> complainsList = this.complainsService.getComplainsByCond(complains);
		map.put("count", pager.getTotal());
		map.put("total", complainsList.size());
		map.put("data", complainsList);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@RequestMapping(value = "cate.action")
	public Map<String, Object> cate(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer limit, String id) {
		System.out.println("cateid  ==>  " + id);
		Map<String, Object> map = new HashMap<String, Object>();
		Cate cate = this.cateService.getCateById(id);
		Foods foods = new Foods();
		foods.setCateid(id);
		Page<Foods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Foods> foodsList = this.foodsService.getFoodsByCond(foods);
		map.put("count", pager.getTotal());
		map.put("total", foodsList.size());
		map.put("data", foodsList);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		map.put("cate", cate);
		return map;
	}

	@RequestMapping(value = "recommend.action")
	public Map<String, Object> recommend(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Foods foods = new Foods();
		foods.setRecommend("是");
		Page<Foods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Foods> foodsList = this.foodsService.getFoodsByCond(foods);
		map.put("count", pager.getTotal());
		map.put("total", foodsList.size());
		map.put("data", foodsList);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@RequestMapping(value = "special.action")
	public Map<String, Object> special(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Foods foods = new Foods();
		foods.setSpecial("是");
		Page<Foods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Foods> foodsList = this.foodsService.getFoodsByCond(foods);
		map.put("count", pager.getTotal());
		map.put("total", foodsList.size());
		map.put("data", foodsList);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@RequestMapping(value = "all.action")
	public Map<String, Object> all(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Foods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Foods> foodsList = this.foodsService.getAllFoods();
		map.put("count", pager.getTotal());
		map.put("total", foodsList.size());
		map.put("data", foodsList);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@RequestMapping(value = "queryFoods.action")
	public Map<String, Object> queryFoods(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "12") Integer limit, String name) {
		System.out.println("name  ==>  " + name);
		Map<String, Object> map = new HashMap<String, Object>();
		Foods foods = new Foods();
		foods.setFoodsname(name);
		Page<Foods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);
		List<Foods> foodsList = this.foodsService.getFoodsByCond(foods);
		map.put("count", pager.getTotal());
		map.put("total", foodsList.size());
		map.put("data", foodsList);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	@RequestMapping(value = "detail.action")
	public Map<String, Object> detail(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Foods foods = this.foodsService.getFoodsById(id);
		foods.setHits("" + (Integer.parseInt(foods.getHits()) + 1));
		this.foodsService.updateFoods(foods);
		map.put("foods", foods);
		Topic topic = new Topic();
		topic.setFoodsid(id);
		List<Topic> topicList = this.topicService.getTopicByCond(topic);
		map.put("topicList", topicList);
		return map;
	}

	@RequestMapping(value = "preTopic.action")
	public Map<String, Object> preTopic(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders = this.ordersService.getOrdersById(id);
		map.put("orders", orders);
		String ordercode = orders.getOrdercode();
		Details details = new Details();
		details.setOrdercode(ordercode);
		List<Details> detailsList = this.detailsService.getDetailsByCond(details);
		List<Items> itemsList = new ArrayList<Items>();
		for (Details x : detailsList) {
			Items items = new Items();
			items.setDetailsid(x.getDetailsid());
			items.setFoodsid(x.getFoodsid());
			items.setFoodsname(x.getFoodsname());
			items.setOrdercode(ordercode);
			itemsList.add(items);
		}
		map.put("detailsList", itemsList);
		return map;
	}

	@PostMapping(value = "addTopic.action")
	public Map<String, Object> addTopic(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		Orders orders = this.ordersService.getOrdersById(obj.getString("ordersid"));
		orders.setStatus("reviewed");
		this.ordersService.updateOrders(orders);
		JSONArray detailsList = JSONArray.parseArray(obj.getString("detailsList"));
		int num = 0;
		for (int i = 0; i < detailsList.size(); i++) {
			JSONObject myJson = JSONObject.parseObject(detailsList.getString(i).toString());
			Topic topic = new Topic();
			topic.setAddtime(VeDate.getStringDate());
			topic.setContents(myJson.getString("contents"));
			topic.setFoodsid(myJson.getString("foodsid"));
			topic.setNum(myJson.getString("tnum"));
			topic.setOrdersid(orders.getOrdersid());
			topic.setUsersid(orders.getUsersid());
			topic.setTopicid("T" + VeDate.getStringId() + i);
			num += this.topicService.insertTopic(topic);
		}
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "saved");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "fail");
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/*", method = RequestMethod.OPTIONS)
	public ResponseEntity handleOptions() {
		return (ResponseEntity) ResponseEntity.noContent();
	}

	// TODO Auto-generated method stub
}

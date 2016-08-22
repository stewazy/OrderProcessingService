package com.stewazy.order.BO;

import com.stewazy.order.BO.exception.BOException;
import com.stewazy.order.DTO.Order;

public interface OrderBO {
	
	boolean placeOrder(Order order)throws BOException;
	boolean cancelOrder(int id)throws BOException;
	boolean deleteOrder(int id)throws BOException;

}

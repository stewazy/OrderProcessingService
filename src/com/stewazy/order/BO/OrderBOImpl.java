package com.stewazy.order.BO;

import java.sql.SQLException;

import com.stewazy.order.BO.exception.BOException;
import com.stewazy.order.DAO.OrderDAO;
import com.stewazy.order.DTO.Order;

public class OrderBOImpl implements OrderBO {
	private OrderDAO dao;

	@Override
	public boolean placeOrder(Order order) throws BOException {
		
		try {
			int result = dao.create(order);
			if (result ==0)
					{
						return false;
					}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean cancelOrder(int id) throws BOException {
		try {
			Order order = dao.read(id);
			order.setStatus("cancel");
			int result = dao.update(order);
			if (result ==0)
			{
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean deleteOrder(int id) throws BOException {
		try {
			int result = dao.delete(id);
			if (result ==0)
			{
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	public OrderDAO getDao() {
		return dao;
	}

	public void setDao(OrderDAO dao) {
		this.dao = dao;
	}

}

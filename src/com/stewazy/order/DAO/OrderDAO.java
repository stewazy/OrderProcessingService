package com.stewazy.order.DAO;

import java.sql.SQLException;
import com.stewazy.order.DTO.*;

public interface OrderDAO {
	int create (Order order) throws SQLException;
	Order read (int id) throws SQLException;
	int update (Order order) throws SQLException;
	int delete (int id) throws SQLException;
	

}

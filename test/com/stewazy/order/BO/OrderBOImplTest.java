package com.stewazy.order.BO;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.stewazy.order.BO.exception.BOException;
import com.stewazy.order.DAO.OrderDAO;
import com.stewazy.order.DTO.Order;

public class OrderBOImplTest {

	private static final int ORDER_ID = 123;
	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;

	@Before

	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}

	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {

		Order order=new Order();

		when(dao.create(order)).thenReturn(new Integer(1));

		boolean result = bo.placeOrder(order);
		assertTrue(result);
		verify(dao,atLeast(1)).create(order);


	}


	@Test
	public void placeOrder_Should_not_Create_An_Order() throws SQLException, BOException {
		Order order=new Order();

		when(dao.create(order)).thenReturn(new Integer(0));

		boolean result = bo.placeOrder(order);
		assertFalse(result);
		verify(dao).create(order);


	}
	
	
	@Test(expected = BOException.class)
	public void placeOrder_Should_throw_BOException() throws SQLException, BOException {
		Order order=new Order();

		when(dao.create(order)).thenThrow(SQLException.class);

		boolean result = bo.placeOrder(order);
		


	}
	
	@Test
	
	public void cancelOrder_shouldCancelTheOrder() throws SQLException, BOException
	{
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(ORDER_ID);
		
		assertTrue(result);
		
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);
		
	}
	
	@Test
	
	public void cancel_Order_shouldNotCancelTheOrder() throws SQLException, BOException
	{
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(ORDER_ID);
		
		assertFalse(result);
		
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);
		
	}
	
	
	@Test(expected=BOException.class)
	
	public void cancel_Order_shouldThrowBOExceptionOnRead() throws SQLException, BOException
	{
		
		when(dao.read(ORDER_ID)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDER_ID);
		
			
	}
	
	@Test(expected=BOException.class)
	
	public void cancel_Order_should_Throw_BOException_On_Update() throws SQLException, BOException
	{
		Order order = new Order();
		when(dao.read(anyInt())).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDER_ID);
		
		
		
	}
	
	@Test
	
	public void deleteOrder_deletes_The_Order() throws SQLException, BOException
	{
		when(dao.delete(ORDER_ID)).thenReturn(1);
		boolean result = bo.deleteOrder(ORDER_ID);
		assertTrue(result);
		verify(dao).delete(ORDER_ID);
	}

}

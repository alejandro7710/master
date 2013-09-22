package com.dsi.capa.test.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.dsi.capa.db.dao.ClienteDAO;
import com.dsi.capa.db.model.Cliente;

public class ClienteDAOTest {
	
	private ClienteDAO clienteDAO;
	
	@Before
	public void setUp() throws Exception {
		clienteDAO = new ClienteDAO();
	}

	@Test
	public void testInsert() {
		Random rand = new Random(System.currentTimeMillis());
		Cliente cliente = new Cliente();
		cliente.setNombres("MAURICIO " + rand.nextInt());
		cliente.setApellidos("JOVEL " + rand.nextInt());
		cliente.setSexo("M");
		clienteDAO.save(cliente);
		assertTrue(true);
	}
	
	@Test
	public void testSelectAll() {
		List<Cliente> cls = clienteDAO.findAll();
		assertNotNull(cls);
		assertFalse(cls.isEmpty());
	}
	
	@Test
	public void testSelectPage() {
		List<Cliente> cls = clienteDAO.findAll(0, 10);
		assertNotNull(cls);
		assertFalse(cls.isEmpty());
	}

}

package net.riomas.api.openfood;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class ProductRepositoryTest {

	@Test
	public void testGetAll() {
		ProductRepository prod = new ProductRepository();
		List<?> data = prod.getAll();
		//System.out.println( data );
		
		assertTrue("No results", data.size()>0);
	}

}

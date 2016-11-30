package net.riomas.api.openfood;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import net.riomas.api.openfood.controler.ProductRepository;
import net.riomas.api.openfood.model.Product;

public class ProductRepositoryTest {

	@Test
	public void testGetAll() {
		ProductRepository prod = new ProductRepository();
		List<?> data = prod.fetchProducts(10, 1, null, new long[0]);
		//System.out.println( data );
		
		assertTrue("No results", data.size()>0);
	}

	@Test
	public void testGetProduct() {
		long id = 1168;
		ProductRepository prod = new ProductRepository();
		Product product = prod.fetchById(id);
		//System.out.println( data );
		
		assertTrue("No results", product.getId()==id);
	}

}

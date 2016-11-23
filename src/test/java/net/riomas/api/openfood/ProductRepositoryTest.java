package net.riomas.api.openfood;

import org.junit.Test;

public class ProductRepositoryTest {

	@Test
	public void testGetAll() {
		ProductRepository prod = new ProductRepository();
		System.out.println( prod.getAll());
	}

}

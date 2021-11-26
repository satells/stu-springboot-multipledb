package com.multipledb;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multipledb.coupon.entities.Coupon;
import com.multipledb.coupon.repository.CouponRepository;
import com.multipledb.product.entities.Product;
import com.multipledb.product.repository.ProductRepository;

@SpringBootTest
class MultipleDbApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CouponRepository couponRepository;

	@Test
	void testCouponSave() {
		Coupon coupon = new Coupon();
		coupon.setCode("sale" + new Random().nextInt(10000));
		coupon.setDiscount(new BigDecimal(new Random().nextDouble()));
		coupon.setExpDate("01/12/2022");

		Coupon saved = couponRepository.save(coupon);
		System.out.println(saved);

	}

	@Test
	void testProductSave() {
		Product product = new Product();
		product.setCuponCode("extra");
		product.setDescription("iphone");
		product.setName("celular");
		product.setPrice(new BigDecimal(new Random().nextDouble() * 100));

		Product saved = productRepository.save(product);
		System.out.println(saved);
	}

}

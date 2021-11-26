package com.multipledb.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multipledb.coupon.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

}

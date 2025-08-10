package com.wipro.carms.repository;

import com.wipro.carms.entity.CarRegistrationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRegistrationDetailRepository extends JpaRepository<CarRegistrationDetail, Long> {
}

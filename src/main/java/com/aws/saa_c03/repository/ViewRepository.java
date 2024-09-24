package com.aws.saa_c03.repository;

import com.aws.saa_c03.domain.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<View,Integer> {

}

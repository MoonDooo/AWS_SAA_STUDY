package com.aws.saa_c03.repository;

import com.aws.saa_c03.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}

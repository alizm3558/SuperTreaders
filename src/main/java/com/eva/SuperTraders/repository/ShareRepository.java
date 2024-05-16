package com.eva.SuperTraders.repository;

import com.eva.SuperTraders.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {

    List<Share> findAll();

    Optional<Share> getShareById(Long id);

    Optional<Share> findBySymbol(String symbol);
}

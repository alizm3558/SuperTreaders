package com.eva.SuperTraders.repository;

import com.eva.SuperTraders.domain.entity.PortfolioItem;
import com.eva.SuperTraders.domain.entity.Share;
import com.eva.SuperTraders.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
    List<PortfolioItem> findByUser(User user);
    Optional<PortfolioItem> findByUserAndShare(User user, Share share);
}

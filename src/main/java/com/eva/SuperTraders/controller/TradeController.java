package com.eva.SuperTraders.controller;

import com.eva.SuperTraders.domain.entity.PortfolioItem;
import com.eva.SuperTraders.domain.entity.Share;
import com.eva.SuperTraders.domain.entity.TransactionHistory;
import com.eva.SuperTraders.domain.entity.User;
import com.eva.SuperTraders.repository.ShareRepository;
import com.eva.SuperTraders.repository.TransactionHistoryRepository;
import com.eva.SuperTraders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @PostMapping("/buy")
    public ResponseEntity<?> buyShare(@RequestParam Long userId, @RequestParam String symbol, @RequestParam int quantity) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOptional.get();

        Optional<Share> shareOptional = shareRepository.findBySymbol(symbol);
        if (!shareOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Share not found");
        }

        Share share = shareOptional.get();

        double price = share.getPrice();
        double totalCost = price * quantity;

        user.getPortfolio().add(new PortfolioItem(share, quantity, price));

        share.setRemainingQuantity(share.getRemainingQuantity() - quantity);
        shareRepository.save(share);

        TransactionHistory transaction = new TransactionHistory();
        transaction.setUser(user);
        transaction.setShare(share);
        transaction.setType("BUY");
        transaction.setQuantity(quantity);
        transaction.setPrice(price);
        transaction.setTimestamp(LocalDateTime.now());
        transactionHistoryRepository.save(transaction);

        return ResponseEntity.ok("Share bought successfully");
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellShare(@RequestParam Long userId, @RequestParam String symbol, @RequestParam int quantity) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOptional.get();

        Optional<Share> shareOptional = shareRepository.findBySymbol(symbol);
        if (!shareOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Share not found");
        }

        Share share = shareOptional.get();

        PortfolioItem portfolioItem = user.getPortfolio().stream()
                .filter(item -> item.getShare().getSymbol().equals(symbol))
                .findFirst()
                .orElse(null);

        if (portfolioItem == null || portfolioItem.getQuantity() < quantity) {
            return ResponseEntity.badRequest().body("Insufficient shares in portfolio");
        }

        double price = share.getPrice();
        double totalRevenue = price * quantity;

        // Update user's portfolio
        portfolioItem.setQuantity(portfolioItem.getQuantity() - quantity);
        if (portfolioItem.getQuantity() == 0) {
            user.getPortfolio().remove(portfolioItem);
        }

        share.setRemainingQuantity(share.getRemainingQuantity() + quantity);
        shareRepository.save(share);

        TransactionHistory transaction = new TransactionHistory();
        transaction.setUser(user);
        transaction.setShare(share);
        transaction.setType("SELL");
        transaction.setQuantity(quantity);
        transaction.setPrice(price);
        transaction.setTimestamp(LocalDateTime.now());
        transactionHistoryRepository.save(transaction);

        return ResponseEntity.ok("Share sold successfully");
    }
}

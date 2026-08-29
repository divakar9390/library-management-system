package com.example.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Loan;

public interface  LoanRepository extends JpaRepository<Loan, String> {
    
    boolean existsByUserUserIdAndBookBookIdAndStatus(String UserId, String BookId, String Borrowed);
    int countByUserUserIdAndStatus(String UserId,String Status);
    List<Loan> findByUserUserIdAndStatus(
        String userId,
        String status
    );
    List<Loan> findByUserUserId(String UserId);
    List<Loan> findByStatus(String status);
}

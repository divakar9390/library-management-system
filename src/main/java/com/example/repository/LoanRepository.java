package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Loan;

public interface  LoanRepository extends JpaRepository<Loan, String> {
    
    boolean existsByUserUserIdAndBookBookIdAndStatus(String UserId, String BookId, String Borrowed);
    int countByUserUserIdAndStatus(String UserId,String Status);
}

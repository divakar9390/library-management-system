package com.example.controller;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.request.LoanRequestDto;
import com.example.dto.request.LoanreturnRequestDto;
import com.example.dto.response.LoanResponseDto;
import com.example.service.LoanService;

@RestController
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;

    }

    @PostMapping("loans/borrow")
    public LoanResponseDto borrow(@RequestBody LoanRequestDto request){
       return  loanService.borrowBook(request);
    }
    @PostMapping("loans/return")
    public LoanResponseDto returned(@RequestBody LoanreturnRequestDto request ){
       return loanService.returnedBook(request);
    }

    @GetMapping("loans")
    public Page<LoanResponseDto> findAll(Pageable pageable){
        return loanService.findall(pageable);
    }

    @GetMapping("loans/user/{userId}")
    public List<LoanResponseDto> finaAllLoans(@PathVariable String userId){
        return loanService.userAllLoans(userId);
    }

    @GetMapping("loans/user/{userId}/{status}")
    public List<LoanResponseDto> findAllLoansByStatus(@PathVariable String userId,@PathVariable String status){
        return loanService.userLoansByStatus(userId,status);
    }

    @DeleteMapping("loans/delete/{loanId}")
    public void  delete(@PathVariable String loanId){
       
        loanService.deleteById(loanId);
    }
    
}

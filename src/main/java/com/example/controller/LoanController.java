package com.example.controller;

import com.example.service.LoanService;
import com.example.dto.request.LoanRequestDto;
import com.example.dto.response.LoanResponseDto;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @DeleteMapping("loans/delete/{id)")
    public void  delete(@PathVariable String LoanId){

        loanService.deleteById(LoanId);
    }
    
}

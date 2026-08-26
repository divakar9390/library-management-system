package com.example.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.request.LoanRequestDto;
import com.example.dto.response.LoanResponseDto;
import com.example.exception.ResourcesNotFoundException;
import com.example.exception.BussinessRuleException;
import com.example.model.Book;
import com.example.model.Loan;
import com.example.model.User;
import com.example.repository.BookRepository;
import com.example.repository.LoanRepository;
import com.example.repository.UserRepository;
import com.example.util.IDGenerator;

@Service
public class LoanService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    LoanService(UserRepository userRepository,BookRepository bookRepository,LoanRepository loanRepository){
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }
    @Transactional
    public LoanResponseDto borrowBook(LoanRequestDto request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(()->new ResourcesNotFoundException("User Not Found" +request.getUserId()));
        if(!user.getStatus().equalsIgnoreCase("ACTIVE")){
            throw new BussinessRuleException("User in not active " +request.getUserId());
        }

        Book book = bookRepository.findById(request.getBookId()).orElseThrow(()->new ResourcesNotFoundException("Book Not Found " +request.getBookId()));
        if(!book.getAvailability()){
            throw new BussinessRuleException("Book is not Available Right Now! "+request.getBookId());
        }

       boolean alreadyBorrowed =
        loanRepository.existsByUserUserIdAndBookBookIdAndStatus(
                request.getUserId(),
                request.getBookId(),
                "BORROWED"
        );

        if(alreadyBorrowed){
            throw new BussinessRuleException("Book is already Borrowed by the User " +request.getUserId());
        }
        
        int count = loanRepository.countByUserUserIdAndStatus(request.getUserId(),"BORROWED");

        if(count>=3){
            throw new BussinessRuleException("User Borrowing Limit Reached "+request.getUserId());
        }

        String LoanId;
        
        do{
            LoanId = IDGenerator.generatorLoanId();
        }while(loanRepository.existsById(LoanId));

        Loan loan = new Loan();

        loan.setLoanId(LoanId);

        loan.setUser(user);
        loan.setBook(book);
        LocalDate date = LocalDate.now();
        loan.setBorrowedDate(date);
        loan.setDueDate(date.plusDays(14));
        loan.setReturnedDate(null);
        loan.setStatus("BORROWED");

        book.setAvailability(false);

        bookRepository.save(book);

        Loan savedLoan = loanRepository.save(loan);

        return new LoanResponseDto(savedLoan.getLoanId(), request.getUserId(), user.getName(), request.getBookId(), book.getTitle(), savedLoan.getBorrowedDate(), savedLoan.getDueDate(), savedLoan.getReturnedDate(), savedLoan.getStatus());
        
    }
    public void deleteById(String LoanId){
        if(!loanRepository.existsById(LoanId)){
            throw new ResourcesNotFoundException("Loan Not found "+LoanId);
        }
        loanRepository.deleteById(LoanId);
    }


}

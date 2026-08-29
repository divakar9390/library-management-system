package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.request.LoanRequestDto;
import com.example.dto.request.LoanreturnRequestDto;
import com.example.dto.response.LoanResponseDto;
import com.example.exception.BussinessRuleException;
import com.example.exception.ResourcesNotFoundException;
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
    public LoanResponseDto returnedBook(LoanreturnRequestDto request){
        Loan loan = loanRepository.findById(request.getLoanId()).orElseThrow(()->new ResourcesNotFoundException("Loan Not Found  "+request.getLoanId()));
        if(!loan.getStatus().equalsIgnoreCase("BORROWED")){
            throw new BussinessRuleException("Book is Not Borrowed"+loan.getStatus());
        }
        Book book = loan.getBook();
        

        loan.setReturnedDate(LocalDate.now());
        loan.setStatus("RETURNED");
        book.setAvailability(true);
        bookRepository.save(book);
        Loan savedLoan = loanRepository.save(loan);
        return new LoanResponseDto(
            savedLoan.getLoanId(),
            savedLoan.getUser().getUserId(),
            savedLoan.getUser().getName(),
            savedLoan.getBook().getBookId(),
            savedLoan.getBook().getTitle(),
            savedLoan.getBorrowedDate(),
            savedLoan.getDueDate(),
            savedLoan.getReturnedDate(),
            savedLoan.getStatus()
        );

    } 
    public Page<LoanResponseDto> findall(Pageable pageable){
        Page<Loan> loans = loanRepository.findAll(pageable);
        if(loans.isEmpty()){
            throw new ResourcesNotFoundException("No Actice Loans");
        }
        return loans.map(loan->new LoanResponseDto(
                loan.getLoanId(),
                loan.getUser().getUserId(),
                loan.getUser().getName(),
                loan.getBook().getBookId(),
                loan.getBook().getTitle(),
                loan.getBorrowedDate(),
                loan.getDueDate(),
                loan.getReturnedDate(),
                loan.getStatus()
        ));

    }   

    public LoanResponseDto findById(String LoanId){
        Loan loan = loanRepository.findById(LoanId).orElseThrow(()-> new ResourcesNotFoundException("Loan Not Found With Id "+LoanId));
        return new LoanResponseDto(
            loan.getLoanId(),
            loan.getUser().getUserId(),
            loan.getUser().getName(),
            loan.getBook().getBookId(),
            loan.getBook().getTitle(),
            loan.getBorrowedDate(),
            loan.getDueDate(),
            loan.getReturnedDate(),
            loan.getStatus()

        );

    }

    public List<LoanResponseDto> userLoansByStatus(String userId,String status){
        List<Loan> loans =loanRepository.findByUserUserIdAndStatus(userId,status);

        if(loans.isEmpty()){
             throw new ResourcesNotFoundException("User has No Active Loans ");
        }
         return loans.stream().map(loan->new LoanResponseDto(
                loan.getLoanId(),
                loan.getUser().getUserId(),
                loan.getUser().getName(),
                loan.getBook().getBookId(),
                loan.getBook().getTitle(),
                loan.getBorrowedDate(),
                loan.getDueDate(),
                loan.getReturnedDate(),
                loan.getStatus()
        )).collect(Collectors.toList());


    }
    public List<LoanResponseDto>  userAllLoans(String userId){
        List<Loan> loans = loanRepository.findByUserUserId(userId);
        if(loans.isEmpty()){
             throw new ResourcesNotFoundException("User has No Active Loans ");
        }
         return loans.stream().map(loan->new LoanResponseDto(
                loan.getLoanId(),
                loan.getUser().getUserId(),
                loan.getUser().getName(),
                loan.getBook().getBookId(),
                loan.getBook().getTitle(),
                loan.getBorrowedDate(),
                loan.getDueDate(),
                loan.getReturnedDate(),
                loan.getStatus()
        )).collect(Collectors.toList());

    }
    public List<LoanResponseDto> findOverDueLoans(){
        List<Loan> loans = loanRepository.findByStatus("BORROWED");
        LocalDate Today = LocalDate.now();
        if(loans.isEmpty()){
            throw new ResourcesNotFoundException("No OverDue Loans ");
        }

       return loans.stream()
            .filter(loan ->
                    loan.getDueDate().isBefore(Today)
            )
            .map(loan -> new LoanResponseDto(
                    loan.getLoanId(),
                    loan.getUser().getUserId(),
                    loan.getUser().getName(),
                    loan.getBook().getBookId(),
                    loan.getBook().getTitle(),
                    loan.getBorrowedDate(),
                    loan.getDueDate(),
                    loan.getReturnedDate(),
                    loan.getStatus()
            )).collect(Collectors.toList());
        
    }

    public void deleteById(String loanId){
        if(!loanRepository.existsById(loanId)){
            throw new ResourcesNotFoundException("Loan Not found "+loanId);
        }
        loanRepository.deleteById(loanId);
    }


}

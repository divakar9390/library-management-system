package com.example.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.request.BookRequestDto;
import com.example.dto.response.BookResponseDto;
import com.example.exception.DuplicateResourcesException;
import com.example.exception.ResourcesNotFoundException;
import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.util.IDGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class BookService {

   public final BookRepository bookRepository;
   public IDGenerator idGenerator;
   public final AuthorRepository authorRepository;
   

   BookService(BookRepository bookRepository, IDGenerator idGenerator, AuthorRepository authorRepository){
      this.idGenerator = idGenerator;
      this.bookRepository = bookRepository;
      this.authorRepository = authorRepository;
    }

   public BookResponseDto save(BookRequestDto request){
      if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourcesException(
            "Book already exists with ISBN: " + request.getIsbn()
             );
       }
      Author author = authorRepository.findById(request.getAuthorId())
        .orElseThrow(() -> new RuntimeException("Author not found"));

       Book book = new Book();

       book.setTitle(request.getTitle());
       book.setIsbn(request.getIsbn());
       book.setPrice(request.getPrice());
       book.setAvailability(true);
       book.setPublicationDate(request.getDate());
       book.setPublisher(request.getPublisher());
       book.setLanguage(request.getLanguage());
       book.setAuthor(author);
       String bookId;

          do {
          bookId = idGenerator.generatorBookId();
          } while (bookRepository.existsById(bookId));

        book.setBookId(bookId);
        Book savedBook = bookRepository.save(book);
        
        return new BookResponseDto(
            savedBook.getBookId(),
            savedBook.getTitle(),
            savedBook.getPrice(),
            savedBook.getAvailability(),
            savedBook.getLanguage()
        );
   }
   public List<BookResponseDto> saveAll(List<BookRequestDto> requests) {

    List<Book> books = requests.stream()
            .map(request -> {
                if (bookRepository.existsByIsbn(request.getIsbn())) {
                       throw new DuplicateResourcesException(
                     "Book already exists with ISBN: " + request.getIsbn()
                    );
                }

                Author author = authorRepository.findById(request.getAuthorId())
                        .orElseThrow(() -> new RuntimeException("Author not found"));

                Book book = new Book();

                book.setTitle(request.getTitle());
                book.setIsbn(request.getIsbn());
                book.setPrice(request.getPrice());
                book.setAvailability(request.getAvailability());
                book.setPublicationDate(request.getDate());
                book.setPublisher(request.getPublisher());
                book.setLanguage(request.getLanguage());
                book.setAuthor(author);

                String bookId;

                do {
                    bookId = idGenerator.generatorBookId();
                } while (bookRepository.existsById(bookId));

                book.setBookId(bookId);

                return book;
            })
            .toList();

        return bookRepository.saveAll(books)
                .stream()
                .map(book -> new BookResponseDto(
                        book.getBookId(),
                        book.getTitle(),
                        book.getPrice(),
                        book.getAvailability(),
                        book.getLanguage()
                ))
                .toList();
    }
    

    

   public Page<BookResponseDto> findall(Pageable pageable){
        Page<Book> books = bookRepository.findAll(pageable);
        if(books.isEmpty()){
            throw new ResourcesNotFoundException("Not found"); 
        }
        
        return books.map(book->new BookResponseDto(
            book.getBookId(),
            book.getTitle(),
            book.getPrice(),
            book.getAvailability(),
            book.getLanguage()
        ));
        
   }
   
   public BookResponseDto findById(String BookId){
        Book book = bookRepository.findById(BookId).orElseThrow(()->new ResourcesNotFoundException("Book with Id not found: " + BookId));
        return new BookResponseDto(
            book.getBookId(),
            book.getTitle(),
            book.getPrice(),
            book.getAvailability(),
            book.getLanguage()
        );
    }
   public BookResponseDto  findByTitle(String title){
        Book book = bookRepository.findByTitle(title);
        if(book == null){
            throw new ResourcesNotFoundException("Book with Title Not found! "+title);
        }
        return new BookResponseDto(
            book.getBookId(),
            book.getTitle(),
            book.getPrice(),
            book.getAvailability(),
            book.getLanguage()
        );
   }
   public BookResponseDto findByIsbn(String isbn){
    Book book = bookRepository.findByIsbn(isbn);
    if(book==null){
        throw new ResourcesNotFoundException("Book with isbn Not found! : "+isbn);
    }

    return new BookResponseDto(
        book.getTitle(),
        book.getTitle(),
        book.getPrice(),
        book.getAvailability(),
        book.getLanguage()

    );
   }

   public void deleteById(String BookId){
        
       if(!bookRepository.existsById(BookId)){
        throw new ResourcesNotFoundException("Book with Id Not Found" +BookId);
        
       }
        bookRepository.deleteById(BookId);
   }
}

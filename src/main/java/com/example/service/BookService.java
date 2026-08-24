package com.example.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.request.BookRequestDto;
import com.example.dto.response.BookResponseDto;
import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRePository;
import com.example.util.IDGenerator;


@Service
public class BookService {

   public final BookRePository bookRepository;
   public IDGenerator idGenerator;
   public final AuthorRepository authorRepository;
   

   BookService(BookRePository bookRepository, IDGenerator idGenerator, AuthorRepository authorRepository){
      this.idGenerator = idGenerator;
      this.bookRepository = bookRepository;
      this.authorRepository = authorRepository;
    }

   public BookResponseDto save(BookRequestDto request){
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
    

    

   public List<BookResponseDto> findall(){
        return bookRepository.findAll().stream().map(book->new BookResponseDto(
            book.getBookId(),
            book.getTitle(),
            book.getPrice(),
            book.getAvailability(),
            book.getLanguage()
        )).collect(Collectors.toList());
   }
   public Optional<BookResponseDto> findById(String BookId){
        return bookRepository.findById(BookId).map(book -> new BookResponseDto(
            book.getBookId(),
            book.getTitle(),
            book.getPrice(),
            book.getAvailability(),
            book.getLanguage()
        ));
   }
   public Optional<BookResponseDto> findByTitle(String title){
        return bookRepository.findByTitle(title).map(book -> new BookResponseDto(
            book.getBookId(),
            book.getTitle(),
            book.getPrice(),
            book.getAvailability(),
            book.getLanguage()
        ));
   }

   public void deleteById(String BookId){
        bookRepository.deleteById(BookId);
   }
}

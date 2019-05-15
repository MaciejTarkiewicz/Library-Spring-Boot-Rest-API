package pl.tarkiewicz.libraryapp;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tarkiewicz.libraryapp.Library.BookDto;
import pl.tarkiewicz.libraryapp.Library.Book;
import pl.tarkiewicz.libraryapp.Library.LibraryRepo;
import pl.tarkiewicz.libraryapp.Library.LibraryService;
import pl.tarkiewicz.libraryapp.User.User;
import pl.tarkiewicz.libraryapp.User.UserRepo;
import pl.tarkiewicz.libraryapp.User.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryAppApplicationTests extends PrepareMockData{


    @Test
    public void getUsersTest_BDD(){
        //given
        UserRepo userRepo = mock(UserRepo.class);
        given(userRepo.findAll()).willReturn(users);
        UserService userService = new UserService(userRepo);
        //when
        List<User> u = userService.getUsers();
        //then
        Assert.assertThat(u, Matchers.hasSize(4));
    }

    @Test
    public void getUserByIdTest_BDD(){
        //given
        UserRepo userRepo = mock(UserRepo.class);
        given(userRepo.findById(1L)).willReturn(Optional.ofNullable(user1));
        UserService userService = new UserService(userRepo);
        //when
        Optional<User> u = userService.findById(1L);
        //then
        assertEquals(u.get(),user1);
    }



    @Test
    public void registerUserTest_BDD(){
        //given
        UserRepo userRepo = mock(UserRepo.class);
        given(userRepo.save(Mockito.any(User.class))).willReturn(user2);
        UserService userService = new UserService(userRepo);
        //when
        System.out.println(userRegistration.getUsername());
        User u = userService.RegisterUser(userRegistration);
        //then
        assertEquals(u, user2);

    }

    @Test
    public void deleteUserTest_BDD(){
        //given
        UserRepo userRepo = mock(UserRepo.class);
        UserService userService = new UserService(userRepo);
        //when
        userService.deleteUser(user3);
        //then
        verify(userRepo,times(1)).delete(user3);
    }


    @Test
    public void getAllBooksTest_BDD() {
        //given
        LibraryRepo libraryRepo = mock(LibraryRepo.class);
        given(libraryRepo.findAll()).willReturn(prepareMockData());
        LibraryService libraryService = new LibraryService(libraryRepo);
        //when
        List<Book> books = libraryService.getAllBooks();
        //then
        Assert.assertThat(books, Matchers.hasSize(3));


    }


//    @Test
//    public void getAllBooksByUserIdTest_BDD() {
//        //given
//        LibraryRepo libraryRepo = mock(LibraryRepo.class);
//        given(libraryRepo.findBookByUserId(1L)).willReturn(prepareMockDataUserId());
//        LibraryService libraryService = new LibraryService(libraryRepo);
//        //when
//        List<Book> books = libraryService.getBooksByUserId(1L);
//        //then
//        Assert.assertThat(books, Matchers.hasSize(2));
//
//
//    }
//
//    @Test
//    public void addBookTest_BDD() {
//        //given
//        LibraryRepo libraryRepo = mock(LibraryRepo.class);
//        given(libraryRepo.save(Mockito.any(Book.class))).willReturn(book4);
//        LibraryService libraryService = new LibraryService(libraryRepo);
//        //when
//        Book book = libraryService.addBook(new BookDto("d","d","1995-09-08","d"));
//        //then
//        Assert.assertEquals(book,book4);
//
//    }

    @Test
    public void deleteBookTesT_BDD() {
        //given
        LibraryRepo libraryRepo = mock(LibraryRepo.class);
        LibraryService libraryService = new LibraryService(libraryRepo);
        //when
        libraryService.delete(book1);
        //then
        verify(libraryRepo,times(1)).delete(book1);

    }


}

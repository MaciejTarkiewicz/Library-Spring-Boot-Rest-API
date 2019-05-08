package pl.tarkiewicz.libraryapp;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tarkiewicz.libraryapp.Library.Entity.Book;
import pl.tarkiewicz.libraryapp.Library.Repo.LibraryRepo;
import pl.tarkiewicz.libraryapp.Library.Service.LibraryService;
import pl.tarkiewicz.libraryapp.User.Entity.User;
import pl.tarkiewicz.libraryapp.User.Repo.UserRepo;
import pl.tarkiewicz.libraryapp.User.Service.UserService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryAppApplicationTests {

    private List<User> users;
    private User user1;
    private User user2;

    @Before
    public void before(){
        user1 = new User("a","a","a@a");
        user2 = new User("b","b","b@b");
        User user3 = new User("c", "c", "c@c");
        User user4 = new User("d","d","d@d");
        User [] tab = {user1,user2, user3,user4};
        users = Arrays.stream(tab).collect(Collectors.toList());

    }

    private List<Book> prepareMockData(){
        List<Book> list = new ArrayList<>();
        list.add(new Book("a","a", LocalDate.parse("1995-09-09"),"a",null));
        list.add(new Book("a","a", LocalDate.parse("1995-10-10"),"a",null));
        list.add(new Book("a","a", LocalDate.parse("1995-11-11"),"a",null));
        return list;

    }

    private Set<Book> prepareMockDataUserId(){
        Set<Book> set = new HashSet<>();
        set.add(new Book("a","a", LocalDate.parse("1995-11-11"),"a",user1));
        set.add(new Book("a","a", LocalDate.parse("1995-12-12"),"a",user2));
        return set;

    }


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
    public void saveUserTest_BDD(){
        //given
        UserRepo userRepo = mock(UserRepo.class);
        given(userRepo.save(user1)).willReturn(user1);
        UserService userService = new UserService(userRepo);
        //when
        User u = userService.save(user1);
        //then
        assertEquals(user1,u);

    }

    @Test
    public void deleteUserTest_BDD(){
        //given
        UserRepo userRepo = mock(UserRepo.class);
        UserService userService = new UserService(userRepo);
        //when
        userService.deleteUser(user1);
        //then
        verify(userRepo,times(1)).delete(user1);
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


    @Test
    public void getAllBooksByUserIdTest_BDD() {
        //given
        LibraryRepo libraryRepo = mock(LibraryRepo.class);
        given(libraryRepo.findBookByUserId(1L)).willReturn(prepareMockDataUserId());
        LibraryService libraryService = new LibraryService(libraryRepo);
        //when
        List<Book> books = libraryService.getBooksByUserId(1L);
        //then
        Assert.assertThat(books, Matchers.hasSize(2));


    }

    @Test
    public void addBookTest_BDD() {
        //given
        LibraryRepo libraryRepo = mock(LibraryRepo.class);
        given(libraryRepo.save(Mockito.any(Book.class))).willReturn(new Book("d","d", LocalDate.parse("1995-12-12"),"d",null));
        LibraryService libraryService = new LibraryService(libraryRepo);
        //when
        Book library = libraryService.save(new Book());
        //then
        Assert.assertEquals(library.getAuthor(),"d" );

    }

    @Test
    public void deleteBookTesT_BDD() {
        //given
        Book book = new Book("a","a", LocalDate.parse("1995-12-12"),"a",user2);
        LibraryRepo libraryRepo = mock(LibraryRepo.class);
        LibraryService libraryService = new LibraryService(libraryRepo);
        //when
        libraryService.delete(book);
        //then
        verify(libraryRepo,times(1)).delete(book);

    }


    @Test
    public void contextLoads() {
    }





}

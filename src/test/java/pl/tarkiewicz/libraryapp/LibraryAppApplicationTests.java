package pl.tarkiewicz.libraryapp;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tarkiewicz.libraryapp.Library.Book;
import pl.tarkiewicz.libraryapp.Library.Controller.LibraryController;
import pl.tarkiewicz.libraryapp.Library.Entity.Library;
import pl.tarkiewicz.libraryapp.Library.Repo.LibraryRepo;
import pl.tarkiewicz.libraryapp.Library.Service.LibraryService;
import pl.tarkiewicz.libraryapp.User.Entity.User;
import pl.tarkiewicz.libraryapp.User.Repo.UserRepo;
import pl.tarkiewicz.libraryapp.User.Service.UserService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryAppApplicationTests {

    private List<User> users;
    private User user1;
    private User user2;

    @Before
    public void BeforeClas(){
        user1 = new User("a","a","a@a");
        user2 = new User("b","b","b@b");
        User user3 = new User("c", "c", "c@c");
        User user4 = new User("d","d","d@d");
        User [] tab = {user1,user2, user3,user4};
        users = Arrays.stream(tab).collect(Collectors.toList());

    }

    @Test
    public void GetUserTest_BDD(){
        //given
        UserRepo userRepo = mock(UserRepo.class);
        given(userRepo.findAll()).willReturn(users);
        UserService userService = new UserService(userRepo);
        //when
        List<User> u = userService.getUser();
        //then
        Assert.assertThat(u, Matchers.hasSize(4));
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


    private List<Library> prepareMockData(){
        List<Library> list = new ArrayList<>();
        list.add(new Library("a","a", LocalDate.parse("1995-09-09"),"a",null));
        list.add(new Library("a","a", LocalDate.parse("1995-10-10"),"a",null));
        list.add(new Library("a","a", LocalDate.parse("1995-11-11"),"a",null));
        return list;

    }

    @Test
    public void getAllBooksTest_BDD() {
        //given
        LibraryRepo libraryRepo = mock(LibraryRepo.class);
        given(libraryRepo.findAll()).willReturn(prepareMockData());
        LibraryService libraryService = new LibraryService(libraryRepo);
        //when
        List<Library> books = libraryService.getLibrary();
        //then
        Assert.assertThat(books, Matchers.hasSize(3));


    }

    private Set<Library> prepareMockDataUserId(){
        Set<Library> set = new HashSet<>();
        set.add(new Library ("a","a", LocalDate.parse("1995-11-11"),"a",user1));
        set.add(new Library ("a","a", LocalDate.parse("1995-12-12"),"a",user2));
        return set;

    }

    @Test
    public void getAllBooksByUserIdTest_BDD() {
        //given
        LibraryRepo libraryRepo = mock(LibraryRepo.class);
        given(libraryRepo.findLibraryByUserId(1L)).willReturn(prepareMockDataUserId());
        LibraryService libraryService = new LibraryService(libraryRepo);
        //when
        List<Library> books = libraryService.getLibraryByUserId(1L);
        //then
        Assert.assertThat(books, Matchers.hasSize(2));


    }


    @Test
    public void AddBookTest_BDD() {
        //given
        LibraryRepo libraryRepo = mock(LibraryRepo.class);
        given(libraryRepo.save(Mockito.any(Library.class))).willReturn(new Library("d","d", LocalDate.parse("1995-12-12"),"d",null));
        LibraryService libraryService = new LibraryService(libraryRepo);
        //when
        Library library = libraryService.save(new Library());
        //then
        Assert.assertEquals(library.getAuthor(),"d" );

    }


    @Test
    public void contextLoads() {
    }





}

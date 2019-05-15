package pl.tarkiewicz.libraryapp;

import org.junit.Before;
import pl.tarkiewicz.libraryapp.Library.Book;
import pl.tarkiewicz.libraryapp.User.Dto.UserRegistration;
import pl.tarkiewicz.libraryapp.User.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PrepareMockData {

    List<User> users;
    User user1;
    User user2;
    User user3;
    User user4;
    Book book1;
    Book book4;

    UserRegistration userRegistration;

    @Before
    public void before() {
        userRegistration = new UserRegistration("k", "k", "k", "a@a");
        user1 = new User.Builder()
                .username("d")
                .password("d")
                .email("d@d")
                .build();
        user2 = new User.Builder()
                .username("e")
                .password("e")
                .email("e@e")
                .build();
        user3 = new User.Builder()
                .username("f")
                .password("f")
                .email("f@f")
                .build();
        user4 = new User.Builder()
                .username("g")
                .password("g")
                .email("g@g")
                .build();

        book1 = new Book.Builder()
                .author("a")
                .title("a")
                .productionYear(LocalDate.parse("1995-09-09"))
                .type("a")
                .user(user1)
                .build();

        book4 = new Book.Builder()
                .author("a")
                .title("a")
                .productionYear(LocalDate.parse("1995-01-01"))
                .type("a")
                .build();
        User[] tab = {user1, user2, user3, user4};
        users = Arrays.stream(tab).collect(Collectors.toList());

    }

    Set<Book> prepareMockDataUserId() {
        Book book2 = new Book.Builder()
                .author("a")
                .title("a")
                .productionYear(LocalDate.parse("1995-09-09"))
                .type("a")
                .user(user2)
                .build();

        Set<Book> set = new HashSet<>();
        set.add(book1);
        set.add(book2);
        return set;
    }

    List<Book> prepareMockData(){


        Book book2 = new Book.Builder()
                .author("b")
                .title("b")
                .productionYear(LocalDate.parse("1995-02-02"))
                .type("b")
                .build();

        Book book3 = new Book.Builder()
                .author("c")
                .title("c")
                .productionYear(LocalDate.parse("1995-03-03"))
                .type("c")
                .build();

        List<Book> list = new ArrayList<>();
        list.add(book4);
        list.add(book2);
        list.add(book3);
        return list;

    }
}

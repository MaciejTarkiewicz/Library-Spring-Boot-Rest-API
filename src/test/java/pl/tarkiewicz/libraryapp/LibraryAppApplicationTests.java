package pl.tarkiewicz.libraryapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tarkiewicz.libraryapp.User.Entity.User;
import pl.tarkiewicz.libraryapp.User.Repo.UserRepo;
import pl.tarkiewicz.libraryapp.User.Service.UserService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryAppApplicationTests {

    @MockBean
    UserRepo userRepo;

    @Autowired
    UserService userService;

    private int count = 0;
    private User user1;
    private User user2;

    @Before
    public void BeforeClas(){
        user1 = new User("a","a","a@a");
        user2 = new User("b","b","b@b");
    }

    @Test
    public void TestGetUser(){
        when(userRepo.findAll()).thenReturn(Stream.of(user1,
                user2).collect(Collectors.toList()));

        for (User u : userService.getUser()){
            count = count + 1;
        }
        assertEquals(2,count);
    }

    @Test
    public void saveUserTest(){
        when(userRepo.save(user1)).thenReturn(user1);
        assertEquals(user1,userService.save(user1));
    }

    @Test
    public void deleteUserTest(){
        userService.deleteUser(user1);
        verify(userRepo,times(1)).delete(user1);
    }

    @Test
    public void contextLoads() {
    }


}

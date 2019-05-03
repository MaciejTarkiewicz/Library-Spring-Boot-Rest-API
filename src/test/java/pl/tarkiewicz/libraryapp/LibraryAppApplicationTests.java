package pl.tarkiewicz.libraryapp;

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

    private boolean status = false;
    private int count = 0;

    @Test
    public void TestGetUser(){
        when(userRepo.findAll()).thenReturn(Stream.of(new User("a","a","a@a"),
               new User("b","b","b@b")).collect(Collectors.toList()));

        for (User u : userService.getUser()){
            count = count + 1;

        }
        assertEquals(2,count);
    }

    @Test
    public void saveUserTest(){
        User user =  new User("b","b","b@b");
        when(userRepo.save(user)).thenReturn(user);
        assertEquals(user,userService.save(user));
    }

    @Test
    public void deleteUserTest(){
        User user =  new User("b","b","b@b");
        userService.deleteUser(user);
        verify(userRepo,times(1)).delete(user);
    }

    @Test
    public void contextLoads() {
    }

}

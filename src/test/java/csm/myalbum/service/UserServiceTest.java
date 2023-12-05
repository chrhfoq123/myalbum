package csm.myalbum.service;


import csm.myalbum.domain.User;
import csm.myalbum.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        User user = new User();
        user.setUserEmail("chrhfoq123@naver.com");
        user.setUserName("최상민");
        user.setUserPassword("asdfasdfasdf123");

        //when
        Long id = userService.join(user);

        //then
        assertEquals(user, userRepository.findOne(id));
    }

    @Test
    public void 중복_아이디_예외() throws Exception{
        //given
        User user1 = new User();
        user1.setUserId("chrhfoq123");

        User user2 = new User();
        user2.setUserName("chrhfoq123");

        //when
        userService.join(user1);
        assertThrows(IllegalStateException.class, () -> userService.join(user2));

        //then
    }

    @Test
    public void 중복_이메일_예외() throws Exception{
        //given
        User user1 = new User();
        user1.setUserEmail("chrhfoq123@naver.com");

        User user2 = new User();
        user2.setUserEmail("chrhfoq123@naver.com");

        //when
        userService.join(user1);
        assertThrows(IllegalStateException.class, () -> userService.join(user2));

        //then
    }
}
package csm.myalbum.service;

import csm.myalbum.domain.User;
import csm.myalbum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(User user){
        validateDuplicateUser(user); //중복 회원 검증
        userRepository.save(user);
        return user.getUserId();
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    /**
     * 회원 1명 조회
     */
    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByName(user.getUserName());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}

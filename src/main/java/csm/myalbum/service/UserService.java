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
        validateDuplicateUserId(user); //중복 회원아이디 검증
        validateDuplicateUserEmail(user); //중복 회원이메일 검증
        userRepository.save(user);
        return user.getId();
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

    /**
     * 아이디 중복 검증
     */
    private void validateDuplicateUserId(User user) {
        List<User> findUsers = userRepository.findByUserId(user.getUserName());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("사용할 수 없는 아이디입니다.");
        }
    }

    /**
     * 이메일 중복 검증
     */
    private void validateDuplicateUserEmail(User user) {
        List<User> findUsers = userRepository.findByUserEmail(user.getUserEmail());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }
    }
}

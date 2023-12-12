package csm.myalbum.service;

import csm.myalbum.domain.User;
import csm.myalbum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public User login(String loginId, String password){
        Optional<User> findUserOptional = userRepository.findByUserId(loginId);
        User user = findUserOptional.get();
        if(user.getUserPassword().equals(password)){
            return user;
        }else{
            return null;
        }
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
        Optional<User> findUsers = userRepository.findByUserId(user.getUserName());
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

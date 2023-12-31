package csm.myalbum.service;

import csm.myalbum.domain.User;
import csm.myalbum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(User user){
        validateDuplicateUserId(user); //중복 회원아이디 검증
        validateDuplicateUserEmail(user); //중복 회원이메일 검증
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 로그인
     */
    public User login(User user){
        Optional<User> findUserOptional = userRepository.findByUserId(user.getUserId());
        if(findUserOptional.isEmpty()){
            return null;
        }

        User userRes = findUserOptional.get();
        if(userRes.getUserPassword().equals(user.getUserPassword())){
            return userRes;
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
     * 회원 정보 수정
     */
    @Transactional
    public void update(Long id, String name, String email, String password){
        User user = userRepository.findOne(id);
        user.setUserName(name);
        user.setUserEmail(email);
        user.setUserPassword(password);
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

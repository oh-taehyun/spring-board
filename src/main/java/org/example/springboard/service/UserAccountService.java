package org.example.springboard.service;

import lombok.RequiredArgsConstructor;
import org.example.springboard.dto.UserAccountRequest;
import org.example.springboard.entity.UserAccount;
import org.example.springboard.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public void join(UserAccountRequest request){
        UserAccount user = UserAccount.builder().userId(request.getUserId()).password(request.getPassword()).build();
        userAccountRepository.save(user);
    }

    public  UserAccount login(UserAccountRequest request){
        String userId = request.getUserId();
        Optional<UserAccount> getUser = userAccountRepository.findByUserId(userId);
        if(getUser.isPresent()){
            UserAccount user = getUser.get();
            if(user.getPassword().equals(request.getPassword())){
                return user;
            }
        }
        return null;
    }
}

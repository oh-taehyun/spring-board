package org.example.springboard.service;

import lombok.RequiredArgsConstructor;
import org.example.springboard.dto.UserAccountRequest;
import org.example.springboard.entity.UserAccount;
import org.example.springboard.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAccount getUser(String username){
        Optional<UserAccount> userAccount = this.userAccountRepository.findByUsername(username);
        if(userAccount.isPresent()){
            return userAccount.get();
        }else{
            throw new UsernameNotFoundException("user not found");
        }
    }
    public void join(UserAccountRequest request){
        UserAccount user = UserAccount.builder().username(request.getUsername()).password(bCryptPasswordEncoder.encode(request.getPassword())).build();
        userAccountRepository.save(user);
    }

    public  UserAccount login(UserAccountRequest request){
        String userName = request.getUsername();
        Optional<UserAccount> getUser = userAccountRepository.findByUsername(userName);
        if(getUser.isPresent()){
            UserAccount user = getUser.get();
            if(user.getPassword().equals(request.getPassword())){
                return user;
            }
        }
        return null;
    }
}

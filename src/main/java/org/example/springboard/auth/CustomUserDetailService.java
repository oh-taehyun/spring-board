package org.example.springboard.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboard.entity.UserAccount;
import org.example.springboard.repository.UserAccountRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("회원정보를 찾을 수 없습니다.");
        });
        return new CustomUserDetails(userAccount);
    }

    public String getCurrentUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            return ((UserDetails)principal).getUsername();
        }else {
            return principal.toString();
        }
    }
}

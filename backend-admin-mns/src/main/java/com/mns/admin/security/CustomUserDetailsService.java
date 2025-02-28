package com.mns.admin.security;

import com.mns.admin.model.Utilisateur;
import com.mns.admin.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = userRepository.findByEmailUtilisateur(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return User.builder()
                .username(utilisateur.getEmailUtilisateur())
                .password(utilisateur.getMotDePasseUtilisateur())
                .roles(utilisateur.getRole().getNomRole())
                .build();
    }
}

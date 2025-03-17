package itu.evaluation.s6.service;

import itu.evaluation.s6.exception.PhoneNumberInvalidException;
import itu.evaluation.s6.model.Admin;
import itu.evaluation.s6.model.Client;
import itu.evaluation.s6.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailService implements UserDetailsService {
    ClientService clientService;
    AdminRepository adminRepository;

    @Autowired
    public UtilisateurDetailService(ClientService clientService, AdminRepository adminRepository) {
        this.clientService = clientService;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String roles = "USER";
        String password = "";

        try {
            Client c = clientService.authentification(username);
        } catch (PhoneNumberInvalidException e) {
            Admin admin = adminRepository.findByUsername(username).orElse(null);
            roles += " ,ADMIN";
            password  = (admin != null) ? admin.getPassword() : "";

            if (admin == null){
                throw new UsernameNotFoundException("Nom d'utilisateur introuvable");
            }
        }

        return User
                .builder()
                .username(username)
                .password(password)
                .roles(roles.split(","))
                .build();
    }
}

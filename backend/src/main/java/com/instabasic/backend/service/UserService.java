package com.instabasic.backend.service;

import java.time.LocalDateTime;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.instabasic.backend.common.util.exception.ErrorHandler;
import com.instabasic.backend.common.util.security.services.UserDetailsImpl;
import com.instabasic.backend.model.Profile;
import com.instabasic.backend.model.User;
import com.instabasic.backend.payload.request.LoginRequest;
import com.instabasic.backend.payload.request.SignupRequest;
import com.instabasic.backend.repository.UserRepository;
import com.instabasic.backend.payload.response.JwtResponse;
import com.instabasic.backend.common.util.security.jwt.JwtUtils;

import com.instabasic.backend.payload.response.MessageResponse;

@Service
public class UserService {

    @Autowired
    UserRepository UserRepository;

    @Autowired
    ProfileService ProfileService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    static final Logger logger = LogManager.getLogger(ProfileService.class.getName());

    // C

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (UserRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (UserRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        // Set<String> strRoles = signUpRequest.getRole();
        // Set<Role> roles = new HashSet<>();
        /*
         * if (strRoles == null) {
         * Role userRole = roleRepository.findByName(ERole.ROLE_USER)
         * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
         * roles.add(userRole);
         * } else {
         * strRoles.forEach(role -> {
         * switch (role.toLowerCase()) {
         * case "admin":
         * Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
         * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
         * roles.add(adminRole);
         * 
         * break;
         * case "mod":
         * Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
         * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
         * roles.add(modRole);
         * 
         * break;
         * default:
         * Role userRole = roleRepository.findByName(ERole.ROLE_USER)
         * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
         * roles.add(userRole);
         * }
         * });
         * }
         */

        // user.setRoles(roles);

        UserRepository.save(user);
        // assegnare un profilo in automatico alla creazione dell'user
        User dbuser = UserRepository.findByUsername(user.getUsername()).get();
        Profile newProfile = new Profile(dbuser.getUsername(), signUpRequest.getFirstName(),
                signUpRequest.getLastName(), signUpRequest.getBirthday(), dbuser);

        ProfileService.save(newProfile);

        return ResponseEntity.status(202).body(new MessageResponse("User registered successfully!"));
    }

    // R
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        /*
         * List<String> roles = userDetails.getAuthorities().stream()
         * .map(item -> item.getAuthority())
         * .collect(Collectors.toList());
         */
        Profile mainProfile = ProfileService.findFirstByUserId(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails, mainProfile));

    }

    public User findById(Long id) {
        if (id != null) {
            Optional<User> optional = UserRepository.findById(id);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new ErrorHandler(404, "Comment not found");
            }
        } else {
            throw new ErrorHandler(400, "Id null");
        }
    }

    public User findByUsername(String name) {
        Optional<User> optional = UserRepository.findByUsername(name);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ErrorHandler(404, "User not found");
        }
    }

    public Page<User> findAll(Pageable pageable) {
        try {
            if (pageable != null) {
                return UserRepository.findAll(pageable);
            } else {
                throw new ErrorHandler(400, "pageable null");
            }
        } catch (Exception e) {
            logger.error("Error UserService:findAll" + e.getMessage(), e);
            return null;
        }
    }

    // U
    public User update(Long id, User userUpdate) {
        if (id == null) {
            throw new ErrorHandler(400, "User id is null");
        }
        Optional<User> userResult = UserRepository.findById(id);
        if (!userResult.isPresent()) {
            throw new ErrorHandler(404, "User not found");
        }
        User existingUser = userResult.get();
        if (userUpdate != null) {
            // Aggiorna i dettagli dell'utente solo se sono stati forniti nel payload
            if (userUpdate.getUsername() != null) {
                existingUser.setUsername(userUpdate.getUsername());
            }
            if (userUpdate.getEmail() != null) {
                existingUser.setEmail(userUpdate.getEmail());
            }
            if (userUpdate.getPassword() != null) {
                existingUser.setPassword(userUpdate.getPassword());
            }
            existingUser.setUpdatedAt(LocalDateTime.now());
            UserRepository.save(existingUser);
            return existingUser;
        } else {
            throw new ErrorHandler(400, "User update details are null");
        }

    }

    // D
    public void delete(Long id) {
        if (id != null) {
            UserRepository.deleteById(id);
        } else {
            throw new ErrorHandler(404, "User not found");
        }
    }
}

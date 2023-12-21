package de.telran.bank.security.service;

import de.telran.bank.security.entity.UserEntity;
import de.telran.bank.security.model.Role;
import de.telran.bank.security.model.User;
import de.telran.bank.security.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling user-related operations.
 * <p>
 * This service provides methods for fetching user data based on login credentials.
 * It initializes a list of users for demonstration purposes.
 * </p>
 *
 * @Service - Indicates that an annotated class is a service component.
 *
 * @author A-R
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserService {

    /**
     * A list of users initialized for demonstration purposes.
     */
//    private final List<User> users;

    /**
     * Constructor initializes the list of users.
     */
    public UserService() {
//        this.users = List.of(
//                new User("andrei", "1234", "Андрей", "Сергеев", Collections.singleton(Role.USER)),
//                new User("petr", "12345", "Петр", "Иванов", Collections.singleton(Role.ADMIN)),
//                new User("serg", "123", "Сергей", "Макаров", Collections.singleton(Role.EDITOR))
//        );
    }

    /**
     * Fetches a user based on the login credentials provided.
     * <p>
     * This method iterates through the list of users and returns an {@link Optional} of {@link User}
     * if a user with the specified login is found.
     * </p>
     *
     * @param login the login credentials of the user.
     * @return an Optional of User if a user with the specified login is found, otherwise an empty Optional.
     */
    public Optional<User> getByLogin(@NonNull String login) {
//        return users.stream()
//                .filter(user -> login.equals(user.getLogin()))
//                .findFirst();
        return getByLoginWithDb(login); // поменял поиск на бд
    }

    // с БД
    @Autowired
    private UserRepository userRepository;
    public Optional<User> getByLoginWithDb(@NonNull String login) {
        List<UserEntity> userEntities = userRepository.findByLogin(login);
        Optional<User> user = Optional.empty();
        if(userEntities !=null && !userEntities.isEmpty()) {
            UserEntity userEntity = userEntities.get(0); // берем первый
            user = Optional.of(User.builder()
                    .login(userEntity.getLogin())
                    .password(userEntity.getPassword())
                    .firstName(userEntity.getFirstName())
                    .lastName(userEntity.getLastName())
                    .roles(
                            Arrays.stream(userEntity.getRoles().split(","))
                                    .distinct()
                                    .map(s -> Role.valueOf(s))
                                    .collect(Collectors.toSet())
                    ).build());
        }
        return user;
    }

}



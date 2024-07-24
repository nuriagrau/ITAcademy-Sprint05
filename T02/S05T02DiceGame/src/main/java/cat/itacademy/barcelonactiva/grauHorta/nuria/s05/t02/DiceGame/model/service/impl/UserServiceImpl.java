package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.impl;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.User;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.UserRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();

        return users;
    }

}

package br.com.gerenciamentohoteis.gerenciador.Service;

import br.com.gerenciamentohoteis.gerenciador.Dto.Request.CreateUserDTO;
import br.com.gerenciamentohoteis.gerenciador.Dto.Request.MyInformationDTO;
import br.com.gerenciamentohoteis.gerenciador.Entity.User;
import br.com.gerenciamentohoteis.gerenciador.Exception.exceptions.UserAlreadyCreatedException;
import br.com.gerenciamentohoteis.gerenciador.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserDTO createUser(CreateUserDTO userDTO){
        if (this.userRepository.findByEmail(userDTO.getEmail()) != null){
            throw new UserAlreadyCreatedException("Usuário já cadastrado");
        }
        String passwordEncrypted = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        User user = new User();
        user.setNameUser(userDTO.getNameUser());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncrypted);
        user.setRole(userDTO.getRole());
        userRepository.save(user);
        return userDTO;
    }
    public MyInformationDTO myInformation(String email){
        User user = (User) userRepository.findByEmail(email);
        if (user == null){
            throw new RuntimeException("Usuario não encontrado");
        }
        return new MyInformationDTO(
                user.getUserId(),
                user.getNameUser(),
                user.getRole());
    }
}

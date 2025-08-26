package br.com.gerenciamentohoteis.gerenciador.Entity;

import br.com.gerenciamentohoteis.gerenciador.Entity.Enum.UserTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String nameUser;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = true)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserTypes role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserTypes.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_BASIC"));
        else return List.of(new SimpleGrantedAuthority("ROLE_BASIC"));
    }

    @Override
    public String getUsername() {
        return getNameUser();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    @NotEmpty(message = "User name may not be empty")
    private String userName;

    @Column
    @Email(regexp = ".+@.+\\..+", message = "User email must contain @ and .something")
    @NotEmpty(message = "User email may not be empty")
    private String email;

    @Column
    @Length(min = 4,  message = "User password must be 4 characters minimum lenght.")
    @NotEmpty(message = "User password may not be empty.")
    private String password;

    @Column(name="registration_date", updatable = false)
    private Date registrationDate;

    @Column
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "User role may not be empty")
    private Role role;

    //@OneToMany(mappedBy = "user")
   // private List<Player> players;


    public User(String userName, String email, String password, Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.registrationDate = new Date();
        this.role = Enum.valueOf(Role.class, role.name());
        //this.players = new ArrayList<Player>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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

    public String getPassword() {
        return password;
    }
}

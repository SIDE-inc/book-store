package inc.side.bookstore.entity;

import inc.side.bookstore.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Valid
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Firstname should not be empty")
    @NotEmpty(message = "Firstname should not be empty")
    private String firstname;

    @NotEmpty(message = "Lastname should not be empty")
    @NotNull(message = "Lastname should not be empty")
    private String lastname;

    @Email(message = "Enter valid email")
    @NotEmpty(message = "Email should not be empty")
    @NotNull(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @NotNull(message = "Password should not be empty")
    private String password;

    @Column(unique = true)
    @NotEmpty(message = "Username should not be empty")
    @NotNull(message = "Username should not be empty")
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
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

package kz.sw_auth_service.model.entity;

import jakarta.persistence.*;
import kz.sw_auth_service.model.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String uuid;
    @Column(unique = true, nullable = false, length = 100)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private Role role = Role.GUEST;
}

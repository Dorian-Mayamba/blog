package uk.co.blog.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

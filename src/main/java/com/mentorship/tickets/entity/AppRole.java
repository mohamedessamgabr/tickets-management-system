package com.mentorship.tickets.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_role")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, unique = true)
    private Integer roleId;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

}

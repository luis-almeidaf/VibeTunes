package com.luis.VibeTunes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String name;

    public enum values {
        ADMIN(1L),
        BASIC(2L);

        @lombok.Getter
        final long roleId;

        values(long roleId) {
            this.roleId = roleId;
        }
    }
}

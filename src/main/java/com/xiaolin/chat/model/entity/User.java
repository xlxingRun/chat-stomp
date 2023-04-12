package com.xiaolin.chat.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


/**
 * @author xlxing
 */
@Entity
@Accessors(chain = true)
@Data
@Table(name = "user")
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    public User() {}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

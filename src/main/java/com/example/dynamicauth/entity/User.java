package com.example.dynamicauth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String password;

    // Thiết lập quan hệ Many-to-Many với bảng Permition
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_permition", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "user_id"), // Khóa ngoại tới bảng User
            inverseJoinColumns = @JoinColumn(name = "permition_id") // Khóa ngoại tới bảng Permition
    )
    private List<Permition> permitions;
}

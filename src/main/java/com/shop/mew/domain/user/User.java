package com.shop.mew.domain.user;

import com.shop.mew.domain.BaseTimeEntity;
import com.shop.mew.domain.cart.Cart;
import com.shop.mew.domain.orderitem.OrderItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.regex.Pattern.matches;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Cart> cart;

    @OneToMany(mappedBy = "user")
    private List<OrderItem> orderItems = new ArrayList<>();


    public User(String email, String name, String password, String address) {
        this(null, name, email, password, address, null);
    }

    @Builder
    public User(Long id, String name, String email,
                String password, String address, Role role) {
        checkArgument(isNotEmpty(email), "email must be provided.");
        checkArgument(
                email.length() >= 4 && email.length() <= 50,
                "email length must be between 4 and 50 characters."
        );
        checkArgument(checkEmail(email), "Invalid email address: " + email);
        checkArgument(isNotEmpty(name), "name must be provided.");
        checkArgument(
                name.length() >= 1 && name.length() <= 10,
                "name length must be between 1 and 10 characters."
        );
        checkNotNull(password, "password must be provided.");
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public void login(PasswordEncoder passwordEncoder, String credentials) {
        if (!passwordEncoder.matches(credentials, password))
            throw new IllegalArgumentException("Bad credential");
    }

    private static boolean checkEmail(String email) {
        return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", email);
    }

}

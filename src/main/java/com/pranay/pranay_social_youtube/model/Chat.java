package com.pranay.pranay_social_youtube.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String chat_name;

    private String image;
    @ManyToMany
    private List<User> users= new ArrayList<>();


    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages=new ArrayList<>();

}

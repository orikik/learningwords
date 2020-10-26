package com.orikik.learningwords.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = UserEntity.TABLE)
public class UserEntity {
    public static final String TABLE = "users";

    @Id
    @SequenceGenerator(name = "users_pk_sequence",
            sequenceName = "users_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_pk_sequence")
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "repetition",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id"))
    private List<WordEntity> wordList;
    private String username;
    private String password;

    public List<WordEntity> getWordList() {
        return wordList;
    }

    public void setWordList(List<WordEntity> wordList) {
        this.wordList = wordList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

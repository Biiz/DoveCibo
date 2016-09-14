package DoveCiboPK;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stefano
 */
public class User {

    private Integer id;
    private String name;
    private String surname;
    private String nickname;
    private String email;
    private String password;

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String surname, String nickname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setNickname(String nickname) {
        this.nickname = nickname;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    protected String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public User() {
    }

}

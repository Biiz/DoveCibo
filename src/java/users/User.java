package users;

/**
 * Contiene i metodi per la gestione di ogni utente dal sito
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
    private String role;
    private Integer like;

    /**
     * Costruttore utente
     *
     * @param id
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * Costruttore utente
     *
     * @param id
     */
    public User(Integer id, String name, String surname, String nickname, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLike(Integer like) {
        this.like = like;
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

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }
    
    public String getRole() {
        return role;
    }

    public Integer getLike() {
        return like;
    }
    
    public User() { }
}

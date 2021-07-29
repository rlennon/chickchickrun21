package ie.lyit.ccr.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "email_verified_at")
    private java.sql.Timestamp emailVerifiedAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public java.sql.Timestamp getEmailVerifiedAt() {
        return this.emailVerifiedAt;
    }

    public void setEmailVerifiedAt(java.sql.Timestamp emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }
}

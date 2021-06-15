package com.unufolio.fondo.model.dataobject;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/05/25
 */
@Entity
@Table(name = "fondo_user")
@SQLDelete(sql = "update user set is_deleted = 1")
@Where(clause = "is_deleted = 0")
@DynamicInsert
@DynamicUpdate
public class User extends AbstractBaseEntity {

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "email_confirmed")
    private Boolean isEmailConfirmed;
    @Column(name = "phone_confirmed")
    private Boolean isPhoneConfirmed;
    @Column(name = "locked")
    private Boolean isLocked;
    @Column(name = "enabled")
    private Boolean isEnabled;
    @Column(name = "credential_expired")
    private Boolean isCredentialExpired;
    @Column(name = "expired")
    private Boolean isExpired;

    public User() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }

    public Boolean getPhoneConfirmed() {
        return isPhoneConfirmed;
    }

    public void setPhoneConfirmed(Boolean phoneConfirmed) {
        isPhoneConfirmed = phoneConfirmed;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getCredentialExpired() {
        return isCredentialExpired;
    }

    public void setCredentialExpired(Boolean credentialExpired) {
        isCredentialExpired = credentialExpired;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isEmailConfirmed=" + isEmailConfirmed +
                ", isPhoneConfirmed=" + isPhoneConfirmed +
                ", isLocked=" + isLocked +
                ", isEnabled=" + isEnabled +
                ", isCredentialExpired=" + isCredentialExpired +
                ", isExpired=" + isExpired +
                "} " + super.toString();
    }
}

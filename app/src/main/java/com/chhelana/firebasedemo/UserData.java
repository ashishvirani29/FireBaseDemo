package com.chhelana.firebasedemo;

/**
 * Created by Veera Developer on 1/2/2018.
 */

public class UserData {
    String question;
    String ans;
    String email;
    String password;

    public UserData(String question, String ans, String email, String password) {
        this.question = question;
        this.ans = ans;
        this.email = email;
        this.password = password;
    } public UserData() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

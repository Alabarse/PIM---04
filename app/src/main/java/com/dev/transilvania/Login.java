package com.dev.transilvania;

class Login {

    private String mLogin;
    private String mPassword;
    private static String id;

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    public String getLogin() {
        return this.mLogin;
    }

    public void setLogin(String login) {
        this.mLogin = login;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
}

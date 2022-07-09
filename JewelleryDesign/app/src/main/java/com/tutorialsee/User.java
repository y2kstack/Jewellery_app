package com.tutorialsee;

public class User {

        public String fullname, email, password, username;

        public User(){

        }

        public User(String fullname, String email, String password, String username) {
                this.email = email;
                this.fullname = fullname;
                this.password = password;
                this.username = username;
        }

}

package com.turulin.SchoolERP.exeptions;

public class UsernameNotFoundException extends Exception {
    public UsernameNotFoundException(String message) {
        super("Пользователь с именем <"+message+"> не найден");
    }
}

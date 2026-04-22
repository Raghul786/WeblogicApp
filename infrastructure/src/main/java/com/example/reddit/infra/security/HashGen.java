package com.example.reddit.infra.security;

import org.mindrot.jbcrypt.BCrypt;

public class HashGen {

    public static void main(String[] args) {

        String hash = "$2a$10$PwOoVTj15t2bdnoJ0MLbBuTLB4WBv8C4ENJmCCUhaWeeJ2nC2Vv4.";

        System.out.println(
            BCrypt.checkpw("test123", hash)
        );
    }
}


package com.kwon.gbraucp2.mail;

import java.util.Random;

public class AuthNumber {
    public static void main(String[] args) {
        // 6자리의 랜덤 숫자 생성
        String randomNumber = generateRandomNumber(100000, 999999);
        System.out.println("랜덤 숫자: " + randomNumber);
    }

    public static String generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return String.format("%06d", randomNumber); // 6자리로 포맷팅
    }
}
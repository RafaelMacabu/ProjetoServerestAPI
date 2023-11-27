package dev.serverest.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerUtils {

    public static String generateEmail(String base){
        Faker faker = new Faker();
        return base + faker.regexify("[0-9]{3}") + "@qa.com";
    }

    public static String generateName(){
        Faker faker = new Faker(new Locale("pt-BR"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generatePassword(){
        Faker faker = new Faker();
        return faker.bothify("#?#?#?#?");
    }

    public static String generateProductName(){
        Faker faker = new Faker();
        return "Placa Nvidia " + faker.regexify("[0-9]{4}") + " GTX";
    }

    public static String generatePrice(){
        Faker faker = new Faker();
        return faker.regexify("[0-9]{3}");
    }

    public static String generateQuantity(){
        Faker faker = new Faker();
        return faker.regexify("[1][0-9]{2}");
    }


}

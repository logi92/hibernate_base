package com.luv2code.shop;

import java.util.Scanner;


public class ShopApp {
    static String[] command;
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Repository.prepareBD();

        System.out.println("Привет! Я являюсь искусственным интелектом в магазине!");
        System.out.println("Все что от тебя требуется это вводить команды и если ты введешь их правильно, то я выполню их для тебя!");
        commandMessage();
        try {
            while (true) {
                command = scanner.nextLine().split(" ");
                switch (command[0]) {
                    case "showAllProducts":
                        Repository.showMeAllProducts();
                        break;
                    case "showAllConsumers":
                        Repository.showMeAllConsumers();
                        break;
                    case "showProductsByConsumer":
                        Repository.showProductsByConsumer(command[1]);
                        break;
                    case "findConsumersByProductName":
                        Repository.findConsumerByProductName(command[1]);
                        break;
                    case "removeConsumer":
                        Repository.removeConsumer(command[1]);
                        break;
                    case "removeProduct":
                        Repository.removeProduct(command[1]);
                        break;
                    case "buy":
                        Repository.toBuy(command[1], command[2]);
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Я не понял твоей комманды, попробуй еще раз. ");
                }
                repeatCommands();
            }
        } finally {
            if (Repository.session != null) {
                Repository.session.close();
            }
            if (Repository.factory != null) {
                Repository.factory.close();
            }
        }
    }

    public static void commandMessage() {
        System.out.println("Доступные команды : \n" +
                "1. Вывести список товаров доступных для покупки : \n" +
                "showAllProducts\n" +
                "2. Вывести список всех покупателей, которые сегодня появлялиь в магазине :\n" +
                "showAllConsumers\n" +
                "3. Посмотреть какие товары покупал клиент :\n" +
                "showProductsByConsumer имя_покупателя\n" +
                "4. Посмотреть какие клиенты купили определенный товар :\n" +
                "findConsumersByProductName название_товара\n" +
                "5. Удалить Покупателя :\n" +
                "removeConsumer имя_покупателя\n" +
                "6. Удалить Товар :\n" +
                "removeProduct название_товара\n" +
                "7. Возможность приобрести товар определенным покупателем :\n" +
                "buy имя_покупателя название_товара\n" +
                "8. Что бы выключить меня введи exit");
    }

    public static void repeatCommands() {
        System.out.println("\n Команды : ");
        System.out.println("showAllProducts | showAllConsumers | showProductsByConsumer имя_покупателя\n" +
                "findConsumersByProductName название_товара | removeConsumer имя_покупателя\n" +
                "removeProduct название_товара | buy имя_покупателя название_товара | exit\n");
    }
}

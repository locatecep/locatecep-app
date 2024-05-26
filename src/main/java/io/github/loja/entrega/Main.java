package io.github.loja.entrega;

import io.github.loja.entrega.service.DeliveryAreaChecker;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java -jar locateCep-app.jar <CEP>");
            return;
        }

        String cep = args[0];
        DeliveryAreaChecker checker = new DeliveryAreaChecker();
        boolean entrega = checker.isWithinDeliveryArea(cep);
        System.out.println("Entrega no CEP " + cep + ": " + (entrega ? "Sim" : "Não"));
    }
}

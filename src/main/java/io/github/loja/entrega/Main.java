package io.github.loja.entrega;

import io.github.loja.entrega.service.DeliveryAreaChecker;
import io.github.loja.entrega.strategy.DeliveryAreaStrategy;
import io.github.loja.entrega.strategy.StateDeliveryStrategy;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java -jar locateCep-app.jar <CEP>");
            return;
        }

        String cep = args[0];
        DeliveryAreaStrategy strategy = new StateDeliveryStrategy("SP", "RJ", "MG"); // Estados com entrega
        DeliveryAreaChecker checker = new DeliveryAreaChecker(strategy);

        boolean entrega = checker.isWithinDeliveryArea(cep);
        if (entrega) {
            System.out.println("Entregamos no CEP " + cep);
        } else {
            System.out.println("Não entregamos no CEP " + cep);
        }
    }
}

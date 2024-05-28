package io.github.loja.entrega.strategy;

import io.github.locatecep.model.Cep;

import java.util.Arrays;
import java.util.List;

public class StateDeliveryStrategy implements DeliveryAreaStrategy {
    private final List<String> allowedStates;

    public StateDeliveryStrategy(String... allowedStates) {
        this.allowedStates = Arrays.asList(allowedStates);
    }

    @Override
    public boolean isWithinDeliveryArea(Cep cep) {
        return allowedStates.contains(cep.getUf());
    }
}

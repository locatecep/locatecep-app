package io.github.loja.entrega.strategy;

import io.github.locatecep.model.Cep;

public interface DeliveryAreaStrategy {
    boolean isWithinDeliveryArea(Cep cep);
}

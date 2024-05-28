package io.github.loja.entrega.service;

import io.github.locatecep.model.Cep;
import io.github.locatecep.service.CepService;
import io.github.locatecep.repository.CepRepository;
import io.github.locatecep.repository.DatabaseCepRepository;
import io.github.loja.entrega.strategy.DeliveryAreaStrategy;

public class DeliveryAreaChecker {
    private final CepService cepService;
    private final DeliveryAreaStrategy deliveryAreaStrategy;

    public DeliveryAreaChecker(DeliveryAreaStrategy deliveryAreaStrategy) {
        CepRepository repository = new DatabaseCepRepository();
        this.cepService = new CepService(repository);
        this.deliveryAreaStrategy = deliveryAreaStrategy;
    }

    public boolean isWithinDeliveryArea(String cep) {
        try {
            Cep cepInfo = cepService.buscar(cep);
            return deliveryAreaStrategy.isWithinDeliveryArea(cepInfo);
        } catch (Exception e) {
            return false;
        }
    }
}

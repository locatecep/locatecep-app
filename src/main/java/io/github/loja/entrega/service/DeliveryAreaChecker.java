package io.github.loja.entrega.service;

import io.github.locatecep.model.Cep;
import io.github.locatecep.service.CepService;
import io.github.locatecep.repository.CepRepository;
import io.github.locatecep.repository.DatabaseCepRepository;

public class DeliveryAreaChecker {
    private CepService cepService;

    public DeliveryAreaChecker() {
        CepRepository repository = new DatabaseCepRepository();
        this.cepService = new CepService(repository);
    }

    public boolean isWithinDeliveryArea(String cep) {
        try {
            Cep cepInfo = cepService.buscar(cep);
            // Lógica para verificar se o CEP está na área de entrega
            // TODO Criar lógica real para verificar se o CEP está na área de entrega
            return cepInfo.getUf().equals("SP"); // Exemplo: entrega apenas em SP
        } catch (Exception e) {
            return false; // CEP não encontrado ou erro na consulta
        }
    }
}

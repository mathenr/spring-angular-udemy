package io.mathenr.service;

import io.mathenr.model.Cliente;
import io.mathenr.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        ClientesRepository clientesRepository = new ClientesRepository();
        clientesRepository.persistir(cliente);
    }

    private void validarCliente(Cliente cliente) {
        // aplica validações
    }
}

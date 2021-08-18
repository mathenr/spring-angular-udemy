package io.mathenr.clientes.controller;

import io.mathenr.clientes.model.Cliente;
import io.mathenr.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente saveClient(@RequestBody @Valid Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("{id}")
    @ResponseStatus
    public Cliente findById(@PathVariable Integer id) {
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Integer id) {
        clienteRepository
                .findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cliente updateClient(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            clienteAtualizado.setId(cliente.getId());
            return clienteRepository.save(clienteAtualizado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

}

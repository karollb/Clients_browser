package pl.edu.wszib.client_browser.service;

import pl.edu.wszib.client_browser.model.Client;

import java.util.List;

public interface IClientService {
    Client getClientById(final Long id);

    List<Client> getAllClients();

    void deleteClient(final Client client);

    boolean addNewClient(final Client client);
}

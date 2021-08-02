package pl.edu.wszib.client_browser.service;

import pl.edu.wszib.client_browser.model.Client;

public interface IClientService {
    Client getClientById(final Long id);

    void deleteClient(final Client client);

    boolean addNewClient(final Client client);
}

package pl.edu.wszib.client_browser.dao;

import pl.edu.wszib.client_browser.model.Client;

import java.util.List;

public interface IClientDAO {
    void addNewClient(final Client client);

    void deleteClient(final Client client);

    Client getClientById(final Long id);

    List<Client> getAllClients();
}

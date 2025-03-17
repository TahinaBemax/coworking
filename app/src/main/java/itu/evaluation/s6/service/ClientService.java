package itu.evaluation.s6.service;

import itu.evaluation.s6.exception.PhoneNumberInvalidException;
import itu.evaluation.s6.model.Client;
import itu.evaluation.s6.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client authentification(String phoneNumber) throws PhoneNumberInvalidException {
        // Expression régulière pour vérifier le format malgache
        String regex = "^(\\+261|0)(32|33|34|38|39|22|20)\\d{7}$";

        // Vérification avec la regex
        if (phoneNumber != null && phoneNumber.matches(regex)){
            Client c = clientRepository.findByNumeroTel(phoneNumber).orElse(null);
            //Si le numero de telephone n'existe pas on l'inscrit
            if (c == null){
                c = new Client();
                c.setNumeroTel(phoneNumber);

                c = clientRepository.save(c);
            }

            return c;
        }

        throw new PhoneNumberInvalidException(phoneNumber);
    }
}

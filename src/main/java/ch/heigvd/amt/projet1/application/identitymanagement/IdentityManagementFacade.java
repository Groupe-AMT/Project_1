package ch.heigvd.amt.projet1.application.identitymanagement;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAOLocal;

import javax.inject.Inject;

public class IdentityManagementFacade {
    IPersonRepository personRepository;

    public void register(RegisterCommand command)throws RegisterFailedException{

        if(personRepository.findByUsername(command.getUsername()) !=null)
            throw new RegisterFailedException("Username is already used");

        try {
            Person newPerson = Person.builder()
                    .username(command.getUsername())
                    .firstname(command.getFirstname())
                    .lastName(command.getLastname())
                    .email(command.getEmail())
                    .clearTextPassword(command.getClearPassword())
                    .build();
            personRepository.save(newPerson);
        }catch (Exception e){
            throw new RegisterFailedException(e.getMessage());
        }
    }

    public CurrentUserDTO authenticate(AuthentificateCommand command) throws AuthentificateFailedException{
        Person person = personRepository.findByUsername(command.getUsername());

        boolean success;
        if (person != null){
            success = person.authenticate(command.getClearPassword());
        } else {
            throw new AuthentificateFailedException("Verification of credentials failed");
        }

        return CurrentUserDTO.builder()
                .username(person.getUsername())
                .firstname(person.getFirstname())
                .lastname(person.getLastName())
                .email(person.getEmail())
                .build();
    }
}

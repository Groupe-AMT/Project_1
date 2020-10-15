package ch.heigvd.amt.projet1.application.identitymanagement;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentifcateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAO;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAOLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;


public class IdentityManagementFacade {
    @Inject
    private PersonDAOLocal personRepository;

    public void register(RegisterCommand command)throws RegisterFailedException{
        Person existingPersonWithSameUsername = personRepository.findByUsername(command.getUsername());//.orElse(null);
        if(existingPersonWithSameUsername !=null){
            throw new RegisterFailedException("Username is already used");
        }
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
    public CurrentUserDTO authenticate(AuthentifcateCommand command) throws AuthentificateFailedException{
        Person person = null;
        try{
            person = personRepository.findByUsername(command.getUsername());
        } catch (Exception e){
            e.printStackTrace();
        }

        boolean success;
        if (person != null){
            success = person.authenticate(command.getClearPassword());
        } else { success = false; }
        if(!success){
            throw new AuthentificateFailedException("Verification of credentials failed");
        }
        CurrentUserDTO currentUser = CurrentUserDTO.builder()
                .username(person.getUsername())
                .firstname(person.getFirstname())
                .lastname(person.getLastName())
                .email(person.getEmail())
                .build();
        return currentUser;
    }
}

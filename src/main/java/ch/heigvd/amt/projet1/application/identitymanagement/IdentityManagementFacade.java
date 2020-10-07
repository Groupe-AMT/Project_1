package ch.heigvd.amt.projet1.application.identitymanagement;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentifcateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.dao.PersonDAOLocal;

public class IdentityManagementFacade {
    private PersonDAOLocal personRepository;
    public IdentityManagementFacade(PersonDAOLocal personRepository){this.personRepository=personRepository;}
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
        Person person = personRepository.findByUsername(command.getUsername());
                //.orElseThrow(()-> new AuthentificateFailedException("user not found"));

        boolean success = person.authenticate(command.getClearPassword());
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

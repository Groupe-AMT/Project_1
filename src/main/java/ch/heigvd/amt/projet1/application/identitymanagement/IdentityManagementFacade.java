package ch.heigvd.amt.projet1.application.identitymanagement;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.AuthentificateFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.login.RegisterFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.updatePassword.UpdatePasswordCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.updatePassword.UpdatePasswordFailedException;
import ch.heigvd.amt.projet1.application.identitymanagement.updateProfile.UpdateProfileCommand;
import ch.heigvd.amt.projet1.application.identitymanagement.updateProfile.UpdateProfileFailedException;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.domain.person.Person;
import org.mindrot.jbcrypt.BCrypt;

public class IdentityManagementFacade {
    IPersonRepository personRepository;

    public IdentityManagementFacade(IPersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public void register(RegisterCommand command)throws RegisterFailedException{
        if (!checkPasswordRules(command.getClearPassword()))
            throw new RegisterFailedException("Mot de passe faible. Le mot de passe doit contenir une minuscule, une majuscule, un chiffre et minimum 8 caractères.");

        if(personRepository.findByUsername(command.getUsername()).isPresent())
            throw new RegisterFailedException("Nom d'utilisateur déjà utilisé");

        try {
            personRepository.save(Person.builder()
                    .username(command.getUsername())
                    .firstname(command.getFirstname())
                    .lastName(command.getLastname())
                    .email(command.getEmail())
                    .clearTextPassword(command.getClearPassword())
                    .build());
        }catch (Exception e){
            throw new RegisterFailedException(e.getMessage());
        }
    }

    public CurrentUserDTO authenticate(AuthentificateCommand command) throws AuthentificateFailedException{
        Person person = personRepository.findByUsername(command.getUsername()).orElse(null);

        if (person != null && person.authenticate(command.getClearPassword())){
            return CurrentUserDTO.builder()
                    .username(person.getUsername())
                    .firstname(person.getFirstname())
                    .lastname(person.getLastName())
                    .email(person.getEmail())
                    .build();
        } else {
            throw new AuthentificateFailedException("Mauvais identifiants");
        }
    }

    public CurrentUserDTO updateProfile(CurrentUserDTO currUser ,UpdateProfileCommand command)throws UpdateProfileFailedException{
        if(personRepository.findByUsername(command.getUsername()).isPresent() && !command.getUsername().equals(currUser.getUsername()))
            throw new UpdateProfileFailedException("Nom d'utilisateur déjà utilisé");

        try {
            String newUsername = command.getUsername();
            String newFirstname = command.getFirstname();
            String newLastname = command.getLastname();
            String newEmail = command.getEmail();
            if (newUsername.equals("")){   newUsername = currUser.getUsername();   }
            if (newFirstname.equals("")){   newFirstname = currUser.getFirstname();   }
            if (newLastname.equals("")){   newLastname = currUser.getLastname();   }
            if (newEmail.equals("")){   newEmail = currUser.getEmail();   }

            personRepository.updating(currUser, newUsername, newFirstname, newLastname, newEmail);
            return CurrentUserDTO.builder()
                    .username(newUsername)
                    .firstname(newFirstname)
                    .lastname(newLastname)
                    .email(newEmail)
                    .build();
        }catch (Exception e){
            throw new UpdateProfileFailedException("Erreur dans la mise à jour du profil");
        }
    }

    public void updatePassword(CurrentUserDTO currUser, UpdatePasswordCommand command)throws UpdatePasswordFailedException{
        if (!checkPasswordRules(command.getNew_pass())){
            throw new UpdatePasswordFailedException("Mot de passe faible. Le mot de passe doit contenir une minuscule, une majuscule, un chiffre et minimum 8 caractères.");
        }

        Person person = personRepository.findByUsername(currUser.getUsername()).orElse(null);

        if (person != null && person.authenticate(command.getPrev_pass())){
            String hashpassword = BCrypt.hashpw(command.getNew_pass(), BCrypt.gensalt());
            try {
                personRepository.changingPass(currUser, hashpassword);
            }catch (Exception e){
                throw new UpdatePasswordFailedException("Echec de la mise à jour");
            }
        } else {
            throw new UpdatePasswordFailedException("Mauvais mot de passe");
        }
    }

    /* Règles à vérifier sur les mots de passe */
    private boolean checkPasswordRules(String clearPass){
        boolean hasDigit = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        if (clearPass.length() >= 8) {
            for (char c : clearPass.toCharArray()) {
                if (Character.isDigit(c) && !hasDigit) {
                    hasDigit = true;
                }
                if (Character.isUpperCase(c) && !hasUpperCase){
                    hasUpperCase = true;
                }
                if (Character.isLowerCase(c) && !hasLowerCase){
                    hasLowerCase = true;
                }
            }
        }
        return hasDigit && hasLowerCase && hasUpperCase;
    }
}

package ch.heigvd.amt.projet1.domain.person;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import ch.heigvd.amt.projet1.domain.IRepository;

import java.util.Optional;
public interface IPersonRepository extends IRepository<Person,PersonId> {
    public Optional<Person> findByUsername(String username);

    // For changing personnal informations
    public void updating(CurrentUserDTO user, String username, String firstname, String lastname, String email);
    public void changingPass(CurrentUserDTO user, String new_pass);
}

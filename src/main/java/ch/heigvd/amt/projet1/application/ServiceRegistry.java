package ch.heigvd.amt.projet1.application;

import ch.heigvd.amt.projet1.application.identitymanagement.IdentityManagementFacade;
import ch.heigvd.amt.projet1.domain.person.IPersonRepository;
import ch.heigvd.amt.projet1.infrastructure.persistence.memory.InMemoryPersonRepository;

public class ServiceRegistry {
    private static ServiceRegistry singleton;
    private static IPersonRepository personRepository;
    private static IdentityManagementFacade identityManagementFacade;
    public static ServiceRegistry getServiceRegistry(){
        if(singleton==null){
            singleton = new ServiceRegistry();
        }
        return singleton;
    }
    private ServiceRegistry(){
        singleton=this;
        personRepository= new InMemoryPersonRepository();
        identityManagementFacade=new IdentityManagementFacade(personRepository);
    }
    public  IdentityManagementFacade getIdentityManagementFacade(){return  identityManagementFacade;}
}

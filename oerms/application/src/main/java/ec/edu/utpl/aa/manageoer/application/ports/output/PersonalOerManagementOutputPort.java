package ec.edu.utpl.aa.manageoer.application.ports.output;

import ec.edu.utpl.aa.manageoer.domain.entity.Oer;

public interface PersonalOerManagementOutputPort {
    Oer retrieveOer();
    Oer updateOer();
    Oer deleteOer();
}

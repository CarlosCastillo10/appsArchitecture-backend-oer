module application {
    exports ec.edu.utpl.aa.manageoer.application.ports.input;
    exports ec.edu.utpl.aa.manageoer.application.ports.output;
    exports ec.edu.utpl.aa.manageoer.application.usecases;
    requires domain;
    requires static java.sql;
    requires jakarta.enterprise.cdi.api;
    requires jakarta.inject.api;
}
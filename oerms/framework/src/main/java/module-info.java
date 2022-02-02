module framework {
    requires domain;
    requires application;
    requires jakarta.enterprise.cdi.api;
    requires jakarta.inject.api;
    requires java.json;
    requires io.helidon.webserver;
    requires com.google.api.apicommon;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires java.sql;
    requires static lombok;
}
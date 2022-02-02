module framework {
    requires domain;
    requires application;
    requires com.google.api.apicommon;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires java.sql;
    requires jakarta.enterprise.cdi.api;
    requires static lombok;
}
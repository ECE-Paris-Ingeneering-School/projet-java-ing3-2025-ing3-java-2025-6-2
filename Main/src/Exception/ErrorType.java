package Exception;

public enum ErrorType {
    DATABASE_ERROR("Erreur de base de données"),
    IMAGE_ERROR("Erreur de chargement d'image"),
    VALIDATION_ERROR("Erreur de validation"),
    AUTHENTICATION_ERROR("Erreur d'authentification"),
    GENERAL_ERROR("Erreur générale");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 
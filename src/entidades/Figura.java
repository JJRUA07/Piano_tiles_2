package entidades;

public enum Figura {
    REDONDA,
    BLANCA,
    NEGRA,
    CORCHEA,
    SEMICORCHEA,
    FUSA,
    SEMIFUSA;

    public String getNombreMostrado() {
        switch(this) {
            case REDONDA:    return "Redonda";
            case BLANCA:     return "Blanca";
            case NEGRA:      return "Negra";
            case CORCHEA:    return "Corchea";
            case SEMICORCHEA:return "Semicorchea";
            case FUSA:       return "Fusa";
            case SEMIFUSA:   return "Semifusa";
            default:         return this.name();
        }
    }
}
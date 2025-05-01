package entidades;

public enum Nota {
    DO,
    DO_SOSTENIDO,
    RE,
    RE_SOSTENIDO,
    MI,
    FA,
    FA_SOSTENIDO,
    SOL,
    SOL_SOSTENIDO,
    LA,
    LA_SOSTENIDO,
    SI;

    public String getNombreMostrado() {
        switch(this) {
            case DO:   return "Do";
            case DO_SOSTENIDO:  return "Do♯";
            case RE:   return "Re";
            case RE_SOSTENIDO:  return "Re♯";
            case MI:   return "Mi";
            case FA:   return "Fa";
            case FA_SOSTENIDO:  return "Fa♯";
            case SOL:  return "Sol";
            case SOL_SOSTENIDO: return "Sol♯";
            case LA:   return "La";
            case LA_SOSTENIDO:  return "La♯";
            case SI:   return "Si";
            default:   return this.name();
        }
    }
}
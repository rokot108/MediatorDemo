package ru.volkov.model.competentions;

public class AbstractCompetence implements Competence {

    String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj.getClass().isInstance(this)) {
            if (this.hashCode() == ((AbstractCompetence) obj).hashCode()) {
                return true;
            }
        }
        return false;
    }
}

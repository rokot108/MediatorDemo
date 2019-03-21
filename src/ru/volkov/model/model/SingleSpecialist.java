package ru.volkov.model.model;

import ru.volkov.model.competentions.Competence;

public class SingleSpecialist {

    private String name;
    private int skill;
    private Competence competence;

    synchronized public void makeAnOperation(Job job) {
        System.out.println("Specialist " + name + " doing " + competence.getName());

        try {
            wait(100_000 / skill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        job.getCompetencesDone().put(competence, true);
        System.out.println("Specialist " + name + " finished task " + competence.getName());
    }

    public SingleSpecialist(String name, int skill, Competence competence) {
        this.name = name;
        this.skill = skill;
        this.competence = competence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
}

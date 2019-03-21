package ru.volkov.model.model;

import ru.volkov.model.competentions.Competence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ITCompany implements JobContractor {

    private String name;
    private HashMap<Competence, Boolean> сompetences = new HashMap();
    private List<SingleSpecialist> specialists = new ArrayList<>();

    public ITCompany(String name) {
        this.name = name;
    }

    @Override
    public boolean haveVacancy(SingleSpecialist specialist) {
        Boolean vacancy = сompetences.get(specialist.getCompetence());
        if (vacancy != null && !vacancy) {
            return true;
        }
        return false;
    }

    @Override
    public void hireSpecialist(SingleSpecialist specialist) {
        System.out.println("Company " + name + " hired " + specialist.getName());
        specialists.add(specialist);
        addCompetence(specialist.getCompetence());
    }

    @Override
    public void fireSpecialist(SingleSpecialist specialist) {
        if (specialists.remove(specialist)) {
            this.сompetences.remove(specialist.getCompetence());
        }
    }

    @Override
    public void addCompetence(Competence competence) {
        сompetences.put(competence, false);
    }

    @Override
    public HashMap<Competence, Boolean> getCompetences() {
        return this.сompetences;
    }

    @Override
    public void signAContract() {
        try {
            wait(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doTheJob(Job job) {
        System.out.println("Company " + name + " got a new contract");
        signAContract();
        final int jobSteps = job.getCompetencesDone().size();
        ExecutorService executorService = Executors.newFixedThreadPool(jobSteps);

        for (SingleSpecialist specialist : specialists) {
            if (!job.getCompetencesDone().get(specialist.getCompetence())) {
                executorService.submit(() ->
                        specialist.makeAnOperation(job)
                );
            }
        }
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}

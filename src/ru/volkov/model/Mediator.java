package ru.volkov.model;

import ru.volkov.model.competentions.Competence;
import ru.volkov.model.model.Job;
import ru.volkov.model.model.JobContractor;
import ru.volkov.model.model.SingleSpecialist;
import ru.volkov.model.model.SpecialistAsContractorHandler;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mediator {

    private List<JobContractor> contractors = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(16);

    public void addContractor(JobContractor contractor) {
        contractors.add(contractor);
    }

    public void addSpecialist(SingleSpecialist specialist) {
        for (JobContractor c : contractors) {
            if (c.haveVacancy(specialist)) {
                c.hireSpecialist(specialist);
                return;
            }
        }
        addSpecialistAsContractor(specialist);
    }

    public void addSpecialistAsContractor(SingleSpecialist specialist) {
        System.out.println(specialist.getName() + " have not been hired and became a freelancer!");
        JobContractor contractor = (JobContractor) Proxy.newProxyInstance(Mediator.class.getClassLoader(),
                new Class[]{JobContractor.class},
                new SpecialistAsContractorHandler(specialist));
        contractors.add(contractor);
    }

    synchronized private JobContractor makeTender(Job job) {
        if (contractors.size() != 0) {
            List<Competence> tenderPositions = new ArrayList<>();
            for (Competence c : job.getCompetencesDone().keySet()) {
                if (!job.getCompetencesDone().get(c)) {
                    tenderPositions.add(c);
                }
            }
            int winnerScore = 0;
            JobContractor winner = null;
            for (JobContractor contractor : contractors) {
                int score = 0;
                for (Competence c : contractor.getCompetences().keySet()) {
                    if (contractor.getCompetences().get(c) && !job.getCompetencesDone().get(c)) {
                        score++;
                    }
                }
                if (score > winnerScore) {
                    winnerScore = score;
                    winner = contractor;
                }
            }

            if (winner != null) {
                return winner;
            }
        }
        return null;
    }

    public void makeAJob(Job job) {
        System.out.println("Mediator performing a job");

        executorService.submit(() -> {
            JobContractor contractor = makeTender(job);
            if (contractor == null) {
                return;
            }
            contractor.doTheJob(job);
            if (!job.isDone()) {
                makeAJob(job);
            }
        });
    }

    public void shutDown() {
        executorService.shutdown();
    }
}

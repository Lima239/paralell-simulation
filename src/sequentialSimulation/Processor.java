package sequentialSimulation;

import java.util.*;

public class Processor {
    private final double latency;
    private ArrayList<Double> listOfTasks;
    private ArrayList<ArrayList<Double>> listOfJobs;
    private Iterator<Double> iteratorOfTasks;
    private JobsIterator iterableOfJobs;
    private Iterator iteratorOfJobs;


    public Processor(double latency){
        this.latency = latency;
        this.listOfTasks = new ArrayList<>();
        this.listOfJobs = new ArrayList<>();
        iteratorOfTasks = listOfTasks.iterator();
        iterableOfJobs = new JobsIterator(listOfJobs);
        iteratorOfJobs = iterableOfJobs.iterator();
    }

    public void reset(){
        this.listOfTasks.clear();
        this.listOfJobs.clear();
    }

    public void read_job(ArrayList<Double> listOfTasks){
        iterableOfJobs.addJob(listOfTasks);
    }

    public double simulateJob(ArrayList<Double> job){
        Iterator<Double> simulateIterator = job.iterator();
        double counter = latency;

        while(simulateIterator.hasNext()){
            counter += simulateIterator.next();
        }

        return counter;
    }

    public double executeTask(){
        double counter = 0;
        if(iteratorOfTasks.hasNext()){
            double nextTask = iteratorOfTasks.next();
            counter += nextTask;
        } else if(iteratorOfJobs.hasNext()){
            this.listOfTasks = new ArrayList<>();
            listOfTasks.addAll((Collection<? extends Double>) iteratorOfJobs.next());
            iteratorOfTasks =  listOfTasks.iterator();
            if(iteratorOfTasks.hasNext()) {
                double nextTask = iteratorOfTasks.next();
                counter += latency;
                counter += nextTask;
            }
        }
        return counter;
    }

    public boolean hasNext(){
        return iteratorOfJobs.hasNext() || iteratorOfTasks.hasNext();
    }

    public double executeJob(){
        double timeCounter = 0;
        double resultTime = executeTask();
        while(resultTime != 0) {
            timeCounter += resultTime;
            resultTime = executeTask();
        }
        return timeCounter;
    }
}

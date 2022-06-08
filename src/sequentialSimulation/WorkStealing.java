package sequentialSimulation;

import java.util.*;

public class WorkStealing implements Algorithms{
    private ArrayList<Double> tasks;
    private Iterator<Double> iteratorOfTasks;
    private int numberOfProcessors;
    private double latency;
    private ArrayList<Processor> processors;
    private int numberOfTasks;

    public WorkStealing(ArrayList<Double> tasks, int numberOfProcessors, double latency){
        this.tasks = tasks;
        this.numberOfProcessors = numberOfProcessors;
        this.latency = latency;
        this.iteratorOfTasks =  tasks.iterator();
        this.numberOfTasks = tasks.size();

        processors = new ArrayList<>();
        for (int i = 0; i < numberOfProcessors; i++){
            Processor processor = new Processor(this.latency);
            processors.add(processor);
        }
    }

    private Double findMax(Map<Processor, Double> map){
        double max = Double.MIN_VALUE;
        Processor maxProcessor = processors.get(0);
        for (int i = 0; i < numberOfProcessors; i++){
            if(max < map.get(processors.get(i))){
                max = map.get(processors.get(i));
                maxProcessor = processors.get(i);
            }
        }
        return map.get(maxProcessor);
    }

    @Override
    public Double redistributeTasks() {
        Map<Processor, Double> map = new HashMap<>();

        for (int i = 0; i < numberOfProcessors; i++){
            map.put(processors.get(i), 0.0);
        }

        while (numberOfTasks > 0) {
            int numberOfTasksPerOne = numberOfTasks / numberOfProcessors;
            int rest = numberOfTasks % numberOfProcessors;

            int begin;
            int end = 0;
            ArrayList<Integer> begins = new ArrayList<>();

            double minValue = Double.MAX_VALUE;
            Processor minProcessor = processors.get(0);
            ArrayList<Double> minJob = new ArrayList<>();

            for (int i = 0; i < numberOfProcessors; i++){
                begin = end;
                begins.add(begin);
                if (rest > 0){
                    end = begin + numberOfTasksPerOne + 1;
                    rest--;
                } else{ end = begin + numberOfTasksPerOne;}

                ArrayList<Double> tasksPerOne = new ArrayList<>();
                tasksPerOne.addAll(tasks.subList(begin, end));

                Processor p = processors.get(i);
                Double simulateValue = p.simulateJob(tasksPerOne);

                if(minValue > simulateValue){
                    minValue = simulateValue;
                    minProcessor = p;
                    minJob = tasksPerOne;
                }
            }
            begins.add(end);

            minProcessor.read_job(minJob);
            map.put(minProcessor, map.get(minProcessor) + minProcessor.executeJob());
            ArrayList<Double> unprocessedTasks = new ArrayList<>();

            for (int i = 0; i < numberOfProcessors; i++){
                Processor p = processors.get(i);
                ArrayList<Double> tasksPerOne = new ArrayList<>();

                if(!p.equals(minProcessor)){
                    int index = begins.get(i);
                    while(p.simulateJob(tasksPerOne) < minValue){
                        tasksPerOne.add(tasks.get(index));
                        index++;
                    }

                    p.read_job(tasksPerOne);
                    Double value = map.get(p);
                    map.put(p, value + p.executeJob());
                    unprocessedTasks.addAll(tasks.subList(index, begins.get(i + 1)));
                }
            }

            this.tasks = unprocessedTasks;
            numberOfTasks = tasks.size();
        }

        return findMax(map);
    }
}

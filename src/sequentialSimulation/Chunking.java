package sequentialSimulation;

import java.util.*;

public class Chunking implements Algorithms{
    private ArrayList<Double> tasks;
    private Iterator<Double> iteratorOfTasks;
    private int numberOfProcessors;
    private double latency;
    private ArrayList<Processor> processors;
    private int numberOfTasks;
    private int parameter;

    public Chunking(ArrayList<Double> tasks, int numberOfProcessors, double latency, int parameter){
        this.tasks = tasks;
        this.numberOfProcessors = numberOfProcessors;
        this.latency = latency;
        this.iteratorOfTasks =  tasks.iterator();
        this.numberOfTasks = tasks.size();
        this.parameter = parameter;

        processors = new ArrayList<>();
        for (int i = 0; i < numberOfProcessors; i++){
            Processor processor = new Processor(this.latency);
            processors.add(processor);
        }
    }

    private Processor findMin(Map<Processor, Double> map){
        double min = Double.MAX_VALUE;
        Processor minProcessor = processors.get(0);
        for (int i = 0; i < numberOfProcessors; i++){
            if(min > map.get(processors.get(i))){
                min = map.get(processors.get(i));
                minProcessor = processors.get(i);
            }
        }
        return minProcessor;
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
            ArrayList<Double> tmpList = new ArrayList<>();
            for ( int j = 0; j < parameter; j++) {
                if(iteratorOfTasks.hasNext()) tmpList.add(iteratorOfTasks.next());
            }
            processors.get(i).read_job(tmpList);
            double value = processors.get(i).executeJob();
            map.put(processors.get(i), value);
        }

        while (iteratorOfTasks.hasNext()) {
            ArrayList<Double> tmpList = new ArrayList<>();
            for (int i = 0; i < parameter; i++) {
                if(iteratorOfTasks.hasNext()) tmpList.add(iteratorOfTasks.next());
            }
            Processor p = findMin(map);
            Double value = map.get(p);
            p.read_job(tmpList);
            map.put(p, p.executeJob() + value);
        }

        return findMax(map);
    }
}

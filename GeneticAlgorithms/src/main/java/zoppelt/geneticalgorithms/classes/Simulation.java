package zoppelt.geneticalgorithms.classes;

import java.util.ArrayList;
import java.util.List;
import net.objecthunter.exp4j.*;
import zoppelt.geneticalgorithms.classes.selection.RouletteSelection;
import zoppelt.geneticalgorithms.classes.selection.SelectionMethod;

public class Simulation {
	public void runSimulation(List<Object> simulationParameters) {
		//Store simulation parameters into variables
		int numberOfGenerations = (int)simulationParameters.get(0);
		int populationSize = (int)simulationParameters.get(1);
		int lengthOfChromosome = (int)simulationParameters.get(2);
		double crossProbability = (Double) simulationParameters.get(3);
		double mutateProbability = (Double) simulationParameters.get(4);
		String selectionStr = (String)simulationParameters.get(6);
		Expression fitnessFunction;		
		
		//Create list to store generations
		List<Generation> genList = new ArrayList<>();
		
		//Set selection method
		SelectionMethod selectionMethod = setSelectionMethod(selectionStr);
		
		try {
			fitnessFunction = new ExpressionBuilder((String)simulationParameters.get(5)).variables("x").build();			
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		Generation initialGeneration = new Generation(0, populationSize, lengthOfChromosome);
		
		initializeFirstGeneration(initialGeneration);
		initialGeneration.calculateFitnessRatio(fitnessFunction);
		initialGeneration.printGeneration();
		
		genList.add(initialGeneration);
		
		//Main Simulation Loop
		for (int i = 1; i < numberOfGenerations; i++) {
			Generation currentGen = new Generation(i, populationSize, lengthOfChromosome);
			
			generateNewPopulation(genList.get(i - 1), currentGen, selectionMethod, crossProbability, mutateProbability);
			mutateChromosomes(currentGen, mutateProbability);
			
			currentGen.calculateFitnessRatio(fitnessFunction);
			currentGen.printGeneration();
			genList.add(currentGen);
		}
		
		
	}
	
	private SelectionMethod setSelectionMethod(String selectionStr) {
		switch(selectionStr) {
		case "Roulette Selection":
			return new RouletteSelection();
		default:
			return new RouletteSelection();
		}
	}

	private void initializeFirstGeneration(Generation generation) {
		for(int i = 0; i < generation.getChromosomes().size(); i++) {
			String geneticInformation = "";
			
			for(int j = 0; j < generation.getChromosome(i).getLength(); j++) {
				geneticInformation += (int)Math.floor(Math.random() * 2);
			}
			
			generation.getChromosome(i).setGeneticInformation(geneticInformation);
		}
	}
	
	private void generateNewPopulation(Generation previousGen, Generation currentGen, SelectionMethod selectionMethod, double crossProbability, double mutateProbability) {
		//Determine how much of the population should be a result of cross probability
		double crossPop = crossProbability * currentGen.getPopulationSize();
		//Determine cross point
		int crossPoint = currentGen.getLengthOfChromosome() / 2;
		
		if(crossPoint < 0) {
			crossPoint = 0;
		}
		
		//If the floor is even use the floor
		if (Math.floor(crossPop) == 0 && crossProbability != 0) {
			crossPop = 2;
		}
		else if(Math.floor(crossPop) % 2 == 0) {
			crossPop = Math.floor(crossPop);
		}
		//If the floor is odd, use floor + 1
		else {
			crossPop = Math.floor(crossPop) + 1;
		}
		
		int index = 0;
		//Generate population using cross population
		for (int i = 0; i < crossPop / 2; i++) {
			Chromosome cross1 = currentGen.getChromosome(index);
			Chromosome cross2 = currentGen.getChromosome(index + 1);
			
			List<String> newGeneticInfo = crossChromosomes(selectionMethod.selectChromosome(previousGen.getChromosomes()), selectionMethod.selectChromosome(previousGen.getChromosomes()), crossPoint);
			
			cross1.setGeneticInformation(newGeneticInfo.get(0));
			cross2.setGeneticInformation(newGeneticInfo.get(1));
			index += 2;
		}
		
		//Generate population via cloning
		for (int i = (int) crossPop; i < currentGen.getPopulationSize(); i++) {
			Chromosome chromosome = currentGen.getChromosome(i);
			chromosome.setGeneticInformation(selectionMethod.selectChromosome(previousGen.getChromosomes()).getGeneticInformation()); 
		}		
	}
	
	private List<String> crossChromosomes(Chromosome c1, Chromosome c2, int crossPoint) {
		List<String> newGeneticInfo = new ArrayList<>(); 
		String c1Str = c1.getGeneticInformation().substring(0, crossPoint) + c2.getGeneticInformation().substring(crossPoint); 
		String c2Str = c2.getGeneticInformation().substring(0, crossPoint) + c1.getGeneticInformation().substring(crossPoint);
		
//		String test1 = "1001";
//		String test2 = "0110";
		
//		String c1Str = test1.substring(0, crossPoint) + test2.substring(crossPoint); 
//		String c2Str = test2.substring(0, crossPoint) + test1.substring(crossPoint);
		
//		System.out.println(c1.getGeneticInformation() + " " + c2.getGeneticInformation());
//		System.out.println(test1 + " " + test2);
//		System.out.println(c1Str + " " + c2Str);
//		System.out.println(crossPoint);
		
		newGeneticInfo.add(c1Str);
		newGeneticInfo.add(c2Str);
		
		return newGeneticInfo;
	}
	
	private void mutateChromosomes(Generation currentGen, double mutationProbability) {
		for(Chromosome chromosome : currentGen.getChromosomes()) {
			if(Math.random() < mutationProbability) {
				char[] temp = chromosome.getGeneticInformation().toCharArray();
				int index = (int)(Math.random() * chromosome.getLength());
				if(temp[index] == '1') {
					temp[index] = '0';
				}
				else {
					temp[index] = '1';
				}
				chromosome.setGeneticInformation(new String(temp));
				System.out.println("Mutated: " + chromosome.getName());
			}
		}
	}
}

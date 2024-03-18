package zoppelt.geneticalgorithms.classes.selection;

import java.util.List;
import zoppelt.geneticalgorithms.classes.Chromosome;

public class RouletteSelection implements SelectionMethod {

	public Chromosome selectChromosome(List<Chromosome> chromosomes) {
		// TODO Auto-generated method stub
		Chromosome chromosome = new Chromosome();

		/*
		 * This code was retrieved from
		 * https://stackoverflow.com/questions/6737283/weighted-randomness-in-java#:~:
		 * text=Add%20the%20weights%20beginning%20with,to%20your%20running%20weight%
		 * 20counter.&text=Then%20you%20just%20have%20to,to%20get%20a%20valid%20number.&
		 * text=will%20give%20you%20the%20random%20weighted%20item.
		 */
		int i = 0;
		for (double r = Math.random() * 1; i < chromosomes.size() - 1; ++i) {
			r -= chromosomes.get(i).getFitnessRatio();
			if (r <= 0.0)
				break;
		}
		chromosome = chromosomes.get(i);
		
		return chromosome;
	}

}

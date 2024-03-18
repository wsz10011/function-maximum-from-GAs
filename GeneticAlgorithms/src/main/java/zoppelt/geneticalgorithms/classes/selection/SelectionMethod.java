package zoppelt.geneticalgorithms.classes.selection;

import java.util.List;
import zoppelt.geneticalgorithms.classes.Chromosome;

public interface SelectionMethod {
	public Chromosome selectChromosome(List<Chromosome> chromosomes);
}

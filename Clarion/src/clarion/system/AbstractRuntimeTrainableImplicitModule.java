package clarion.system;

import java.util.Collection;

/**
 * This class implements a runtime trainable implicit module within CLARION. It extends the AbstractTrainableImplicitModule class
 * and implements the InterfaceRuntimeTrainable and InterfaceTracksMatchStatistics interfaces. This class is abstract and 
 * therefore cannot be instantiated on its own.
 * <p>
 * <b>Usage:</b>
 * <p>
 * The runtime trainable implicit module is a framework for building an implicit module that can be used in the bottom level of 
 * the CLARION subsystems. A runtime trainable implicit module is unique from other implicit modules in that it
 * can be trained during runtime. Any implicit modules that extend this class will be capable of being trained during runtime 
 * within the CLARION library.
 * <p>
 * <b>Known Subclasses:</b><br>
 * <ul>
 * <li>TableLookup</li>
 * </ul>
 * <p>
 * This class contains both global (static) and local constants. The default is to use the local 
 * constants. If you want to change any of the global constants, you need to do so before any
 * instances of this class are initialized.
 * @version 6.0.4
 * @author Nick Wilson
 */
public abstract class AbstractRuntimeTrainableImplicitModule extends AbstractTrainableImplicitModule  implements 
	InterfaceRuntimeTrainable, InterfaceTracksMatchStatistics{
	
	/**The immediate feedback given to the network (if given).*/
    protected double Feedback;
	
	/**The positive match counter.*/
	protected double PM = 0;
	/**The negative match counter.*/
	protected double NM = 0;
	
    /**The threshold for the positive match criterion. */
    public static double GLOBAL_POSITIVE_MATCH_THRESHOLD = .5;
    /**The threshold for the positive match criterion. */
    public double POSITIVE_MATCH_THRESHOLD = GLOBAL_POSITIVE_MATCH_THRESHOLD;
	
    /**
     * Initializes an implicit module that is capable of being trained during runtime.
     * <p>
	 * If this is being used as an implicit module in the ACS and you are using goals
	 * or specialized working memory chunks, remember that the input space must also contain 
	 * all dimension-value pairs within those chunks that differ from the sensory information
	 * space.
     * @param InputSpace A collection of dimension-value pairs to set as the input nodes.
     * @param Outputs The chunks to associate with the output layer.
     */
	public AbstractRuntimeTrainableImplicitModule(Collection<Dimension> InputSpace, AbstractOutputChunkCollection<? extends AbstractOutputChunk> Outputs) {
		super(InputSpace, Outputs);
	}
	
	/**
	 * Gets the positive match statistic.
	 * @return The positive match statistic.
	 */
	public double getPM ()
	{
		return PM;
	}
	
	/**
	 * Gets the negative match statistic.
	 * @return The negative match statistic.
	 */
	public double getNM ()
	{
		return NM;
	}
	
	/**
	 * Sets the positive match statistic. This method can be used to update the 
	 * positive match statistic if the user wishes to provide their own match criterion 
	 * function.
	 * @param pm The value to set as the positive match statistic.
	 */
	public void setPM (double pm)
	{
		PM = pm;
	}
	
	/**
	 * Sets the negative match statistic. This method can be used to update the 
	 * negative match statistic if the user wishes to provide their own match criterion 
	 * function.
	 * @param nm The value to set as the negative match statistic.
	 */
	public void setNM (double nm)
	{
		NM = nm;
	}
	
	/**
     * Gets the immediate feedback that was last provided to the rule. This method is only 
     * used if feedback is being provided.
     * @return The feedback.
     */
    public double getFeedback()
    {
    	return Feedback;
    }
    
    /**
     * Sets the immediate feedback for the rule. This method should be called before the
     * updateMatchStatistics method is called. This method is only used if feedback is 
     * being provided.
     * @param feedback The value of the feedback.
     */
    public void setFeedback (double feedback)
    {
    	Feedback = feedback;
    }
	
	/**
	 * Updates the positive or negative match statistics based on the feedback.
	 * <p>
	 * This update is usually performed after the feedback has been set.
	 * @param MatchCalculator The match calculator to use to determine positivity.
	 */
	public void updateMatchStatistics (AbstractMatchCalculator MatchCalculator)
	{
		if (MatchCalculator.isPositive(Feedback, POSITIVE_MATCH_THRESHOLD))
		{
			++PM;
		}
		else
			++NM;
	}
	
	/**
	 * Resets the match statistics.
	 */
	public void resetMatchStatistics ()
	{
		PM = 0;
		NM = 0;
	}
	
	/**
	 * Increments the positive match statistic. This method can be used to update the 
	 * positive match statistic if the user wishes to provide their own match criterion 
	 * function.
	 */
	public void incrementPM()
	{
		++PM;
	}
	
	/**
	 * Increments the negative match statistic. This method can be used to update the 
	 * negative match statistic if the user wishes to provide their own match criterion 
	 * function.
	 */
	public void incrementNM()
	{
		++NM;
	}
}

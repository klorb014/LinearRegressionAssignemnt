/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent for multiple variables
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class LinearRegression{


	/** 
     * Number of features (usually "n" in litterature) 
     */
	private int nbreOfFeatures;

	/** 
     * Number of samples (usually "m" in litterature) 
     */
	private int nbreOfSamples;


	/** 
     * the nbreOfFeatures X nbreOfSamples Matrix of samples
     */
	private double[][] samplesMatrix;

	/** 
     * the nbreOfSamples Matrix of samples target values
     */
	private double[] samplesValues;

	/** 
     * the nbreOfFeatures Matrix theta of current hypthesis function
     */
	private double[] theta;


	/** 
     * number of samples received so far
     */
	private int currentNbreOfSamples;

	/** 
     * a place holder for theta during descent calculation
     */
	private double[] tempTheta;


	/** 
     * counts how many iterations have been performed
     */
	private int iteration;


	/** 
     * Object's contructor. The constructor initializes the instance
     * variables. The starting hypthesis is theta[i]=0.0 for all i
     * 
     * @param n the number of features that we will have
     * @param m the number of samples that we will have
	 *
     */
 	public LinearRegression(int n, int m){

		// your code goes there
		nbreOfFeatures = n;
		nbreOfSamples = m;
		samplesMatrix = new double[m][n];
		samplesValues = new double[m];
		theta = new double[n+1];
		currentNbreOfSamples = 0;
		tempTheta = new double[n+1];
		iteration = 0;

	}

	/** 
     * Add a new sample to samplesMatrix and samplesValues
     * 
     * @param x the vectors of samples
     * @param y the coresponding expected value
     *
	 */
	public void addSample(double[] x, double y){

		// your code goes there
		samplesMatrix[currentNbreOfSamples] = x;
		samplesValues[currentNbreOfSamples] = y;
		currentNbreOfSamples ++;

	}

	/** 
     * Returns the current hypothesis for the value of the input
     * @param x the input vector for which we want the current hypothesis
     * 
	 * @return h(x)
	 */

	private double hypothesis(double[] x){

		// your code goes there
		double hypothesis = 0;
		
		for (int i = 0; i <x.length +1 ; i++ ) {
			if (i==0) {
				hypothesis += theta[i];
			}else{
				hypothesis += theta[i] * x[i-1];
			}
		}
		return hypothesis;

	}

	/** 
     * Returns a string representation of hypthesis function
     * 
	 * @return the string "theta0 x_0 + theta1 x_1 + .. thetan x_n"
	 */

	public String currentHypothesis(){

		// your code goes there
		String hyp = " ";

		for (int i =0; i<nbreOfFeatures+1; i++ ) {
			if (i == nbreOfFeatures) {
				hyp += theta[i] + " x_" + i;	
			}else if (i == 0) {
				hyp += theta[i] + " + ";

			}else{
				hyp += theta[i] + " x_" + i + " + ";
			}
			
			
		}

		return hyp;

	}

	/** 
     * Returns the current cost
     * 
	 * @return the current value of the cost function
	 */

	public double currentCost(){

		// your code goes there
		double cost = 0;
		for (int i = 0 ; i < nbreOfSamples; i++){
				cost = cost + Math.pow((hypothesis(samplesMatrix[i]) - samplesValues[i]),2);				
			

		}
		return cost / nbreOfSamples;

	}

	/** 
     * runs several iterations of the gradient descent algorithm
     * 
     * @param alpha the learning rate
     *
     * @param numberOfSteps how many iteration of the algorithm to run
     */

	public void gradientDescent(double alpha, int numberOfSteps) {


		// your code goes there
		for (int h = 0; h < numberOfSteps ; h++ ) {
			for (int i = 0; i< nbreOfSamples ; i++ ) {
				for (int j= 0; j< nbreOfFeatures +1 ; j++ ) {
					if (j ==0) {
						tempTheta[j] = tempTheta[j] + ((hypothesis(samplesMatrix[i]) - samplesValues[i]) * 1);
					}else{
						tempTheta[j] = tempTheta[j] + ((hypothesis(samplesMatrix[i]) - samplesValues[i]) * samplesMatrix[i][j-1]);
					}
					
				}
				
			}
			for (int k =0; k<nbreOfFeatures + 1; k++){
				theta[k] = theta[k] - ((alpha * (2) * tempTheta[k])/nbreOfSamples) ;
				tempTheta[k] = 0;
			}

		}


	}


	/** 
     * Getter for theta
     *
	 * @return theta
	 */

	public double[] getTheta(){

		// your code goes there
		return theta;

	}

	/** 
     * Getter for iteration
     *
	 * @return iteration
	 */

	public int getIteration(){

		// your code goes there
		return iteration;

	}
}
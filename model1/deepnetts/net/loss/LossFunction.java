/**
 *  DeepNetts is pure Java Deep Learning Library with support for Backpropagation
 *  based learning and image recognition.
 *
 *  Copyright (C) 2017  Zoran Sevarac <sevarac@gmail.com>
 *
 * This file is part of DeepNetts.
 *
 * DeepNetts is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.package
 * deepnetts.core;
 */
package deepnetts.net.loss;


import deepnetts.net.NeuralNetwork;
import javax.visrec.ml.data.DataSet;
import deepnetts.data.MLDataItem;

/**
 * Base Interface for all loss functions.
 * Loss function is a component of a deep learning algorithm which calculates an error,
 * as a difference of actual (or predicted) and desired (target) output of a neural network.
 * The total error for some training is usually calculated as a average of errors for all individual input-output pairs.
 * The higher value of loss function, means higher error and lower accuracy of prediction.
 *
 * @see MeanSquaredErrorLoss
 * @see CrossEntropyLoss
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public interface LossFunction {

    /**
     * Calculates pattern error for singe pattern for the specified predicted and target outputs,
     * adds the error to total error, and returns the pattern error.
     *
     * @param predictedOutput predicted/actual network output vector
     * @param targetOutput target network output vector
     * @return error vector error vector for the given predicted and target vectors
     */
    public float[] addPatternError(float[] predictedOutput, float[] targetOutput);

    /**
     * Adds regularization sum to loss function
     * @param regSum regularization sum
     */
    public void addRegularizationSum(float regSum);

    /**
     * Returns the total error calculated by this loss function.
     *
     * @return total error calculated by this loss function
     */
    public float getTotal();

    /**
     * Resets the total error and pattern counter.
     */
    public void reset();

    /**
     * Calculates and returns loss function value for the given neural network and test set.
     *
     * @param nnet
     * @param testSet
     * @return
     */
    default public float valueFor(NeuralNetwork nnet, DataSet<? extends MLDataItem> testSet) {
        for(MLDataItem tsItem : testSet) {
            nnet.setInput(tsItem.getInput());
            float[] output = nnet.getOutput();
            addPatternError(output, tsItem.getTargetOutput().getValues());
        }
        return getTotal();
    }

}

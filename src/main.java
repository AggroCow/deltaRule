import java.util.ArrayList;

public class main {


    public static void main(String args[]){

        ArrayList<Double> listOfOldWeights = new ArrayList<>();
        ArrayList<Double> listOfNewWeights = new ArrayList<>();
        ArrayList<Double> listOfInputValues = new ArrayList<>();

        String calculationAsString = new String();

        //Gather old weights and input values
        do{
            //clear possible wrong entries
            listOfOldWeights.clear();
            listOfInputValues.clear();

            String inputWeightString = IO.readString("Gewichte als double mit Leerzeichen getrennt eingeben (Bsp: \"-1.0 2.5 4.01\"): ");
            String[] inputWeightStringArray = inputWeightString.split(" ");

            for (String singleWeightAsString : inputWeightStringArray) {
                listOfOldWeights.add(Double.parseDouble(singleWeightAsString));
            }


            String inputValueString = IO.readString("Inputwerte als double mit Leerzeichen getrennt eingeben ((Bsp: \"-1.0 1.0 1.0\"): ");
            String[] inputValueArray = inputValueString.split(" ");

            for (String singleValueAsString : inputValueArray) {
                listOfInputValues.add(Double.parseDouble(singleValueAsString));
            }

        }while(listOfInputValues.size() != listOfOldWeights.size());


        double expectedTargetValue = IO.readDouble("Erwarteten Ziel-double-Wert eingeben (Bsp: \"-1.0\"): ");
        double learningRate = IO.readDouble("Learning rate (alpha) als double Wert eingeben (Bsp \"0.5\"): ");

        double actualValue = 0;
        for(int i = 0; i< listOfInputValues.size(); i++){
            actualValue += listOfInputValues.get(i) * listOfOldWeights.get(i);
        }

        if(actualValue == expectedTargetValue){
            System.out.println("Der Erwartete und tatsächliche Zielwert stimmen überein. Die Gewichte müssen nicht mehr verändert werden. Zielwert ist: "+actualValue+"\nGewichte: "+ listOfOldWeights.toString());
        }else{
            //calculating new weights
            for(int i = 0; i< listOfInputValues.size(); i++){
                double tmpValue = listOfOldWeights.get(i) + (learningRate * (expectedTargetValue - actualValue) * listOfInputValues.get(i));
                listOfNewWeights.add(tmpValue);
                calculationAsString += listOfOldWeights.get(i) +" + "+ learningRate + " * ("+ expectedTargetValue +" - "+ actualValue +") * "+ listOfInputValues.get(i) +" = "+ tmpValue+ "\n";
            }

            System.out.println("\n\nNeue Gewichte: "+ listOfNewWeights.toString() +"\nNeue Gewichte als Rechnung: \n"+calculationAsString);
        }

    }

}

import java.util.ArrayList;
public class Obi {




        public static void main(String[] args) {
            StringBuilder theNumbers = theOddNumbers();
                System.out.print(theNumbers);

        }

        public static StringBuilder theOddNumbers(){
            int[] numbers = new int[10];
            for(int i= 0; i < numbers.length; i++){
                numbers[i] = i+1;
            }

            int k = 0;
            ArrayList<Integer> oddNumbers = new ArrayList<Integer>();
            for( int i= 0; i < numbers.length; i++){

                if(numbers[i] % 2 == 0)
                    oddNumbers.add(i);
                ++k;
            }


            StringBuilder oddNumbers2 = new StringBuilder();

            for (int i= 0; i < oddNumbers.size(); i++) {
                oddNumbers2.append(oddNumbers.get(i));

                char num = 'A';
                char num1 = Character.toUpperCase(num);

            }
            return oddNumbers2;





        }
    }



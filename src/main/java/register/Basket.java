package register;

import java.util.ArrayList;

public class Basket {
    /**
     * Task 5: Create a Basket class with list of goods. This class should have:
     *      - createReceipt method to generate a String with name and price of goods in separate rows
     *      - getTotalPrice method to return with the total price of the basket
     * Try these methods in main.
     */
    protected ArrayList<Goods> goodsList = new ArrayList<>();

    public String createReceipt() {
        //String result = "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < goodsList.size(); i++) {
            //result += goodsList.get(i).getName() + " " + goodsList.get(i).getPrice();
            builder.append(goodsList.get(i).getName()).append(" ")
                    .append(goodsList.get(i).getPrice());
            if (i < goodsList.size() - 1) {
                //result += "\r\n";
                builder.append("\r\n");
            }
        }
        return builder.toString();
    }

    public int getTotalPrice() {
        int result = 0;
        for (Goods goods : goodsList) {
            result += goods.getPrice();
        }
        return result;
    }
}

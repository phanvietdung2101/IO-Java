import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProductManager {
    public static HashMap<String, Product> productHashMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        readFromDatabase(); // requirement

        addProduct("id2","ka","vietnam","1000vnd","tutor");
        showProducts();

        writeToDatabase(); // requirement
    }

    public static void writeToDatabase() throws Exception {
        FileOutputStream fileOutput = new FileOutputStream("data/products.dat");
        try {
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
            for(Map.Entry<String,Product> entry : productHashMap.entrySet()){
                output.writeObject(entry.getValue());
            }
            output.flush();
            output.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void readFromDatabase() throws Exception {
        FileInputStream fileInput = new FileInputStream("data/products.dat");
        try {
            ObjectInputStream input = new ObjectInputStream(fileInput);
            Product product;
            while((product = (Product) input.readObject()) != null){
                productHashMap.put(product.getId(),product);
            }
            input.close();
        } catch (EOFException e){
            System.out.println("Load success");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void addProduct(String id,String name,String manufacturer,String price,String description) throws Exception {
        readFromDatabase();
        productHashMap.put(id,new Product(id,name,manufacturer,price,description));
        writeToDatabase();
    }

    public static void showProducts() throws Exception{
        readFromDatabase();
        productHashMap.forEach((k,v) -> {
            System.out.println(v.toString());
        });
    }

    public static void searchProducts(String key) throws Exception {
        readFromDatabase();
        System.out.println(productHashMap.get(key).toString());
    }


}

package lk.ijse.swe.db;

import lk.ijse.swe.model.Book;
import lk.ijse.swe.model.Customer;
import lk.ijse.swe.model.Meal;
import lk.ijse.swe.model.Room;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Room> roomTable
            = new ArrayList<>();
    public static ArrayList<Meal> mealTable
            = new ArrayList<>();
    public static ArrayList<Customer> customerTable
            = new ArrayList<>();
    public static ArrayList<Book> BookingTable
            = new ArrayList<>();

    static {
        customerTable.add(new Customer("C-1","Piumika","11010v","077111033","piumika@gmail.com","Colombo"));
        customerTable.add(new Customer("C-2","Nimal","22010v","078111056","Ni@gmail.com","Panadura"));

        mealTable.add(new Meal("M-1","Rice",15000.00,"Local Meal"));
        mealTable.add(new Meal("M-2","Pizza",15000.00,"French Meal"));
        mealTable.add(new Meal("M-3","Kottu",15000.00,"Local Meal"));

        roomTable.add(new Room("R-1","Single room",10000.00,"Available"));
        roomTable.add(new Room("R-2","Double room",20000.00,"Not Available"));

        BookingTable.add(new Book("C-1","M-1","R-1",2,15000.0));
        BookingTable.add(new Book("C-2","M-2","R-2",1,7500.0));
        BookingTable.add(new Book("C-3","M-3","R-3",3,22500.0));
        BookingTable.add(new Book("C-4","M-4","R-4",1,7500.0));

    }
    }

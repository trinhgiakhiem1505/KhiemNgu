/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Windows 10
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nuocm
 */
public class WorkerDAO {
ArrayList<Worker> contactList = new ArrayList<>();
 Connection connection;
    PreparedStatement statement;
    ResultSet result;

    

    public ArrayList<Worker> getListStudent() {
        contactList = new ArrayList<>();
        try {
            String q = "select * from worker";
            connection = new DBContext().getConnection();
            statement = connection.prepareStatement(q);
            result = statement.executeQuery();
            while (result.next()){
                Worker student = new Worker(result.getString(1),
                                                result.getString(2),
                                                result.getString(3),
                                                result.getString(4) );
                contactList.add(student);
            }
            return contactList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WorkerDAO() {
    }
 public void delete(String id) {
        String q = "delete from worker\n" +
                "where Id=?";
        try {
            connection = new DBContext().getConnection();
            statement = connection.prepareStatement(q);
            statement.setString(1,id);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Worker getWorkerbyId(String id){
        String q= "select * from worker\n" +
                "where id=?";
        try {
            connection = new DBContext().getConnection();
            statement = connection.prepareStatement(q);
            statement.setString(1,id);
            result = statement.executeQuery();
            while (result.next()){
                return new Worker(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4) );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateWorker(String id, String name, String address, String email){
        String q="update worker\n" +
                "set [name]=?,\n" +
                "[address]=?,\n" +
                "email=?\n" +
                "where Id=?";
        try {
            connection = new DBContext().getConnection();
            statement = connection.prepareStatement(q);
            statement.setString(1,name);
            statement.setString(2,address);
            statement.setString(3,email);
            statement.setString(4,id);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createWorker(String name, String address, String email){
        String q="insert into worker\n" +
                "values(?,?,?)";
        try {
            connection = new DBContext().getConnection();
            statement = connection.prepareStatement(q);
            statement.setString(1,name);
            statement.setString(2,address);
            statement.setString(3,email);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
public List<Worker> getListbyPage(List<Worker> list,int start,int end){
    ArrayList<Worker> arr=new ArrayList<>();
    for(int i=start;i<end;i++){
        arr.add(list.get(i));
}
   return arr;
}
public List<Worker> searchByName(String name) {
    WorkerDAO workerDAO = new WorkerDAO();
    List<Worker> workers = workerDAO.getListStudent();
    List<Worker> filteredWorkers = new ArrayList<>();
    for (Worker worker : workers) {
        if (worker.getName().toLowerCase().contains(name.toLowerCase())) {
            filteredWorkers.add(worker);
        }
    }
    return filteredWorkers;
}
}
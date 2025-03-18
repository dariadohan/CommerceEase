package dataaccess;

import connectionpack.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * acesta clasa creeaza un obiect de tip DAO
 * @param <T>
 */
public class DAO<T>{
    private final Class<T> type;

    /**
     * constructorul clasei
     */
    public DAO(){
        this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * metoda care afiseaza informatiile
     * @return
     */
    public List<T> printAll(){
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            connection= ConnectionFactory.getConnection();
            String query="SELECT * FROM " + type.getSimpleName();
            ps=connection.prepareStatement(query);
            rs=ps.executeQuery();
            return prepareRS(rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            ConnectionFactory.close(ps);
            ConnectionFactory.close(rs);
            ConnectionFactory.close(connection);
        }

    }

    /**
     *metoda foloseste reflexia pentru a crea obiecte de tipul T
     * @param rs
     * @return
     */
    private List<T> prepareRS(ResultSet rs){
        if(rs==null)
            return new ArrayList<>();
        List<T> res = new ArrayList<T>();
        Constructor[] constructors=type.getDeclaredConstructors();
        Constructor constructor=null;
        for(Constructor<T> constantin:constructors){
            constructor=constantin;
            if(constructor.getGenericParameterTypes().length==0){
                break;
            }
        }
        try{
            while(rs.next()){
                constructor.setAccessible(true);
                T object = (T)constructor.newInstance();
                for(Field field:type.getDeclaredFields()){
                    String name=field.getName();
                    Object val=rs.getObject(name);
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(name,type);
                    Method method=propertyDescriptor.getWriteMethod();
                    method.invoke(object,val);
                }
                res.add(object);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.close(rs);
        }
        return res;
    }

    /**
     * aceasta metoda scrie intr-un tabel
     * @param table
     * @param list
     * @return
     */
    public boolean writeTable(JTable table, List<?> list){
        if (list.isEmpty())
            return false;
        DefaultTableModel defaultTM= (DefaultTableModel) table.getModel();
        defaultTM.setRowCount(0);
        Class<?> cls=list.get(0).getClass();
        Field[] fields=cls.getDeclaredFields();
        String[] col=new String[fields.length];
        int i=0;
        for(Field field:fields){
            col[i]=field.getName();
            i++;
        }
        defaultTM.setColumnIdentifiers(col);
        for(Object obj:list){
            Object[] row=new Object[fields.length];
            int contor=0;
            for(Field field:fields){
                field.setAccessible(true);
                try{
                    row[contor]=field.get(obj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                contor++;
            }
            defaultTM.addRow(row);
        }
        return true;
    }

    /**
     * aceasta metoda insereaza clienti, produse sau comezi noi
     * @param object
     */
    public void insertF(T object){
        Connection connection=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try{
            connection=ConnectionFactory.getConnection();
            String query;
            query="INSERT INTO " + type.getSimpleName() +"(";
            for(Field field:type.getDeclaredFields()){
                query+= field.getName()+",";
            }
            StringBuffer sb=new StringBuffer(query);
            sb.deleteCharAt(query.length()-1);
            query=new String(sb);
            query+=") VALUES (";
            for(Field field:type.getDeclaredFields()){
                query+= "?,";
            }
            sb=new StringBuffer(query);
            query=new String(sb);
            query=query.substring(0,query.length()-1);
            query+=")";
            st=connection.prepareStatement(query);
            int index=1;
            for(Field field:type.getDeclaredFields()){
                field.setAccessible(true);
                st.setObject(index,field.get(object));
                index++;
            }
            st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * aceasta metoda modifica tabelele deja existente
     * @param object
     * @param id
     */
    public void modifyF(T object,int id){
        Connection connection=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try{
            connection=ConnectionFactory.getConnection();
            String query;
            query = "UPDATE "+type.getSimpleName()+" SET ";
            for(Field field:type.getDeclaredFields()){
                query = query+field.getName()+" =?,";
            }
            StringBuffer sb=new StringBuffer(query);
            query = new String(sb);
            query = query.substring(0,query.length()-1);
            query+=" WHERE id"+"=?";
            st= connection.prepareStatement(query);
            int i=1;
            for(Field field:type.getDeclaredFields()){
                field.setAccessible(true);
                st.setObject(i,field.get(object));
                i++;
            }
            st.setObject(i,id);
            st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(connection);
        }
    }
    /** acesta metoda sterge clienti, produse sau comezi
     *
     */
    public void deleteF(int id){
        Connection connection=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try{
            connection=ConnectionFactory.getConnection();
            String query;
            query="DELETE FROM "+type.getSimpleName();
            query+=" WHERE id"+"=?";
            st=connection.prepareStatement(query);
            st.setObject(1,id);
            st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(connection);
        }
    }

    public int numProducts(int id){
        Connection connection=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        int result;
        try{
            connection=ConnectionFactory.getConnection();
            String str="SELECT * FROM produs WHERE id=?";
            st=connection.prepareStatement(str);
            st.setObject(1,id);
            rs=st.executeQuery();
            if(rs.next())
                result=Integer.parseInt(rs.getString(4));
            else result=0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(connection);
        }
        return result;
    }
    public int UnderS(int id, int nrProdComandate, JPanel panel){
        if(numProducts(id)<nrProdComandate){
            JOptionPane.showMessageDialog(panel, "Nu sunt destule produse","EROARE", JOptionPane.ERROR_MESSAGE);
            return 1;
        }
        return 0;
    }

    public void decrementProducts(int id,int newQuant){
        Connection connection=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try {
            connection = ConnectionFactory.getConnection();
            String str="UPDATE Produs SET nr_buc=? WHERE id=?";
            st=connection.prepareStatement(str);
            st.setObject(1,newQuant);
            st.setObject(2,id);
            st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(connection);
        }
    }
}

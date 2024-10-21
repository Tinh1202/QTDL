/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vntin
 */
public class Specification { // lớp thông số kỹ thuật
    
    private String name_spec; // tên thông số
    private String data;   // số liệu kỹ thuật
    
    
    Specification() {
        this.name_spec = new String("");
        this.data = new String("");
    }
    
    Specification(String name_spec, String data){
        this.name_spec = new String(name_spec);
        this.data = new String(data);
    }
    
    Specification(Object spec){
        if (spec instanceof Specification){
            Specification spec_new = new Specification(spec);
            this.name_spec = new String(spec_new.name_spec);
            this.data = new String(spec_new.data);
        } else {
            Specification spec_new = new Specification();
            this.name_spec = new String(spec_new.name_spec);
            this.data = new String(spec_new.data);
        }
    }
    
    private String getNameSpec(){
        return new String(this.name_spec);
    }
    
    private void setNameSpec(String name_spec){
        this.name_spec = new String(name_spec);
    }
    
    private String getData(){
        return new String(this.data);
    }
    
    private void setData(String data){
        this.data = new String(this.data);
    }
    
    @Override
    public String toString(){
        Specification spec = new Specification(this);
        return "Name specification: " + spec.getNameSpec() + "\n"
                + "Data specification: " + spec.getData() + "\n";
    }
}

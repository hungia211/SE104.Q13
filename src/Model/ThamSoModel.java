/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trung
 */


public class ThamSoModel {

    private String tenThamSo;
    private float giaTri;

    public ThamSoModel() {
    }

    public ThamSoModel(String tenThamSo, float giaTri) {
        this.tenThamSo = tenThamSo;
        this.giaTri = giaTri;
    }

    public String getTenThamSo() {
        return tenThamSo;
    }

    public void setTenThamSo(String tenThamSo) {
        this.tenThamSo = tenThamSo;
    }

    public float getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(float giaTri) {
        this.giaTri = giaTri;
    }

    @Override
    public String toString() {
        return "ThamSoModel{" + 
                "tenThamSo='" + tenThamSo + '\'' +
                ", giaTri=" + giaTri + 
                '}';
    }
}




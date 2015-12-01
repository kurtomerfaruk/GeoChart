/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtomerfaruk.geochart;

/**
 *
 * @author Omer Faruk KURT
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@ManagedBean(name = "mapBean")
@SessionScoped
public class MapBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 89459996582054769L;
    private List<MapClass> mapList;

    @PostConstruct
    public void init() {
        calistir();
    }

    public List<MapClass> getMapList() {
        return mapList;
    }

    public void setMapList(List<MapClass> mapList) {
        this.mapList = mapList;
    }

    public void calistir() {
        try {

            Document doc = Jsoup.connect("http://www.nufusu.com").get();
            Element table = doc.select("table").get(2); //select the first table.
            Elements rows = table.select("tr");
            mapList = new ArrayList<>();
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                String sehir="";
                
                /**
                 * baş harfi İ olanları I olarak degistir.
                 * Bunun sebebi ise GeoChart Istanbul ve Izmir olarak tanimaktadir.
                 */
                if(cols.get(1).text().startsWith("İ")){
                    cols.get(1).text(cols.get(1).text().replace("İ", "I"));
                }
                
                /**
                 * Mersin'i Icel olarak degistir.
                 * Bunun sebebi ise GeoChart Mersin'i tanimiyor.
                 */
                if(cols.get(1).text().equals("Mersin")){
                    cols.get(1).text("Içel");
                }
                
                /**
                 * Afyon ile baslayani Afyon olarak degistir.
                 * http://www.nufusu.com adresinde Afyonkarahisar olarak gelen veriyi GeoChart tanimiyor.
                 */
                if(cols.get(1).text().startsWith("Afyon")){
                    cols.get(1).text("Afyon");
                }
                
                /**
                 * Replace yapmamizin amaci noktali sayi degerlerini duz hale cevirmek.
                 * Bu da yine GeoChart'in istedigi sekil
                 */
                mapList.add(new MapClass(cols.get(1).text(), cols.get(4).text().replace(".", "")));

            }
            
            setMapList(mapList);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

package co.cstad;

import java.time.LocalDate;
import java.util.Objects;
public class StockManagement {
    private Integer id;
    private String name;
    private Integer unitPrice;
    private Integer Qty;

    public Integer getId() {
        return id;
    }

    public Integer setId(Integer id) {
        this.id = id;
        return id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Integer setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
        return unitPrice;
    }

    public Integer getQty() {
        return Qty;
    }

    public Integer setQty(Integer qty) {
        Qty = qty;
        return qty;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private LocalDate date;

    public StockManagement() {
    }

    public StockManagement(Integer id, String name, Integer unitPrice, Integer qty, LocalDate date) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.Qty = qty;
        this.date = date;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockManagement that = (StockManagement) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(Qty, that.Qty) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitPrice, Qty, date);
    }

    @Override
    public String toString() {
        return "ItemManagement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", Qty=" + Qty +
                ", date=" + date +
                '}';
    }
}

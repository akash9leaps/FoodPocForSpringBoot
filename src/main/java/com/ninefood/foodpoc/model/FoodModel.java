package com.ninefood.foodpoc.model;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.google.common.base.Objects;



@Table(keyspace = "ninefood", name = "foodnineleaps")
public class FoodModel {

    @PartitionKey
    @Column(name = "item_id")
    private String itemId;

    @ClusteringColumn
    @Column(name = "item_price")
    private Integer itemPrice;

    private String name;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass())
            return false;

        FoodModel that = (FoodModel) other;
        return Objects.equal(itemId, that.itemId) && Objects.equal(itemPrice, that.itemPrice)
                && Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(itemId, itemPrice, name);
    }
}

package com.bh.store.entity;

public class Favourite extends BaseEntity {
    private Integer uid;
    private Integer id;
    private Integer productId;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favourite)) return false;
        if (!super.equals(o)) return false;

        Favourite favourite = (Favourite) o;

        if (getUid() != null ? !getUid().equals(favourite.getUid()) : favourite.getUid() != null) return false;
        if (getId() != null ? !getId().equals(favourite.getId()) : favourite.getId() != null) return false;
        if (getProductId() != null ? !getProductId().equals(favourite.getProductId()) : favourite.getProductId() != null)
            return false;
        if (getCategoryId() != null ? !getCategoryId().equals(favourite.getCategoryId()) : favourite.getCategoryId() != null)
            return false;
        if (getItemType() != null ? !getItemType().equals(favourite.getItemType()) : favourite.getItemType() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(favourite.getTitle()) : favourite.getTitle() != null) return false;
        if (getSellPoint() != null ? !getSellPoint().equals(favourite.getSellPoint()) : favourite.getSellPoint() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(favourite.getPrice()) : favourite.getPrice() != null) return false;
        if (getNum() != null ? !getNum().equals(favourite.getNum()) : favourite.getNum() != null) return false;
        if (getImage() != null ? !getImage().equals(favourite.getImage()) : favourite.getImage() != null) return false;
        if (getStatus() != null ? !getStatus().equals(favourite.getStatus()) : favourite.getStatus() != null)
            return false;
        return getPriority() != null ? getPriority().equals(favourite.getPriority()) : favourite.getPriority() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
        result = 31 * result + (getCategoryId() != null ? getCategoryId().hashCode() : 0);
        result = 31 * result + (getItemType() != null ? getItemType().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getSellPoint() != null ? getSellPoint().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getPriority() != null ? getPriority().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "uid=" + uid +
                ", id=" + id +
                ", productId=" + productId +
                ", categoryId=" + categoryId +
                ", itemType='" + itemType + '\'' +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
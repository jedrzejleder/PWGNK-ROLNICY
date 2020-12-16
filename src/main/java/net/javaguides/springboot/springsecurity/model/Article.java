package net.javaguides.springboot.springsecurity.model;


import javax.persistence.*;

@Entity
@Table(name="Article")
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "articleId")
    private Long articleId;

    private String name;
    private String placeOfTheObject;
    private String description;
    private String price;
    private String available;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY )
    @JoinColumn(name = "user_owner")
    private User user_owner;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "article",
            optional = false)
    private Transaction transaction;




    public Article(){
    }

    public Article(Long articleId, String name, String placeOfTheObject, String description, String price, String available) {
        this.articleId = articleId;
        this.name = name;
        this.placeOfTheObject = placeOfTheObject;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long id) {
        this.articleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfTheObject() {
        return placeOfTheObject;
    }

    public void setPlaceOfTheObject(String placeOfTheObject) {
        this.placeOfTheObject = placeOfTheObject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public User getUser_owner() {
        return user_owner;
    }

    public void setUser_owner(User user_owner) {
        this.user_owner = user_owner;
    }

    @Override
    public String toString(){
        return "Article [id= "+ articleId + " name= " + name + " placeOfTheObject= " + placeOfTheObject + " description= " + description + " price= " + price + " available= " + available +" ]";
    }
}

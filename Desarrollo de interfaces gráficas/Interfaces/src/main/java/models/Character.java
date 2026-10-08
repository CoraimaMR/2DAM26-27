package models;

public class Character {

    protected String name, race;
    protected Integer age;
    protected boolean shoes;
    protected Enum<Genre>genre;

    public Character(Enum<Genre> genre, boolean shoes, Integer age, String race, String name) {
        this.genre = genre;
        this.shoes = shoes;
        this.age = age;
        this.race = race;
        this.name = name;
    }
    
}

package uk.ac.ncl.botnetwork.domain;

import javax.persistence.*;

/**
 *
 * Database entity to represent a string to
 * be posted on Twitter by the bots within
 * the network.
 *
 * @author Jonathan Carlton
 */
@Entity
@Table(name = "generated_tweet", schema = "bot_network")
public class GeneratedTweet
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private Long classificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "config_Id")
    private Config config;

    public GeneratedTweet() { }

    public GeneratedTweet(String text) {
        this.text = text;
    }

    public GeneratedTweet(String text, Long classificationId) {
        this.text = text;
        this.classificationId = classificationId;
    }

    public GeneratedTweet(String text, Long classificationId, User user) {
        this.text = text;
        this.classificationId = classificationId;
        this.user = user;
    }

    public GeneratedTweet(String text, Long classificationId, User user, Config config) {
        this.text = text;
        this.classificationId = classificationId;
        this.user = user;
        this.config = config;
    }

    @Override
    public String toString() {
        return "GeneratedTweet [" +
                "text='" + text + '\'' +
                ", classificationId=" + classificationId +
                ", user=" + user +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneratedTweet tweet = (GeneratedTweet) o;

        return text.equals(tweet.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}

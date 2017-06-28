package uk.ac.ncl.botnetwork.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

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
@Table(name = "tweet", schema = "bot_network")
public class Tweet 
{
    @Id
    private String text;
    private Long classificationId;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public Tweet() { }

    public Tweet(String text) {
        this.text = text;
    }

    public Tweet(String text, Long classificationId, User user) {
        this.text = text;
        this.classificationId = classificationId;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tweet [" +
                "text='" + text + '\'' +
                ", classificationId=" + classificationId +
                ", user=" + user +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

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
}

package uk.ac.ncl.botnetwork.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Tweet() { }

    public Tweet(String text) {
        this.text = text;
    }

    public Tweet(String text, Long classificationId) {
        this.text = text;
        this.classificationId = classificationId;
    }

    @Override
    public String toString() {
        return "Tweet [" +
                "text='" + text + '\'' +
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
}

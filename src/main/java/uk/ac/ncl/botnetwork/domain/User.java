package uk.ac.ncl.botnetwork.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Database entity to represent a user within
 * the network.
 *
 * @author Jonathan Carlton
 */
@Entity
@Table(name = "user", schema = "bot_network")
public class User 
{
    @Id private Long twitterId;
    private String screenName;

    public User() { }

    public User(Long twitterId, String screenName) {
        this.twitterId = twitterId;
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "User [" +
                "twitterId=" + twitterId +
                ", screenName='" + screenName + '\'' +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!twitterId.equals(user.twitterId)) return false;
        return screenName.equals(user.screenName);
    }

    @Override
    public int hashCode() {
        int result = twitterId.hashCode();
        result = 31 * result + screenName.hashCode();
        return result;
    }

    public Long getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(Long twitterId) {
        this.twitterId = twitterId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}

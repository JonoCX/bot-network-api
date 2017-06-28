package uk.ac.ncl.botnetwork.domain;

import javax.persistence.*;

/**
 * Store user configuration to
 * generate network
 *
 * @autor Callum McClean
 */
@Entity
@Table(name = "config", schema = "bot_network")
public class Config
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long configId;

    private int numUsers;
    private int maxTweets;
    private int minTweets;
    private int maxFollowers;
    private int minFollowers;

    public Config() { }

    public Config(int numUsers, int maxTweets, int minTweets, int maxFollowers, int minFollowers) {
        this.numUsers = numUsers;
        this.maxTweets = maxTweets;
        this.minTweets = minTweets;
        this.maxFollowers = maxFollowers;
        this.minFollowers = minFollowers;
    }

    @Override
    public String toString() {
        return "Config [" +
                "configId=" + configId +
                ", numUsers=" + numUsers +
                ", maxTweets=" + maxTweets +
                ", minTweets=" + minTweets +
                ", maxFollowers=" + maxFollowers +
                ", minFollowers=" + minFollowers +
                '"' +
                ']';
    }


    @Override
    public int hashCode() {
        int result = configId.hashCode();
        result = 31 * result + configId.hashCode();
        return result;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public int getMaxTweets() {
        return maxTweets;
    }

    public void setMaxTweets(int maxTweets) {
        this.maxTweets = maxTweets;
    }

    public int getMinTweets() {
        return minTweets;
    }

    public void setMinTweets(int minTweets) {
        this.minTweets = minTweets;
    }

    public int getMaxFollowers() {
        return maxFollowers;
    }

    public void setMaxFollowers(int maxFollowers) {
        this.maxFollowers = maxFollowers;
    }

    public int getMinFollowers() {
        return minFollowers;
    }

    public void setMinFollowers(int minFollowers) {
        this.minFollowers = minFollowers;
    }
}

package uk.ac.ncl.botnetwork.domain;

import javax.persistence.*;

/**
 *
 * Database entity for the connection between
 * two users.
 *
 * @author Jonathan Carlton
 */
@Entity
@Table(name = "connection", schema = "bot_network")
public class Connection 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id")
    private User origin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private User destination;

    @ManyToOne
    @JoinColumn(name = "config_Id")
    private Config config;

    public Connection() { }

    public Connection(User origin, User destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Connection(User origin, User destination, Config config) {
        this.origin = origin;
        this.destination = destination;
        this.config = config;
    }

    @Override
    public String toString() {
        return "Connection [" +
                "id=" + id +
                ", origin=" + origin +
                ", destination=" + destination +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Connection that = (Connection) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }
}

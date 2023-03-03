package org.hibernate.bugs;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class WithEmbedded {
    @Id
    private long id;

    @Embedded
    private AnEmbeddable e;

    public WithEmbedded(long id, AnEmbeddable e) {
        this.id = id;
        this.e = e;
    }

    public WithEmbedded() {
    }

    public long getId() {
        return id;
    }

    public AnEmbeddable getE() {
        return e;
    }
}

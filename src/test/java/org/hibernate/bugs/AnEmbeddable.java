package org.hibernate.bugs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AnEmbeddable {
    @Column(nullable = false)
    private String foo;
    @Column(nullable = false)
    private String bar;

    public AnEmbeddable(String foo, String bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public AnEmbeddable() {
    }

    public String getFoo() {
        return foo;
    }

    public String getBar() {
        return bar;
    }
}

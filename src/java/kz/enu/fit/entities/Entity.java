
package kz.enu.fit.entities;

import java.io.Serializable;


public abstract class Entity implements Serializable {
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * is intended to be overridden by subclasses to the implementation of 
     * agreements on common content comparison of two objects
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entity other = (Entity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * Returns the hash code of the object
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    /**
     * returns the representation of the object as a string
     * @return 
     */
    @Override
    public String toString() {
        return "Entity{" + "id=" + id + '}';
    }

    
}

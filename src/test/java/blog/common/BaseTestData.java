package blog.common;

import blog.common.Dependency;
import blog.common.ObjectUnderTest;
import blog.common.TestData;
import org.junit.Test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import java.lang.reflect.ParameterizedType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public abstract class BaseTestData<E extends BaseEntity> {
    private final Class<E> entityClass;

    @PersistenceContext
    private EntityManager em;

    private int id;

    @SuppressWarnings("unchecked")
    protected BaseTestData() {
        entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PostConstruct
    private void beginTransaction() {
        EntityTransaction transaction = em.getTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    @PreDestroy
    private void endTransaction() {
        EntityTransaction transaction = em.getTransaction();

        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    protected final int getId() {
        return id++;
    }

    public final void save(BaseEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        }

        em.flush();
    }

    public final E find(Long entityId) {
        return em.find(entityClass, entityId);
    }

    public final void detachFromPersistenceContext(BaseEntity entity) {
        em.detach(entity);
    }

    public final E assertSavedToDB(E entity) {
        if (em.contains(entity)) {
            em.detach(entity);
        }

        E fromDb = em.find(entityClass, entity.getId());

        assertEquals(entity.getId(), fromDb.getId());
        assertNotSame(entity, fromDb);
        return fromDb;
    }

    public final void assertDeletedFromDB(BaseEntity entity) {
        BaseEntity deletedEntity = find(entity.getId());
        assertNull(deletedEntity);
    }

    public final E buildAndSave() {
        E entity = build();
        save(entity);
        return entity;
    }

    protected abstract E build();
}

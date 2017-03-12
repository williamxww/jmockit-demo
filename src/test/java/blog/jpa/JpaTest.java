package blog.jpa;

import blog.user.User;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author vv
 * @since 2017/3/12.
 */
public class JpaTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void createTable() {
        // 可以验证生成表是否正确
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
        factory.close();
    }

    @Test
    public void save() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User user = new User();
        user.setFirstName("Test");
        user.setSurname("User");
        user.setUsername("tester");
        user.setPassword("1234");
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    /**
     * new 、托管、脱管、删除
     */
    @Test
    public void update() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User person = em.find(User.class, 1);
        person.setSurname("hmk"); // person为托管状态
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test
    public void update2() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User person = em.find(User.class, 1);
        em.clear(); // 把实体管理器中的所有实体变为脱管状态
        person.setSurname("hmk2");
        em.merge(person); // 把脱管状态变为托管状态,merge可以自动选择insert or update 数据
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test
    public void remove() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User person = em.find(User.class, 1);
        em.remove(person); // 删除实体
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test
    public void find() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
        EntityManager em = factory.createEntityManager();
        User person = em.find(User.class, 2); // 类似于hibernate的get方法,没找到数据时，返回null
        System.out.println(person.getUsername());
        em.close();
        factory.close();
    }

    @Test
    public void find2() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
        EntityManager em = factory.createEntityManager();
        User person = em.getReference(User.class, 2); // 类似于hibernate的load方法,延迟加载.没相应数据时会出现异常
        System.out.println(person.getUsername()); // 真正调用时才查找数据
        em.close();
        factory.close();
    }
}

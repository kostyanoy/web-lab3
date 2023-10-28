import com.itmo.weblab3.beans.PointBean;
import hibernate.HibernateUtils;
import org.hibernate.Session;

public class TestMain {
    public static void main(String[] args) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

//            PointBean checkBean = PointBean.builder()
//                    .x(1)
//                    .y(2)
//                    .build();
//            session.persist(checkBean);
//
//            session.getTransaction().commit();

            var p = new PointBean();
            var s = p.getSessionId();
            System.out.println(s);
            System.out.println("Good job!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

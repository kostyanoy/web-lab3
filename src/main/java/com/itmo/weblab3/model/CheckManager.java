package com.itmo.weblab3.model;

import com.itmo.weblab3.beans.PointBean;
import com.itmo.weblab3.hibernate.CheckEntity;
import com.itmo.weblab3.hibernate.HibernateUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.RollbackException;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Named("checkManager")
@ApplicationScoped
class CheckManager implements CheckManagerInterface {

    private static final List<CheckEntity> stub = new ArrayList<>() {{
        add(CheckEntity.builder().x(1).build());
        add(CheckEntity.builder().y(1).build());
    }};

    @Override
    public boolean savePoint(PointBean point) {
        if (!HibernateUtils.isActive()) {
            return false;
        }

        var startTime = System.currentTimeMillis();

        // get parameters from point
        var sessionId = point.getSessionId();
        var x = point.getX();
        var y = point.getY();
        var r = point.getR();
        var isInside = CheckUtils.makeFullCheck(x, y, r); // check point
        var checkDate = new Date();
        var executionTime = System.currentTimeMillis() - startTime;

        // create entity
        var entity = CheckEntity.builder().
                sessionId(sessionId).
                x(x).
                y(y).
                r(r).
                result(isInside).
                checkDate(checkDate).
                executionTime(executionTime).
                build();

        // try to save to database
        try (var session = HibernateUtils.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePoints(String sessionId) {
        if (!HibernateUtils.isActive()) {
            return false;
        }

        try (var session = HibernateUtils.getSessionFactory().openSession()) {

            // start transaction
            var transaction = session.beginTransaction();

            //update entities
            var entities = getAllPoints(sessionId);
            for (var entity : entities) {
                entity.setDeleted(true);
                session.merge(entity);
            }

            // close transaction
            transaction.commit();

            return true;
        } catch (HibernateException | IllegalStateException | RollbackException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<CheckEntity> getAllPoints(String sessionId) {
        if (!HibernateUtils.isActive()) {
            return stub;
        }

        // try to get all points
        try (var session = HibernateUtils.getSessionFactory().openSession()) {
            // create criteria
            var criteriaBuilder = session.getCriteriaBuilder();

            // from
            var query = criteriaBuilder.createQuery(CheckEntity.class);
            var root = query.from(CheckEntity.class);

            // where
            var condition = criteriaBuilder.and(criteriaBuilder.equal(root.get("isDeleted"), false), criteriaBuilder.equal(root.get("sessionId"), sessionId));

            // connect condition
            query.select(root).where(condition);

            // select list
            var resultList = session.createQuery(query).getResultList();

            return resultList;
        } catch (HibernateException | IllegalAccessError e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}

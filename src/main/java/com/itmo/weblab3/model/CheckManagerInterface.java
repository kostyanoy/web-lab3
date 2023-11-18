package com.itmo.weblab3.model;

import com.itmo.weblab3.beans.PointBean;
import com.itmo.weblab3.hibernate.CheckEntity;

import java.util.List;

// manage point parameters
public interface CheckManagerInterface {
    // save attempt to database with given parameters
    boolean savePoint(PointBean point);

    // delete all points with given session id
    boolean deletePoints(String sessionId);

    //get all points with gives session id
    List<CheckEntity> getPoints(String sessionId, int offset, int limit);
}

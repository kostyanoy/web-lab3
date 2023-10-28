package model;

import com.itmo.weblab3.beans.PointBean;
import hibernate.CheckEntity;

import java.util.List;

// manage point parameters
public interface CheckManagerInterface {
    // save attempt to database with given parameters
    boolean savePoint(PointBean point);

    // delete all points with given session id
    boolean deletePoints(String sessionId);

    //get all points with gives session id
    List<CheckEntity> getAllPoints(String sessionId);
}

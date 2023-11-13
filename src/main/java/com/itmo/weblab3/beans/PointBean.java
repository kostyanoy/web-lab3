package com.itmo.weblab3.beans;

import com.itmo.weblab3.hibernate.CheckEntity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.itmo.weblab3.model.CheckManagerInterface;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// bean for user input
@Data
@AllArgsConstructor
@Builder
@Named("pointBean")
@SessionScoped
public class PointBean implements Serializable {

    public PointBean() {
        sessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
    }

    @Inject
    private CheckManagerInterface checkManager;
    private String sessionId;
    private List<CheckEntity> currentPoints = new ArrayList<>();

    private double x;
    private double y;
    private double r;
//    private boolean result;

    // save point to database
    public boolean savePoint() {
        return checkManager.savePoint(this);
    }

    // delete points in this session
    public boolean deletePoints() {
        return checkManager.deletePoints(sessionId);
    }

    // cache points
    public boolean updatePoints() {
        currentPoints = checkManager.getAllPoints(sessionId);
        return true;
    }

    // return cached points
    public List<CheckEntity> receivePoints() {
        return currentPoints;
    }

    // generate json for ajax
    public String receiveJsonPoints() {
        var json = new StringBuilder("[");
        var points = receivePoints();
        var l = points.size();
        for (var i = 0; i < points.size(); i++){
            json.append(points.get(i).toJson());
            if (i != l - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }

    // get point from ajax
    public void sendJsPoint() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            var x = Double.parseDouble(params.get("x"));
            var y = Double.parseDouble(params.get("y"));
            var r = Double.parseDouble(params.get("r"));
            this.x = x;
            this.y = y;
            this.r = r;
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        savePoint();
    }
}

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

    public boolean savePoint() {
        return checkManager.savePoint(this);
    }

    public boolean deletePoints() {
        return checkManager.deletePoints(sessionId);
    }

    public boolean updatePoints() {
        System.out.println("HELLO FROM UPDATE POINTS@!!!!!!!!!!!!!!!!!!!!!");
        currentPoints = checkManager.getAllPoints(sessionId);
        return true;
    }

    public List<CheckEntity> receivePoints() {
        return currentPoints;
    }

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

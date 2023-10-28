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
import java.util.List;

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

    private double x;
    private double y;
    private double r;
    private boolean result;

    public boolean savePoint() {
        return checkManager.savePoint(this);
    }

    public boolean deletePoints() {
        return checkManager.deletePoints(sessionId);
    }

    public List<CheckEntity> receivePoints() {
        return checkManager.getAllPoints(sessionId);
    }
}

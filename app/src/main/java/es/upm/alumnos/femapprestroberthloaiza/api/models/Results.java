package es.upm.alumnos.femapprestroberthloaiza.api.models;

import java.util.ArrayList;
import java.util.List;

public class Results {

    private Integer status;

    private List<Result> result = new ArrayList<Result>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        String licorsToString = "";

        for (Result licors : result)
            licorsToString += licors.toString();

        return "Results{" +
                "status=" + status +
                ", result=" + licorsToString +
                '}';
    }
}

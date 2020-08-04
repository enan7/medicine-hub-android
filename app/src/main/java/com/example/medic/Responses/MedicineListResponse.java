package com.example.medic.Responses;

import java.util.List;

public class MedicineListResponse {

    private String responseCode;
    private String responseMessage;
    private List<MedicineResponse> medicines;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<MedicineResponse> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineResponse> medicines) {
        this.medicines = medicines;
    }


}

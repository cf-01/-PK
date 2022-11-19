package com.cf.ssm.pojo;

public class Manager {

    private Integer managerId;

    private String managerAccount;

    private String managerPassword;

    private Integer managerPower;

    public Manager() {
    }

    public Manager(Integer managerId, String managerAccount, String managerPassword, Integer managerPower) {
        this.managerId = managerId;
        this.managerAccount = managerAccount;
        this.managerPassword = managerPassword;
        this.managerPower = managerPower;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerAccount() {
        return managerAccount;
    }

    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public Integer getManagerPower() {
        return managerPower;
    }

    public void setManagerPower(Integer managerPower) {
        this.managerPower = managerPower;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", managerAccount='" + managerAccount + '\'' +
                ", managerPassword='" + managerPassword + '\'' +
                ", managerPower=" + managerPower +
                '}';
    }
}

package com.nickless.blog.model;

/**
 * creat by nickless
 *
 * @Date 2020/1/2 13:13
 */
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreat;
    private Long gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaken() {
        return token;
    }

    public void setTaken(String taken) {
        this.token = taken;
    }

    public Long getGmtCreat() {
        return gmtCreat;
    }

    public void setGmtCreat(Long gmtCreat) {
        this.gmtCreat = gmtCreat;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}

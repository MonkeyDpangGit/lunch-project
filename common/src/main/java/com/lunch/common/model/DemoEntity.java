package com.lunch.common.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DemoEntity
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: DemoEntity
 */
@Document
public class DemoEntity extends BaseModel {

    @Indexed(unique = true)
    private String name;

    private String desc;

    private String[] org;

    @Indexed(expireAfterSeconds = 60)
    private Date expiredDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String[] getOrg() {
        return org;
    }

    public void setOrg(String[] org) {
        this.org = org;
    }
}

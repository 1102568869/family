package tech.washmore.family.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Bill {

    private Integer id;
    @NotBlank(message = "账单事项不能为空")
    private String name;
    @NotNull(message = "需要选择事项类型")
    private Integer type;
    @NotNull(message = "金额不得小于0元")
    private Double money;
    @NotNull(message = "需要选择经手人")
    private Integer member;
    @NotNull(message = "需要选择经手时间")
    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private Date recordtime;
    private String comment;
    @NotNull(message = "需要选择收支类型")
    private Integer balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }


    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}

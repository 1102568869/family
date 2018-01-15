package tech.washmore.family.model.view;

import tech.washmore.family.common.enums.BalanceType;
import tech.washmore.family.model.Bill;

public class BillView extends Bill {

    private String memberName;
    private String typeName;

    public String getBalanceName() {
        return BalanceType.of(getBalance()).getName();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

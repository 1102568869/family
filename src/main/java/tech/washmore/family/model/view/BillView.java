package tech.washmore.family.model.view;

import org.apache.commons.collections4.CollectionUtils;
import tech.washmore.family.common.enums.BalanceType;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.Billtag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BillView extends Bill {

    private String memberName;
    private String typeName;
    private List<Billtag> tags = new ArrayList<>();
    private List tagIds = new ArrayList<>();

    public List getTagIds() {
        return tagIds;
    }

    public void setTagIds(List tagIds) {
        this.tagIds = tagIds;
    }

    public List<Billtag> getTags() {
        return tags;
    }

    public void setTags(List<Billtag> tags) {
        this.tags = tags;
        if (CollectionUtils.isNotEmpty(tags)) {
            this.tagIds = tags.stream().map(Billtag::getId).collect(Collectors.toList());
        }
    }

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

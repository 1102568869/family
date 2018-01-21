package tech.washmore.family.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.washmore.family.model.Billtag;
import tech.washmore.family.model.Billtype;
import tech.washmore.family.service.BilltagService;
import tech.washmore.family.service.BilltypeService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/billtag/")
public class BilltagController {
    @Autowired
    private BilltagService billtagService;

    @GetMapping("/get/top10")
    public List<Billtag> getTop10() {
        List<Billtag> billtags = billtagService.getAllBilltags();
        if (CollectionUtils.isEmpty(billtags)) {
            return new ArrayList<>();
        }
        return billtags.stream().sorted(Comparator.comparing(Billtag::getId).reversed()).filter(distinctByKey(tag -> tag.getId())).collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}

package org.shuai.core;

import org.shuai.entity.User;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yangs
 */
public class Simple {

    /**
     * 提取列表中的属性聚合为一个列表或集合
     */
    public void toList(List<User> users) {
        Stream<User> stream = users.stream();
        // 以列表形式返回
        List<String> chineseNames = stream.map(User::getChineseName).collect(Collectors.toList());
        // 以集合形式返回
        Set<String> englishNames = users.stream().map(User::getEnglishName).collect(Collectors.toSet());
    }

    /**
     * 将双层列表的值聚合为一个大列表
     */
    public void flatMapWithList(List<List<String>> names) {
        Stream<List<String>> stream = names.stream();
        // 提取所有名字, 使用列表返回
        List<String> name = stream.flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * 将列表转成Map
     */
    public void toMap(List<User> users) {
        // 如果Key重复会报错
        Map<String, String> nameMap = users.stream().collect(Collectors.toMap(User::getChineseName, User::getEnglishName));
        // 如果Key重复, 取前一个
        Map<String, String> repeatKeyBefore = users.stream().collect(Collectors.toMap(User::getChineseName, User::getEnglishName, (k1, k2) -> k1));
        // 如果Key重复, 取后一个
        Map<String, String> repeatKeyAfter = users.stream().collect(Collectors.toMap(User::getChineseName, User::getEnglishName, (k1, k2) -> k2));
    }

    /**
     * 列表根据某个属性进行分组, 返回分组Map
     * */
    public void groupMap(List<User> users) {
        // 分组后提取对象列表
        Map<Integer, List<User>> groupByAge = users.stream().collect(Collectors.groupingBy(User::getAge));
    }

    /**
     * 截取列表
     */
    public void d(List<User> users) {
        // 跳过第1个元素后, 向后取3个元素
        List<User> intervalUsers = users.stream().skip(1).limit(3).collect(Collectors.toList());
    }

    /**
     * 过滤列表
     */
    public void filter(List<Integer> nums) {
        List<Integer> filteredUsers = nums.stream().filter(e -> e > 10).collect(Collectors.toList());
    }

    /**
     * 统计数量
     */
    public void count(List<Integer> nums) {
        long count = nums.stream().filter(e -> e > 10).count();
    }

    /**
     * BigDecimal累加计算
     */
    public void reduceBigDecimal(List<BigDecimal> moneys) {
        BigDecimal sum = moneys.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Integer累加计算
     */
    public void reduceInteger(List<Integer> moneys) {
        int count = moneys.stream().reduce(Integer::sum).orElse(0);
    }

    /**
     * Double累加计算
     */
    public void reduceDouble(List<Double> moneys) {
        double sumMoney = moneys.stream().reduce(Double::sum).orElse(0D);
    }
}

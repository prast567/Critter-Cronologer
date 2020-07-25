package com.udacity.jdnd.course3.critter.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCustomRespository {
    @Autowired
    EntityManager em;

    public List<BigInteger> getEmployeeIdsBySkillAND(String where) {
        String s = "select employee_id from employee_skill_model WHERE employee_skill in (1,2) group by employee_id having count(DISTINCT employee_skill) = 2 " + where;
        System.out.println(s);

        List<BigInteger> test = em.createNativeQuery(s).getResultList();

        return test;
    }
}
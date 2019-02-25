package com.example.slyangsecurity;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilsTest {

    class Person{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Person(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public Person() {
        }


    }

    class Person2{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Person2(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public Person2() {
        }


    }


    @Test
    public void testCopy(){

        Person person = new Person("i am slyang","hello");

        Person person1 = new Person();
        BeanUtils.copyProperties(person,person1);

        Map<String,Object> person2 = new HashMap<>();
        BeanUtils.copyProperties(person,person2);

        Person2 person3 = new Person2();
        BeanUtils.copyProperties(person,person3);

        System.out.println(JSON.toJSONString(person1)); //正常  Person >  Person
        System.out.println(JSON.toJSONString(person2)); //空    Person >  Map
        System.out.println(JSON.toJSONString(person3)); //正常   Person >  Person2



    }


    @Test
    public void testCopy2(){

        Map<String,Object> person = new HashMap<>();

        person.put("name","i am slyang");
        person.put("age","hello");

        Map<String,Object> person1 = new HashMap<>();
        BeanUtils.copyProperties(person,person1);

        Person person2 = new Person();
        BeanUtils.copyProperties(person,person2);

        Person person3 = new Person();
        BeanUtils.copyProperties(person,person3,Person.class);

        System.out.println(JSON.toJSONString(person1)); //空  Map >  Map
        System.out.println(JSON.toJSONString(person2)); //空  Map >  Person
        System.out.println(JSON.toJSONString(person3)); //空  Map >  Person


//        BeanUtils beanUtils;
    }

    @Test
    public void apacheCopy() throws InvocationTargetException, IllegalAccessException {
        Person person = new Person("i am slyang","hello");
        Map<String,Object> person1 = new HashMap<>();
        //
        org.apache.commons.beanutils.BeanUtils.copyProperties(person,person1);

    }







}

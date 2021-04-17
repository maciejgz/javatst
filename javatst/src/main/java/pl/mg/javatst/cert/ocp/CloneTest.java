package pl.mg.javatst.cert.ocp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

public class CloneTest {

    public static void main(String[] args) {
        CloneTest cloneTest = new CloneTest();
        cloneTest.tests();
    }

    public void tests() {
        SiblingClass child = new SiblingClass("warsaw", 145);
        SuperClass parent = new SuperClass("mark", 30, 187, child);


        try {
            SuperClass secondParent = parent.clone();
            System.out.println(parent);
            secondParent.getSibling().setAddress("kreakow");
            System.out.println(parent);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    private class SuperClass implements Cloneable {
        private String name;
        private int age;
        private Integer height;
        private SiblingClass sibling;

        public SuperClass(SuperClass from) {
            this.name = from.getName();
            this.age = from.getAge();
            this.height = from.getHeight();
            this.sibling = new SiblingClass(from.getSibling());
        }

        @Override
        protected SuperClass clone() throws CloneNotSupportedException {
            return new SuperClass(this);
        }
    }

    @Data
    @AllArgsConstructor
    private class SiblingClass implements Cloneable {
        private String address;
        private int number;

        public SiblingClass(SiblingClass from) {
            this.address = from.getAddress();
            this.number = from.getNumber();
        }

        @Override
        protected SiblingClass clone() throws CloneNotSupportedException {
            return new SiblingClass(this);
        }
    }

}

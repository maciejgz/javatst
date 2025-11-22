package pl.mg.javatst.pattern.filter;

import java.util.List;

public interface Criteria {

    public List<Person> meetCriteria(List<Person> persons);
}

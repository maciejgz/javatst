package pl.mg.javatst.pattern.filter;

import java.util.List;

public class AndCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaResult = this.criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaResult);
    }
}

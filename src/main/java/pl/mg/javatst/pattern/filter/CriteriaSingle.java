package pl.mg.javatst.pattern.filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<>();

        for (Person person : persons) {
            if (person.getMartialStatus().equalsIgnoreCase("SINGLE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
